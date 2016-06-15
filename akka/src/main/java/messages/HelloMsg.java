package messages;

import java.io.Serializable;

public class HelloMsg implements Serializable {

    private String message;

    public HelloMsg(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
