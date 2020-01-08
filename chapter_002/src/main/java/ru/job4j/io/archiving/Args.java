package ru.job4j.io.archiving;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import org.apache.commons.cli.*;

/**
 * Args - helper-class for holding command line parameters for Zip class.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Args {
    /** source directory */
    private String directory;

    /** excluded extensions list */
    private List<String> excludeList = new ArrayList<>();

    /** full destination file path */
    private String output;

    /**
     * Constructor.
     * Parsing command line arguments using Apache Commons-cli.
     * @param args - command line arguments.
     */
    public Args(String ... args) {
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption("d", true, "archiving directory");
        Option excludeExtOption = new Option("e", true, "excluded extensions");
        excludeExtOption.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(excludeExtOption);
        options.addOption("o", true, "full output file name");
        options.addOption("help", false, "Print help");
        try {
            CommandLine line = parser.parse(options, args);
            if (line.hasOption("help")) {
                new HelpFormatter().printHelp("Zip", options);
                System.exit(0);
            } else {
                if (line.hasOption("d")) {
                    this.directory = line.getOptionValue("d");
                }
                if (line.hasOption("o")) {
                    this.output = line.getOptionValue("o");
                }
                if (line.hasOption("e")) {
                    String[] exts = line.getOptionValues("e");
                    for (String ext : exts) {
                        this.excludeList.add(ext);
                    }
                }
                if (!line.hasOption("d") || !line.hasOption("e") || !line.hasOption("o")) {
                    throw new ParseException("Missing option");
                }
            }
        } catch (ParseException exp) {
            System.out.println("Unexpected exception during command line parsing occured: " + exp.getMessage());
            new HelpFormatter().printHelp("Zip", options);
            System.exit(0);
        }
    }

    /**
     * Alternative method for parsing command line args without any external libraries.
     * @param args - command line args.
     */
    private void parseCommandLineWithoutCommonsCLI(String ... args) {
        List<String> inputArgs = new ArrayList<>();
        for (String arg : args) {
            inputArgs.add(arg);
        }
        BiPredicate<Integer, List<String>> argsPredicate = (Integer index, List<String> arguments) -> {
            return index != -1 && arguments.size() > (index + 1) && arguments.get(index + 1).charAt(0) != '-';
        };
        int i = inputArgs.indexOf("-d");
        if (argsPredicate.test(i, inputArgs)) {
                this.directory = inputArgs.get(i + 1);
        } else {
            throw new IllegalArgumentException("Illegal directory argument has ocured");
        }
        i = inputArgs.indexOf("-e");
        if (argsPredicate.test(i, inputArgs)) {
            String excludeArg = inputArgs.get(i + 1);
            for (String exArg : excludeArg.split(";")) {
                String[] str = exArg.split("\\.");
                this.excludeList.add("." + str[str.length - 1]);
            }
        } else {
            throw new IllegalArgumentException("Illegal exclude argument has ocured");
        }
        i = inputArgs.indexOf("-o");
        if (argsPredicate.test(i, inputArgs)) {
            this.output = inputArgs.get(i + 1);
        } else {
            throw new IllegalArgumentException("Illegal output argument has ocured");
        }
    }

    /**
     * Get-method.
     * @return - source directory.
     */
    public String directory() {
        return directory;
    }

    /**
     * Get-method.
     * @return - excluded extensions list.
     */
    public List<String> exclude() {
        return excludeList;
    }

    /**
     * Get-mthod.
     * @return - full destination file path.
     */
    public String output() {
        return output;
    }
}