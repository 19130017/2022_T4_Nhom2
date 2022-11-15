package constants;

public class QUERY {
    public static class CONTROL {
        public static final String GET_CONFIG = "CALL proc_get_config(?);";
        public static final String GET_LOG = "CALL proc_get_log(?,?);";
        public static final String UPDATE_LOG = "CALL proc_update_log(?, ?, ?);";
        public static final String CREATE_LOG = "CALL proc_add_log(?, ?, ?, ?, ?, ?);";
    }

    public static class STAGING {
        public static final String TRUNCATE_STAGING_DIM = "CALL proc_truncate_staging()";
        public static final String LOAD_FILE_INTO_STAGING_DIM = "LOAD DATA INFILE ? INTO TABLE staging_dim FIELDS TERMINATED BY  ',' ENCLOSED BY '' LINES TERMINATED BY '\n';";
        public static final String GET_PROVINCE_DIM = "CALL proc_get_province(?)";
        public static final String GET_DATE_DIM = "CALL proc_get_date(?)";

        public static final String DELETE_NATURAL_KEY_NULL = "CALL proc_delete_staging()";
        public static final String ADD_STAGING_FACT = "CALL proc_add_fact()";
        public static final String TRUNCATE_STAGING_FACT = "CALL proc_truncate_fact()";
    }


}
