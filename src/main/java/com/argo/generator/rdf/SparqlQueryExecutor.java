package com.argo.generator.rdf;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;

import java.io.File;
import java.io.FileOutputStream;


public class SparqlQueryExecutor {

    public static ResultSet retrieveDataFromRemoteFusekiServer(String modelName, String selectQuery) {
        String fullModelName = String.format("http://localhost:3030/%s/sparql", modelName);
        QueryExecution qExe = QueryExecutionFactory.sparqlService(fullModelName, selectQuery);
        return qExe.execSelect();
    }

    public static ResultSet retrieveDataFromLocalModel(String modelName, String selectQuery) {
        Model model = ModelFactory.createDefaultModel();
        model.read(String.format("sample-rdf/%s.ttl", modelName));
        Query query = QueryFactory.create(selectQuery);
        QueryExecution qExe = QueryExecutionFactory.create(query, model);
        return qExe.execSelect();
    }


    public static void updateDataToRemoteFusekiServer(Model model, String modelName) {
        String fullURI = String.format("http://localhost:3030/%s", modelName);
        RDFConnection connection = RDFConnectionFactory.connect(fullURI);
        connection.put(model);
    }


    public static void updateDataToLocalRdfGraph(Model model, String fileName) {
        try {
            File outputFile = new File("src/test/resources/sample-rdf/" + fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);

            if(!outputFile.exists()) {
                //noinspection ResultOfMethodCallIgnored
                outputFile.createNewFile();
            }

            RDFDataMgr.write(fileOutputStream, model, RDFFormat.TURTLE);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
