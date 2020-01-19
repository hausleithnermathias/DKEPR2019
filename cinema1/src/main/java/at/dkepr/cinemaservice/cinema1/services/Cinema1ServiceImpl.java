package at.dkepr.cinemaservice.cinema1.services;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.stereotype.Service;

@Service
public class Cinema1ServiceImpl implements Cinema1Service{

    @Override
    public Model getMoviesByDay(String serviceURI, String day) {
        Model model=null;
        Property property;
        QuerySolution sol;
        org.apache.jena.rdf.model.Resource resource = null;
        String recourceOld = "";
        String recourceNew = "";


        String service = serviceURI;
        String query = "PREFIX dt: <http://www.cinemas.fake/starmovie/movies/dt#/>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT * WHERE {?m ?x ?y.{SELECT ?m WHERE {?m <http://www.cinemas.fake/starmovie/movies/dt#days> ?y. FILTER(CONTAINS(?y,'" + day + "'))}}}";

        try (QueryExecution qe = QueryExecutionFactory.sparqlService(service, query)) {

            model = ModelFactory.createDefaultModel();
            ResultSet results = qe.execSelect();
            while (results.hasNext()) {
                sol = results.nextSolution();
                recourceNew = sol.getResource("m").toString();
                if(recourceOld.compareTo(recourceNew) != 0){
                    resource=model.createResource(sol.getResource("m").toString());
                }
                recourceOld = recourceNew;
                property = ResourceFactory.createProperty(sol.get("x").toString());
                resource.addProperty(property, sol.getLiteral("y"));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }

    @Override
    public Model getAllMovies(String serviceURI) {
        DatasetAccessor accessor = DatasetAccessorFactory.createHTTP(serviceURI);
        Model m = accessor.getModel();
        return m;
    }


    @Override
    public Model getAllReservations(String serviceURI) {
        Model model=null;
        Property property;
        QuerySolution sol;
        org.apache.jena.rdf.model.Resource resource = null;
        String recourceOld = "";
        String recourceNew = "";


        String service = serviceURI;
        String query = "PREFIX dt: <http://www.cinemas.fake/starmovie/movies/dt#/>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT * WHERE {?m ?x ?y.{SELECT ?y WHERE {?m <http://www.cinemas.fake/starmovie/movies/dt#reservations> ?y.}}}";

        try (QueryExecution qe = QueryExecutionFactory.sparqlService(service, query)) {

            model = ModelFactory.createDefaultModel();
            ResultSet results = qe.execSelect();
            while (results.hasNext()) {
                sol = results.nextSolution();
                recourceNew = sol.getResource("m").toString();
                if(recourceOld.compareTo(recourceNew) != 0){
                    resource=model.createResource(sol.getResource("m").toString());
                }
                recourceOld = recourceNew;
                property = ResourceFactory.createProperty(sol.get("x").toString());
                resource.addProperty(property, sol.getLiteral("y"));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }

    @Override
    public Model getReservationsByMovie(String serviceURI, String movie) {
        Model model=null;
        Property property;
        QuerySolution sol;
        org.apache.jena.rdf.model.Resource resource = null;
        String recourceOld = "";
        String recourceNew = "";


        String service = serviceURI;
        String query = "PREFIX dt: <http://www.cinemas.fake/starmovie/movies/dt#/>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT * WHERE {?m ?x ?y.{SELECT ?y WHERE {<http://www.cinemas.fake/starmovie/movies/" + movie + "> <http://www.cinemas.fake/starmovie/movies/dt#reservations> ?y.}}}";

        try (QueryExecution qe = QueryExecutionFactory.sparqlService(service, query)) {

            model = ModelFactory.createDefaultModel();
            ResultSet results = qe.execSelect();
            while (results.hasNext()) {
                sol = results.nextSolution();
                recourceNew = sol.getResource("m").toString();
                if(recourceOld.compareTo(recourceNew) != 0){
                    resource=model.createResource(sol.getResource("m").toString());
                }
                recourceOld = recourceNew;
                property = ResourceFactory.createProperty(sol.get("x").toString());
                resource.addProperty(property, sol.getLiteral("y"));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }


    @Override
    public void removeReservationFromMovie(String movieName, String reservation) {
        updateTables(movieName, "reservations", reservation,false);
    }

    private void updateTables(String movieName, String type, String text, boolean add) {
        String query ="PREFIX dt: <http://www.cinemas.fake/starmovie/movies/dt#/>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +  "SELECT * WHERE {?m ?x ?y.{SELECT ?m WHERE {?m <http://www.cinemas.fake/starmovie/movies/dt#title> ?y. FILTER(CONTAINS(?y,'" + movieName + "'))}}}";
        String service = "http://localhost:3030/cinema1";
        QuerySolution sol = null;
        Resource res = null;
        String property = "";
        String object = "";
        UpdateRequest update = null;
        UpdateProcessor exec = null;


        try (QueryExecution qe = QueryExecutionFactory.sparqlService(service, query)) {

            ResultSet results = qe.execSelect();

            update  = UpdateFactory.create("DELETE WHERE { <http://www.cinemas.fake/starmovie/movies/" + movieName.replaceAll(" ","").toLowerCase() + "> <http://www.cinemas.fake/starmovie/movies/dt#" + type +"> ?o}");
            exec = UpdateExecutionFactory.createRemote(update, "http://localhost:3030/cinema1");
            exec.execute();

            while (results.hasNext()) {
                sol = results.nextSolution();
                res = sol.getResource("m");
                property = sol.get("x").toString();

                if(property.toString().toLowerCase().contains(type)) {
                    if(add) {
                        object = sol.getLiteral("y").toString() + text + "; ";
                    }
                    else if(!add) {
                        object = sol.getLiteral("y").toString();
                        String[] splitArray = object.split(";");
                        object = "";
                        for(int i = 0; i < splitArray.length-1; i++) {
                            if(splitArray[i].trim().equals(text.trim())) { }
                            else
                                object += splitArray[i] + "; ";
                        }
                    }
                    update = UpdateFactory.create("INSERT DATA{<" + res + "> <" + property + "> \"" + object + "\"}");
                    exec = UpdateExecutionFactory.createRemote(update, "http://localhost:3030/cinema1");
                    exec.execute();
                    break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addReservationToMovie(String movieName, String reservation) {
        updateTables(movieName, "reservations", reservation, true);
    }

}
