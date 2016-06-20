package spring.akka_lightbend_HW.AkkA.messages;

import ent.Click;
import java.io.Serializable;

public class NewClick implements Serializable {

    private Click click;

    public NewClick(Click click) {
        this.click = click;
    }

    public Click getClick() {
        return click;
    }

    public void setClick(Click click) {
        this.click = click;
    }

}
