package ru.job4j.io.archiving;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.function.BiPredicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import ru.job4j.io.bfs.Search;

/**
 * Zip - class for archiving directories in File System with ability to determine
 * source and destination directories and the list of excluded file extensions.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Zip {
    /** command line args:
     *          -d - directory for archiving;
     *          -e - list of file extensions which excluded from archiving, extensions should be split by ";" symbol;
     *          -o - full destination archive path;
     *          -help - print help.
     */
    private Args args;

    /**
     * Constructor.
     * @param args - command line arguments (see args).
     */
    public Zip(String[] args) {
        this.args = new Args(args);
    }

    /**
     * Packs the directory files which specified in command line arguments into the zip archive.
     * Uses Breadth-first search (BFS) for traversing file system tree (realization from ru.job4j.io.bfs.Search).
     * Archiving parameters such as source and destination folders, exclusion file extensions
     * are specified in the command line arguments.
     */
    public void pack() {
        List<File> files = new Search().files(this.args.directory(), this.args.exclude(), new BiPredicate<List<String>, File>() {
            @Override
            public boolean test(List<String> exts, File file) {
                boolean result = true;
                if (file.exists() && file.isFile()) {
                    for (String ext : exts) {
                        if (file.getName().endsWith(ext)) {
                            result = false;
                            break;
                        }
                    }
                }
                return result;
            }
        });
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(this.args.output())))) {
            for (File file : files) {
                if (file.isFile()) {
                    zip.putNextEntry(new ZipEntry(file.getPath()));
                    try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                        zip.write(out.readAllBytes());
                    }
                } else if (file.isDirectory()) {
                    String dirName = file.getPath();
                    if (!dirName.endsWith(File.separator)) {
                        dirName += File.separator;
                    }
                    zip.putNextEntry(new ZipEntry(dirName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method.
     * @param args - command line arguments (see args).
     */
    public static void main(String[] args) {
        new Zip(args).pack();
    }

    /**
     * Do not using.
     * Instead it uses method files(String parent, T val, BiPredicate<T, File> biPredicate) from ru.job4j.io.bfs.Search.
     * Is looking for files in the specified root directory with extensions does not appear
     * in the specified extensions list.
     * This method uses Breadth-first search (BFS) for traversing file system tree.
     * @param root - root directory.
     * @param exts - extensions list.
     * @return - list of files with extensions does not appear in the specified extensions list.
     */
    public List<File> seekBy(String root, List<String> exts) {
        ArrayList<File> files = new ArrayList<>();
        Queue<File> queue = new ArrayDeque<>();
        queue.offer(new File(root));
        while (!queue.isEmpty()) {
            File[] branch = queue.peek().listFiles();
            if (branch != null) {
                for (File file : branch) {
                    queue.offer(file);
                }
            }
            File file = queue.poll();
            boolean exclude = false;
            for (String ext : exts) {
                if (file.getName().endsWith(ext)) {
                    exclude = true;
                    break;
                }
            }
            if (!exclude) {
                files.add(file);
            }
        }
        return files;
    }
}