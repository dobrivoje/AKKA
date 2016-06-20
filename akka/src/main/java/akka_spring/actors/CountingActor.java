package akka_spring.actors;

import akka.actor.UntypedActor;
import org.springframework.context.annotation.Scope;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import akka_spring.ICountingService;
import akka_spring.messages.Count;
import akka_spring.messages.Get;

@Named("CountingActor")
@Scope("prototype")
public class CountingActor extends UntypedActor {

    final ICountingService countingService;

    private int count = 0;

    @Autowired
    public CountingActor(@Named("cntService") ICountingService countingService) {
        this.countingService = countingService;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Count) {
            count = countingService.increment(count);
        } else if (message instanceof Get) {
            getSender().tell(count, getSelf());
        } else {
            unhandled(message);
        }
    }

}
