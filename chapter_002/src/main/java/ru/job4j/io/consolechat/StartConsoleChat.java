package ru.job4j.io.consolechat;

import org.apache.commons.cli.*;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * StartConsoleChat - Entry point class into console chat.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class StartConsoleChat {
    /**
     * Main-method.
     * @param args - command line arguments.
     *             -s (--source) <file path> - path to the chat phrases file.
     *             -l (--log) <file path> - path to the chat log.
     *             -help - print help.
     */
    public static void main(String[] args) {
        List<String> paths = parse(args);
        Chat chat = new Chat(paths.get(0), paths.get(1));
        Scanner scanner = new Scanner(System.in);
        System.out.println("----- Our chat started ! -----");
        String str = scanner.nextLine();
        while (!str.equals(chat.getExitWord())) {
            String answer = chat.ask(str);
            if (answer != null) {
                System.out.println(answer);
            }
            str = scanner.nextLine();
        }
        chat.ask(str);
        chat.writeLog();
    }

    /**
     * Helper method for command line parsing.
     * @param args - command line arguments.
     * @return - list which contains two elements:
     *              0 position - chat phrases file path;
     *              1 position - log file path.
     */
    private static List<String> parse(String[] args) {
        List<String> result = new ArrayList<>();
        String phrasesPath = "";
        String logPath = "";
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption("s", "source", true, "source file");
        options.addOption("l", "log", true, "log file");
        options.addOption("help", false, "Print help");
        try {
            CommandLine line = parser.parse(options, args);
            if (line.hasOption("help")) {
                StringJoiner joiner = new StringJoiner(System.lineSeparator());
                joiner.add("ConsoleChat");
                joiner.add("Special words:");
                joiner.add("stop - chat pause");
                joiner.add("continue - continue chat");
                joiner.add("exit - exit ConsoleChat");
                new HelpFormatter().printHelp(joiner.toString(), options);
                System.exit(0);
            } else {
                if (line.hasOption("s")) {
                    if (!Files.isRegularFile(Paths.get(line.getOptionValue("s")), LinkOption.NOFOLLOW_LINKS)) {
                        throw new ParseException("Incorrect source path: it is not a file");
                    } else {
                        phrasesPath = line.getOptionValue("s");
                    }
                }
                if (line.hasOption("l")) {
                    if (!Files.isRegularFile(Paths.get(line.getOptionValue("l")), LinkOption.NOFOLLOW_LINKS)) {
                        throw new ParseException("Incorrect log path: it is not a file");
                    } else {
                        logPath = line.getOptionValue("l");
                    }
                }
                if (!line.hasOption("s") || !line.hasOption("l")) {
                    throw new ParseException("Missing option");
                }
            }
        } catch (ParseException exp) {
            System.out.println("Unexpected exception during command line parsing occured: " + exp.getMessage());
            new HelpFormatter().printHelp("ConsoleChat", options);
            System.exit(0);
        }
        result.add(phrasesPath);
        result.add(logPath);
        return result;
    }
}
