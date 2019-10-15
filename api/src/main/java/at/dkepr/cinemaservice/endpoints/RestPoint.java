package at.dkepr.cinemaservice.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RestPoint {

    @GetMapping
    public String test() {
        return "Hallo Welt!";
    }
}
