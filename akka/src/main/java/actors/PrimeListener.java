package actors;

import akka.actor.UntypedActor;
import output.Result;

public class PrimeListener extends UntypedActor {
    
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Result) {
            Result result = (Result) message;
            
            System.out.println("Prime numbers : ");
            System.out.println("");
            
            for (long r : result.getResults()) {
                System.out.println(r);
            }
            
            getContext().system().shutdown();
        } else {
            unhandled(message);
        }
    }
    
}
