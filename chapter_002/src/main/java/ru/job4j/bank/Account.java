package ru.job4j.bank;

/**
 * Account - class describes User`s Account.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Account {
    /** amount of money on the Account */
    private double value;
    /** Account requisites - unique Account identifier */
    private String requisites;

    /**
     * Constructor
     * @param value - amount of money on the Account
     * @param requisites - Account requisites
     */
    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    /**
     * Get-method
     * @return - Account value
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Set-method
     * @param newValue - new (setted) value for this Account
     */
    void setValue(double newValue) {
        this.value = newValue;
    }

    /**
     * Get-method
     * @return - Account requisites
     */
    public String getRequisites() {
        return this.requisites;
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
            Account that = (Account) o;
            result = (Double.compare(that.value, this.value) == 0)
                    && this.requisites.equals(that.getRequisites());
        }
        return result;
    }

    /**
     * Override method hashCode()
     * @return - Account hash code (considering Account`s value and requisites)
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(this.value);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + this.requisites.hashCode();
        return result;
    }

    /**
     * Override method toString()
     * @return - String about the Account
     */
    @Override
    public String toString() {
        return "Account{"
                + "value=" + value
                + ", requisites='" + requisites + '\''
                + '}';
    }
}
