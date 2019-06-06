package ru.job4j.generic;

/**
 * UserStore - the class describes the store of Users.
 * UserStore extends AbstractStore.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class UserStore extends AbstractStore<User> {
    /**
     * Constructor.
     * @param size - store size.
     */
    public UserStore(int size) {
        super(size);
    }
}
