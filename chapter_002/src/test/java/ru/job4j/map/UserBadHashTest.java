package ru.job4j.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * UserEqualsTest.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class UserBadHashTest {
    @Test public void when100UsersWithBadHashInHashMap() {
        Map<UserBadHash, Object> map = new HashMap<>();
        for (int i = 1; i <= 100; i++) {
            map.put(new UserBadHash(i, "Ivan" + String.valueOf(i)), null);
        }
    }
}