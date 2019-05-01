package ru.job4j.list;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abuse - the class cleans string lists from words from the stop-lists.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Abuse {
    /**
     * The method cleans specified list from words from the specified stop-list.
     * @param words - string list
     * @param filthy - stop list
     * @return - cleaned string list
     */
    public List<String> clean(List<String> words, List<String> filthy) {
        return words.stream()
                .flatMap(word -> Arrays.stream(word.split(" ")))
                .filter((word) -> {
                    return !filthy.stream().anyMatch(badWord -> {
                        return word.contains(badWord);
                    });
                })
                .collect(Collectors.toList());
    }
}