package at.dkepr.cinemaservice.cinema1.services;

import org.apache.jena.rdf.model.Model;

public interface Cinema1Service {
    Model getMoviesByDay(String URL, String day);
}
