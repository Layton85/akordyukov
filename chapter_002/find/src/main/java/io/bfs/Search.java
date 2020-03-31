package io.bfs;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.function.BiPredicate;

/**
 * Search - class for searching files in File System.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Search {
    /**
     * Is looking for files in root directory which meet a predicate condition.
     * @param parent - root directory.
     * @param val - unknown parameter. It is possible to use it like a list of conditions for file.
     * @param biPredicate - predicate for 2 parameters (first of them - unknown).
     * @param <T> - unknown type. For example List<String> - list of conditions for files.
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

