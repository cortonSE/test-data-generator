/*
 * assume at one time user can only apply 1 mutation rule to a particular predicate
 * i.e.
 *      allow:      zip -> 6 characters, age -> negative float with comma
 *      not allow:  zip -> {6 characters, change characters}, age -> {zero, overflow}
 */

package com.argo.argo.generator.constraints;

import java.util.HashMap;


public class Mutation {

    private static int mutationOptions = 0;

    // number mutations
    public static final int CREATE_NEGATIVE_INTEGER = mutationOptions++;
    public static final int CREATE_POSITIVE_INTEGER = mutationOptions++;
    public static final int CREATE_NEGATIVE_OVERFLOW_INTEGER = mutationOptions++;
    public static final int CREATE_POSITIVE_OVERFLOW_INTEGER = mutationOptions++;
    public static final int CREATE_NEGATIVE_FLOATING_POINT = mutationOptions++;
    public static final int CREATE_POSITIVE_FLOATING_POINT = mutationOptions++;
    public static final int CREATE_NEGATIVE_INTEGER_WITH_COMMA = mutationOptions++;
    public static final int CREATE_POSITIVE_INTEGER_WITH_COMMA = mutationOptions++;
    public static final int CREATE_NEGATIVE_FLOATING_POINT_WITH_COMMA= mutationOptions++;
    public static final int CREATE_POSITIVE_FLOATING_POINT_WITH_COMMA = mutationOptions++;
    public static final int CREATE_SCIENTIFIC_NOTATION_NUMBER = mutationOptions++;
    public static final int CREATE_ZERO = mutationOptions++;
    public static final int CONVERT_TO_EUROPEAN_STYLE = mutationOptions;
    private static final int NUMBER_MUTATION_MARKER = mutationOptions++;


    // string mutations
    public static final int REPLACE_CHARACTER = mutationOptions++;
    public static final int APPEND_NUMBER_OF_CHARACTERS = mutationOptions++;
    public static final int TRUNCATE_NUMBER_OF_CHARACTERS = mutationOptions++;
    public static final int EMPTIFY = mutationOptions++;
    public static final int CONVERT_TO_MULTIPLE_SPACES = mutationOptions++;
    public static final int ADD_LEADING_SPACE = mutationOptions++;
    public static final int ADD_TRAILING_SPACE = mutationOptions++;
    public static final int CREATE_RANDOM_STRING = mutationOptions++;
    public static final int ADD_SPECIAL_CHARACTERS = mutationOptions;
    private static final int STRING_MUTATION_MARKER = mutationOptions++;


    // date-time mutations
    public static final int CREATE_TODAY_DATE = mutationOptions++;
    public static final int CREATE_PAST_OR_FUTURE_DATE = mutationOptions++;
    public static final int CREATE_ALWAYS_INVALID_DATES = mutationOptions++;
    public static final int CREATE_DATE_IN_ALL_FORMATS_EXCEPT = mutationOptions++;
    public static final int CREATE_DATE_IN_FORMAT = mutationOptions++;
    public static final int CREATE_LEAP_DATES = mutationOptions;
    private static final int DATE_TIME_MUTATION_MARKER = mutationOptions++;


    public static String mutate(int mutationOption, String... mutationData) {
        String generatedData = "";

        if(mutationOption <= NUMBER_MUTATION_MARKER) {
            generatedData = NumberMutation.mutate(mutationOption, mutationData);
        }
        else if(mutationOption > NUMBER_MUTATION_MARKER && mutationOption <= STRING_MUTATION_MARKER) {
            generatedData = StringMutation.mutate(mutationOption, mutationData);
        }
        else if(mutationOption > STRING_MUTATION_MARKER && mutationOption <= DATE_TIME_MUTATION_MARKER) {
            generatedData = DateTimeMutation.mutate(mutationOption, mutationData);
        }
        else {
            generatedData = "";
        }

        return generatedData;
    }


