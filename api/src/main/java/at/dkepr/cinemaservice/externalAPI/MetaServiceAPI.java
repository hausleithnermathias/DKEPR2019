package at.dkepr.cinemaservice.externalAPI;

import at.dkepr.cinemaservice.core.MetaServiceImpl;
import at.dkepr.cinemaservice.domain.Cinema;
import at.dkepr.cinemaservice.domain.Movie;
import at.dkepr.cinemaservice.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class MetaServiceAPI {

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    MetaServiceImpl metaService;

    @GetMapping
    public String test() {
        return "Hallo Welt!";
    }

    @RequestMapping(value = "/movies/{cinema}/{day}", method = RequestMethod.GET)
    public @ResponseBody List<Movie> getMoviesByDay(@PathVariable String cinema ,@PathVariable String day) throws IOException {
       return metaService.getMoviesByDay(cinema, day);
    }

    @RequestMapping(value = "/movies/{cinema}/all", method = RequestMethod.GET)
    public @ResponseBody List<Movie> getAllMovies(@PathVariable String cinema) throws IOException {
        return metaService.getAllMovies(cinema);
    }

    @RequestMapping(value = "/cinemas/all", method = RequestMethod.GET)
    public @ResponseBody List<Cinema> getAllCinemas() throws IOException {
        return metaService.getAllCinemas();
    }

    @RequestMapping(value="/Remove/Reservation/{cinema}/{movie}", consumes= "application/json", method= RequestMethod.POST)
    public void removeReservation(@PathVariable String cinema, @PathVariable String movie, @RequestBody Reservation reservation) throws IOException {
        try {
            metaService.removeReservation(cinema, movie, reservation);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/Create/Reservation/{cinema}/{movie}", consumes= "application/json", method= RequestMethod.POST)
    public void createReservation(@PathVariable String cinema, @PathVariable String movie, @RequestBody Reservation reservation) throws IOException {
        try {
            metaService.createReservation(cinema, movie, reservation);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}
