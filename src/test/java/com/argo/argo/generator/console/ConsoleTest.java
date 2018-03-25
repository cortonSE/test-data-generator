package com.argo.argo.generator.console;

import com.argo.argo.generator.annotations.Note;
import com.argo.argo.generator.constraints.Constraint;
import com.argo.argo.generator.constraints.ConstraintCollection;
import com.argo.argo.generator.constraints.Mutation;
import com.argo.argo.generator.rdf.QueryPreparer;
import com.argo.argo.generator.rdf.RDFGenerator;
import com.argo.argo.generator.triple.Triple;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.junit.jupiter.api.Disabled;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;


@Disabled
public class ConsoleTest {

    public static void main(String[] args) {
        String limit = "80";
        String[] predicates = {"council_person", "full_address", "city", "zip", "dob"};

        // good data
        ResultSet originalResultSet = executeQueryAgainstLocalModel(limit, predicates);
        List<Triple> triples = createListOfTriples(
                originalResultSet, null, predicates
        );
        Model model = RDFGenerator.createDefaultModel();
        model = RDFGenerator.addMultipleTriplesToModel(model, triples);
        saveRDFGraphToFile(model, "GoodModel.ttl");

        // bad data from local model
        ResultSet modifiedResultSet = executeQueryAgainstLocalModel(limit, predicates);
        ConstraintCollection constraintCollection = createConstraintCollection();
        List<Triple> modifiedTriples = createListOfTriples(
                modifiedResultSet, constraintCollection, predicates
        );
        Model badModel = RDFGenerator.createDefaultModel();
        badModel = RDFGenerator.addMultipleTriplesToModel(badModel, modifiedTriples);
        saveRDFGraphToFile(badModel, "BadModelLocal.ttl");

        // bad data from remote server
        ResultSet modifiedResultSetFromFuseki = executeQueryAgainstRemoveFusekiModel(limit, predicates);
        ConstraintCollection constraintCollection2 = createConstraintCollection();
        List<Triple> modifiedTriples2 = createListOfTriples(
                modifiedResultSetFromFuseki, constraintCollection2, predicates
        );
        Model badModelFromFuseki = RDFGenerator.createDefaultModel();
        badModelFromFuseki = RDFGenerator.addMultipleTriplesToModel(badModelFromFuseki, modifiedTriples2);
        saveRDFGraphToFile(badModelFromFuseki, "BadModelFuseki.ttl");
    }


    @Note(message =
            "This method receives all necessary parameters from the UI." +
            "Trying to manipulate these by hardcode values is hard since" +
            "there is no clear facade (method signature). But the UI will" +
            "know exactly how many parameters are needed for each Mutation options" +
            "before calling this method."
    )
    private static ConstraintCollection createConstraintCollection() {
        ConstraintCollection constraintCollection = new ConstraintCollection();
        Constraint constraint = null;

        // change zip
        constraint = new Constraint("zip",
                Mutation.CREATE_RANDOM_STRING, "6");
        constraintCollection.addConstraintToCollection(constraint);

        // change council_person
        constraint = new Constraint("council_person",
                Mutation.TRUNCATE_NUMBER_OF_CHARACTERS, "3");
        constraintCollection.addConstraintToCollection(constraint);

        // change date
        constraint = new Constraint("dob",
                Mutation.CREATE_PAST_OR_FUTURE_DATE, "-3");
        constraintCollection.addConstraintToCollection(constraint);

        return constraintCollection;
    }


    private static void printTriples(List<Triple> triples) {
        for(Triple triple : triples) {
            System.out.println(triple.toString());
        }
    }


    @Note(message =
        "Currently reading base model from the local file." +
        "Prefer to be able to connect to Fuseki and read from it."
    )
    private static ResultSet executeQueryAgainstLocalModel(String limit, String... predicates) {
        String queryString = QueryPreparer.createSelectQuery(limit, predicates);
        Model model = ModelFactory.createDefaultModel();
        model.read("sample-rdf/AddressesShortNoSubject.ttl");
        Query query = QueryFactory.create(queryString);
        QueryExecution qExe = QueryExecutionFactory.create(query, model);

        return qExe.execSelect();
    }


    private static ResultSet executeQueryAgainstRemoveFusekiModel(String limit, String... predicates) {
        String queryString = QueryPreparer.createSelectQuery(limit, predicates);
        QueryExecution qExe = QueryExecutionFactory.sparqlService(
                "http://localhost:3030/AddressesShortNoSubject/sparql",
                queryString
        );
        return qExe.execSelect();
    }


    private static List<Triple> createListOfTriples(
            ResultSet resultSet,
            ConstraintCollection constraintCollection,
            String... predicateNames) {

        List<Triple> triples = new LinkedList<>();
        QuerySolution solution = null;
        String subjectName = "", objectValue = "";
        int totalSubjectCount = 0;

        while(resultSet.hasNext()) {
            solution = resultSet.next();
            subjectName= "Subject" + totalSubjectCount++;

            for(String predicate : predicateNames) {
                objectValue = solution.get(predicate).toString();
                objectValue = mutateObjectIfRequiredByPredicate(predicate, objectValue, constraintCollection);
                triples.add(new Triple(subjectName, predicate, objectValue));
            }
        }

        return triples;
    }


    private static String mutateObjectIfRequiredByPredicate(
            String predicate,
            String objectValue,
            ConstraintCollection constraintCollection) {

        if(constraintCollection != null) {
            Constraint constraintForCurrentPredicate =
                    constraintCollection.getConstraintForPredicate(predicate);

            if (constraintForCurrentPredicate != null) {
                constraintForCurrentPredicate.appendMutationParameter(objectValue);
                objectValue = Mutation.mutate(
                        constraintForCurrentPredicate.getMutationOption(),
                        constraintForCurrentPredicate.getMutationParameters()
                );
            }
        }
        return objectValue;
    }


    private static void saveRDFGraphToFile(Model model, String fileName) {
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
