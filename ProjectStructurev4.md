# Self Service Agent for Test Data

## Introduction

This file shows the current structure of the project. It only shows up-to the package level to help new developers familiarize themselves with how the code is organized. The developers are encourage to read the actual code to understand it.

## Structure
**ARGO Self-Service Agent**
<pre>
+-- <a href="/fuseki">fuseki</a> - A third-party tool used to retrieve data from the web.
+-- <a href="/src">src</a> - Documentation
|   +-- <a href="/src/main">main</a> - Main code repository
|   |   +-- <a href="/src/main/java/com/argo">java/com/argo</a> - Primary execution code repository
|   |   |   +-- <a href="/src/main/java/com/argo/controller">controller</a> - Defines a class ModelController
|   |   |   +-- <a href="/src/main/java/com/argo/dao">dao</a> - Handles giving requests over to Fuseki
|   |	|   +-- <a href="/src/main/java/com/argo/generator">generator</a> - Back-End code for various creation and mutation operations
|   |	|   |   +-- <a href="/src/main/java/com/argo/generator/annotations">annotations</a> - Defines a Note interface
|	|	|   |   +-- <a href="/src/main/java/com/argo/generator/constraints">constraints</a> - Defines the constraints and handles mutating the data
|	|	|   |   +-- <a href="/src/main/java/com/argo/generator/rdf">rdf</a> - Builds RDF models and executes SPARQL queries on the models or Fuseki databases
|	|	|   |   +-- <a href="/src/main/java/com/argo/generator/triple">triple</a> - Creates class to store triples and create new mutated triples
|	|   |   +-- <a href="/src/main/java/com/argo/model">model</a> - Defines a class RDF_Model
|	|   |   +-- <a href="/src/main/java/com/argo/service">service</a> - Defines a class RDF_Service
|   |   +-- <a href="/src/main/resources">resources</a> - Resources to help with execution
|   |       +-- <a href="/src/main/resources/static">static</a> - Constant system or user resources
|	|       |   +-- <a href="/src/main/resources/static/css">css</a> - Contains style guidelines
|	|	    |   +-- <a href="/src/main/resources/static/lib">lib</a> - Helper code for the N3 resource
|	|	    |   +-- <a href="/src/main/resources/static/plugins">plugins</a> - Additional plugins and support
|   +-- <a href="/src/test">test</a> - Tests for various sections of the system
|   |   +-- <a href="/src/test/java/com/argo">java/com/argo</a> - System tests subdirectory
|   |   |   +-- <a href="/src/test/java/com/argo/generator">generator</a> - Tests for the back end generation code
|	|   |   |   +-- <a href="/src/test/java/com/argo/generator/console">console</a> - Tests for the integration of the Front-End and Back-End code
|	|	|   |   +-- <a href="/src/test/java/com/argo/generator/constraints">constraints</a> - Tests for mutating data based on constraints
|	|	|   |   +-- <a href="/src/test/java/com/argo/generator/rdf">rdf</a> - Tests for creating, editing, and querying RDF models
|	|	|   |   +-- <a href="/src/test/java/com/argo/generator/triple">triple</a> - Tests sending in a constraint and data for mutation
|   |   +-- <a href="/src/test/resources">resources</a> - Resources to help with testing
|   |   |   +-- <a href="/src/test/resources/SPARQL">SPARQL</a> - Sample test querys
|	|   |   +-- <a href="/src/test/resources/assets">assets</a> - Cheat sheets for SPARQL and other resources
|	|   |   +-- <a href="/src/test/resources/sample-rdf">sample-rdf</a> - Sample test rdf data
</pre>