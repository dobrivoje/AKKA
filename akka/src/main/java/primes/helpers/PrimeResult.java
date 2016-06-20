package primes.helpers;

import java.util.Set;
import java.util.TreeSet;

public class PrimeResult {

    private final Set<Long> results = new TreeSet<>();

    public Set<Long> getResult() {
        return results;
    }

}
