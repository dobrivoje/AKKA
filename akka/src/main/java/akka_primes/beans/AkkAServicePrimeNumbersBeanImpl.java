package akka_primes.beans;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.Creator;
import org.springframework.stereotype.Component;
import primes.actors.FinalResultListener;
import primes.actors.MasterActor;
import primes.messages.NumberRangeMessage;
import akka_primes.services.IAkkAPrimeNumbersService;

@Component
public class AkkAServicePrimeNumbersBeanImpl implements IAkkAPrimeNumbersService {

    private static int noOfWorkers;
    private static final ActorSystem ACTOR_SYSTEM = ActorSystem.create("primeCalculus");
    private static final ActorRef FINAL_RESULT_LISTENER = ACTOR_SYSTEM.actorOf(
            Props.create(FinalResultListener.class), "finalResult");
    private static final Props PROPS = Props.create(new Creator<MasterActor>() {
        @Override
        public MasterActor create() throws Exception {
            return new MasterActor(noOfWorkers, FINAL_RESULT_LISTENER);
        }
    });
    private static final ActorRef PRIMEMASTER = ACTOR_SYSTEM.actorOf(PROPS);

    public AkkAServicePrimeNumbersBeanImpl() {
        AkkAServicePrimeNumbersBeanImpl.noOfWorkers = 10;
    }

    /*
    public AkkAServicePrimeNumbersBeanImpl(final int noOfWorkers) {
        AkkAServicePrimeNumbersBeanImpl.noOfWorkers = noOfWorkers;
    }
    */

    @Override
    public void calculatePrimeNumbers(long startNumber, long endNumber) {
        PRIMEMASTER.tell(new NumberRangeMessage(startNumber, endNumber), ActorRef.noSender());
    }

    @Override
    public void printNoOfWorkers() {
        System.err.println("-------------");
        System.err.println("#of workers : " + noOfWorkers);
        System.err.println("-------------");
    }

}
