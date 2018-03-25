package com.argo.generator.constraints;

import com.argo.generator.annotations.Note;

@Note(message = "Temporary class to generate constraint collection. May not need later.")
public class ConstraintCollectionFactory {

    @Note(message =
            "This method receives all necessary parameters from the UI." +
                    "Trying to manipulate these by hardcode values is hard since" +
                    "there is no clear facade (method signature). But the UI will" +
                    "know exactly how many parameters are needed for each Mutation options" +
                    "before calling this method."
    )
    public static ConstraintCollection createConstraintCollection() {
        ConstraintCollection constraintCollection = new ConstraintCollection();

        // change zip
        Constraint constraint1 = new Constraint("zip",
                Mutation.TRUNCATE_NUMBER_OF_CHARACTERS, "1");
        constraintCollection.addConstraint(constraint1);

        // change council_person
        Constraint constraint2 = new Constraint("council_person",
                Mutation.REPLACE_CHARACTER, "a", "w");
        constraintCollection.addConstraint(constraint2);

        // change date
        Constraint constraint3 = new Constraint("dob",
                Mutation.CREATE_PAST_OR_FUTURE_DATE, "-3");
        constraintCollection.addConstraint(constraint3);

        return constraintCollection;
    }


}
