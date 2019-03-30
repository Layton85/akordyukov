package ru.job4j.profiles;

import org.junit.Test;
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
        List<Address> expectedList = List.of(
                new Address("Moscow", "Stalevarov", 10, 101),
                new Address("Moscow", "Perovskaya", 23, 87),
                new Address("Saratov", "Chapaeva", 60, 15)
        );
        List<Profile> profilesList = List.of(
                new Profile(new Address("Moscow", "Stalevarov", 10, 101)),
                new Profile(new Address("Moscow", "Perovskaya", 23, 87)),
                new Profile(new Address("Saratov", "Chapaeva", 60, 15))
        );
        assertThat(
                new Profiles().collect(profilesList).equals(expectedList),
                is(true)
        );
    }

    @Test
    public void whenRepeatedProfilesThanUniqueAddresses() {
        List<Address> expectedList = List.of(
                new Address("Moscow", "Stalevarov", 10, 101),
                new Address("Moscow", "Perovskaya", 23, 87),
                new Address("Saratov", "Chapaeva", 60, 15)
        );
        List<Profile> profilesList = List.of(
                new Profile(new Address("Moscow", "Stalevarov", 10, 101)),
                new Profile(new Address("Moscow", "Perovskaya", 23, 87)),
                new Profile(new Address("Saratov", "Chapaeva", 60, 15)),
                new Profile(new Address("Moscow", "Stalevarov", 10, 101)),
                new Profile(new Address("Moscow", "Perovskaya", 23, 87))
        );
        assertThat(
                new Profiles().collect(profilesList).equals(expectedList),
                is(true)
        );
    }

    @Test
    public void when4ProfilesThan4AddressesSortedByCities() {
        List<Address> expectedList = List.of(
                new Address("Moscow", "Stalevarov", 10, 101),
                new Address("Moscow", "Perovskaya", 23, 87),
                new Address("Saratov", "Chapaeva", 60, 15),
                new Address("Vladimir", "Streleckaya", 12, 0)
        );
        List<Profile> profilesList = List.of(
                new Profile(new Address("Saratov", "Chapaeva", 60, 15)),
                new Profile(new Address("Moscow", "Stalevarov", 10, 101)),
                new Profile(new Address("Vladimir", "Streleckaya", 12, 0)),
                new Profile(new Address("Moscow", "Perovskaya", 23, 87))
        );
        assertThat(
                new Profiles().collect(profilesList).equals(expectedList),
                is(true)
        );
    }
}
