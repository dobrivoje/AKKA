package spring.akkaHW.AkkA.messages;

import java.io.Serializable;

public class NewStat implements Serializable {

    private long userId;
    private String dateFrom;
    private String dateTo;

    public NewStat(long userId, String dateFrom, String dateTo) {
        this.userId = userId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

}
