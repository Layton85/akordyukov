package io.find;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.BiPredicate;
import java.util.regex.PatternSyntaxException;

import io.bfs.Search;

/**
 * Find - class which searches files in specified directory using file-name mask-list
 * and outputs result into specified log file.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Find {
    /** command line args */
    private Args args;

    /**
     * Constructor.
     * @param args - command line arguments.
     */
    public Find(String[] args) {
        this.args = new Args(args);
    }

    /**
     * Searches for files in specified directory which pass filters (see args)
     * and outputs result files names list into specified log file.
     */
    public void find() {
        List<File> files = new ArrayList<>();
        BiPredicate<List<String>, File> predicate = new SearchPredicateFactory().getPredicate(this.args.getMaskMode());
        try {
            files = new Search()
                    .files(this.args.getDirectory(), this.args.getMaskList(), predicate);
        } catch (PatternSyntaxException e) {
            System.out.println("Invalid Regular Expression:");
            System.out.println(e.getMessage());
        }
        this.write(files);
    }

    /**
     * Writes log file.
     * @param files - resulting files list.
     */
    private void write(List<File> files) {
        StringJoiner foundedFileNames = new StringJoiner(System.lineSeparator());
        for (File file : files) {
            foundedFileNames.add(file.getAbsolutePath());
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.args.getOutput()))) {
            writer.write(foundedFileNames.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method.
     * @param args - command line args:
     *             -d - search directory;
     *             -n - list of filename masks, which should be split by ";" symbol;
     *             -o - full output log-file path;
     *             -help - print help.
     */
    public static void main(String[] args) {
        new Find(args).find();
    }
}