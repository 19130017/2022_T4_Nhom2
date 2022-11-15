package dao;

import constants.QUERY;
import entities.control.Config;
import entities.control.Log;
import entities.staging.DateDim;
import entities.staging.ProvinceDim;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DAO {
    Connection connection;

    public DAO(Connection connection) {
        this.connection = connection;
    }

    // get one record from the database control table Config by name
    public Config getConfig(String sourceName) {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.CONTROL.GET_CONFIG);
            statement.setString(1, sourceName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Config(rs.getInt("id"),
                        rs.getString("sourceName"),
                        rs.getString("sourcePath"),
                        rs.getString("ipFTP"),
                        rs.getString("usernameFTP"),
                        rs.getString("passwordFTP"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Log getLog(int hour, String status) {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.CONTROL.GET_LOG);
            statement.setInt(1, hour);
            statement.setString(2, status);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Log(rs.getInt("id"),
                        rs.getString("fileName"),
                        rs.getString("timeZone"),
                        rs.getDate("dateCrawl"),
                        rs.getInt("hourCrawl"),
                        rs.getString("status"),
                        rs.getString("author")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addLog(int id, String fileName, Date dateCrawl, int hour, String status, String author) {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.CONTROL.CREATE_LOG);
            statement.setInt(1, id);
            statement.setString(2, fileName);
            statement.setDate(3, new java.sql.Date(dateCrawl.getTime()));
            statement.setInt(4, hour);
            statement.setString(5, status);
            statement.setString(6, author);
            return statement.executeUpdate() == 1 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ProvinceDim getProvince(String name) {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.STAGING.GET_PROVINCE_DIM);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new ProvinceDim(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("codeName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DateDim getDateDim(Date date) {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.STAGING.GET_DATE_DIM);
            statement.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new DateDim(rs.getInt("date_id"),
                        rs.getDate("date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateLog(Log log) {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.CONTROL.UPDATE_LOG);
            statement.setInt(1, log.getId());
            statement.setString(2, log.getFileName());
            statement.setString(3, log.getStatus());
            return statement.executeUpdate() == 1 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean truncateStaging() {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.STAGING.TRUNCATE_STAGING_DIM);
            return statement.executeUpdate() == 1 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loadFileIntoStaging(String fileLocalPath) {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.STAGING.LOAD_FILE_INTO_STAGING_DIM);
            statement.setString(1, fileLocalPath);
            return statement.executeUpdate() != 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
