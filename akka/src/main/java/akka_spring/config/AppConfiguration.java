package akka_spring.config;

import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import akka_spring.CountingService;
import akka_spring.ICountingService;
import static akka_spring.infra.SpringExtension.SpringExtProvider;

@Configuration
public class AppConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ICountingService getCountingService() {
        return new CountingService();
    }

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem system = ActorSystem.create("akkaJavaSpring");
        SpringExtProvider.get(system).initialize(applicationContext);

        return system;
    }
}
