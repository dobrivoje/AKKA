
import ent.Click;
import ent.Users;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.jpa.config.DbServiceConfig;
import spring.jpa.services.IClickService;
import spring.jpa.services.IUserService;

public class testSpring {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DbServiceConfig.class);
        IUserService userService = ac.getBean(IUserService.class);
        IClickService clickService = ac.getBean(IClickService.class);

        Users u = userService.getUser(7L);
        String us = u.getName() + " " + u.getSurname();
        String from = "2015-4-17";
        String to = "2015-11-13";

        //<editor-fold defaultstate="collapsed" desc="test1 - userService.getAllUsers()">
        Logger.getLogger("Test1").info("Svi kupci");
        System.err.println(userService.getAllUsers());
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="test2 - Sve posete kupca">
        Logger.getLogger("Test2").info("Sve posete korisnika ID=" + u.getIdu()
                + " od :" + from + " - " + to);
        try {
            for (Click click : clickService.getStatistics(u.getIdu(), from, to)) {
                System.err.println(click);
            }
        } catch (ParseException ex) {
            Logger.getLogger(testSpring.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="test3 - Svi sajtovi korisnika">
        Logger.getLogger("Test3")
                .info("Svi sajtovi koje je posetio: " + us + ", od : " + from + "-" + to);
        try {
            List<String> sajtovi = clickService.getStatistics(7, from, to).stream()
                    .map(Click::getIpaddress).distinct().collect(Collectors.toList());

            System.err.println();
            System.err.println("------------TEST1------------");
            System.err.println("All user's clicks over time : " + sajtovi.size());
            for (String s : sajtovi) {
                System.out.println(s);
            }

            List<String> allUsersSites = u.getClickList().stream().map(Click::getIpaddress).collect(Collectors.toList());
            System.err.println("");
            System.err.println("------------TEST2------------");
            System.err.println("All user's clicks no: " + allUsersSites.size());
            for (String s2 : allUsersSites) {
                System.out.println(s2);
            }

        } catch (ParseException ex) {
            Logger.getLogger(testSpring.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }
    
}
