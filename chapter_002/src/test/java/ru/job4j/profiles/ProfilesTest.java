package ru.job4j.profiles;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * ProfilesTest.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ProfilesTest {
    @Test
    public void when3ProfilesThan3Addresses() {
        List<Address> expectedList = new ArrayList<>();
        expectedList.add(new Address("Moscow", "Stalevarov", 10, 101));
        expectedList.add(new Address("Moscow", "Perovskaya", 23, 87));
        expectedList.add(new Address("Saratov", "Chapaeva", 60, 15));
        List<Profile> profilesList = new ArrayList<>();
        profilesList.add(new Profile(new Address("Moscow", "Stalevarov", 10, 101)));
        profilesList.add(new Profile(new Address("Moscow", "Perovskaya", 23, 87)));
        profilesList.add(new Profile(new Address("Saratov", "Chapaeva", 60, 15)));
        assertThat(
                new Profiles().collect(profilesList).equals(expectedList),
                is(true)
        );
    }
}
