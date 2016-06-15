package akka;

import ent.Click;
import ent.Users;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.config.DbServiceConfig;
import spring.services.IUserService;

public class testSpring {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DbServiceConfig.class);
        IUserService userService = ac.getBean(IUserService.class);

        //<editor-fold defaultstate="collapsed" desc="test1 - userService.getAllUsers()">
        Logger.getLogger("Test1").info("Svi kupci");
        System.err.println(userService.getAllUsers());
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="test2 - Sve posete kupca">
        Logger.getLogger("Test2").info("Sve posete korisnika ID=1 od \"2015-1-1\", \"2015-6-30\" ");
        try {
            for (Click click : userService.getStatistics(1, "2015-1-1", "2015-6-30")) {
                System.err.println(click);
            }
        } catch (ParseException ex) {
            Logger.getLogger(testSpring.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="test3 - Svi sajtovi korisnika">
        Users u = userService.getUser(7L);
        String us = u.getName() + " " + u.getSurname();
        String from = "2015-4-17";
        String to = "2015-11-13";

        Logger.getLogger("Test3")
                .info("Svi sajtovi koje je posetio: " + us + ", od : " + from + "-" + to);
        try {
            List<String> sajtovi = userService.getStatistics(7, from, to).stream()
                    .map(Click::getIpaddress).distinct().collect(Collectors.toList());

            for (String s : sajtovi) {
                System.out.println(s);
            }

        } catch (ParseException ex) {
            Logger.getLogger(testSpring.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
    }
}
