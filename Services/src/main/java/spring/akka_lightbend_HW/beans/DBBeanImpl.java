package spring.akka_lightbend_HW.beans;

import ent.Click;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.jpa.services.IDBService;
import spring.akka_lightbend_HW.services.IDBAkkA_Service;

@Component
public class DBBeanImpl implements IDBAkkA_Service {

    private final IDBService DBService;

    @Autowired
    public DBBeanImpl(IDBService DBService) {
        this.DBService = DBService;
    }

    @Override
    public void newClick(Click click) {
        DBService.insertNewClick(click.getFkIdu().getIdu(), click.getDate(), click.getIpaddress());
    }

    @Override
    public List<Click> getStatistics(long userId, String from, String to) throws ParseException {
        return DBService.getStatistics(userId, from, to);
    }

}
