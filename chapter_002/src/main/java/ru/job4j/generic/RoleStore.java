package ru.job4j.generic;

/**
 * RoleStore - the class describes the store of Roles.
 * RoleStore extends AbstractStore.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class RoleStore extends AbstractStore<Role> {
    /**
     * Constructor.
     * @param size - store size.
     */
    public RoleStore(int size) {
        super(size);
    }
}
