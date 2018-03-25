# Self Service Agent for Test Data

## Introduction

This file list all current features of the tool, along with existing bugs/defects, and some requirements that the end-users need to know before using this tool. 

## Features

Here are the current features that this tool supports:
- Retrieve any model (with dynamic name) from Fuseki
- Modify **_literals_** data (currently support [Number](src/main/java/com/argo/generator/constraints/NumberMutation.java), [String](src/main/java/com/argo/generator/constraints/StringMutation.java), and [DateTime](src/main/java/com/argo/generator/constraints/DateTimeMutation.java)) to produce invalid data
- Save models as Turtle files (can be changed to any other type in the source code) 

## Bugs

Currently none

## Important Notes

The developers are **highly encouraged** to run [full regression test](src/test/java) and revise this document before release new version.

The end-users have to know the exact predicate names that are stored in the RDF graph in order to retrieve them. Even with 1 misspelled predicate name causes the result set to be empty, since the select query looks for all matching predicate and retrieve them as once.