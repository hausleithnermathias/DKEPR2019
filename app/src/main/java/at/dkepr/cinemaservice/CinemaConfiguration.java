package at.dkepr.cinemaservice;

import at.dkepr.cinemaservice.cinema1.FusekiServerTest;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Configuration
public class CinemaConfiguration implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent applicationReadyEvent) {
        System.out.println("Hello!");

        /*
        try {
            FusekiServerTest.start();
        }
        catch (Exception c) {
            c.printStackTrace();
        }

         */

    }
}
