package com.argo.generator.rdf;

import org.apache.jena.query.*;
import org.apache.jena.sparql.resultset.ResultSetCompare;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class SparqlQueryExecutorTest {

    private final String QUERY = SparqlQueryPreparer.createSelectQuery("80", "council_person", "full_address", "city", "zip", "dob");
    private final String MODEL_NAME = "AddressesShortNoSubject";
    // assume AddressesShortNoSubject.ttl is also used on Fuseki server


    @BeforeEach
    void setUp() {
    }


    @AfterEach
    void tearDown() {
    }


    @Test
    void testRetrieveDataFromBothLocalGraphAndRemoteServer() {
        ResultSet local = SparqlQueryExecutor.retrieveDataFromLocalModel(MODEL_NAME, QUERY);
        ResultSet remote = SparqlQueryExecutor.retrieveDataFromRemoteFusekiServer(MODEL_NAME, QUERY);
        assertTrue(ResultSetCompare.equalsByTerm(local, remote));
    }

}