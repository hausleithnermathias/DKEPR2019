package at.dkepr.cinemaservice.externalAPI;
import at.dkepr.cinemaservice.cinema1.FusekiServerTest;
import at.dkepr.cinemaservice.internalAPI.InternalAPI;
import org.apache.jena.rdf.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.*;


@RestController
@RequestMapping("/test")
public class ExternalAPI {

    @Autowired
    InternalAPI internalAPI;

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

    @RequestMapping(value="/Movies/{day}", method=RequestMethod.GET, produces={"application/xml", "application/rdf+xml"})
    public @ResponseBody String getMoviesByDay(@PathVariable String day) throws IOException {
        return "adsf";
    }


}
