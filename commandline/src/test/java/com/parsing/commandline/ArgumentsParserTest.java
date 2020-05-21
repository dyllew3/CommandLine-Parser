package com.parsing.commandline;

import com.parsing.commandline.arguments.AllRequired;
import com.parsing.commandline.arguments.ExampleOne;
import com.parsing.commandline.arguments.ExampleTwo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for ArgumentsParser.
 */
class ArgumentsParserTest {

    private String allFlagsString = "-e=%s -g=%s -h=%s";

    /**
     * Test that a null instance of a class is given when a required field is not supplied.
     */
    @Test
    void testRequiredInvalidFields() {
        String invalidInput = "";
        String validArray = "1,2,3";
        String validCustomArray = "1;2;3";
        String validField = "test";

        // Test invalid input for string field
        String invalidFlagsString = String.format(allFlagsString,
            invalidInput, validArray, validCustomArray);
        assertEquals(null, ArgumentsParser.parseArguments(AllRequired.class, invalidFlagsString.split(" ")),
            "Should return null as required value not given for exampleOne field");

        // Test invalid input for string array field
        invalidFlagsString = String.format(allFlagsString,
            validField, invalidInput, validCustomArray);
        assertEquals(null, ArgumentsParser.parseArguments(AllRequired.class, invalidFlagsString.split(" ")),
            "Should return null as required value not given for exampleArray");

        // Test invalid input for string custom array field
        invalidFlagsString = String.format(allFlagsString,
            validField, validArray, invalidInput);
        assertEquals(null, ArgumentsParser.parseArguments(AllRequired.class, invalidFlagsString.split(" ")),
            "Should return null as required value not given for customSeparatorArray");
    }

    /**
     * Test that a null instance of a class is given when a required field is not supplied.
     */
    @Test
    void testRequiredValidFields() {
        String validArray = "1,2,3";
        String validCustomArray = "1;2;3";
        String validField = "test";

        AllRequired allRequired = new AllRequired(validField, validArray.split(","), validCustomArray.split(";"));

        // Test invalid input for string field
        String validFlagsString = String.format(allFlagsString,
            validField, validArray, validCustomArray);
        assertEquals(allRequired, ArgumentsParser.parseArguments(AllRequired.class, validFlagsString.split(" ")),
            "Should return valid value as required values given every field");
    }

    /**
     * Test that a null instance of a class is given when both name and names fields are set.
     */
    @Test
    void testNamesInvalid() {
        String validField = "test";

        // Test invalid input for string field
        String validFlagsString = String.format("-e=%s", validField);
        assertEquals(null, ArgumentsParser.parseArguments(ExampleOne.class, validFlagsString.split(" ")),
            "Should return valid value as required values given every field");
    }

    /**
     * Test that names field with valid names works.
     */
    @Test
    void testNamesValid() {
        String validField = "test";
        String validArray = "1,2,3";
        ExampleTwo exampleTwo = new ExampleTwo();

        // Test invalid input for string field
        String validFlagsString = String.format("-e=%s --garbage=%s", validField, validArray);
        exampleTwo.setExampleArray(validArray.split(","));
        exampleTwo.setValidField(validField);

        assertEquals(exampleTwo, ArgumentsParser.parseArguments(ExampleTwo.class, validFlagsString.split(" ")),
            "Should return valid value as required values given every field");

        validFlagsString = String.format("--example=%s -g=%s", validField, validArray);

        assertEquals(exampleTwo, ArgumentsParser.parseArguments(ExampleTwo.class, validFlagsString.split(" ")),
            "Should return valid value as required values given every field");
    }
}
