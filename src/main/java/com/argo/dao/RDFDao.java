package com.argo.dao;

import com.argo.model.RDF_Model;


public interface RDFDao {

    int insertNewRDFModel(Integer modelId, RDF_Model rdf_model);

    RDF_Model selectModelById(Integer modelId);

    int updateRDFModelById(Integer modelId, RDF_Model rdf_model);

    int deleteRDFModelById(Integer modelId);
}
