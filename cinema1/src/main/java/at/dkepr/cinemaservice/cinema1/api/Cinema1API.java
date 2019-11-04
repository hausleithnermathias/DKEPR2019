package at.dkepr.cinemaservice.cinema1.api;

import at.dkepr.cinemaservice.cinema1.FusekiServerTest;
import at.dkepr.cinemaservice.cinema1.services.Cinema1Service;
import org.apache.jena.rdf.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/Cinema1")
public class Cinema1API {

    @Autowired
    Cinema1Service cinema1Service;

    @RequestMapping(value="/Movies/{day}", method= RequestMethod.GET, produces={"application/xml", "application/rdf+xml"})
    public @ResponseBody String getMoviesByDay(@PathVariable String day) throws IOException {
        Model model = cinema1Service.getMoviesByDay("http://localhost:3030/cinema1", day);
        try(final ByteArrayOutputStream os = new ByteArrayOutputStream() ){
            model.write(os);
            return os.toString();
        }
    }
}
