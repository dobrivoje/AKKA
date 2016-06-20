package akka_spring;

import org.springframework.stereotype.Component;

@Component("cntService")
public class CountingService implements ICountingService {

    @Override
    public int increment(int cnt) {
        return 1 + cnt;
    }
}
