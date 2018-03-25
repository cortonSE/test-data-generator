package com.argo.generator.triple;

import com.argo.generator.constraints.Constraint;
import com.argo.generator.constraints.ConstraintCollection;
import com.argo.generator.constraints.Mutation;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import java.util.LinkedList;
import java.util.List;


public class TripleFactory {

    public static List<Triple> createListOfTriples(
            ResultSet resultSet,
            ConstraintCollection constraintCollection,
            String... predicateNames) {

        List<Triple> triples = new LinkedList<>();
        QuerySolution solution = null;
        String subjectName = "", objectValue = "";
        int totalSubjectCount = 0;

        while(resultSet.hasNext()) {
            solution = resultSet.next();
            subjectName = "Subject" + totalSubjectCount++;

            for(String predicate : predicateNames) {
                objectValue = solution.get(predicate).toString();
                objectValue = mutateObjectIfRequiredByPredicate(predicate, objectValue, constraintCollection);
                triples.add(new Triple(subjectName, predicate, objectValue));
            }
        }

        return triples;
    }


    static String mutateObjectIfRequiredByPredicate(
            String predicate,
            String objectValue,
            ConstraintCollection constraintCollection) {

        if(constraintCollection != null) {
            Constraint constraintForCurrentPredicate =
                    constraintCollection.getConstraintForPredicate(predicate);

            if(constraintForCurrentPredicate != null) {
                /*
                 * the same constraint applies for both "Trae Welch" and "Matt Watson"
                 * thus the same constraint object for both of them
                 * BUT in the parameters linked list of the constraint object
                 * is Trae Welch -> Matt Watson -> Trae Welch -> Matt Watson -> ...
                 * how to solve this?
                 *
                 * SOLVED (need to document)
                 */
                String originalObjectValue = objectValue;

                constraintForCurrentPredicate.appendMutationParameter(originalObjectValue);
                objectValue = Mutation.mutate(
                        constraintForCurrentPredicate.getMutationOption(),
                        constraintForCurrentPredicate.getMutationParameters()
                );

                constraintForCurrentPredicate.removeMutationParameter(originalObjectValue);

            }
        }
        return objectValue;
    }

}
