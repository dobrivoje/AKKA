package akka_primes.services;

public interface IAkkAPrimeNumbersService {

    void calculatePrimeNumbers(long startNumber, long endNumber);

    void printNoOfWorkers();

}
