package at.dkepr.cinemaservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class CinemaserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaserviceApplication.class, args);

	}


}
