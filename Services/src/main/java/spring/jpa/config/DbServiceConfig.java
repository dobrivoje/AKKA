/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.jpa.config;

import javax.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import spring.jpa.beans.ClickServiceBean;
import spring.jpa.beans.UserServiceBean;
import spring.jpa.services.IClickService;
import spring.jpa.services.IUserService;

@Configuration
@EnableTransactionManagement
@ComponentScan
public class DbServiceConfig {

    @Bean
    IUserService getUserService() {
        return new UserServiceBean();
    }

    @Bean
    IClickService getClickService(IUserService userService) {
        return new ClickServiceBean(userService);
    }

    //<editor-fold defaultstate="collapsed" desc="infra">
    @Bean
    public LocalEntityManagerFactoryBean emfBean() {
        LocalEntityManagerFactoryBean e = new LocalEntityManagerFactoryBean();
        e.setPersistenceUnitName("com.cbs.homeworks.dobri_DB_PU");

        return e;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory em) {
        return new JpaTransactionManager(em);
    }
    //</editor-fold>

}
