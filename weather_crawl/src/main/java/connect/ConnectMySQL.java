package connect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectMySQL {
    private String path;
    private String username;
    private String password;
    private String database;

    public ConnectMySQL(String database) {
        try {
            this.database = database;
            config();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    private void config() throws IOException {
        Properties properties = new Properties();
        properties.load(ConnectMySQL.class.getClassLoader().getResourceAsStream("application.properties"));
        this.username = properties.getProperty("db.USERNAME");
        this.password = properties.getProperty("db.PASSWORD");
        this.path = properties.getProperty("db.PATH");
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(path.concat(database), username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
