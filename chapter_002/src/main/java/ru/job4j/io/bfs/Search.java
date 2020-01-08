package ru.job4j.io.bfs;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/**
 * Search - class for searching files in File System.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Search {
    /**
     * The method is looking for files which have extension from extensions list.
     * This method uses Breadth-first search (BFS) for traversing file system tree.
     * @param parent - root directory.
     * @param exts - extensions list.
     * @return - list of files from parent which have extension from exts.
     */
    public List<File> files(String parent, List<String> exts) {
        List<File> files = new ArrayList<>();
        if (exts != null) {
            Queue<File> queue = new ArrayDeque<>();
            queue.offer(new File(parent));
            while (!queue.isEmpty()) {
                File[] branch = queue.peek().listFiles();
                if (branch != null) {
                    for (File file : branch) {
                        queue.offer(file);
                    }
                }
                File curFile = queue.poll();
                if (this.checkFileExtension(exts, curFile)) {
                    files.add(curFile);
                }
            }
        }
        return files;
    }

    /**
     * Checks if the specified file have an extension matches some item in the extensions list.
     * @param exts - extensions list.
     * @param file - checked file.
     * @return - true if the checked file extension matches some item in the exts.
     *           false - otherwise.
     */
    private boolean checkFileExtension(List<String> exts, File file) {
        boolean result = false;
        if (file.exists() && file.isFile()) {
            for (String ext : exts) {
                if (file.getName().endsWith(ext)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Is looking for files in root directory which meet a filter condition.
     * @param parent - root directory.
     * @param val - unknown type parameter.
     * @param biFilter - filter for 2 parameters (one of them - unknown).
     * @param <T> - unknown type.
     * @return - list of files from parent which meet a filter condition.
     */
    public <T> List<File> filesBiFilter(String parent, T val, BiFunction<T, File, Boolean> biFilter) {
        List<File> files = new ArrayList<>();
        if (val != null) {
            Queue<File> queue = new ArrayDeque<>();
            queue.offer(new File(parent));
            while (!queue.isEmpty()) {
                File[] branch = queue.peek().listFiles();
                if (branch != null) {
                    for (File file : branch) {
                        queue.offer(file);
                    }
                }
                File curFile = queue.poll();
                if (biFilter.apply(val, curFile)) {
                    files.add(curFile);
                }
            }
        }
        return files;
    }

    /**
     * Is looking for files in root directory which meet a predicate condition.
     * @param parent - root directory.
     * @param val - unknown parameter.
     * @param biPredicate - predicate for 2 parameters (first of them - unknown).
     * @param <T> - unknown type.
     * @return - list of files from parent which meet a predicate condition.
     */
    public <T> List<File> files(String parent, T val, BiPredicate<T, File> biPredicate) {
        List<File> files = new ArrayList<>();
            Queue<File> queue = new ArrayDeque<>();
            queue.offer(new File(parent));
            while (!queue.isEmpty()) {
                File[] branch = queue.peek().listFiles();
                if (branch != null) {
                    for (File file : branch) {
                        queue.offer(file);
                    }
                }
                File curFile = queue.poll();
                if (biPredicate.test(val, curFile)) {
                    files.add(curFile);
                }
            }
        return files;
    }
}

