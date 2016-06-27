package spring.akkaHW;

import java.util.concurrent.TimeUnit;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.akkaHW.config.AppConfiguration;
import spring.akkaHW.AkkA.messages.NewStat;
import java.util.Date;
import java.util.logging.Logger;
import spring.akkaHW.AkkA.helpers.Stat;
import spring.akkaHW.AkkA.messages.GetStat;
import spring.akkaHW.AkkA.messages.NewClick;
import spring.akkaHW.services.IClickStat;

public class Main2 {

    private static final ApplicationContext CTX = new AnnotationConfigApplicationContext(AppConfiguration.class);
    private static final IClickStat STAT = CTX.getBean(IClickStat.class);

    public static void main(String[] args) throws Exception {

        System.out.println("************************************************");
        Logger.getLogger("test").info("Test 2 : Insert i Statistika");
        Logger.getLogger("test").info("Test 2 : sve je u spring servisima !");
        System.out.println("");
        System.out.println("************************************************");

        STAT.newMessage(new NewClick(2, new Date(), "http://dobrivoje.github.io"));
        STAT.newMessage(new NewStat(2, "2016-6-26", "2016-6-30"));

        Stat st = STAT.statResults(new GetStat(), 11, TimeUnit.SECONDS);

        st.getClickList().stream().forEach((s) -> {
            System.out.println(s);
        });

    }
}
