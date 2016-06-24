package hw2.actors;

import akka.actor.UntypedActor;
import hw2.helpers.Stat;
import hw2.messages.StatMessage;
import java.text.ParseException;
import spring.jpa.services.IClickService;

public class Master extends UntypedActor {

    private final Stat stat = new hw2.helpers.Stat();
    private final IClickService clickService;

    public Master(IClickService clickService) {
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
            } finally {
                //getContext().system().shutdown();
            }
        } else {
            unhandled(message);
        }
    }
}
