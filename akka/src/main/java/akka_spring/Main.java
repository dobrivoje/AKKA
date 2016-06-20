package akka_spring;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import static akka.pattern.Patterns.ask;
import akka.util.Timeout;
import scala.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import scala.concurrent.Await;
import scala.concurrent.duration.FiniteDuration;
import akka_spring.infra.SpringExtension;
import akka_spring.messages.Count;
import akka_spring.messages.Get;

public class Main {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan("spring.akka_lightbend");
        ctx.refresh();

        ActorSystem system = ctx.getBean(ActorSystem.class);
        ActorRef counter = system.actorOf(SpringExtension.SpringExtProvider
                .get(system).props("CountingActor"), "counter");

        for (int i = 0; i < 1000; i++) {
            counter.tell(new Count(), ActorRef.noSender());
        }

        FiniteDuration duration = FiniteDuration.create(11, TimeUnit.SECONDS);
        Future<Object> result = ask(counter, new Get(), Timeout.durationToTimeout(duration));

        try {
            System.out.println("Rezultat : " + Await.result(result, duration));
        } catch (Exception e) {
            System.err.println("Greška : " + e.getMessage());
            throw e;
        } finally {
            system.shutdown();
            system.awaitTermination();
        }
    }

}
