package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * PhoneDictionaryTest.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        List<Person> persons = phones.find("Petr");
        assertThat(persons.iterator().next().getSurname(), is("Arsentev"));
    }

    @Test
    public void whenFindBySequenceThen2Occurrences() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        phones.add(
                new Person("Ivan", "Andreev", "3222233", "Omsk")
        );
        phones.add(
                new Person("Sergey", "Ivanov", "715471", "Moscow")
        );
        List<Person> persons = phones.find("sk");
        List<String> founded = new ArrayList<>();
        for (Iterator<Person> it = persons.iterator(); it.hasNext();) {
            founded.add(it.next().getSurname());
        }
        String[] result = new String[founded.size()];
        result = founded.toArray(result);
        assertArrayEquals(new String[] {"Arsentev", "Andreev"}, result);
    }
}