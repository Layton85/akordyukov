package ru.job4j.io.bfs;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * SearchTest.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SearchTest {
    private final String rootPath = System.getProperty("java.io.tmpdir");
    private final String slash = File.separator;
    private final BiFunction<List<String>, File, Boolean> biFilter = (List<String> exts, File file) -> {
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
    };
    private final BiPredicate<List<String>, File> biPredicate = (List<String> exts, File file) -> {
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
    };

    @Test
    public void whenNoAnyFiles() {
        File dir1 = new File(rootPath + slash + "rfr");
        File dir2 = new File(rootPath + slash + "rfr" + slash + "45");
        File dir3 = new File(rootPath + slash + "rfr" + slash + "45" + slash + "internal");
        File dir4 = new File(rootPath + slash + "rfr" + slash + "Next");
        dir1.mkdir();
        dir2.mkdir();
        dir3.mkdir();
        dir4.mkdir();
        Search searcher = new Search();
        assertThat(
                searcher.files(dir1.getPath(), Arrays.asList("txt")),
                is(new ArrayList<File>())
        );
        deleteDirs(dir1);
    }

    @Test
    public void whenNullExtensionsSpecified() {
        File dir1 = makeDirs();
        Search searcher = new Search();
        assertThat(
                searcher.files(dir1.getPath(), null),
                is(new ArrayList<File>())
        );
        deleteDirs(dir1);
    }

    @Test
    public void whenNoAnyExtensionsSpecified() {
        File dir1 = makeDirs();
        Search searcher = new Search();
        assertThat(
                searcher.files(dir1.getPath(), new ArrayList<>()),
                is(new ArrayList<File>())
        );
        deleteDirs(dir1);
    }

    @Test
    public void whenAbsentExtensionSpecified() {
        File dir1 = makeDirs();
        Search searcher = new Search();
        assertThat(
                searcher.files(dir1.getPath(), Arrays.asList("jpg")),
                is(new ArrayList<File>())
        );
        deleteDirs(dir1);
    }

    @Test
    public void whenOneTXTFileWasFound() throws IOException {
        File dir1 = makeDirs();
        Search searcher = new Search();
        assertThat(
                searcher.files(dir1.getPath(), Arrays.asList("txt")),
                is(Arrays.asList(new File(rootPath + slash + "rfr" + slash + "45" + slash + "1.txt")))
        );
        deleteDirs(dir1);
    }

    @Test
    public void whenOneTXTAndOneDOCFilesWasFound() throws IOException {
        File dir1 = makeDirs();
        Search searcher = new Search();
        assertThat(
                searcher.files(dir1.getPath(), Arrays.asList("txt", "doc")),
                is(Arrays.asList(
                        new File(rootPath + slash + "rfr" + slash + "2.doc"),
                        new File(rootPath + slash + "rfr" + slash + "45" + slash + "1.txt")))
        );
        deleteDirs(dir1);
    }

    @Test
    public void whenNoAnyFilesInBiFilterMethod() {
        File dir1 = new File(rootPath + slash + "rfr");
        File dir2 = new File(rootPath + slash + "rfr" + slash + "45");
        File dir3 = new File(rootPath + slash + "rfr" + slash + "45" + slash + "internal");
        File dir4 = new File(rootPath + slash + "rfr" + slash + "Next");
        dir1.mkdir();
        dir2.mkdir();
        dir3.mkdir();
        dir4.mkdir();
        Search searcher = new Search();
        assertThat(
                searcher.filesBiFilter(dir1.getPath(), Arrays.asList("txt"), this.biFilter),
                is(new ArrayList<File>())
        );
        deleteDirs(dir1);
    }

    @Test
    public void whenNullExtensionsSpecifiedInBiFilterMethod() {
        File dir1 = makeDirs();
        Search searcher = new Search();
        assertThat(
                searcher.filesBiFilter(dir1.getPath(), null, this.biFilter),
                is(new ArrayList<File>())
        );
        deleteDirs(dir1);
    }

    @Test
    public void whenNoAnyExtensionsSpecifiedInBiFilterMethod() {
        File dir1 = makeDirs();
        Search searcher = new Search();
        assertThat(
                searcher.filesBiFilter(dir1.getPath(), new ArrayList<>(), this.biFilter),
                is(new ArrayList<File>())
        );
        deleteDirs(dir1);
    }

    @Test
    public void whenAbsentExtensionSpecifiedInBiFilterMethod() {
        File dir1 = makeDirs();
        Search searcher = new Search();
        assertThat(
                searcher.filesBiFilter(dir1.getPath(), Arrays.asList("jpg"), this.biFilter),
                is(new ArrayList<File>())
        );
        deleteDirs(dir1);
    }

    @Test
    public void whenOneTXTFileWasFoundInBiFilterMethod() throws IOException {
        File dir1 = makeDirs();
        Search searcher = new Search();
        assertThat(
                searcher.filesBiFilter(dir1.getPath(), Arrays.asList("txt"), this.biFilter),
                is(Arrays.asList(new File(rootPath + slash + "rfr" + slash + "45" + slash + "1.txt")))
        );
        deleteDirs(dir1);
    }

    @Test
    public void whenOneTXTAndOneDOCFilesWasFoundInBiFilterMethod() throws IOException {
        File dir1 = makeDirs();
        Search searcher = new Search();
        assertThat(
                searcher.filesBiFilter(dir1.getPath(), Arrays.asList("txt", "doc"), this.biFilter),
                is(Arrays.asList(
                        new File(rootPath + slash + "rfr" + slash + "2.doc"),
                        new File(rootPath + slash + "rfr" + slash + "45" + slash + "1.txt")))
        );
        deleteDirs(dir1);
    }

    @Test
    public void whenNoAnyFilesInBiPredicateMethod() {
        File dir1 = new File(rootPath + slash + "rfr");
        File dir2 = new File(rootPath + slash + "rfr" + slash + "45");
        File dir3 = new File(rootPath + slash + "rfr" + slash + "45" + slash + "internal");
        File dir4 = new File(rootPath + slash + "rfr" + slash + "Next");
        dir1.mkdir();
        dir2.mkdir();
        dir3.mkdir();
        dir4.mkdir();
        Search searcher = new Search();
        assertThat(
                searcher.files(dir1.getPath(), Arrays.asList("txt"), this.biPredicate),
                is(new ArrayList<File>())
        );
        deleteDirs(dir1);
    }

    @Test
    public void whenNullExtensionsSpecifiedInBiPredicateMethod() {
        File dir1 = makeDirs();
        Search searcher = new Search();
        assertThat(
                searcher.files(dir1.getPath(), null, this.biPredicate),
                is(new ArrayList<File>())
        );
        deleteDirs(dir1);
    }

    @Test
    public void whenNoAnyExtensionsSpecifiedInBiPredicateMethod() {
        File dir1 = makeDirs();
        Search searcher = new Search();
        assertThat(
                searcher.files(dir1.getPath(), new ArrayList<>(), this.biPredicate),
                is(new ArrayList<File>())
        );
        deleteDirs(dir1);
    }

    @Test
    public void whenAbsentExtensionSpecifiedInBiPredicateMethod() {
        File dir1 = makeDirs();
        Search searcher = new Search();
        assertThat(
                searcher.files(dir1.getPath(), Arrays.asList("jpg"), this.biPredicate),
                is(new ArrayList<File>())
        );
        deleteDirs(dir1);
    }

    @Test
    public void whenOneTXTFileWasFoundInPredicateMethod() throws IOException {
        File dir1 = makeDirs();
        Search searcher = new Search();
        assertThat(
                searcher.files(dir1.getPath(), Arrays.asList("txt"), this.biPredicate),
                is(Arrays.asList(new File(rootPath + slash + "rfr" + slash + "45" + slash + "1.txt")))
        );
        deleteDirs(dir1);
    }

    @Test
    public void whenOneTXTAndOneDOCFilesWasFoundInBiPredicateMethod() throws IOException {
        File dir1 = makeDirs();
        Search searcher = new Search();
        assertThat(
                searcher.files(dir1.getPath(), Arrays.asList("txt", "doc"), this.biPredicate),
                is(Arrays.asList(
                        new File(rootPath + slash + "rfr" + slash + "2.doc"),
                        new File(rootPath + slash + "rfr" + slash + "45" + slash + "1.txt")))
        );
        deleteDirs(dir1);
    }


    /**
     * Recursively deletes all files and directories in specified dir including it.
     * @param dir - directory which should be recursively deleted.
     */
    private void deleteDirs(File dir) {
        try {
            assertThat(this.recursiveDelete(dir), is(true));
        } catch (Exception e) {
            System.out.println("There was a problem with the directory removal:");
            e.printStackTrace();
        }
    }

    /**
     * Prepares standard directories and files for this test.
     * @return - top-level directory.
     */
    private File makeDirs() {
        File dir1 = new File(rootPath + slash + "rfr");
        File dir2 = new File(rootPath + slash + "rfr" + slash + "45");
        File file1 = new File(rootPath + slash + "rfr" + slash + "45" + slash + "1.txt");
        File file2 = new File(rootPath + slash + "rfr" + slash + "2.doc");
        File dir3 = new File(rootPath + slash + "rfr" + slash + "Next");
        try {
            dir1.mkdir();
            dir2.mkdir();
            dir3.mkdir();
            file1.createNewFile();
            file2.createNewFile();
        } catch (Exception e) {
            System.out.println("There was a problem with the directory creation:");
            e.printStackTrace();
        }
        return dir1;
    }

    /**
     * Helper method which recursively deletes all levels of nested files and directories in a specified directory.
     * @param dir - the directory which should be cleaned.
     * @return - true - if specified directory was cleaned;
     *           false - otherwise.
     */
    private boolean recursiveDelete(File dir) {
        boolean result = false;
        if (dir.exists()) {
            if (dir.isDirectory()) {
                for (File f : dir.listFiles()) {
                    recursiveDelete(f);
                }
                dir.delete();
            } else {
                dir.delete();
            }
            result = true;
        }
        return result;
    }
}