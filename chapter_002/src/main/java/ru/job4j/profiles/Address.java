package ru.job4j.profiles;

/**
 * Address - class describes some address.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Address {
    /** city */
    private String city;

    /** street */
    private String street;

    /** home number*/
    private int home;

    /** apartment number */
    private int apartment;

    /**
     * Constructor.
     * @param city - specified city.
     * @param street - specified street.
     * @param home - specified home number.
     * @param apartment - specified apartment number.
     */
    public Address(String city, String street, int home, int apartment) {
        this.city = city;
        this.street = street;
        this.home = home;
        this.apartment = apartment;
    }

    /**
     * Constructor.
     * @param addr - another Address Object.
     */
    public Address(Address addr) {
        this.city = addr.city;
        this.street = addr.street;
        this.home = addr.home;
        this.apartment = addr.apartment;
    }

    /**
     * Override method equals(Object o)
     * @param o - compared object
     * @return - result of comparison
     */
    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            Address address = (Address) o;
            if (this.home != address.home
                    || this.apartment != address.apartment
                        || !this.city.equals(address.city)
                            || !this.street.equals(address.street)) {
                result = false;
            } else {
                result = true;
            }
        }
        return result;
    }

    /**
     * Override method hashCode()
     * @return - hash code
     */
    @Override
    public int hashCode() {
        int result = city.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + home;
        result = 31 * result + apartment;
        return result;
    }
}
