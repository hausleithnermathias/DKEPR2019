package at.dkepr.cinemaservice.externalAPI;

import org.apache.jena.rdf.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import java.io.*;


@RestController
@RequestMapping("/test")
public class MetaServiceAPI {

    @Autowired
    ResourceLoader resourceLoader;

    @GetMapping
    public String test() {
        return "Hallo Welt!";
    }

    @RequestMapping(value = "/Movies/{day}", method = RequestMethod.GET)
    public @ResponseBody String getMoviesByDay(@PathVariable String day) throws IOException {

        Model model = ModelFactory.createDefaultModel();
        model.read("http://localhost:8080/Cinema1/Movies/" + day);

        // list the statements in the Model
        StmtIterator iter = model.listStatements();
        // print out the predicate, subject and object of each statement
        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();  // get next statement
            org.apache.jena.rdf.model.Resource subject = stmt.getSubject();     // get the subject
            Property predicate = stmt.getPredicate();   // get the predicate
            RDFNode object = stmt.getObject();      // get the object

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
        try(final ByteArrayOutputStream os = new ByteArrayOutputStream() ){
            model.write(os);
            return os.toString();
        }
    }


}
