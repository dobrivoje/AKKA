package spring.akka_lightbend_HW.services;

import ent.Click;
import java.text.ParseException;
import java.util.List;

public interface IDBAkkA_Service {

    void newClick(Click click);

    List<Click> getStatistics(long userId, String from, String to) throws ParseException;
}
