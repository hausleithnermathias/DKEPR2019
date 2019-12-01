package at.dkepr.cinemaservice.cinema2.services;

import org.apache.jena.rdf.model.Model;

public interface Cinema2Service {
    Model getMoviesByDay(String URL, String day);
    Model getAllMovies(String URL);
    Model getMenusPerMovie(String URL);
    Model getReservationName(String URL, String name);
    Model getMoviesPerGenre(String URL, String genre);
}
