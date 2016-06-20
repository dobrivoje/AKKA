package hw2.actors;

import akka.actor.UntypedActor;
import akka_hw.akka.messages.StatMessage;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lightbend.HelloAkkaJavaOrig_saJPA;
import spring.jpa.services.IDBService;

public class Master extends UntypedActor {

    private final hw2.helpers.Stat stat = new hw2.helpers.Stat();
    private final IDBService clickService;

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
                Logger.getLogger(HelloAkkaJavaOrig_saJPA.class.getName()).log(Level.SEVERE, "greška OnRecieve metod.", ex);
            } finally {
                //getContext().system().shutdown();
            }
        } else {
            unhandled(message);
        }
    }
}
