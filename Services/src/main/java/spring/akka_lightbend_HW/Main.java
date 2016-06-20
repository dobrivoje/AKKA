package spring.akka_lightbend_HW;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.util.Timeout;
import scala.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import scala.concurrent.Await;
import scala.concurrent.duration.FiniteDuration;
import spring.akka_lightbend_HW.config.AppConfiguration;
import spring.akka_lightbend_HW.AkkA.messages.NewStat;
import static akka.pattern.Patterns.ask;
import ent.Click;
import spring.akka_lightbend_HW.AkkA.helpers.Stat;
import spring.akka_lightbend_HW.AkkA.messages.GetStat;

public class Main {

    private static final ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);
    private static final ActorSystem system = ctx.getBean(ActorSystem.class);
    private static final ActorRef actorRef = ctx.getBean(ActorRef.class);

    private static Stat returnResult(Object message, long howMuch, TimeUnit timeUnit) throws Exception {

        FiniteDuration duration = FiniteDuration.create(howMuch, timeUnit);
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

    public static void main(String[] args) throws Exception {
        actorRef.tell(new NewStat(7, "2015-1-1", "2015-1-31"), ActorRef.noSender());

        Stat stat = returnResult(new GetStat(), 11, TimeUnit.SECONDS);

        for (Click s : stat.getClickList()) {
            System.out.println(s);
        }
    }
}
