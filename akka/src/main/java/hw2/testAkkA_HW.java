package hw2;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import akka.japi.Creator;
import ent.Users;
import hw2.actors.Master;
import hw2.helpers.Stat;
import hw2.messages.StatMessage;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;
import spring.jpa.config.DbServiceConfig;
import spring.jpa.services.IDBService;

public class testAkkA_HW {

    public static void main(String[] args) throws InterruptedException, ParseException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DbServiceConfig.class);
        /*
        IClickService clickService = ac.getBean(IClickService.class);
        IUserService us = ac.getBean(IUserService.class);
        */
        IDBService us = ac.getBean(IDBService.class);
        Users u = us.getUser(7);

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

        final Stat g1 = (Stat) inbox.receive(FiniteDuration.Zero());
        System.out.println("------------------------------------------------------------------");
        System.err.println("");
        System.out.println(g1.getClickList());
    }

}
