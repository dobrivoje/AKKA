package services;

import mappers.ClickMapper;
import ent.Click;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClickService {

    @Autowired
    private ClickMapper clickMapper;

    public List<Click> getStatistics(long userId, Date from, Date to) {
        return clickMapper.getStatistics(userId, from, to);
    }

    public List<Click> getStatistics(long userId, String from, String to) throws ParseException {
        return clickMapper.getStatistics(userId, from, to);
    }

    @Transactional
    public void insertNewClick(long userID, Date date, String webAddress) {
        clickMapper.insertNewClick(userID, date, webAddress);
    }

}
