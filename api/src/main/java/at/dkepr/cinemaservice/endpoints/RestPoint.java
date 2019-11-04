package at.dkepr.cinemaservice.endpoints;
import at.dkepr.cinemaservice.cinema1.FusekiServerTest;
import org.apache.jena.rdf.model.Model;
import org.springframework.web.bind.annotation.*;
import java.io.*;


@RestController
@RequestMapping("/test")
public class RestPoint {

    @GetMapping
    public String test() {
        return "Hallo Welt!";
    }

    @RequestMapping(value="/Movies/Mo", method=RequestMethod.GET, produces={"application/xml", "application/rdf+xml"})
    public @ResponseBody String getMondayMovies() throws IOException {
        Model model = FusekiServerTest.getMoviesByDay("http://localhost:3030/cinema1", "Mo");
        try(final ByteArrayOutputStream os = new ByteArrayOutputStream() ){
            model.write(os);
            return os.toString();
        }
    }


}
