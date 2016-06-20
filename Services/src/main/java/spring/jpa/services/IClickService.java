package spring.jpa.services;

import ent.Click;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IClickService {

    void insertNewClick(long userID, Date date, String webAddress);

    List<Click> getAllUsersClicks(String userName);

    List<Click> getStatistics(long userId, Date from, Date to);

    List<Click> getStatistics(long userId, String from, String to) throws ParseException;

}
