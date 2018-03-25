package com.argo.generator.console;

import com.argo.generator.constraints.ConstraintCollection;
import com.argo.generator.constraints.ConstraintCollectionFactory;
import com.argo.generator.rdf.RDFGenerator;
import com.argo.generator.rdf.SparqlQueryExecutor;
import com.argo.generator.rdf.SparqlQueryPreparer;
import com.argo.generator.triple.Triple;
import com.argo.generator.triple.TripleFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.junit.jupiter.api.Disabled;

import java.util.List;


@Disabled
public class IntegrationTest {

    public static void main(String[] args) {
        String limit = "80";
        String[] predicates = {"council_person", "full_address", "city", "zip", "dob"};
        String query = SparqlQueryPreparer.createSelectQuery(limit, predicates);
        String modelName = "AddressesShortNoSubject";

        // good data
        ResultSet originalResultSet = SparqlQueryExecutor.retrieveDataFromRemoteFusekiServer(modelName, query);
        List<Triple> triples = TripleFactory.createListOfTriples(
                originalResultSet, null, predicates
        );
        Model model = RDFGenerator.createDefaultModel();
        model = RDFGenerator.addMultipleTriplesToModel(model, triples);
        SparqlQueryExecutor.updateDataToLocalRdfGraph(model, "GoodModel.ttl");

        // bad data from remote server
        ResultSet modifiedResultSetFromFuseki =
                SparqlQueryExecutor.retrieveDataFromRemoteFusekiServer(modelName, query);
        ConstraintCollection constraintCollection2 = ConstraintCollectionFactory.createConstraintCollection();
        List<Triple> modifiedTriples2 = TripleFactory.createListOfTriples(
                modifiedResultSetFromFuseki, constraintCollection2, predicates
        );
        Model badModelFromFuseki = RDFGenerator.createDefaultModel();
        badModelFromFuseki = RDFGenerator.addMultipleTriplesToModel(
                badModelFromFuseki, modifiedTriples2);
        SparqlQueryExecutor.updateDataToLocalRdfGraph(badModelFromFuseki, "BadModelFuseki.ttl");

        // update bad data to fuseki
        SparqlQueryExecutor.updateDataToRemoteFusekiServer(badModelFromFuseki, "BadModel");
    }

}
