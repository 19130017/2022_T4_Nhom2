package entities.control;

import java.util.Date;

public class Log {
    private int id;
    private String file_name;
    private String time_zone;
    private Date date_crawl;
    private int hour_crawl;
    private String status;
    private String author;

    public Log() {
    }

    public Log(int id, String file_name, String time_zone, Date date_crawl, int hour_crawl, String status, String author) {
        this.id = id;
        this.file_name = file_name;
        this.time_zone = time_zone;
        this.date_crawl = date_crawl;
        this.hour_crawl = hour_crawl;
        this.status = status;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getTime_zone() {
        return time_zone;
    }

    public void setTime_zone(String time_zone) {
        this.time_zone = time_zone;
    }

    public Date getDate_crawl() {
        return date_crawl;
    }

    public void setDate_crawl(Date date_crawl) {
        this.date_crawl = date_crawl;
    }

    public int getHour_crawl() {
        return hour_crawl;
    }

    public void setHour_crawl(int hour_crawl) {
        this.hour_crawl = hour_crawl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
