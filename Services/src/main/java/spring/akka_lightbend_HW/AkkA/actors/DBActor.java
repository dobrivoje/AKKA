package spring.akka_lightbend_HW.AkkA.actors;

import akka.actor.UntypedActor;
import org.springframework.context.annotation.Scope;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import spring.akka_lightbend_HW.AkkA.helpers.Stat;
import spring.akka_lightbend_HW.AkkA.messages.GetStat;
import spring.akka_lightbend_HW.AkkA.messages.NewStat;
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
        } else {
            unhandled(message);
        }
    }

}
