package com.argo.generator.rdf;

import com.argo.generator.annotations.Note;
import com.argo.generator.triple.Triple;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

import java.util.LinkedList;
import java.util.List;


@Note(message = "Require knowledge of the RDF model and its prefixes")
public class SparqlQueryPreparer {

    static final String PREFIXES =
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX dcat: <http://www.w3.org/ns/dcat#>\n" +
            "PREFIX dcterms: <http://purl.org/dc/terms/>\n" +
            "PREFIX ds: <https://data.brla.gov/resource/_6fyg-p3r9/>\n" +
            "PREFIX dsbase: <https://data.brla.gov/resource/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX geo: <http://www.opengis.net/ont/geosparql#>\n" +
            "PREFIX geo1: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n" +
            "PREFIX ods: <http://open-data-standards.github.com/2012/01/open-data-standards#>\n" +
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
            "PREFIX socrata: <http://www.socrata.com/rdf/terms#>\n" +
            "PREFIX xml: <http://www.w3.org/XML/1998/namespace>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n\n";

    private static final String COUNT_ALL_QUERY =
            "SELECT (COUNT(?s) AS ?triples) WHERE {\n" +
            "    ?s ?p ?o\n" +
            "}";


    public static String createSelectQuery(String limit, String... predicates) {
        return PREFIXES + createSelectWhereClause(limit, predicates);
    }


    public static String createUpdateQuery(Model model, String modelName) {
        StringBuilder query = new StringBuilder();

        query.append("INSERT DATA\n");
        query.append("{\n");
        query.append("  GRAPH <").append(modelName).append(">\n");
        query.append("  {\n");

        StmtIterator iter = model.listStatements();
        while(iter.hasNext()) {
            Statement statement = iter.nextStatement();
            RDFNode object = statement.getObject();
            query.append("    " + "<").append(statement.getSubject().toString()).append("> <");
            query.append(statement.getPredicate().toString()).append("> ");
            if (object.isResource()) {
                query.append("<").append(object.toString()).append("> .\n");
            } else if (object.isLiteral()) {
                query.append("\"").append(object.toString()).append("\" .\n");
            }
        }
        query.append(" }\n" + "}");

        return query.toString();
    }


    private static String createSelectWhereClause(String limit, String... predicates) {
        StringBuilder query = new StringBuilder();

        // select clause
        query.append("select distinct");
        for (String predicate : predicates) {
            query.append(String.format(" ?%s", predicate));
        }

        // where clause
        query.append("\nwhere {\n");
        for (String predicate : predicates) {
            query.append(String.format("    ?subject ds:%s ?%s.\n", predicate, predicate));
        }
        query.append("}\n");
        query.append(String.format("limit %s", limit));

        return query.toString();
    }


    public static String createCountAllQuery() {
        return COUNT_ALL_QUERY;
    }

}
