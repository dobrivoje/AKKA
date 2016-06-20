package primes.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinPool;
import primes.messages.NumberRangeMessage;
import primes.helpers.PrimeResult;

public class MasterActor extends UntypedActor {

    private final ActorRef workerRouter;
    private final ActorRef listener;

    private final int noOfWorkers;
    private int noOfResults = 0;

    private final PrimeResult finalResults = new PrimeResult();

    public MasterActor(int noOfWorkers, ActorRef listener) {
        this.noOfWorkers = noOfWorkers;
        this.listener = listener;

        this.workerRouter = this.getContext()
                .actorOf(Props.create(WorkerActor.class)
                        .withRouter(new RoundRobinPool(noOfWorkers)), "workerRouter");
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof NumberRangeMessage) {
            NumberRangeMessage nrm = (NumberRangeMessage) message;

            long segment = nrm.getInterval() / noOfWorkers;

            for (int i = 0; i < noOfWorkers; i++) {
                long startWorkerNumber = nrm.getLowerNo() + i * segment;
                long endWorkerNumber = (i != noOfWorkers - 1 ? startWorkerNumber + segment - 1 : nrm.getHigherNo());

                workerRouter.tell(new NumberRangeMessage(startWorkerNumber, endWorkerNumber), getSelf());
            }
        } else if (message instanceof PrimeResult) {
            PrimeResult r = (PrimeResult) message;
            finalResults.getResult().addAll(r.getResult());

            if (++noOfResults == noOfWorkers) {
                this.listener.tell(finalResults, getSelf());

                getContext().stop(getSelf());
            }
        } else {
            unhandled(message);
        }
    }

}
