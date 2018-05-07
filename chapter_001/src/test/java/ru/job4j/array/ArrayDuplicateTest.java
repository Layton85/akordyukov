package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ArrayDuplicateTest.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate phrase = new ArrayDuplicate();
        String[] result = phrase.remove(new String[]{"Привет", "Мир", "Привет", "Супер", "Мир"});
        String[] expect = new String[] {"Привет", "Мир", "Супер"};
        assertThat(result, is(expect));
    }
}