package spring.akkaHW.services;

import ent.Click;
import ent.Users;
import java.text.ParseException;
import java.util.List;

public interface IDBAkkA_Service {

    Users getUser(long userID);

    void newClick(Click click);

    List<Click> getStatistics(long userId, String from, String to) throws ParseException;
}
