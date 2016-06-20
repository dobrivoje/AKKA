package akka_hw.akka;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Inbox;
import akka.japi.Creator;
import akka_hw.akka.helpers.Stat;
import akka_hw.akka.messages.StatMessage;
import ent.Users;
import java.text.ParseException;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.jpa.config.DbServiceConfig;
import spring.jpa.services.IDBService;

public class HelloAkkaJavaOrig_saJPA {

    public static class Master extends UntypedActor {

        private final Stat stat = new Stat();
        private final IDBService clickService;

        public Master(IDBService clickService) {
            this.clickService = clickService;
        }

        @Override
        public void onReceive(Object message) {
            if (message instanceof StatMessage) {
                StatMessage sm = (StatMessage) message;

                try {
                    stat.getClickList().addAll(
                            clickService.getStatistics(sm.getUserId(), sm.getDateFrom(), sm.getDateTo())
                    );
                    getSender().tell(stat, getSelf());
                } catch (ParseException ex) {
                    Logger.getLogger(HelloAkkaJavaOrig_saJPA.class.getName()).log(Level.SEVERE, "greška OnRecieve metod.", ex);
                } finally {
                    //getContext().system().shutdown();
                }
            } else {
                unhandled(message);
            }
        }
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DbServiceConfig.class);
        /*
        IClickService clickService = ac.getBean(IClickService.class);
        IUserService userService = ac.getBean(IUserService.class);
        Users u = userService.getUser(12);
         */

        IDBService dbService = ac.getBean(IDBService.class);
        Users u = dbService.getUser(12);

        final ActorSystem system = ActorSystem.create("system");
        final Inbox inbox = Inbox.create(system);
        final ActorRef master = system.actorOf(Props.create(new Creator<Master>() {
            @Override
            public Master create() throws Exception {
                return new Master(dbService);
            }
        }), "master");

        inbox.send(master, new StatMessage(7, "2015-1-1", "2015-1-31"));

        final Stat g1 = (Stat) inbox.receive(Duration.create(10, TimeUnit.MILLISECONDS));
        System.out.println("------------------------------------------------------------------");
        System.err.println("");
        System.out.println(g1.getClickList());

        /*
        inbox.send(master, new StatMessage(1, "2015-6-1", "2015-6-30"));
        final Stat g2 = (Stat) inbox.receive(Duration.create(10, TimeUnit.MILLISECONDS));
        System.out.println("------------------------------------------------------------------");
        System.err.println("");
        System.out.println(g2.getClickList());
         */
    }

}
