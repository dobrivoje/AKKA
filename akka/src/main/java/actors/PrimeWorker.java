package actors;

import akka.actor.UntypedActor;
import messages.NumberRangeMessage;
import output.Result;

public class PrimeWorker extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof NumberRangeMessage) {
            NumberRangeMessage nrm = (NumberRangeMessage) message;
            Result r = new Result();

            for (long number = nrm.getStartNumber(); number < nrm.getEndNumber(); number++) {
                if (isPrime(number)) {
                    r.getResults().add(number);
                }
            }

            getSender().tell(r, getSelf());
        } else {
            unhandled(message);
        }
    }

    private boolean isPrime(long number) {
        if (number > 1 && number < 4) {
            return true;
        }

        if (number % 2 == 0) {
            return false;
        }

        for (int i = 5; i < (int) number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

}
