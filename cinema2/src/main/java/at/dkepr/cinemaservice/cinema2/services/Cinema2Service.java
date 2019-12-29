package at.dkepr.cinemaservice.cinema2.services;

import org.apache.jena.rdf.model.Model;

public interface Cinema2Service {
    Model getMoviesByDay(String URL, String day);
    Model getAllMovies(String URL);
    Model getMenus(String URL);
    Model getReservationName(String URL, String name);
    Model getMoviesPerGenre(String URL, String genre);
    void addReservationToMovie(String movieName, String reservation);
    void addMenuToMovie(String movieName, String menu);
    void removeMenuFromMovie(String movieName, String menu);
    void removeReservationFromMovie(String movieName, String reservation);
}
