package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;
import messages.NumberRangeMessage;
import output.Result;

public class PrimeMaster extends UntypedActor {

    private final ActorRef workerRouter;
    private final ActorRef listener;

    private final int noOfWorkers;

    private long numberOfNumbers;
    private long segment;
    private int returnedResults = 0;

    private final Result finalResult = new Result();

    public PrimeMaster(int noOfWorkers, ActorRef listener) {
        this.listener = listener;
        this.noOfWorkers = noOfWorkers;

        this.workerRouter = this.getContext().actorOf(
                new Props(PrimeWorker.class)
                .withRouter(new RoundRobinRouter(noOfWorkers)), "workerRouter");
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof NumberRangeMessage) {

            NumberRangeMessage nrm = (NumberRangeMessage) message;
            numberOfNumbers = nrm.getEndNumber() - nrm.getStartNumber();
            segment = numberOfNumbers / noOfWorkers;

            for (int workIndex = 0; workIndex < this.noOfWorkers; workIndex++) {
                long workerStartNo = nrm.getStartNumber() + workIndex * segment;
                long workerEndNo = nrm.getStartNumber() + segment - 1;

                workerRouter.tell(new NumberRangeMessage(workerStartNo, workerEndNo), getSelf());
            }
        } else if (message instanceof Result) {
            Result receivedResult = (Result) message;
            finalResult.getResults().addAll(receivedResult.getResults());

            if (++returnedResults > noOfWorkers) {
                listener.tell(finalResult, getSelf());
                getContext().stop(getSelf());
            }

        } else {
            unhandled(message);
        }
    }

}
