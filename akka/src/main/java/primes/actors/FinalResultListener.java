package primes.actors;

import akka.actor.UntypedActor;
import java.util.concurrent.TimeUnit;
import primes.helpers.PrimeResult;
import primes.messages.Answer;
import scala.concurrent.duration.Duration;

public class FinalResultListener extends UntypedActor {

    private PrimeResult result;

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof PrimeResult) {
            result = (PrimeResult) message;

            result.getResult().stream().forEach(r -> {
                System.out.println(r);
            });

            getContext().system().shutdown();
        }

        if (message instanceof Answer) {
            try {
                Answer answer = (Answer) message;

                if (result == null) {
                    Duration.create(5, TimeUnit.SECONDS);
                }

                answer.setResults(result.getResult());
                getSender().tell(answer, getSelf());

            } catch (Exception e) {
            } finally {
                getContext().system().shutdown();
            }
        } else {
            unhandled(message);
        }
    }
}
