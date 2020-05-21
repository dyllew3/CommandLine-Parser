package com.parsing.commandline.arguments;

import com.parsing.commandline.Argument;
import java.util.Arrays;

/**
 * Example class using Argument Annotation.
 */
public class ExampleTwo {

    @Argument(names = { "-e", "--example" }, description = "Example one field", required = false)
    private String validField;

    @Argument(names = { "-g", "--garbage" }, description = "Example array", required = false)
    private String[] exampleArray;

    /**
     * Getter for invalidField.
     * @return invalidField field.
     */
    public String getValidField() {
        return validField;
    }

    /**
     * Setter for invalidField.
     * @param invalidField invalid field value.
     */
    public void setValidField(String invalidField) {
        this.validField = invalidField;
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof ExampleTwo)) {
            return false;
        }
        final ExampleTwo required = (ExampleTwo) object;

        return Arrays.equals(this.exampleArray, required.getExampleArray())
            && this.validField.equals(required.getValidField());
    }
}
