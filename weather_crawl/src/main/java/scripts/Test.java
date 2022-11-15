package scripts;

import connect.ConnectMySQL;
import constants.DBConstants;
import dao.DAO;

import java.sql.Connection;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
       ScriptExtractSourceTTVN scriptExtractSourceTTVN = new ScriptExtractSourceTTVN();
       scriptExtractSourceTTVN.extractData();
    }
}
