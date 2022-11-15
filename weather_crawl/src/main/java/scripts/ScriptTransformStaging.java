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

public class ScriptTransformStaging {

    public void transform() {
        ConnectMySQL connectControlDB = new ConnectMySQL(DBConstants.CONTROL);
        Connection connectionControlDB = connectControlDB.getConnection();
        FileService fileService = new FileService();
        DateService dateService = new DateService();
        ActionService actionService = new ActionService();
        DAO dao = new DAO(connectionControlDB);

        Log log = dao.getLog(dateService.getHour(), Status.EXTRACT_READY);
        if (log != null) {
            Config config = dao.getConfig(StrConstants.SOURCE_NAME_TTVN);

            try {
                String fileName = config.getSourceName().concat("_" + dateService.getFileName());
                String fileLocalPath = StrConstants.LOCAL_STORAGE.concat(fileName);
                String remoteFileName = "logs/" + dateService.getDateWithDelimited() + "/" + fileName;
                fileService.downloadCSV(config.getIpFTP(), config.getUsernameFTP(), config.getPasswordFTP(), fileLocalPath, remoteFileName);

                // load file into staging_dim
                boolean check = actionService.loadFile(fileLocalPath);
                if (check) log.setStatus(Status.EXTRACT_SUCCESSFUL);
                else log.setStatus(Status.EXTRACT_NULL);

                dao.updateLog(log);

                // transform
                actionService.transform();
                log.setStatus(Status.TRANSFORM);
                dao.updateLog(log);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
