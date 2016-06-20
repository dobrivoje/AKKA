package spring.akkaHW.AkkA.messages;

import java.io.Serializable;
import java.util.Date;

public class NewClick implements Serializable {

    private long userID;
    private Date clickDate;
    private String webAddress;

    public NewClick(long userID, Date clickDate, String webAddress) {
        this.userID = userID;
        this.clickDate = clickDate;
        this.webAddress = webAddress;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public Date getClickDate() {
        return clickDate;
    }

    public void setClickDate(Date clickDate) {
        this.clickDate = clickDate;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

}
