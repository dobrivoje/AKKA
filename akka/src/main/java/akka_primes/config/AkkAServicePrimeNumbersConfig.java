/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akka_primes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import akka_primes.beans.AkkAServicePrimeNumbersBeanImpl;
import akka_primes.services.IAkkAPrimeNumbersService;
import akka_primes.myAnnotations.AkkA_Primes;

@Configuration
@ComponentScan
public class AkkAServicePrimeNumbersConfig {

    @Bean
    @AkkA_Primes
    public IAkkAPrimeNumbersService getAkkAService() {
        return new AkkAServicePrimeNumbersBeanImpl();
    }

    /*
    @Bean
    @AkkA_Primes
    public IAkkAPrimeNumbersService getAkkAService(final int noOfWorkers) {
    return new AkkAServicePrimeNumbersBeanImpl(noOfWorkers);
    }
     */
}
