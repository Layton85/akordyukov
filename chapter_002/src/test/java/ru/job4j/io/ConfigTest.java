package ru.job4j.io;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * ConfigTest class.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConfigTest {
    private String s = File.separator;
    @Test
    public void whenPairWithoutComment() {
        String path = "." + s + "data" + s + "io" + s + "configLoad" + s + "pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("Alex Kordyukov")
        );
    }

    @Test
    public void whenPairWithComment() {
        String path = "." + s + "data" + s + "io" + s + "configLoad" + s + "pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("Alex Kordyukov")
        );
    }

    @Test
    public void whenUsualConfig() {
        String path = "." + s + "data" + s + "io" + s + "configLoad" + s + "usualConfig.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Alex Kordyukov"));
        assertThat(config.value("phone"), is("111"));
        assertThat(config.value("e-mail"), is("1qwert@asdf2.com"));
        assertThat(config.value("status"), is(""));
    }
}