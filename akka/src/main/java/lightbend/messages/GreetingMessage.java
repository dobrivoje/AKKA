package lightbend.messages;

import java.io.Serializable;

public class GreetingMessage implements Serializable {

    private final String message;

    public GreetingMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
