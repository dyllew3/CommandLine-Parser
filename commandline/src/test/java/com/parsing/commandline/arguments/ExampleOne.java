package com.parsing.commandline.arguments;

import com.parsing.commandline.Argument;

/**
 * Example class using Argument Annotation.
 */
public class ExampleOne {

    @Argument(name = "-invalid", names = { "-e", "--example" }, description = "Example one field", required = false)
    private String invalidField;

    @Argument(names = { "-g", "--garbage" }, description = "Example array", required = false)
    private String[] exampleArray;

    @Argument(name = "-h", description = "Example array", required = false, separator = ";")
    private String[] customSeparatorArray;

    /**
     * Getter for customSeparatorArray.
     * @return customSeparatorArray field.
     */
    public String[] getCustomSeparatorArray() {
        return customSeparatorArray;
    }

    /**
     * Getter for invalidField.
     * @return invalidField field.
     */
    public String getInvalidField() {
        return invalidField;
    }

    /**
     * Setter for invalidField.
     * @param invalidField new invalid field value.
     */
    public void setInvalidField(String invalidField) {
        this.invalidField = invalidField;
    }

    /**
     * Getter for exampleArray.
     * @return exampleArray field.
     */
    public String[] getExampleArray() {
        return exampleArray;
    }

    /**
     * Setter for exampleArray.
     * @param exampleArray new example array value.
     */
    public void setExampleArray(String[] exampleArray) {
        this.exampleArray = exampleArray;
    }

    /**
     * Setter for customSeparatorArray.
     * @param customSeparatorArray new custom separator array value
     */
    public void setCustomSeparatorArray(String[] customSeparatorArray) {
        this.customSeparatorArray = customSeparatorArray;
    }
}
