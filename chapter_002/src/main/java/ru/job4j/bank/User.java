package ru.job4j.bank;

/**
 * User - class describes the user.
 * User implements interface Comparable for using in Collections.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class User implements Comparable<User> {
    /** User name */
    private String name;
    /** User passport - unigue User identifier */
    private String passport;

    /**
     * User default constructor.
     */
    public User() {
        this.name = "";
        this.passport = "";
    }

    /**
     * User constructor
     * @param name - User name
     * @param passport - Uset passport
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * Get-method
     * @return - Uset name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get-method
     * @return - User passport
     */
    public String getPassport() {
        return this.passport;
    }

    /**
     * Override method toString()
     * @return - String about the User
     */
    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", passport='" + passport + '\''
                + '}';
    }

    /**
     * Override method equals()
     * @param o - another User object
     * @return - true if the objects are the same,
     *              else - in another case
     */
    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) {
            result = true;
        } else if (o == null || this.getClass() != o.getClass()) {
            result = false;
        } else {
            User that = (User) o;
            result = this.name.equals(that.getName())
                    && this.passport.equals(that.getPassport());
        }
        return result;
    }

    /**
     * Override method hashCode()
     * @return - User hash code (considering User`s name and passport)
     */
    @Override
    public int hashCode() {
        int result = this.name.hashCode();
        result = 31 * result + this.passport.hashCode();
        return result;
    }

    /**
     * Override method compareTo(..) from the interface Comparable
     * @param o - Another object which is comparing with this User Object.
     * @return - the compare code of the passport strings comparison.
     */
    @Override
    public int compareTo(User o) {
        return this.passport.compareTo(o.getPassport());
    }
}
