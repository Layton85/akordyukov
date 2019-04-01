package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

/**
 * PhoneDictionary - class describes phonebook
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PhoneDictionary {
    /** storage of Persons */
    private List<Person> persons = new ArrayList<Person>();

    /**
     * The method adds new person into Persons storage
     * @param person - new Person object
     */
    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * The method finds a sublist of Persons which corresponds search key.
     * @param key - search key.
     * @return The list of the users meeting conditions.
     */
    public List<Person> find(String key) { //Поиск мы должны осуществлять по всем полям через метод String.contains.
        var result = new ArrayList<Person>();
        for (var person : this.persons) {
            if (person.getName().contains(key)) {
                result.add(person);
            } else if (person.getSurname().contains(key)) {
                result.add(person);
            } else if (person.getPhone().contains(key)) {
                result.add(person);
            } else if (person.getAddress().contains(key)) {
                result.add(person);
            }
        }
        return result;
    }
}
