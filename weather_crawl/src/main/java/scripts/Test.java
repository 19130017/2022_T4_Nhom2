package scripts;

import connect.ConnectMySQL;
import constants.DBConstants;
import dao.DAO;
import services.FileService;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws IOException {
//        ScriptExtractSourceTTVN extract = new ScriptExtractSourceTTVN();
//        extract.extractData();
        ScriptTransformStaging scriptTransformStaging = new ScriptTransformStaging();
        scriptTransformStaging.transform();
    }
}