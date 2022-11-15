package entities.control;

public class Config {
    private int id;
    private String sourceName;
    private String sourcePath;
    private String ipFTP;
    private String usernameFTP;
    private String passwordFTP;

    public Config(int id, String sourceName, String sourcePath, String ipFTP, String usernameFTP, String passwordFTP) {
        this.id = id;
        this.sourceName = sourceName;
        this.sourcePath = sourcePath;
        this.ipFTP = ipFTP;
        this.usernameFTP = usernameFTP;
        this.passwordFTP = passwordFTP;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getIpFTP() {
        return ipFTP;
    }

    public void setIpFTP(String ipFTP) {
        this.ipFTP = ipFTP;
    }

    public String getUsernameFTP() {
        return usernameFTP;
    }

    public void setUsernameFTP(String usernameFTP) {
        this.usernameFTP = usernameFTP;
    }

    public String getPasswordFTP() {
        return passwordFTP;
    }

    public void setPasswordFTP(String passwordFTP) {
        this.passwordFTP = passwordFTP;
    }


    @Override
    public String toString() {
        return "Config{" +
                "id=" + id +
                ", sourceName='" + sourceName + '\'' +
                ", sourcePath='" + sourcePath + '\'' +
                ", ipFTP='" + ipFTP + '\'' +
                ", usernameFTP='" + usernameFTP + '\'' +
                ", passwordFTP='" + passwordFTP + '\'' +
                '}';
    }
}
