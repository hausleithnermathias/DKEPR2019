package at.dkepr.cinemaservice.cinema1;

import org.apache.jena.fuseki.main.FusekiServer;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetAccessor;
import org.apache.jena.query.DatasetAccessorFactory;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.vocabulary.VCARD;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FusekiServerTest {

    public static void start() throws IOException {
        Dataset ds = DatasetFactory.createTxnMem() ;
        FusekiServer server = FusekiServer.create()
                .setPort(3030)
                .add("/ds", ds, true)
                .build() ;
        server.start() ;

        Resource resource = new ClassPathResource("test.rdf");
        File file = resource.getFile();
        uploadRDF(file, "http://localhost:3030/ds/data");
        System.out.println("File uploaded");
        downloadRDF("http://localhost:3030/ds/data");
        System.out.println("File downloaded");

        server.stop() ;

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
}
