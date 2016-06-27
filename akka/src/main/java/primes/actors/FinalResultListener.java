package primes.actors;

import akka.actor.UntypedActor;
import primes.helpers.PrimeResult;
import primes.messages.Answer;

public class FinalResultListener extends UntypedActor {

    private PrimeResult result;

    @Override
    public void onReceive(Object message) {
        if (message instanceof PrimeResult) {
            result = (PrimeResult) message;
        } else if (message instanceof Answer) {
            getSender().tell(result, getSelf());
            getContext().system().shutdown();
        } else {
            unhandled(message);
        }
    }
}
