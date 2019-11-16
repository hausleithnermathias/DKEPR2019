package at.dkepr.cinemaservice.externalAPI;

import at.dkepr.cinemaservice.domain.Movie;
import at.dkepr.cinemaservice.domain.WeekDayEnum;
import org.apache.commons.compress.utils.Lists;
import org.apache.jena.base.Sys;
import org.apache.jena.rdf.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/test")
public class MetaServiceAPI {

    @Autowired
    ResourceLoader resourceLoader;

    @GetMapping
    public String test() {
        return "Hallo Welt!";
    }

    @RequestMapping(value = "/Movies/{day}", method = RequestMethod.GET)
    public @ResponseBody List<Movie> getMoviesByDay(@PathVariable String day) throws IOException {

        Model model = ModelFactory.createDefaultModel();
        model.read("http://localhost:8080/Cinema1/Movies/" + day);

        List<Movie> movieList = Lists.newArrayList();
        Movie movie = new Movie();
        // list the statements in the Model
        StmtIterator iter = model.listStatements();
        org.apache.jena.rdf.model.Resource subject = null;
        Property predicate;
        RDFNode object;
        // print out the predicate, subject and object of each statement
        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();  // get next statement
            if(subject == null){
                subject = stmt.getSubject();
            }
            System.out.println(subject.getLocalName() + "  " + stmt.getSubject().getLocalName());
            if(!subject.getLocalName().equals(stmt.getSubject().getLocalName())){
                movieList.add(movie);
                movie = new Movie();
            }
            subject = stmt.getSubject(); // get the subject
            predicate = stmt.getPredicate();   // get the predicate
            object = stmt.getObject();// get the object
            String[] properties;

            switch(predicate.getLocalName()){
                case "actors":
                    properties = object.toString().split(", ");
                    for(int i = 0; i<properties.length; i++){
                        movie.getActors().add(properties[i]);
                    }
                    break;
                case "country":
                    movie.setCountry(object.toString());
                    break;
                case "genre":
                    movie.setGenre(object.toString());
                    break;
                case "duration":
                    movie.setMovieLength(object.toString());
                    break;
                case "production":
                    properties = object.toString().split(", ");
                    for(int i = 0; i<properties.length; i++){
                        movie.getProducer().add(properties[i]);
                    }
                    break;
                case "language":
                    movie.setLanguage(object.toString());
                    break;
                case "reservations":
                    properties = object.toString().split(";");
                    for(int i = 0; i<properties.length; i++){
                        String[] subProperties = properties[i].split(":");
                        movie.getReservations().get(WeekDayEnum.valueOf(subProperties[1])).add(subProperties[0]);
                    }
                    break;
                case "days":
                    properties = object.toString().split(";");
                    for(int i = 0; i<properties.length; i++){
                        movie.getDaysPlayed().add(WeekDayEnum.valueOf(properties[i]));
                    }
                    break;
                case "agerating":
                    movie.setAgeRating(object.toString());
                    break;
                case "title":
                    movie.setTitle(object.toString());
                    break;
            }
        }
        movieList.add(movie);
        return movieList;
    }


}
