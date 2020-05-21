package com.parsing.commandline;

import com.parsing.commandline.example.Options;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {

        String[] examples = new String[] {"-f=test", "-n=1,2,3" };
        System.out.println("Example:");
        Options b = ArgumentsParser.parseArguments(Options.class, examples);
        System.out.println(b.toString());
        System.out.println("-----------\n");

        System.out.println("Commandline input:");
        b = ArgumentsParser.parseArguments(Options.class, args);
        System.out.println(b.toString());
    }
}
