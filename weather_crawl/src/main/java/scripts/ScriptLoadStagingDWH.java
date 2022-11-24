package scripts;

import connect.ConnectMySQL;
import constants.DBConstants;
import constants.Status;
import constants.StrConstants;
import dao.DAO;
import entities.control.Log;
import services.ActionService;
import services.DateService;
import services.FileService;

import java.sql.Connection;

public class ScriptLoadStagingDWH {

    public void load() {
        ConnectMySQL connectControlDB = new ConnectMySQL(DBConstants.CONTROL);
        Connection connectionControlDB = connectControlDB.getConnection();
        DAO dao = new DAO(connectionControlDB);
        ActionService actionService = new ActionService();
        DateService dateService = new DateService();
        FileService fileService = new FileService();
        Log log = dao.getLog(dateService.getHour(), Status.TRANSFORM, dateService.getDateCrawl(dateService.getDate()));

        if (log != null) {
            String dateDimPath = StrConstants.LOCAL_STORAGE.concat("date_dim.csv");
            String provinceDimPath = StrConstants.LOCAL_STORAGE.concat("province_dim.csv");
            String factPath = StrConstants.LOCAL_STORAGE.concat("fact_weather.csv");

            actionService.exportFile(dateDimPath, provinceDimPath, factPath);

            actionService.load(dateDimPath, provinceDimPath, factPath);
            log.setStatus(Status.LOADED);
            dao.updateLog(log);
            System.out.println("Done load!");

            fileService.deleteFileLocal(dateDimPath);
            fileService.deleteFileLocal(provinceDimPath);
            fileService.deleteFileLocal(factPath);
        }
    }
}
