package akka;

import actors.PrimeListener;
import actors.PrimeMaster;
import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActorFactory;
import messages.NumberRangeMessage;

public class PrimeCalculus {

    private void calculate(long from, long to) {
        ActorSystem actorSystem = ActorSystem.create("primeCalculus");
        final ActorRef primeListener = actorSystem.actorOf(new Props(PrimeListener.class), "primeListener");

        ActorRef primeMaster = actorSystem.actorOf(new Props(new UntypedActorFactory() {
            @Override
            public Actor create() throws Exception {
                return new PrimeMaster(10, primeListener);
            }
        }), "primeMaster"
        );

        primeMaster.tell(new NumberRangeMessage(from, to), null);
    }

    public static void main(String... args) {
        /*
        long startNumber = Long.parseLong(args[0]);
        long endNumber = Long.parseLong(args[1]);
         */

        long startNumber = 1;
        long endNumber = 20;

        PrimeCalculus calculus = new PrimeCalculus();
        calculus.calculate(startNumber, endNumber);
    }
}
