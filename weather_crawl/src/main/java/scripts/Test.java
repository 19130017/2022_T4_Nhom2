package scripts;

import connect.ConnectMySQL;
import constants.DBConstants;
import dao.DAO;
import services.ActionService;
import services.FileService;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws IOException {

        ActionService actionService = new ActionService();
        actionService.crawl("TTVN","https://thoitiet.vn");
    }
}
