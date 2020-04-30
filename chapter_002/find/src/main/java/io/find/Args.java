package io.find;

import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Args - helper-class which is holding command line parameters for the Find class.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Args {
    /** search directory */
    private String directory;

    /** list of filename masks */
    private List<String> maskList = new ArrayList<>();

    /** mask Mode:
        * m - maximum coincidence of file name and search key
        * f - full coincidence of file name and search key
        * r - search key - is a regular expression
     */
    private String maskMode = null;

    /** full output log-file path */
    private String output;

    /**
     * Getter.
     * @return - search directory
     */
    public String getDirectory() {
        return this.directory;
    }

    /**
     * Getter.
     * @return - maskList
     */
    public List<String> getMaskList() {
        return this.maskList;
    }

    /**
     * Getter
     * @return - mask mode string.
     */
    public String getMaskMode() {
        return this.maskMode;
    }

    /**
     * Getter.
     * @return - full output log-file path
     */
    public String getOutput() {
        return this.output;
    }

    /**
     * Constructor.
     * Parsing command line arguments using Apache Commons-cli.
     * @param args - command line arguments.
     */
    public Args(String ... args) {
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption("d", true, "search directory");
        Option maskOption = new Option("n", true, "list of filename masks");
        maskOption.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(maskOption);
        OptionGroup maskModeOptionGroup = new OptionGroup();
        maskModeOptionGroup.addOption(new Option("m", false,
                "maximum coincidence of file name and search key"));
        maskModeOptionGroup.addOption(new Option("f", false,
                "full coincidence of file name and search key"));
        maskModeOptionGroup.addOption(new Option("r", false,
                "search key - is a regular expression"));
        options.addOptionGroup(maskModeOptionGroup);
        ArrayList<Option> maskModeOptions = new ArrayList<>(maskModeOptionGroup.getOptions());
        boolean maskModeOptionsIsGiven = false;
        options.addOption("o", true, "full output log-file path");
        options.addOption("help", false, "Print help");
        try {
            CommandLine line = parser.parse(options, args);
            if (line.hasOption("help")) {
                new HelpFormatter().printHelp("Find", options);
                System.exit(0);
            } else {
                if (line.hasOption("d")) {
                    this.directory = line.getOptionValue("d");
                }
                if (line.hasOption("o")) {
                    this.output = line.getOptionValue("o");
                }
                if (line.hasOption("n")) {
                    String[] masks = line.getOptionValues("n");
                    for (String mask : masks) {
                        this.maskList.add(mask);
                    }
                }
                for (Option option : maskModeOptions) {
                    if (line.hasOption(option.getOpt())) {
                        this.maskMode = option.getOpt();
                        maskModeOptionsIsGiven = true;
                        break;
                    }
                }
                if (!line.hasOption("d") || !line.hasOption("n") || !line.hasOption("o") || !maskModeOptionsIsGiven) {
                    throw new ParseException("Missing option");
                }
            }
        } catch (ParseException exp) {
            System.out.println("Unexpected exception during command line parsing occured: " + exp.getMessage());
            new HelpFormatter().printHelp("Find", options);
            System.exit(0);
        }
    }
}
