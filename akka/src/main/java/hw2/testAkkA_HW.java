package hw2;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import akka.japi.Creator;
import hw2.actors.Master;
import hw2.helpers.Stat;
import hw2.messages.StatMessage;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import scala.concurrent.duration.Duration;
import spring.jpa.config.DbServiceConfig;
import spring.jpa.services.IClickService;

public class testAkkA_HW {

    public static void main(String[] args) throws InterruptedException, ParseException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DbServiceConfig.class);

        IClickService us = ac.getBean(IClickService.class);

        final ActorSystem system = ActorSystem.create("system");
        final Inbox inbox = Inbox.create(system);
        final ActorRef master = system.actorOf(
                Props.create(new Creator<Master>() {
                    @Override
                    public Master create() throws Exception {
                        return new Master(us);
                    }
                }), "master");

        inbox.send(master, new StatMessage(7, "2015-1-1", "2015-1-31"));

        final Stat g1 = (Stat) inbox.receive(Duration.create(11, TimeUnit.SECONDS));
        System.out.println("------------------------------------------------------------------");
        System.err.println("");
        System.out.println(g1.getClickList());
    }

}
