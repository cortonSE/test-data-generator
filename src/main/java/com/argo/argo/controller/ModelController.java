package com.argo.argo.controller;

import com.argo.argo.model.RDF_Model;
import com.argo.argo.service.RDFService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/rdf-model")
public class ModelController {

    private final RDFService rdfService;

    @Autowired
    public ModelController(RDFService rdfService){
        this.rdfService = rdfService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public RDF_Model getModel(Integer id){
        return  rdfService.getModelById(id);
    }

}
