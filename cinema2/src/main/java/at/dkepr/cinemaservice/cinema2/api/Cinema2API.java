package at.dkepr.cinemaservice.cinema2.api;

import at.dkepr.cinemaservice.cinema2.services.Cinema2Service;
import org.apache.jena.rdf.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@ApiIgnore
@RestController
@RequestMapping("/Cinema2")
public class Cinema2API {

    @Autowired
    Cinema2Service cinema2Service;

    @RequestMapping(value="/Movies/{day}", method= RequestMethod.GET, produces={"application/xml", "application/rdf+xml"})
    public @ResponseBody String getMoviesByDay(@PathVariable String day) throws IOException {
        Model model = cinema2Service.getMoviesByDay("http://localhost:3030/cinema2", day);
        try(final ByteArrayOutputStream os = new ByteArrayOutputStream() ){
            model.write(os);
            return os.toString();
        }
    }

    @RequestMapping(value="/Movies/All", method= RequestMethod.GET, produces={"application/xml", "application/rdf+xml"})
    public @ResponseBody String getAllMovies() throws IOException {
        Model model = cinema2Service.getAllMovies("http://localhost:3030/cinema2");
        try(final ByteArrayOutputStream os = new ByteArrayOutputStream() ){
            model.write(os);
            return os.toString();
        }
    }

    @RequestMapping(value="/Movies/Reservations/{name}", method= RequestMethod.GET, produces={"application/xml", "application/rdf+xml"})
    public @ResponseBody String getReservationName(@PathVariable String name) throws IOException {
        Model model = cinema2Service.getReservationName("http://localhost:3030/cinema2", name);
        try(final ByteArrayOutputStream os = new ByteArrayOutputStream() ){
            model.write(os);
            return os.toString();
        }
    }

    @RequestMapping(value="/Movies/Genre/{genre}", method= RequestMethod.GET, produces={"application/xml", "application/rdf+xml"})
    public @ResponseBody String getMoviesPerGenre(@PathVariable String genre) throws IOException {
        Model model = cinema2Service.getMoviesPerGenre("http://localhost:3030/cinema2", genre);
        try(final ByteArrayOutputStream os = new ByteArrayOutputStream() ){
            model.write(os);
            return os.toString();
        }
    }

    @RequestMapping(value="/Movies/Menus", method= RequestMethod.GET, produces={"application/xml", "application/rdf+xml"})
    public @ResponseBody String getMenus() throws IOException {
        Model model = cinema2Service.getMenus("http://localhost:3030/cinema2");
        try(final ByteArrayOutputStream os = new ByteArrayOutputStream() ){
            model.write(os);
            return os.toString();
        }
    }
}
