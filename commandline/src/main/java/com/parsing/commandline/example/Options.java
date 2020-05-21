package com.parsing.commandline.example;

import com.parsing.commandline.Argument;

/**
 * Example of using the command line arguments interface.
 */
public class Options {
    /**
     * Filename.
     */
    @Argument(name = "-f", description = "Filename", required = true)
    private String filename;

    /**
     * Numbers.
     */
    @Argument(name = "-n", description = "Numbers")
    private String[] numbers;

    /**
     * Get filename.
     * @return
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Get numbers.
     * @return String array;
     */
    public String[] getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        String numberString = String.join(",", this.numbers);
        return String.format("-f=%s -n=%s", this.filename, numberString);
    }
}
