package hw;

import akka.actor.UntypedActor;

public class ResultActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Result) {
            Result finalResult = (Result) message;

            /*
            finalResult.getResults().stream().forEach((pn) -> {
            System.err.println(pn);
            });
             */
            getContext().system().shutdown();
        } else {
            unhandled(message);
        }
    }

}
