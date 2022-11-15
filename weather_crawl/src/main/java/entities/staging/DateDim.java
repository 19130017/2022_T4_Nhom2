package entities.staging;

import java.util.Date;

public class DateDim {

    private int id;
    private Date date;

    public DateDim(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }
}
