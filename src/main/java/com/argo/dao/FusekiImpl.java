package com.argo.dao;

import com.argo.model.RDF_Model;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.springframework.stereotype.Repository;

@Repository("modelDao")
public class FusekiImpl implements RDFDao {

    // insert rdf model into Fuseki triplestore
    @Override
    public int insertNewRDFModel(Integer modelId, RDF_Model rdf_model) {
        Model model = ModelFactory.createDefaultModel();
        final String url = "https://www.w3.org/ns/regorg#legalName;";
        model.read(url);
        return 0;
    }

    //retrieve rdf model from Fuseki triplestore by ID
    @Override
    public RDF_Model selectModelById(Integer modelId) {
        return null;
    }

    //modify rdf model with specified id
    @Override
    public int updateRDFModelById(Integer modelId, RDF_Model rdf_model) {
        return 0;
    }

    //delete rdf model from Fuseki triplestore
    @Override
    public int deleteRDFModelById(Integer modelId) {
        return 0;
    }
}
