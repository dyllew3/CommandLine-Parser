package com.parsing.commandline;

/**
 * Exception for errors during parsing.
 */
public class ParsingException extends Exception {
    /**
     * Serial version id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * @param message Error message.
     */
    public ParsingException(String message) {
        super(message);
    }

    /**
     * Constructor.
     * @param message Error message.
     * @param err Error being thrown.
     */
    public ParsingException(String message, Throwable err) {
        super(message, err);
    }
}
