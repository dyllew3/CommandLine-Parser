# CommandLine-Parser

Command line flags parser for java

## What is it

Simple minimal flag parsing for parsing commandline arguments
into java classes. Currently the only field values for these classes are strings or string arrays.
Looking to expand it to any value or providing way for user to supply conversion function.
If it cannot parse the arguments into the class it will return a null value and print out where the issues arose.
Flags must be in the form %(FLAG_NAME)=%(VALUE)

## How to use it

This will run through the basics of using it and provide some limited examples.

### Creating class for command line arguments

First a class in java needs to be created which will have the argument values loaded into it.
Field values must be either string or string arrays. When you have created the fields to specify that
they are command line argument flags use the Argument annotation. The class should either not have a constructor
or if it has a constructor then it must have a constructor which takes no parameters in order for the loading of the class
to work.

The Argument annotation has the following fields:

+ name: name of the command line flag, required if names field not set
+ names: all valid names for the flag, required if name field not set
+ description: description of the flag, required
+ required: boolean representing whether the flag is required or not, this field is not required and default is false
+ separator: Separator used to split string into string array for the class' field, this field is not required and default is ","
  + example separator = ";" on string "1;2;3;4" => {"1","2","3","4"}

For the name/names field it will try to match the name directly with the input provided so for the flag "--g"
the name in the annotation should also be @Argument(name= "--g").

Full Example:

```java
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
     * Custom separators.
     */
    @Argument(names = {"-c", "-separ"}, description = "Custom separators", separator = ";")
    public String[] customSeparators;

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
}
```

### Command Line arguments

Each flag argument should come in the form "%(FLAG)=%(VALUE)" where %(FLAG) is the name of the flag
and %(VALUE) is the value associated with flag and must have an equals symbol separating them.
For example java -cp .\commandline\target\commandline-1.0.jar  com.parsing.commandline.App -f=blah -n="1,2,3,4" when running packaged version from base directory.

### Loading them into class

To parse them use the parseArguments static method in the ArgumentsParser class. It takes two inputs the class type that will be created
and the flags as an array of strings. The string array should be in the form { "--f=blah", "-n=1,2,3" } where each element has contains the flag
argument( "%(FLAG)=%(VALUE)" ).

If it is unable to load the class it will return a null value and print the reason for failure.
If it is successful then an instance of the class will be created with the fields of the class populated with the correct values(hopefully)

Example:

```java
Options b = ArgumentsParser.parseArguments(Options.class, arguments);
```
