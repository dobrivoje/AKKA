package spring.services;

import ent.Click;
import ent.Users;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IUserService {

    List<Users> getAllUsers();

    Users getUser(long ID);

    void insertNewClick(long userID, Date date, String webAddress);

    List<Click> getStatistics(long userId, Date from, Date to);

    List<Click> getStatistics(long userId, String from, String to) throws ParseException;

}
