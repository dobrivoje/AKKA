package spring.akkaHW.services;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.util.Timeout;
import java.util.concurrent.TimeUnit;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;
import spring.akkaHW.AkkA.helpers.Stat;
import static akka.pattern.Patterns.ask;

public class StatisticstImpl implements IClickStat {

    private static ActorRef actorRef;
    private static ActorSystem system;

    public StatisticstImpl(ActorSystem system, ActorRef actorRef) {
        StatisticstImpl.system = system;
        StatisticstImpl.actorRef = actorRef;
    }

    @Override
    public void newMessage(Object message) {
        actorRef.tell(message, ActorRef.noSender());
    }

    @Override
    public Stat statResults(Object message, long timeToWait, TimeUnit timeUnit) throws Exception {
        FiniteDuration duration = FiniteDuration.create(timeToWait, timeUnit);
        Future<Object> result = ask(actorRef, message, Timeout.durationToTimeout(duration));

        Stat statistika;

        try {
            statistika = (Stat) Await.result(result, duration);
        } catch (Exception e) {
            System.err.println("Greška : " + e.getMessage());
            throw e;
        } finally {
            system.shutdown();
            system.awaitTermination();
        }

        return statistika;
    }

}
