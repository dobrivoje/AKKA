package spring.akkaHW.AkkA.helpers;

import ent.Click;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Stat implements Serializable {

    private final List<Click> clickList = new LinkedList<>();

    public List<Click> getClickList() {
        return clickList;
    }

}
