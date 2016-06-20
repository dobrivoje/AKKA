package primes.messages;

import java.util.Set;
import java.util.TreeSet;

public class Answer {

    public final String PORUKA = "ЈЖ";

    private Set<Long> results = new TreeSet<>();

    public Set<Long> getResult() {
        return results;
    }

    public void setResults(Set<Long> results) {
        this.results = results;
    }

}
