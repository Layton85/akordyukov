package ru.job4j.io.configreading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Config - class provides reading of the config files.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Config {
    /** path to the config file. */
    private final String path;

    /** storage of config values */
    private final Map<String, String> values = new HashMap<String, String>();

    /**
     * Constructor.
     * @param path - path to the config file.
     */
    public Config(final String path) {
        this.path = path;
    }

    /** The method loads config values into the values storage from the config file in specified path.*/
    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(str -> {
                        if (!str.isEmpty()) {
                            int eqPos = str.indexOf("=");
                            int commentPos = str.indexOf("//");
                            if (eqPos != -1) {
                                if (commentPos != -1 && commentPos > eqPos) {
                                    str = str.substring(0, commentPos);
                                }
                                if (!(commentPos != -1 && commentPos < eqPos)) {
                                    this.values.put(str.substring(0, eqPos), str.substring(eqPos + 1));
                                }
                            }
                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The method returns config values corresponded to their keys.
     * @param key - config key.
     * @return - config value or "" if this.values contains no mapping for the key.
     */
    public String value(String key) {
        String result = this.values.get(key);
        if (result == null) {
            result = "";
        }
        return result;
    }

    /**
     * Override mehtod toString().
     * This method outputs config file contents into the string.
     * @return - the string composed of config file contents.
     */
    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    /**
     * main method.
     * @param args - command line`a arguments.
     */
    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}