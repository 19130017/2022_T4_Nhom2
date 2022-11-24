package entities.control;

public class Config {
    private int id;
    private String source_name;
    private String source_path;
    private String ftp_ip;
    private String ftp_username;
    private String ftp_password;

    public Config(int id, String source_name, String source_path, String ftp_ip, String ftp_username, String ftp_password) {
        this.id = id;
        this.source_name = source_name;
        this.source_path = source_path;
        this.ftp_ip = ftp_ip;
        this.ftp_username = ftp_username;
        this.ftp_password = ftp_password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public String getSource_path() {
        return source_path;
    }

    public void setSource_path(String source_path) {
        this.source_path = source_path;
    }

    public String getFtp_ip() {
        return ftp_ip;
    }

    public void setFtp_ip(String ftp_ip) {
        this.ftp_ip = ftp_ip;
    }

    public String getFtp_username() {
        return ftp_username;
    }

    public void setFtp_username(String ftp_username) {
        this.ftp_username = ftp_username;
    }

    public String getFtp_password() {
        return ftp_password;
    }

    public void setFtp_password(String ftp_password) {
        this.ftp_password = ftp_password;
    }
}
