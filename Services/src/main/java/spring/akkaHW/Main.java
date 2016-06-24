package spring.akkaHW;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.util.Timeout;
import scala.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import scala.concurrent.Await;
import scala.concurrent.duration.FiniteDuration;
import spring.akkaHW.config.AppConfiguration;
import spring.akkaHW.AkkA.messages.NewStat;
import static akka.pattern.Patterns.ask;
import java.util.Date;
import java.util.logging.Logger;
import spring.akkaHW.AkkA.helpers.Stat;
import spring.akkaHW.AkkA.messages.GetStat;
import spring.akkaHW.AkkA.messages.NewClick;

public class Main {

    private static final ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);
    private static final ActorSystem system = ctx.getBean(ActorSystem.class);
    private static final ActorRef actorRef = ctx.getBean(ActorRef.class);

    private static Stat returnStatResult(Object message, long timeToWait, TimeUnit timeUnit) throws Exception {

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

    public static void main(String[] args) throws Exception {

        Logger.getLogger("test").info("Test 1 : Insert i Statistika...");
        
        actorRef.tell(new NewClick(1, new Date(), "https://kurir.rs"), ActorRef.noSender());

        actorRef.tell(new NewStat(1, "2016-6-19", "2016-6-30"), ActorRef.noSender());

        Stat stat = returnStatResult(new GetStat(), 11, TimeUnit.SECONDS);

        stat.getClickList().stream().forEach((s) -> {
            System.out.println(s);
        });

    }
}
