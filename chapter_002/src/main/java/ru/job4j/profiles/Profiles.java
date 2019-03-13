package ru.job4j.profiles;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Profiles - class intended for operations at tourists profiles.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Profiles {
    /**
     * The method select the addresses from the profiles list.
     * @param profiles - List of profiles of some tourists.
     * @return - List of their addresses.
     */
    public List<Address> collect(List<Profile> profiles) {
        return profiles.stream().map((profile) -> new Address(profile.getAddress())).collect(Collectors.toList());
    }
}
