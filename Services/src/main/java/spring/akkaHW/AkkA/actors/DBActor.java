package spring.akkaHW.AkkA.actors;

import akka.actor.UntypedActor;
import org.springframework.context.annotation.Scope;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import spring.akkaHW.AkkA.helpers.Stat;
import spring.akkaHW.AkkA.messages.GetStat;
import spring.akkaHW.AkkA.messages.NewClick;
import spring.akkaHW.AkkA.messages.NewStat;
import spring.jpa.services.IDBService;

@Named("DBActor")
@Scope("prototype")
public class DBActor extends UntypedActor {

    private final Stat stat = new Stat();
    private final IDBService DBService;

    @Autowired
    public DBActor(IDBService DBService) {
        this.DBService = DBService;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof NewStat) {

            NewStat sm = (NewStat) message;
            stat.getClickList().addAll(DBService.getStatistics(sm.getUserId(), sm.getDateFrom(), sm.getDateTo()));

        } else if (message instanceof GetStat) {

            getSender().tell(stat, getSelf());

        } else if (message instanceof NewClick) {

            NewClick nc = (NewClick) message;

            DBService.insertNewClick(nc.getUserID(), nc.getClickDate(), nc.getWebAddress());

        } else {
            unhandled(message);
        }
    }

}
