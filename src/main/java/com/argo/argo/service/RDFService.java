package com.argo.argo.service;

import com.argo.argo.dao.RDFDao;
import com.argo.argo.model.RDF_Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RDFService {

    private final RDFDao rdfDao;

    @Autowired
    public RDFService(@Qualifier("modelDao") RDFDao rdfDao){
        this.rdfDao = rdfDao;
    }

    public int persisttNewRDFModel(Integer modelId, RDF_Model rdf_model){
        return  rdfDao.insertNewRDFModel(modelId, rdf_model);
    }

    public RDF_Model getModelById(Integer modelId){
        return rdfDao.selectModelById(modelId);
    }

    public int updateRDFModelById(Integer modelId, RDF_Model rdf_model_update){
        return rdfDao.updateRDFModelById(modelId, rdf_model_update);
    }

    public int deleteRDFModelById(Integer modelId){
        return  rdfDao.deleteRDFModelById(modelId);
    }
}