    public static HashMap<Integer, String> getAllMutationOptions() {
        HashMap<Integer, String> mutationOptions = new HashMap<>();

        mutationOptions.put(CREATE_NEGATIVE_INTEGER, "Create Negative Integer");
        mutationOptions.put(CREATE_POSITIVE_INTEGER, "Create Positive Integer");
        mutationOptions.put(CREATE_NEGATIVE_OVERFLOW_INTEGER, "Create Negative Overflow Integer");
        mutationOptions.put(CREATE_POSITIVE_OVERFLOW_INTEGER, "Create Positive Overflow Integer");
        mutationOptions.put(CREATE_NEGATIVE_FLOATING_POINT, "Create Negative Floating Point");
        mutationOptions.put(CREATE_POSITIVE_FLOATING_POINT, "Create Positive Floating Point");
        mutationOptions.put(CREATE_NEGATIVE_INTEGER_WITH_COMMA, "Create Negative Integer With Comma");
        mutationOptions.put(CREATE_POSITIVE_INTEGER_WITH_COMMA, "Create Positive Integer With Comma");
        mutationOptions.put(CREATE_NEGATIVE_FLOATING_POINT_WITH_COMMA, "Create Negative Floating Point With Comma");
        mutationOptions.put(CREATE_POSITIVE_FLOATING_POINT_WITH_COMMA, "Create Positive Floating Point With Comma");
        mutationOptions.put(CREATE_SCIENTIFIC_NOTATION_NUMBER, "Create Scientific Notation Number");
        mutationOptions.put(CREATE_ZERO, "Create Zero");
        mutationOptions.put(CONVERT_TO_EUROPEAN_STYLE, "Convert to European Style");
        mutationOptions.put(REPLACE_CHARACTER, "Replace Character");
        mutationOptions.put(APPEND_NUMBER_OF_CHARACTERS, "Append Number of Characters");
        mutationOptions.put(TRUNCATE_NUMBER_OF_CHARACTERS, "Truncate Number of Characters");
        mutationOptions.put(EMPTIFY, "Emptify");
        mutationOptions.put(CONVERT_TO_MULTIPLE_SPACES, "Convert to Multiple Spaces");
        mutationOptions.put(ADD_LEADING_SPACE, "Add Leading Space");
        mutationOptions.put(ADD_TRAILING_SPACE, "Add Trailing Space");
        mutationOptions.put(CREATE_RANDOM_STRING, "Create Random String");
        mutationOptions.put(ADD_SPECIAL_CHARACTERS, "Add Special Characters");
        mutationOptions.put(CREATE_TODAY_DATE, "Create Today Date");
        mutationOptions.put(CREATE_PAST_OR_FUTURE_DATE, "Create Past or Future Date");
        mutationOptions.put(CREATE_ALWAYS_INVALID_DATES, "Create Always Invalid Dates");
        mutationOptions.put(CREATE_DATE_IN_ALL_FORMATS_EXCEPT, "Create Date in All Formats Except");
        mutationOptions.put(CREATE_DATE_IN_FORMAT, "Create Date in Format");
        mutationOptions.put(CREATE_LEAP_DATES, "Create Leap Dates");
        
        return mutationOptions;
    }


    public static String[] getRequiredParametersForMutationOption(int mutationOption) {
        String[] parameters = null;

        // NumberMutation: no method that requires additional parameters

        // StringMutation
        if(mutationOption == REPLACE_CHARACTER) {
            parameters = new String[]{"Character To Be Replaced", "Character To Replace"};
        }
        else if(mutationOption == APPEND_NUMBER_OF_CHARACTERS) {
            parameters = new String[]{"How Many Characters To Append"};
        }
        else if(mutationOption == TRUNCATE_NUMBER_OF_CHARACTERS) {
            parameters = new String[]{"How Many Characters To Truncate"};
        }
        else if(mutationOption == CREATE_RANDOM_STRING) {
            parameters = new String[]{"Length of the String"};
        }

        // DateTimeMutation
        else if(mutationOption == CREATE_PAST_OR_FUTURE_DATE) {
            parameters = new String[]{"Days Variance"};
        }
        else if(mutationOption == CREATE_DATE_IN_ALL_FORMATS_EXCEPT) {
            parameters = new String[]{"Date Format to Exclude"};
        }
        else if(mutationOption == CREATE_DATE_IN_FORMAT) {
            parameters = new String[]{"Date Format"};
        }

        // those that have not been mentioned do not require any additional parameters
        else {
            parameters = new String[]{};
        }

        return parameters;
    }


}
