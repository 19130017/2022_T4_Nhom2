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
                        rs.getString("source_name"),
                        rs.getString("source_path"),
                        rs.getString("ftp_ip"),
                        rs.getString("ftp_username"),
                        rs.getString("ftp_password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Log getLog(int hour, String status, Date date) {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.CONTROL.GET_LOG);
            statement.setInt(1, hour);
            statement.setString(2, status);
            statement.setDate(3, new java.sql.Date(date.getTime()));
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Log(rs.getInt("id"),
                        rs.getString("file_name"),
                        rs.getString("time_zone"),
                        rs.getDate("date_crawl"),
                        rs.getInt("hour_crawl"),
                        rs.getString("status"),
                        rs.getString("author")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addLog(int id, Date date_crawl, int hour, String status, String author) {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.CONTROL.CREATE_LOG);
            statement.setInt(1, id);
            statement.setDate(2, new java.sql.Date(date_crawl.getTime()));
            statement.setInt(3, hour);
            statement.setString(4, status);
            statement.setString(5, author);
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
                        rs.getString("code_name"));
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
            statement.setString(2, log.getFile_name());
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

    public boolean truncateStagingFact() {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.STAGING.TRUNCATE_STAGING_FACT);
            return statement.executeUpdate() != 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteNaturalKey() {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.STAGING.DELETE_NATURAL_KEY_NULL);
            return statement.executeUpdate() != 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addStagingFact() {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.STAGING.ADD_STAGING_FACT);
            return statement.executeUpdate() != 0 ? true : false;
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

    // load
    public boolean exportFileDateDim(String fileLocalPath) {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.STAGING.EXPORT_FILE_DATE_DIM);
            statement.setString(1, fileLocalPath);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean exportFileProvinceDim(String fileLocalPath) {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.STAGING.EXPORT_FILE_PROVINCE_DIM);
            statement.setString(1, fileLocalPath);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean exportFileStagingFact(String fileLocalPath) {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.STAGING.EXPORT_FILE_STAGING_FACT);
            statement.setString(1, fileLocalPath);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean loadFileFact(String fileLocalPath) {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.DATA_WAREHOUSE.LOAD_FILE_INTO_FACT);
            statement.setString(1, fileLocalPath);
            System.out.println("hello");
            return statement.executeUpdate() != 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loadFileDateDim(String fileLocalPath) {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.DATA_WAREHOUSE.LOAD_FILE_DATE_DIM);
            statement.setString(1, fileLocalPath);
            return statement.executeUpdate() != 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loadFileProvinceDim(String fileLocalPath) {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.DATA_WAREHOUSE.LOAD_FILE_PROVINCE_DIM);
            statement.setString(1, fileLocalPath);
            return statement.executeUpdate() != 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getCountDateDim() {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.DATA_WAREHOUSE.COUNT_DATE_DIM);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getCountProvinceDim() {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.DATA_WAREHOUSE.COUNT_PROVINCE_DIM);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getCountFact() {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.DATA_WAREHOUSE.COUNT_FACT_WEATHER);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public boolean updateExpired() {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.DATA_WAREHOUSE.UPDATE_EXPIRED);
            return statement.executeUpdate() != 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
