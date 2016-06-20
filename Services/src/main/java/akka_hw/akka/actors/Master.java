package akka_hw.akka.actors;

import akka.actor.UntypedActor;
import akka_hw.akka.helpers.Stat;
import akka_hw.akka.messages.StatMessage;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.jpa.services.IDBService;

@Component
public class Master extends UntypedActor {

    private final Stat stat = new Stat();
    
    private final IDBService clickService;

    @Autowired
    public Master(IDBService clickService) {
        this.clickService = clickService;
    }

    @Override
    public void onReceive(Object message) {
        if (message instanceof StatMessage) {
            StatMessage sm = (StatMessage) message;

            try {
                stat.getClickList().addAll(
                        clickService.getStatistics(sm.getUserId(), sm.getDateFrom(), sm.getDateTo())
                );
                getSender().tell(stat, getSelf());
            } catch (ParseException ex) {
                Logger.getLogger(akka_hw.akka.actors.Master.class.getName()).log(Level.SEVERE, "greška OnRecieve metod.", ex);
            } finally {
                //getContext().system().shutdown();
            }
        } else {
            unhandled(message);
        }
    }
}
