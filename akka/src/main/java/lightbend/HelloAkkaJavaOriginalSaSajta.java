package lightbend;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Inbox;
import java.io.Serializable;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

public class HelloAkkaJavaOriginalSaSajta {

    public static class Greet implements Serializable {
    }

    public static class WhoToGreet implements Serializable {

        public final String who;

        public WhoToGreet(String who) {
            this.who = who;
        }
    }

    public static class Greeting implements Serializable {

        public final String message;

        public Greeting(String message) {
            this.message = message;
        }
    }

    public static class Greeter extends UntypedActor {

        String greeting = "";

        @Override
        public void onReceive(Object message) {
            if (message instanceof WhoToGreet) {
                greeting = "hello, " + ((WhoToGreet) message).who;
            } else if (message instanceof Greet) {
                getSender().tell(new Greeting(greeting), getSelf());
            } else {
                unhandled(message);
            }
        }
    }

    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("helloakka");
        final ActorRef greeter = system.actorOf(Props.create(Greeter.class), "greeter");
        final Inbox inbox = Inbox.create(system);

        greeter.tell(new WhoToGreet("akka"), ActorRef.noSender());
        inbox.send(greeter, new Greet());
        final Greeting g1 = (Greeting) inbox.receive(Duration.create(5, TimeUnit.NANOSECONDS));
        System.out.println("Greeting: " + g1.message);

        greeter.tell(new WhoToGreet("typesafe"), ActorRef.noSender());
        inbox.send(greeter, new Greet());
        final Greeting g2 = (Greeting) inbox.receive(Duration.create(5, TimeUnit.SECONDS));
        System.out.println("Greeting: " + g2.message);

        final ActorRef greetPrinter = system.actorOf(Props.create(GreetPrinter.class));
        system.scheduler().schedule(
                Duration.Zero(), Duration.create(1, TimeUnit.SECONDS), greeter, new Greet(), system.dispatcher(), greetPrinter
        );
    }

    public static class GreetPrinter extends UntypedActor {

        @Override
        public void onReceive(Object message) {
            if (message instanceof Greeting) {
                System.out.println(((Greeting) message).message);
            }
        }
    }
}
