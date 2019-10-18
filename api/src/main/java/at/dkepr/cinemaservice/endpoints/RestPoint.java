package at.dkepr.cinemaservice.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class RestPoint {

    @GetMapping
    public String test() {
        System.out.println("s123");

        return "Hallo Welt!";
    }
}
