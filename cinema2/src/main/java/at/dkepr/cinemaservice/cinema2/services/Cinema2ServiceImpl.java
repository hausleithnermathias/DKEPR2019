package at.dkepr.cinemaservice.cinema2.services;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.springframework.stereotype.Service;

@Service
public class Cinema2ServiceImpl implements Cinema2Service{

    @Override
    public Model getMoviesByDay(String serviceURI, String day) {
        // direct querying on fuseki server
        Literal name = null;
        Model model=null;
        org.apache.jena.rdf.model.Resource resource = null;

        String service = serviceURI;
        String query = "PREFIX fi: <http://www.cinemas.fake/megaplex/movies/info#/>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT * WHERE {?movies <http://www.cinemas.fake/megaplex/movies/info#days> ?y. FILTER(CONTAINS(?y,'" + day + "'))}";
        try (QueryExecution qe = QueryExecutionFactory.sparqlService(service, query)) {
            // create an empty Model
            model = ModelFactory.createDefaultModel();
            ResultSet results = qe.execSelect();
            while (results.hasNext()) {
                QuerySolution sol=results.nextSolution();
                resource = sol.getResource("movies");
                //System.out.println(resource);

                String service1 = serviceURI;
                String query1 = "PREFIX dt: <http://www.cinemas.fake/megaplex/movies/info#/>\n" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                        "SELECT * WHERE {<" + resource + "> ?x ?y. }";

                QueryExecution qe1 = QueryExecutionFactory.sparqlService(service1, query1);
                ResultSet results1 = qe1.execSelect();

                // create the resource
                Resource cinema = model.createResource(resource.toString());

                while (results1.hasNext()) {
                    QuerySolution sol1 = results1.nextSolution();
                    org.apache.jena.rdf.model.Resource resource1=sol1.getResource("movies");
                    Property property = ResourceFactory.createProperty(sol1.get("x").toString());
                    cinema.addProperty(property, sol1.getLiteral("y"));

                }

                /*
                // list the statements in the Model
                StmtIterator iter = model.listStatements();
                // print out the predicate, subject and object of each statement
                while (iter.hasNext()) {
                    Statement stmt      = iter.nextStatement();  // get next statement
                    org.apache.jena.rdf.model.Resource subject   = stmt.getSubject();     // get the subject
                    Property  predicate = stmt.getPredicate();   // get the predicate
                    RDFNode   object    = stmt.getObject();      // get the object

                    System.out.print(subject.toString());
                    System.out.print(" " + predicate.toString() + " ");
                    if (object instanceof Resource) {
                        System.out.print(object.toString());
                    } else {
                        // object is a literal
                        System.out.print(" \"" + object.toString() + "\"");
                    }
                    System.out.println(" .");
                }
*/
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
    public Model getMenusPerMovie(String URL) {
        return null;
    }

    @Override
    public Model getReservationName(String URL, String name) {
        Model model=null;
        org.apache.jena.rdf.model.Resource resource = null;

        String service = URL;
        String query = "PREFIX fi: <http://www.cinemas.fake/megaplex/movies/info#/>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT * WHERE {?movies <http://www.cinemas.fake/megaplex/movies/info#reservations> ?y. FILTER(CONTAINS(?y,'" + name + "'))}";
        try (QueryExecution qe = QueryExecutionFactory.sparqlService(service, query)) {
            // create an empty Model
            model = ModelFactory.createDefaultModel();
            ResultSet results = qe.execSelect();
            while (results.hasNext()) {
                QuerySolution sol=results.nextSolution();
                resource = sol.getResource("movies");
                //System.out.println(resource);

                String service1 = URL;
                String query1 = "PREFIX dt: <http://www.cinemas.fake/megaplex/movies/info#/>\n" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                        "SELECT * WHERE {<" + resource + "> ?x ?y. }";

                QueryExecution qe1 = QueryExecutionFactory.sparqlService(service1, query1);
                ResultSet results1 = qe1.execSelect();

                // create the resource
                Resource cinema = model.createResource(resource.toString());

                while (results1.hasNext()) {
                    QuerySolution sol1 = results1.nextSolution();
                    org.apache.jena.rdf.model.Resource resource1=sol1.getResource("movies");
                    Property property = ResourceFactory.createProperty(sol1.get("x").toString());
                    cinema.addProperty(property, sol1.getLiteral("y"));

                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }

    @Override
    public Model getMoviesPerGenre(String URL, String genre) {
        Model model=null;
        org.apache.jena.rdf.model.Resource resource = null;

        String service = URL;
        String query = "PREFIX fi: <http://www.cinemas.fake/megaplex/movies/info#/>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT * WHERE {?movies <http://www.cinemas.fake/megaplex/movies/info#genre> ?y. FILTER(CONTAINS(?y,'" + genre + "'))}";
        try (QueryExecution qe = QueryExecutionFactory.sparqlService(service, query)) {
            // create an empty Model
            model = ModelFactory.createDefaultModel();
            ResultSet results = qe.execSelect();
            while (results.hasNext()) {
                QuerySolution sol=results.nextSolution();
                resource = sol.getResource("movies");
                //System.out.println(resource);

                String service1 = URL;
                String query1 = "PREFIX dt: <http://www.cinemas.fake/megaplex/movies/info#/>\n" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                        "SELECT * WHERE {<" + resource + "> ?x ?y. }";

                QueryExecution qe1 = QueryExecutionFactory.sparqlService(service1, query1);
                ResultSet results1 = qe1.execSelect();

                // create the resource
                Resource cinema = model.createResource(resource.toString());

                while (results1.hasNext()) {
                    QuerySolution sol1 = results1.nextSolution();
                    org.apache.jena.rdf.model.Resource resource1=sol1.getResource("movies");
                    Property property = ResourceFactory.createProperty(sol1.get("x").toString());
                    cinema.addProperty(property, sol1.getLiteral("y"));

                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }
}
