package ru.job4j.generic;

/**
 * Base - base abstract class for the classes which objects can be store elements.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public abstract class Base {
    /** element id. */
    private final String id;

    /**
     * Constructor.
     * @param id - element id.
     */
    protected Base(final String id) {
        this.id = id;
    }

    /**
     * Get-method.
     * @return - element id.
     */
    public String getId() {
        return id;
    }

    /**
     * Override method equals.
     * @param o - another object.
     * @return - boolean value:
     *              true - if the objects have similar type and id;
     *              false - in other cases.
     */
    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            Base base = (Base) o;
            result = getId().equals(base.getId());
        }
        return result;
    }

    /**
     * Override hash method.
     * @return - hashCode which is calculate using id.
     */
    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
