package com.argo.generator.triple;

import com.argo.generator.constraints.Constraint;
import com.argo.generator.constraints.ConstraintCollection;
import com.argo.generator.constraints.ConstraintCollectionFactory;
import com.argo.generator.constraints.Mutation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class TripleFactoryTest {

    private Constraint truncateZipConstraint = new Constraint(
            "zip", Mutation.TRUNCATE_NUMBER_OF_CHARACTERS, "1");

    private Constraint replaceCharacterCouncilPersonConstraint = new Constraint(
            "council_person", Mutation.REPLACE_CHARACTER, "H", "A");

    private ConstraintCollection constraintCollection;


    @BeforeEach
    void setUp() {
        constraintCollection = new ConstraintCollection();
    }


    @AfterEach
    void tearDown() {
        constraintCollection = null;
    }


    @Test
    void testMutateObjectIfRequiredByPredicateNoModify() {
        String predicate = truncateZipConstraint.getPredicate();
        String objectValue = "75074";
        String expectedValue = "75074";
        String actualValue = TripleFactory.mutateObjectIfRequiredByPredicate(
                predicate, objectValue, null
        );
        assertEquals(expectedValue, actualValue);

        actualValue = TripleFactory.mutateObjectIfRequiredByPredicate(
                predicate, objectValue, constraintCollection
        );
        assertEquals(expectedValue, actualValue);
    }


    @Test
    void testMutateObjectIfRequiredByPredicateSingleTriple() {
        String predicate = truncateZipConstraint.getPredicate();
        constraintCollection.addConstraint(truncateZipConstraint);
        String objectValue = "75074";
        String expectedModifiedValue = "7507";
        String actualModifiedValue = TripleFactory.mutateObjectIfRequiredByPredicate(
                predicate, objectValue, constraintCollection
        );
        assertEquals(expectedModifiedValue, actualModifiedValue);

    }


    @Test
    void testMutateObjectIfRequiredByPredicateMultipleTriplesSameRule() {
        String predicate = replaceCharacterCouncilPersonConstraint.getPredicate();
        constraintCollection.addConstraint(replaceCharacterCouncilPersonConstraint);

        // modify first triple
        String objectValue1 = "Hh Ww";
        String expectedModifiedValue1 = "Ah Ww";
        String actualModifiedValue1 = TripleFactory.mutateObjectIfRequiredByPredicate(
                predicate, objectValue1, constraintCollection
        );
        assertEquals(expectedModifiedValue1, actualModifiedValue1);

        // modify second triple with the same rule
        String objectValue2 = "H";
        String expectedModifiedValue2 = "A";
        String actualModifiedValue2 = TripleFactory.mutateObjectIfRequiredByPredicate(
                predicate, objectValue2, constraintCollection
        );
        assertEquals(expectedModifiedValue2, actualModifiedValue2);
        assertNotEquals(expectedModifiedValue2, actualModifiedValue1);

    }
}