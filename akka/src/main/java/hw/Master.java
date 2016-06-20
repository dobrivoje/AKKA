package hw;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class Master extends UntypedActor {

    private final ActorRef resultActor;

    private final Result finalResults = new Result();

    public Master(ActorRef resultActor) {
        this.resultActor = resultActor;
    }

    @Override
    public void onReceive(Object message) {
        if (message instanceof ClickMessage) {

            ClickMessage cm = (ClickMessage) message;

        } else if (message instanceof Result) {
            Result result = (Result) message;
            finalResults.getResults().addAll(result.getResults());

            resultActor.tell(finalResults, getSelf());
            getContext().stop(getSelf());

        } else {
            unhandled(message);
        }
    }
}
