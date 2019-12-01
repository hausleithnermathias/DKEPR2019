package at.dkepr.cinemaservice.cinema1.services;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
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


}
