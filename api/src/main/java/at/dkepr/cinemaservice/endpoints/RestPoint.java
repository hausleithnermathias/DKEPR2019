package at.dkepr.cinemaservice.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class RestPoint {

    @GetMapping
    public String test() {
        return "Hallo Welt!";
    }

    @GetMapping("/allMovies")
    public List<Integer> getAllMovies(){

        List<Integer> intlist = new ArrayList<>();

        intlist.add(1);
        intlist.add(2);
        intlist.add(3);

        return intlist;
    }


}
