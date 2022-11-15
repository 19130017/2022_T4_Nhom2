package scripts;

import connect.ConnectMySQL;
import constants.DBConstants;
import constants.Status;
import constants.StrConstants;
import dao.DAO;
import entities.control.Config;
import entities.control.Log;
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
        DAO dao = new DAO(connectionControlDB);

        Log log = dao.getLog(dateService.getHour(), Status.EXTRACT_READY);
        if (log != null) {
            Config config = dao.getConfig(StrConstants.SOURCE_NAME_TTVN);

            try {
                String fileName = config.getSourceName().concat("_" + dateService.getFileName());
                String fileLocalPath = StrConstants.LOCAL_STORAGE.concat(fileName);
                String remoteFileName = "logs/" + dateService.getDateWithDelimited() + "/" + fileName;
                fileService.downloadCSV(config.getIpFTP(), config.getUsernameFTP(), config.getPasswordFTP(), fileLocalPath, remoteFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
