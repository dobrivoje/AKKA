package spring.akkaHW.services;

import java.util.concurrent.TimeUnit;
import spring.akkaHW.AkkA.helpers.Stat;

public interface IClickStat {

    void newMessage(Object message);

    Stat statResults(Object message, long timeToWait, TimeUnit timeUnit) throws Exception;

}
