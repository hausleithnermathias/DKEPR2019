package at.dkepr.cinemaservice.cinema2.services;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.springframework.stereotype.Service;
import org.apache.jena.rdf.model.Resource;

@Service
public class Cinema2ServiceImpl implements Cinema2Service{
    String queryPattern = "PREFIX fi: <http://www.cinemas.fake/starmovie/movies/info#/>\n" +
                          "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n";

    @Override
    public Model getMoviesByDay(String serviceURI, String day) {
        String query = queryPattern + "SELECT * WHERE {?m ?x ?y.{SELECT ?m WHERE {?m <http://www.cinemas.fake/megaplex/movies/info#days> ?y. FILTER(CONTAINS(?y,'" + day + "'))}}}";
        return queryProcessing(serviceURI, query, null);
    }

    @Override
    public Model getAllMovies(String serviceURI) {
        DatasetAccessor accessor = DatasetAccessorFactory.createHTTP(serviceURI);
        Model m = accessor.getModel();
        return m;
    }

    @Override
    public Model getMenus(String URL) {
        String propertyForQuery = "http://www.cinemas.fake/megaplex/movies/info#menu";
        String query = queryPattern + "SELECT * WHERE {?m <" + propertyForQuery + ">  ?y}";

        return queryProcessing(URL, query,propertyForQuery);
    }

    @Override
    public Model getReservationName(String URL, String name) {
        String query = queryPattern + "SELECT * WHERE {?m ?x ?y.{SELECT ?m WHERE {?m <http://www.cinemas.fake/megaplex/movies/info#reservations> ?y. FILTER(CONTAINS(?y,'" + name + "'))}}}";
        return queryProcessing(URL, query, null);
    }

    @Override
    public Model getMoviesPerGenre(String URL, String genre) {
        String query = queryPattern + "SELECT * WHERE {?m ?x ?y.{SELECT ?m WHERE {?m <http://www.cinemas.fake/megaplex/movies/info#genre> ?y. FILTER(CONTAINS(?y,'" + genre + "'))}}}";
        return queryProcessing(URL, query,null);
    }

    public Model queryProcessing(String service, String query, String queryExtension) {
        Model model = null;
        QuerySolution sol = null;
        String resourceNew = "";
        String resourceOld = "";
        Property property = null;
        Resource resource = null;

        try (QueryExecution qe = QueryExecutionFactory.sparqlService(service, query)) {

            model = ModelFactory.createDefaultModel();
            ResultSet results = qe.execSelect();
            while (results.hasNext()) {
                sol = results.nextSolution();
                resourceNew = sol.getResource("m").toString();
                if(resourceOld.compareTo(resourceNew) != 0){
                    resource=model.createResource(sol.getResource("m").toString());
                }
                resourceOld = resourceNew;

                if(queryExtension == null)
                    property = ResourceFactory.createProperty(sol.get("x").toString());
                else
                    property = ResourceFactory.createProperty(queryExtension);

                resource.addProperty(property, sol.getLiteral("y"));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
}
