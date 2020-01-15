package at.dkepr.cinemaservice.core;

import at.dkepr.cinemaservice.domain.Cinema;
import at.dkepr.cinemaservice.domain.Movie;
import at.dkepr.cinemaservice.domain.RegisteredCinemas;
import at.dkepr.cinemaservice.domain.WeekDayEnum;
import org.apache.commons.compress.utils.Lists;
import org.apache.jena.rdf.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaServiceImpl {

    @Autowired
    Environment environment;

    public List<Movie> getMoviesCinema1(Model model){
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

    public List<Movie> getMoviesCinema2(Model model){
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
            if(!subject.getLocalName().equals(stmt.getSubject().getLocalName())){
                movieList.add(movie);
                movie = new Movie();
            }
            subject = stmt.getSubject(); // get the subject
            predicate = stmt.getPredicate();   // get the predicate
            object = stmt.getObject();// get the object
            String[] properties;

            switch(predicate.getLocalName()){
                case "title":
                    movie.setTitle(object.toString());
                    break;
                case "actors":
                    properties = object.toString().split(", ");
                    for(int i = 0; i<properties.length; i++){
                        movie.getActors().add(properties[i]);
                    }
                    break;

                case "genre":
                    movie.setGenre(object.toString());
                    break;
                case "duration":
                    movie.setMovieLength(object.toString());
                    break;
                case "language":
                    movie.setLanguage(object.toString());
                    break;
                case "reservations":
                    properties = object.toString().split(";");
                    for(int i = 0; i<properties.length; i++){
                        String[] subProperties = properties[i].split(":");
                        movie.getReservations().get(WeekDayEnum.valueOf(subProperties[1].trim())).add(subProperties[0].trim());
                    }
                    break;
                case "menu" :
                    properties = object.toString().split(",");
                    for(int i = 0; i<properties.length; i++){
                        movie.getMenu().add(properties[i].trim());
                    }
                    break;
                case "FSK":
                    movie.setAgeRating(object.toString());
                    break;
                case "days":
                    properties = object.toString().split(";");
                    for(int i = 0; i<properties.length; i++){
                        movie.getDaysPlayed().add(WeekDayEnum.valueOf(properties[i].trim()));
                    }
                    break;
            }
        }
        movieList.add(movie);
        return movieList;
    }

    public List<Movie> getMoviesByDay(String cinema, String day){

        Model model = ModelFactory.createDefaultModel();
        String url = environment.getProperty(cinema.toLowerCase() + ".url");
        model.read(url + "/Movies/" + day);

        switch(RegisteredCinemas.valueOf(cinema.toUpperCase())) {
            case STARMOVIE:
                return getMoviesCinema1(model);
            case MEGAPLEX:
                return getMoviesCinema2(model);
            case GEIL:
                return getMoviesCinema1(model);
        }
        return null;
    }

    public List<Movie> getAllMovies(String cinema){

        Model model = ModelFactory.createDefaultModel();
        String url = environment.getProperty(cinema.toLowerCase() + ".url");
        model.read(url + "/Movies/All");

        switch(RegisteredCinemas.valueOf(cinema.toUpperCase())) {
            case STARMOVIE:
                return getMoviesCinema1(model);
            case MEGAPLEX:
                return getMoviesCinema2(model);
            case GEIL:
                return getMoviesCinema1(model);
        }
        return null;
    }

    public List<Cinema> getAllCinemas(){

        List<Cinema> cinemas = Lists.newArrayList();

        for(RegisteredCinemas registeredCinema: RegisteredCinemas.values()){
            Cinema cinema = new Cinema();
            cinema.setName(registeredCinema.value);
            cinema.setMovies(getAllMovies(registeredCinema.value));
            cinemas.add(cinema);
        }
        return cinemas;
    }
}
