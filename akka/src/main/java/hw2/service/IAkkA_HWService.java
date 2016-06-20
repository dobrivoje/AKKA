package hw2.service;

import ent.Click;
import java.text.ParseException;
import java.util.List;

public interface IAkkA_HWService {

    void newClick(Click click);

    List<Click> getStatistics(long userId, String from, String to) throws ParseException;
}
