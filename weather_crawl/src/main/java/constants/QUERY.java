package constants;

public class QUERY {
    public static class CONTROL {
        public static final String GET_CONFIG = "CALL proc_get_config(?);";
        public static final String GET_LOG = "CALL proc_get_log(?,?);";
        public static final String UPDATE_LOG = "CALL proc_update_log(?, ?, ?);";
        public static final String CREATE_LOG = "CALL proc_add_log(?, ?, ?, ?, ?);";
    }

    public static class STAGING {
        public static final String TRUNCATE_STAGING_DIM = "CALL proc_truncate_staging()";
        public static final String LOAD_FILE_INTO_STAGING_DIM = "LOAD DATA INFILE ? INTO TABLE staging_dim FIELDS TERMINATED BY  ',' ENCLOSED BY '' LINES TERMINATED BY '\n';";
        public static final String GET_PROVINCE_DIM = "CALL proc_get_province(?)";
        public static final String GET_DATE_DIM = "CALL proc_get_date(?)";

        public static final String DELETE_NATURAL_KEY_NULL = "CALL proc_delete_staging()";
        public static final String ADD_STAGING_FACT = "CALL proc_add_fact()";
        public static final String TRUNCATE_STAGING_FACT = "CALL proc_truncate_fact()";


        public static final String EXPORT_FILE_DATE_DIM = "SELECT * FROM date_dim INTO OUTFILE ? FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n';";
        public static final String EXPORT_FILE_PROVINCE_DIM = "SELECT * FROM province_dim INTO OUTFILE ? FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n';";
        public static final String EXPORT_FILE_STAGING_FACT = "SELECT * FROM staging_fact INTO OUTFILE ? FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n';";
    }

    public static class DATA_WAREHOUSE {
        public static final String LOAD_FILE_INTO_FACT = "LOAD DATA INFILE ? INTO TABLE fact_weather FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\n' (naturalKey, forecastDateId, provinceId, description, minTemperature, maxTemperature, humidity,windSpeed, sunrise, sundown);";

        public static final String LOAD_FILE_DATE_DIM = "LOAD DATA INFILE ? INTO TABLE date_dim FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\n';";
        public static final String LOAD_FILE_PROVINCE_DIM = "LOAD DATA INFILE ? INTO TABLE province_dim FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\n';";

        public static final String COUNT_DATE_DIM = "CALL proc_count_date_dim();";
        public static final String COUNT_PROVINCE_DIM = "CALL proc_count_province_dim();";
        public static final String COUNT_FACT_WEATHER = "CALL proc_count_fact_weather();";

        public static final String UPDATE_EXPIRED = "CALL proc_update_expired_time()";

    }

}
