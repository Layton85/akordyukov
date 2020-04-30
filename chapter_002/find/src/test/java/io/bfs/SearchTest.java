package io.bfs;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private final BiPredicate<List<String>, File> biPredicate = (List<String> exts, File file) -> {
        boolean result = false;
        if (file.exists() && file.isFile() && exts != null) {
            for (String ext : exts) {
                if (file.getName().endsWith(ext)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    };

    @Rule
    public TemporaryFolder root = new TemporaryFolder();

    @Test
    public void whenNoAnyFilesInBiPredicateMethod() throws IOException {
        File dir1 = root.newFolder("rfr");
        root.newFolder("rfr", "45");
        root.newFolder("rfr", "45", "internal");
        root.newFolder("rfr", "Next");
        Search searcher = new Search();
        assertThat(
                searcher.files(dir1.getPath(), Arrays.asList(".txt"), this.biPredicate),
                is(new ArrayList<File>())
        );
    }

    @Test
    public void whenNullExtensionsSpecifiedInBiPredicateMethod() throws IOException {
        File dir1 = makeDirs();
        Search searcher = new Search();
        assertThat(
                searcher.files(dir1.getPath(), null, this.biPredicate),
                is(new ArrayList<File>())
        );
    }

    @Test
    public void whenNoAnyExtensionsSpecifiedInBiPredicateMethod() throws IOException {
        File dir1 = makeDirs();
        Search searcher = new Search();
        assertThat(
                searcher.files(dir1.getPath(), new ArrayList<>(), this.biPredicate),
                is(new ArrayList<File>())
        );
    }

    @Test
    public void whenAbsentExtensionSpecifiedInBiPredicateMethod() throws IOException {
        File dir1 = makeDirs();
        Search searcher = new Search();
        assertThat(
                searcher.files(dir1.getPath(), Arrays.asList(".jpg"), this.biPredicate),
                is(new ArrayList<File>())
        );
    }

    @Test
    public void whenOneTXTFileWasFoundInPredicateMethod() throws IOException {
        File dir1 = makeDirs();
        Search searcher = new Search();
        assertThat(
                searcher.files(dir1.getPath(), Arrays.asList(".txt"), this.biPredicate),
                is(Arrays.asList(new File(root.getRoot() + slash + "rfr" + slash + "45" + slash + "1.txt")))
        );
    }

    @Test
    public void whenOneTXTAndOneDOCFilesWasFoundInBiPredicateMethod() throws IOException {
        File dir1 = makeDirs();
        Search searcher = new Search();
        assertThat(
                searcher.files(dir1.getPath(), Arrays.asList(".txt", ".doc"), this.biPredicate),
                is(Arrays.asList(
                        new File(root.getRoot() + slash + "rfr" + slash + "2.doc"),
                        new File(root.getRoot() + slash + "rfr" + slash + "45" + slash + "1.txt")))
        );
    }

    /**
     * Prepares standard directories and files for this test.
     * @return - top-level directory.
     */
    private File makeDirs() throws IOException {
        File dir = root.newFolder("rfr");
        root.newFolder("rfr", "45");
        root.newFile("rfr" + slash + "45" + slash + "1.txt");
        root.newFile("rfr" + slash + "2.doc");
        root.newFolder("rfr", "Next");
        return dir;
    }
}