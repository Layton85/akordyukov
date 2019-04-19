package ru.job4j.list;

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
                .map((word) -> {
                    StringBuffer cleaned = new StringBuffer(word);
                    filthy.stream().forEach((badWord) -> {
                        int index = cleaned.indexOf(badWord);
                        while (index != -1) {
                            int length = badWord.length();
                            if (cleaned.charAt(index - 1) == ' ') {
                                index--;
                                length++;
                            }
                            cleaned.replace(index, index + length, "");
                            index = cleaned.indexOf(badWord);
                        }
                    });
                    return cleaned.toString();
                }).collect(Collectors.toList());
    }
}