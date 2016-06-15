package akka;

import actors.HelloActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import messages.HelloMsg;

public class main {

    public static void main(String[] args) {
        ActorSystem actorSys = ActorSystem.create("mojSistem");
        ActorRef actorRef = actorSys.actorOf(new Props(HelloActor.class), "mojActor");

        actorRef.tell(new HelloMsg("prva prvcijata poruka !"), actorRef);

        actorSys.stop(actorRef);
        actorSys.shutdown();
    }
}
