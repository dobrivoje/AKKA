package actors;

import akka.actor.UntypedActor;
import messages.HelloMsg;

public class HelloActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof HelloMsg) {
            System.err.println("Poruka : " + ((HelloMsg) message).getMessage());
        }
    }

}
