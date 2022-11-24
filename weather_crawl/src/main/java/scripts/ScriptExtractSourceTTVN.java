package scripts;


import connect.ConnectMySQL;
import constants.DBConstants;
import constants.Status;
import constants.StrConstants;
import dao.DAO;
import entities.control.Config;
import entities.control.Log;

import services.ActionService;
import services.DateService;
import services.FileService;

import java.io.IOException;
import java.sql.Connection;

public class ScriptExtractSourceTTVN {

    public void extractData() {
        ConnectMySQL connectControlDB = new ConnectMySQL(DBConstants.CONTROL);
        Connection connectionControlDB = connectControlDB.getConnection();
        FileService fileService = new FileService();
        ActionService actionService = new ActionService();
        DAO dao = new DAO(connectionControlDB);

        DateService dateService = new DateService();
        Log log = dao.getLog(dateService.getHour(), Status.EXTRACT_STARTING, dateService.getDateCrawl(dateService.getDate()));
        if (log == null) {
            Config config = dao.getConfig(StrConstants.SOURCE_NAME_TTVN);
            dao.addLog(config.getId(), dateService.getDateCrawl(dateService.getDate()), dateService.getHour(), Status.EXTRACT_STARTING, StrConstants.AUTHOR1);

            try {
                String fileName = actionService.crawl(config.getSource_name(), config.getSource_path());
                String localPath = null;

                if (!fileName.endsWith(".err")) {
                    localPath = StrConstants.LOCAL_STORAGE.concat(fileName);
                    log = dao.getLog(dateService.getHour(), Status.EXTRACT_STARTING, dateService.getDateCrawl(dateService.getDate()));
                    fileService.sendFileFTP(config.getFtp_ip(), config.getFtp_username(), config.getFtp_password(), localPath, fileName, dateService.getDateWithDelimited());
                    log.setFile_name(fileName);
                    log.setStatus(Status.EXTRACT_READY);
                    dao.updateLog(log);
                } else {
                    localPath = StrConstants.LOCAL_STORAGE.concat(fileName.substring(0, fileName.lastIndexOf(".err")));
                    log.setStatus(Status.EXTRACT_NULL);
                    dao.updateLog(log);
                }
                fileService.deleteFileLocal(localPath);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Extract Starting");
            return;
        }

    }
}
