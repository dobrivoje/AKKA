package primes;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import akka.japi.Creator;
import java.util.concurrent.TimeUnit;
import primes.actors.FinalResultListener;
import primes.actors.MasterActor;
import primes.helpers.PrimeResult;
import primes.messages.Answer;
import primes.messages.NumberRangeMessage;
import scala.concurrent.duration.Duration;

public class PrimeCalculator {

    public static void calculatePrimeNumbers(long startNumber, long endNumber, final int noOfWorkers) throws Exception {
        ActorSystem actorSystem = ActorSystem.create("primeCalculus");
        Inbox inbox = Inbox.create(actorSystem);

        final ActorRef finalResultListener = actorSystem.actorOf(Props.create(FinalResultListener.class), "finalResult");

        ActorRef primeMaster = actorSystem.actorOf(
                Props.create(new Creator<MasterActor>() {
                    @Override
                    public MasterActor create() throws Exception {
                        return new MasterActor(noOfWorkers, finalResultListener);
                    }
                }));

        primeMaster.tell(new NumberRangeMessage(startNumber, endNumber), ActorRef.noSender());
        
        inbox.send(finalResultListener, new Answer());

        PrimeResult g1 = (PrimeResult) inbox.receive(Duration.create(11, TimeUnit.SECONDS));
        System.out.println("Odgovor : " + g1.getResult().toString());
    }

    public static void main(String[] args) throws Exception {
        calculatePrimeNumbers(1, 1000, 10);
    }
}
