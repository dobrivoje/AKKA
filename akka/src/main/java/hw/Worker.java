package hw;

import akka.actor.UntypedActor;
import ent.Users;

public class Worker extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof ClickMessage) {
            ClickMessage cm = (ClickMessage) message;

            Result result = new Result();
            
            Users u = cm.getClick().getFkIdu();
            u.getClickList().add(cm.getClick());
            
            result.getResults().add(u);

            getSender().tell(result, getSelf());
        } else {
            unhandled(message);
        }
    }

}
