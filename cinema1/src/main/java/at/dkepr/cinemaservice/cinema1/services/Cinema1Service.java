package at.dkepr.cinemaservice.cinema1.services;

import org.apache.jena.rdf.model.Model;

public interface Cinema1Service {
    Model getMoviesByDay(String URL, String day);
    Model getAllMovies(String URL);
    Model getAllReservations(String URL);
    Model getReservationsByMovie(String URL, String movie);
    void removeReservationFromMovie(String movieName, String reservation);
    void addReservationToMovie(String movieName, String reservation);
}
