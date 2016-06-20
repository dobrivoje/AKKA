package lightbend.actors;

import akka.actor.UntypedActor;
import lightbend.messages.GiveMeCurrentGreetMessage;
import lightbend.messages.GreetingMessage;

public class Greeter extends UntypedActor {

    private String greetingMessage = "";

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof GreetingMessage) {
            greetingMessage = "Zdravo " + ((GreetingMessage) message).getMessage();
        } else if (message instanceof GiveMeCurrentGreetMessage) {
            getSender().tell(new GreetingMessage(greetingMessage), getSelf());
        } else {
            unhandled(message);
        }
    }

}
