package mappers;

import ent.Click;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ClickMapper {

    List<Click> getStatistics(long userId, Date from, Date to);

    List<Click> getStatistics(long userId, String from, String to) throws ParseException;

    void insertNewClick(long userID, Date date, String webAddress);

}
