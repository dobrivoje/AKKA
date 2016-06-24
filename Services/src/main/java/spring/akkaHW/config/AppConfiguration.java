package spring.akkaHW.config;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import spring.akkaHW.AkkA.infra.SpringExtension;
import static spring.akkaHW.AkkA.infra.SpringExtension.SpringExtProvider;

@Configuration
@Transactional
@ComponentScan(basePackages = {"spring.akkaHW", "spring.jpa.config"})
public class AppConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem system = ActorSystem.create("akkaJavaSpring");
        SpringExtProvider.get(system).initialize(applicationContext);

        return system;
    }

    @Bean
    public ActorRef getActorRef_JPA_DB(ActorSystem system) {
        return system.actorOf(SpringExtension.SpringExtProvider
                .get(system).props("DBActor"), "dbactor");
    }

}
