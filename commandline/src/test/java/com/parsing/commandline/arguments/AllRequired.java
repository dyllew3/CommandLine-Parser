package com.parsing.commandline.arguments;

import com.parsing.commandline.Argument;
import java.util.Arrays;

/**
 * Class where all arguments are required.
 */
public class AllRequired {

    @Argument(name = "-e", description = "Example one field", required = true)
    private String exampleOne;

    @Argument(name = "-g", description = "Example array", required = true)
    private String[] exampleArray;

    @Argument(name = "-h", description = "Example array", required = true, separator = ";")
    private String[] customSeparatorArray;

    /**
     * Constructor.
     */
    public AllRequired() {
    }

    /**
     * Constructor.
     * @param exampleOne Value for example one field.
     * @param exampleArray Value for exampleArray field.
     * @param customSeparator CUstom separator array.
     */
    public AllRequired(final String exampleOne, final String[] exampleArray, final String[] customSeparator) {
        this.customSeparatorArray = customSeparator;
        this.exampleArray = exampleArray;
        this.exampleOne = exampleOne;
    }

    /**
     * Get exampleOne field.
     * @return String value of exampleOne field.
     */
    public String getExampleOne() {
        return exampleOne;
    }

    /**
     * Get exampleArray field.
     * @return String value of exampleArray field.
     */
    public String[] getExampleArray() {
        return exampleArray;
    }

    /**
     * Get customSeparatorArray field.
     * @return String array value of customSeparatorArray field.
     */
    public String[] getCustomSeparators() {
        return customSeparatorArray;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof AllRequired)) {
            return false;
        }
        final AllRequired required = (AllRequired) object;

        return Arrays.equals(this.customSeparatorArray, required.getCustomSeparators())
            && Arrays.equals(this.exampleArray, required.getExampleArray())
            && this.exampleOne.equals(required.getExampleOne());
    }
}
