package at.dkepr.cinemaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CinemaserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaserviceApplication.class,args);
	}

	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}

}
