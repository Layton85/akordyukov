package ru.job4j.search;

/**
 * Person - class holds information about some person.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Person {
    /** Person`s name */
    private String name;
    /** Person`s surname */
    private String surname;
    /** Person`s phone number */
    private String phone;
    /** Person`s address */
    private String address;

    /**
     * Constructor
     * @param name - Person`s name
     * @param surname - Person`s surname
     * @param phone - Person`s phone number
     * @param address - Person`s address
     */
    public Person(String name, String surname, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    /**
     * get-method
     * @return Person`s name
     */
    public String getName() {
        return name;
    }

    /**
     * get-method
     * @return Person`s surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * get-method
     * @return Person`s phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * get-method
     * @return Person`s address
     */
    public String getAddress() {
        return address;
    }
}
