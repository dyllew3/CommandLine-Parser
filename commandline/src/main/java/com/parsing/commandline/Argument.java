package com.parsing.commandline;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Argument interface which will be used to define command line
 * arguments to be parsed. Needs to be retained for runtime.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Argument {
    /**
     * The names that the argument can have.
     * If this is set then the name field cannot be set.
     */
    String[] names() default {};

    /**
     * The name of the argument.
     * If this is set the names field cannot be set
     */
    String name() default "";

    /**
     * Description of the argument.
     */
    String description();

    /**
     * Whether the argument is required.
     */
    boolean required() default false;

    /**
     * Separator of elements if argument is a list of strings.
     */
    String separator() default ",";
}
