package at.dkepr.cinemaservice.cinema1.services;
import org.apache.jena.fuseki.build.FusekiConfig;
import org.apache.jena.fuseki.main.FusekiServer;
import org.apache.jena.fuseki.server.DataAccessPoint;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.apache.jena.vocabulary.VCARD;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.function.Predicate;

public class FusekiServerTest {



    public static void start() throws IOException {

        /*
        Dataset ds = DatasetFactory.createTxnMem() ;
        FusekiServer server = FusekiServer.create()
                .setPort(3030)
                .add("/ds", ds, true)
                .build() ;
        server.start();
        */


        /*
        Resource r = new ClassPathResource("config.ttl");
        File f = r.getFile();
        Model m = ModelFactory.createDefaultModel();
        try (FileInputStream in = new FileInputStream(f)) {
            m.read(in,"TURTLE");
        }

        String s;
        FusekiConfig::processServerConfiguration(m;
        */

        /*
        Resource resource = new ClassPathResource("cinema1.rdf");
        File file = resource.getFile();
        uploadRDF(file, "http://localhost:3030/cinema1/data");
        System.out.println("File uploaded");
        */

        /*
        downloadRDF("http://localhost:3030/cinema1/data");
        System.out.println("File downloaded");
        */

        /*
        sparqlTest("http://localhost:3030/cinema1/data");
        getMoviesByDay("http://localhost:3030/cinema1", "Mo");

        Model m = ModelFactory.createDefaultModel();
        try (FileInputStream in = new FileInputStream("out.rdf")) {
            m.read(in,"RDF/XML");
        }

        m.write(System.out);


        /*
        resource = new ClassPathResource("cinema1.rdf");
        file = resource.getFile();
        uploadRDF(file, "http://localhost:3030/cinema2/data");
        System.out.println("File uploaded");
        */

        /*
        downloadRDF("http://localhost:3030/cinema2/data");
        System.out.println("File downloaded");
        */


        //server.stop() ;

    }

    public static void uploadRDF(File rdf,String serviceURI)
            throws IOException {

        // parse the file
        Model m = ModelFactory.createDefaultModel();
        try (FileInputStream in = new FileInputStream(rdf)) {
            m.read(in, null, "RDF/XML");
        }

        // upload the resulting model
        DatasetAccessor accessor = DatasetAccessorFactory.createHTTP(serviceURI);
        accessor.putModel(m);

    }

    public static void downloadRDF(String serviceURI) {

        // download the model
        DatasetAccessor accessor = DatasetAccessorFactory.createHTTP(serviceURI);
        Model m = accessor.getModel();
        m.write(System.out);
    }

    public static void sparqlTest(String serviceURI) {

        // direct querying on fuseki server
        Literal name = null;
        String service = "http://localhost:3030/cinema1";
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                "PREFIX dt: <http://www.cinemas.fake/starmovie/movies/dt#/> " +
                "SELECT * WHERE { " +
                " <http://www.cinemas.fake/starmovie/movies/joker> ?x ?y." +
                "}";
        try (QueryExecution qe = QueryExecutionFactory.sparqlService(service, query)) {
            ResultSet results = qe.execSelect();
            while (results.hasNext()) {
                QuerySolution sol=results.nextSolution();
                try {
                    org.apache.jena.rdf.model.Resource resource = sol.getResource("x");
                    System.out.print(resource);
                    name = sol.getLiteral("y");
                    System.out.println(": " + name);
                }
                catch (Exception e){
                    //org.apache.jena.rdf.model.Resource resource = sol.getResource("y");
                    System.out.print(" " + sol.get("y").getModel().toString());
                    System.out.println(": Exception during getLiteral");
                }

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        /* get whole model local from fuseki and query the mode
        Literal name = null;
        DatasetAccessor accessor = DatasetAccessorFactory.createHTTP(serviceURI);
        Model m = accessor.getModel();

        String queryString =
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                        "PREFIX dt: <http://www.cinemas.fake/starmovie/movies/dt#/> " +
                        "SELECT * WHERE { " +
                        " <http://www.cinemas.fake/starmovie/movies/joker> ?x ?y . " +
                        "}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, m);
        try {
            ResultSet results=qexec.execSelect();
            while (results.hasNext()) {
                QuerySolution sol=results.nextSolution();

                try {
                    org.apache.jena.rdf.model.Resource resource = sol.getResource("x");
                    System.out.print(resource);
                    name = sol.getLiteral("y");
                    System.out.println(": " + name);
                }
                catch (Exception e){
                    System.out.println(": Exception during getLiteral");
                }

            }
        } finally {
            qexec.close();
        }
        */

    }

    public static Model getMoviesByDay(String serviceURI, String day) {

        return null;
    }
}
