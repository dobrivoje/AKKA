package hw2.messages;

import ent.Click;
import java.io.Serializable;

public class ClickMessage implements Serializable {

    private Click click;

    public ClickMessage(Click click) {
        this.click = click;
    }

    public Click getClick() {
        return click;
    }

    public void setClick(Click click) {
        this.click = click;
    }

}
