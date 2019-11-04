package at.dkepr.cinemaservice.init;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CinemaConfiguration {

    @PostConstruct
    public void test() {
        System.out.println("asdf");

        System.out.println("ddsf");
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
