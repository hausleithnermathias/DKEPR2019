package at.dkepr.cinemaservice.cinema1.api;

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

    @RequestMapping(value="/Movies/All", method= RequestMethod.GET, produces={"application/xml", "application/rdf+xml"})
    public @ResponseBody String getAllMovies() throws IOException {
        Model model = cinema1Service.getAllMovies("http://localhost:3030/cinema1");
        try(final ByteArrayOutputStream os = new ByteArrayOutputStream() ){
            model.write(os);
            return os.toString();
        }
    }

    @RequestMapping(value="/Reservations/All", method= RequestMethod.GET, produces={"application/xml", "application/rdf+xml"})
    public @ResponseBody String getAllReservations() throws IOException {
        Model model = cinema1Service.getAllReservations("http://localhost:3030/cinema1");
        try(final ByteArrayOutputStream os = new ByteArrayOutputStream() ){
            model.write(os);
            return os.toString();
        }
    }

    @RequestMapping(value="/Reservations/{movie}", method= RequestMethod.GET, produces={"application/xml", "application/rdf+xml"})
    public @ResponseBody String getReservationsByMovie(@PathVariable String movie) throws IOException {
        Model model = cinema1Service.getReservationsByMovie("http://localhost:3030/cinema1", movie);
        try(final ByteArrayOutputStream os = new ByteArrayOutputStream() ){
            model.write(os);
            return os.toString();
        }
    }


}
