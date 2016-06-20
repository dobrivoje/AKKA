package primes.actors;

import akka.actor.UntypedActor;
import primes.messages.NumberRangeMessage;
import primes.helpers.PrimeResult;

public class WorkerActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof NumberRangeMessage) {
            NumberRangeMessage nrm = (NumberRangeMessage) message;
            PrimeResult result = new PrimeResult();

            for (long n = nrm.getLowerNo(); n < nrm.getHigherNo(); n++) {
                if (isPrime(n)) {
                    result.getResult().add(n);
                }
            }

            getSender().tell(result, getSelf());
        }
    }

    private boolean isPrime(long number) {
        if (number > 1 && number < 4) {
            return true;
        }

        if (number % 2 == 0) {
            return false;
        }

        for (long i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

}
