package services;

import connect.ConnectMySQL;
import constants.DBConstants;
import constants.StrConstants;
import dao.DAO;
import entities.staging.DateDim;
import entities.staging.ProvinceDim;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ActionService {
    ConnectMySQL connectStagingDB = new ConnectMySQL(DBConstants.STAGING);
    java.sql.Connection connectionStagingDB = connectStagingDB.getConnection();
    DAO dao = new DAO(connectionStagingDB);

    public String crawl(String sourceName, String sourcePath) throws IOException {
        boolean success = false;
        DateService dateService = new DateService();
        FileService fileService = new FileService();
        String fileName = sourceName.concat("_" + dateService.getFileName());

        Connection con = Jsoup.connect(sourcePath).timeout(3000);
        Document doc = con.get();

        Elements elements = doc.select(".mega-submenu li a[href]");
        for (Element e : elements) {
            String newURL = sourcePath + e.attr("href") + "/7-ngay-toi";
            con = Jsoup.connect(newURL);
            doc = con.get();

            String province = doc.select(".location-name-main").text();
            elements = doc.select("details.weather-day");

            ProvinceDim provinceDim = dao.getProvince(province);
            for (Element element : elements) {
                String forecastDate = element.select(".summary-day span").text().trim();
                if (forecastDate.length() == 7) {
                    String dayCraw = dateService.getDate();
                    forecastDate = dayCraw;
                } else {
                    int lastDelimited = forecastDate.lastIndexOf("/");
                    forecastDate = dateService.getYear() + "-" + forecastDate.substring(lastDelimited + 1) + "-" + forecastDate.substring(lastDelimited - 2, lastDelimited);
                }
                DateDim dateDim = dao.getDateDim(dateService.getDateCrawl(forecastDate));
                String naturalKey = sourceName + provinceDim.getCodeName() + dateService.getHour() + "h" + dateDim.getId();
                String description = element.select(".summary-description-detail").text().trim();
                String minTemp = element.select(".summary-temperature-min").text().trim().substring(0, 2);
                String maxTemp = element.select(".summary-temperature-max-value").text().trim().substring(0, 2);
                String humidity = element.select(".summary-humidity").text().trim().substring(0, 2);
                String windSpeed = element.select(".summary-speed").text().trim();
                windSpeed = windSpeed.substring(windSpeed.indexOf(" ") + 1, windSpeed.lastIndexOf(" "));

                String sunrise = element.select(".weather-content-item-sun .weather-sun span:first-child").text().trim();
                String sundown = element.select(".weather-content-item-sun .weather-sun span:last-child").text().trim();

                // save local storage
                success = fileService.saveCSV(StrConstants.LOCAL_STORAGE.concat(fileName), naturalKey, province, forecastDate, description, minTemp, maxTemp, humidity, windSpeed, sunrise, sundown);
            }
        }

        return success ? fileName : fileName + ".err";
    }

    public boolean loadFile(String fileLocalPath) {
        FileService fileService = new FileService();

        dao.truncateStaging();
        boolean check = dao.loadFileIntoStaging(fileLocalPath);

        fileService.deleteFileLocal(fileLocalPath);
        return check;
    }

    public boolean transform() {
        // transform delete naturalKey = null
        dao.deleteNaturalKey();

        dao.truncateStagingFact();
        // transform join dim
        return dao.addStagingFact();

    }

    public void load(String dateDimPath, String provinceDimPath, String factPath) {
        ConnectMySQL connectDwh = new ConnectMySQL(DBConstants.DATA_WAREHOUSE);
        java.sql.Connection connectionDwh = connectDwh.getConnection();
        DAO dao = new DAO(connectionDwh);
        int count = dao.getCountDateDim();
        if (count == 0) dao.loadFileDateDim(dateDimPath);

        count = dao.getCountProvinceDim();
        if (count == 0) dao.loadFileProvinceDim(provinceDimPath);

        count = dao.getCountFact();
        if (count == 0) {
            dao.loadFileFact(factPath);
        } else {
            boolean success = dao.updateExpired();
            if (success) dao.loadFileFact(factPath);
        }
    }

    public void exportFile(String dateDimPath, String provinceDimPath, String factPath) {
        dao.exportFileDateDim(dateDimPath);
        dao.exportFileProvinceDim(provinceDimPath);
        dao.exportFileStagingFact(factPath);
    }
}
