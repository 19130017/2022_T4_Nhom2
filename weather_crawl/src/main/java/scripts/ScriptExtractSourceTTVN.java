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
        Config config = dao.getConfig(StrConstants.SOURCE_NAME_TTVN);

        DateService dateService = new DateService();
        Log log = dao.getLog(dateService.getHour(), Status.EXTRACT_STARTING);

        if (log == null) {
            dao.addLog(config.getId(), null, dateService.getDateCrawl(dateService.getDate()), dateService.getHour(), Status.EXTRACT_STARTING, StrConstants.AUTHOR1);

            try {
                String fileName = actionService.crawl(config.getSourceName(), config.getSourcePath());
                String localPath = StrConstants.LOCAL_STORAGE.concat(fileName);

                if (!fileName.isEmpty()) {
                    log = dao.getLog(dateService.getHour(), Status.EXTRACT_STARTING);
                    fileService.sendFileFTP(config.getIpFTP(), config.getUsernameFTP(), config.getPasswordFTP(), localPath, fileName, dateService.getDateWithDelimited());
                    log.setFileName(fileName);
                    log.setStatus(Status.EXTRACT_READY);
                    dao.updateLog(log);
                } else {
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
