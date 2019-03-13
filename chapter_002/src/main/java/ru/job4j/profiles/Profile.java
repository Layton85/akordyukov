package ru.job4j.profiles;

/**
 * Profile - class described tourist profile.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Profile {
    /** address */
    private Address address;

    /**
     * Constructor
     * @param address - specified address.
     */
    public Profile(Address address) {
        this.address = address;
    }

    /**
     * Get-method
     * @return - address.
     */
    public Address getAddress() {
        return this.address;
    }
}
