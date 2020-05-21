package com.parsing.commandline;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for parsing string arguments into a class.
 */
public final class ArgumentsParser {

    private ArgumentsParser() {
    }

    /**
     * Parse commandline Arguments into a class.
     * @param <T> Type that has the arguments.
     * @param clazz The class type that has the arguments, needed for reflection.
     * @param arguments Arguments to be parsed to options.
     * @return Either null or an instance of the class containing the arguments.
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @SuppressWarnings("unchecked")
    public static <T> T parseArguments(final Class<T> clazz, final String... arguments) {
        Map<String, String> givenArguments = loadArguments(arguments);

        try {
            // Create instance of Options class
            Constructor<?> constructor = getDefaultConstructor(clazz);
            constructor.setAccessible(true);
            T result = (T) constructor.newInstance();

            for (final Field field : clazz.getDeclaredFields()) {
                Argument optionAnno = field.getAnnotation(Argument.class);

                // Try and get the value for the option field
                try {
                    if (optionAnno != null) {

                        String optionValue = getArgumentValue(optionAnno, givenArguments);
                        Class<?> fieldType = field.getType();
                        field.setAccessible(true);

                        if (optionValue == null && optionAnno.required()) {

                            // Get annotation name for error
                            String annoName = !optionAnno.name().equals("")
                                ? optionAnno.name() : optionAnno.names()[0];
                            throw new ParsingException(String.format("Required value not set %s", annoName));

                        } else if (fieldType.isAssignableFrom(String[].class)) {

                            // Split string into an array of strings if that is the argument type
                            String[] argValues = optionValue.split(optionAnno.separator());
                            field.set(result, argValues);

                        } else {

                            field.set(result, optionValue);
                        }
                    }
                } catch (IllegalArgumentException | ParsingException | IllegalAccessException e) {
                    String errorMsg = String.format("Exception occured on option field %s with error: %s",
                        field.getName(), e.getMessage());
                    throw new ParsingException(errorMsg, e);
                }
            }

            return result;

        } catch (ParsingException | InstantiationException | IllegalAccessException
            | IllegalArgumentException | InvocationTargetException exception) {
            System.out.println(String.format(
                "Error occured when parsing for class %s with error: %s", clazz.getName(), exception.getMessage()));
            return null;
        }
    }

    /**
     * Load the arguments into a map.
     * @param arguments List of arguments to be loaded into map.
     * @return Map containing arguments and their values.
     */
    private static Map<String, String> loadArguments(final String... arguments) {
        Map<String, String> givenArguments = new HashMap<String, String>();

        // Load all the arguments in
        for (final String argument : arguments) {

            final String[] splitValues = argument.split("=");

            if (splitValues.length != 2) {
                System.out.println(String.format("Invalid format for argument: %s", argument));

            } else if (splitValues[0].isBlank()) {
                System.out.println(String.format("Not value given for left side of argument %s", argument));

            } else if (!splitValues[1].isBlank()) {
                givenArguments.put(splitValues[0], splitValues[1]);

            } else {
                System.out.println(String.format("Not value given for right side of argument %s", argument));
            }
        }
        return givenArguments;
    }

    /**
     * Get the value of the argument.
     * @param arg Instance of the argument class.
     * @param arguments Mapping of valid arguments to their values.
     * @throws ParsingException
     * @return Parameter value if it is present in map otherwise null.
     */
    private static String getArgumentValue(Argument arg, Map<String, String> arguments) throws ParsingException {

        // Check that both the name and names fields have values in them
        if (!arg.name().equals("") && arg.names().length > 0) {
            String errorMsg = String.format(
                "Both the name and names fields of the option annotation were set for flag %s", arg.name());
            throw new ParsingException(errorMsg);
        }

        if (!validArgumentName(arg)) {
            String message = "The Argument annotation doesn't have any valid names";
            throw new ParsingException(message);
        }

        String value = null;
        boolean found = false;

        if (arg.name().equals("") || arg.names().length > 0) {

            for (String argumentName : arg.names()) {
                String argument = arguments.get(argumentName);

                // Check if flag has been set multiple times
                if (found && argument != null) {

                    String errorMsg = String.format("Flag has been set multiple times %s ", argument);
                    throw new ParsingException(errorMsg);

                } else if (argument != null) {
                    value = argument;
                    found = true;
                }
            }
            return value;
        } else {
            return arguments.get(arg.name());
        }
    }

    /**
     * Determine if all names for the argument are valid.
     * @param argument argument name to validate
     * @return whether the argument has a valid name or valid names
     */
    private static boolean validArgumentName(Argument argument) {
        if (argument.name().isBlank() && argument.names().length == 0) {
            return false;
        }

        if (!argument.name().isBlank()) {
            return true;
        }

        // Check that all names in the argument are not blank
        for (String name : argument.names()) {
            if (name.isBlank()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Get the default constructor of the class.
     * @param classType The type of class.
     * @return Constructor of class with zero arguments.
     */
    public static Constructor<?> getDefaultConstructor(Class<?> classType) {
        final Constructor<?>[] constructors = classType.getConstructors();

        for (final Constructor<?> ctor : constructors) {

            if (ctor.getGenericParameterTypes().length == 0) {
                return ctor;
            }
        }
        return null;
    }
}
