package spring.akka_lightbend_HW.config;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import spring.akka_lightbend_HW.AkkA.infra.SpringExtension;
import static spring.akka_lightbend_HW.AkkA.infra.SpringExtension.SpringExtProvider;
import spring.akka_lightbend_HW.beans.DBBeanImpl;
import spring.jpa.services.IDBService;
import spring.akka_lightbend_HW.services.IDBAkkA_Service;

@Configuration
@Transactional
@ComponentScan(basePackages = {"spring.akka_lightbend_HW", "spring.jpa.config"})
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

    @Bean
    public IDBAkkA_Service getHWService(IDBService dbService) {
        return new DBBeanImpl(dbService);
    }

}
