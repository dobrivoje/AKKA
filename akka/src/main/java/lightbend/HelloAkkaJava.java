package lightbend;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Inbox;
import java.text.SimpleDateFormat;
import java.util.Date;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lightbend.actors.Greeter;
import lightbend.messages.GiveMeCurrentGreetMessage;
import lightbend.messages.GreetingMessage;

public class HelloAkkaJava {

    public static void main(String[] args) throws TimeoutException {

        final ActorSystem system = ActorSystem.create("helloAkkASystem");
        final ActorRef greeter = system.actorOf(Props.create(Greeter.class), "greeterActor");
        final Inbox inbox = Inbox.create(system);

        greeter.tell(new GreetingMessage("Dobrivoje Prtenjak"), ActorRef.noSender());
        // ovo isto radi !
        // inbox.send(greeter, new GreetingMessage("Dobrivoje Prtenjak - Preko inbox.send"));

        inbox.send(greeter, new GiveMeCurrentGreetMessage());
        GreetingMessage g1 = (GreetingMessage) inbox.receive(Duration.create(1, TimeUnit.MICROSECONDS));
        System.out.println("Greeting: " + g1.getMessage());

        greeter.tell(new GreetingMessage("Nina dušanko moja malllllllaaa !"), ActorRef.noSender());

        inbox.send(greeter, new GiveMeCurrentGreetMessage());
        GreetingMessage g2 = (GreetingMessage) inbox.receive(Duration.create(5, TimeUnit.MICROSECONDS));
        System.out.println("Greeting2: " + g2.getMessage());

        ActorRef greetPrinter = system.actorOf(Props.create(GreetPrinter.class));
        system.scheduler().schedule(
                Duration.Zero(),
                Duration.create(3, TimeUnit.SECONDS),
                greeter,
                new GiveMeCurrentGreetMessage(),
                system.dispatcher(),
                greetPrinter
        );
    }

    public static class GreetPrinter extends UntypedActor {

        @Override
        public void onReceive(Object message) {
            if (message instanceof GreetingMessage) {
                System.out.println(((GreetingMessage) message).getMessage()
                        + " @ "
                        + new SimpleDateFormat("dd-MM-yyyy hh:mm:ss")
                        .format(new Date(System.currentTimeMillis())));
            }
        }
    }
}
