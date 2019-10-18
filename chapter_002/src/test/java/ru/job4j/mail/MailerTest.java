package ru.job4j.mail;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Mailer test.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class MailerTest {
    private Mailer mailer = new Mailer();
    private LinkedHashMap<String, LinkedHashSet<String>> users = new LinkedHashMap<>();
    private LinkedHashMap<String, LinkedHashSet<String>> expected = new LinkedHashMap<>();

    //---WithoutIntersections---//
    private void prepareInputMapWithoutIntersections() {
        users.put("U1", new LinkedHashSet<String>(Arrays.asList("m1")));
        users.put("U2", new LinkedHashSet<String>(Arrays.asList("m2", "m4")));
        users.put("U3", new LinkedHashSet<String>(Arrays.asList("m3")));
    }
    @Test
    public void mergeUsersWithoutIntersections() {
        this.prepareInputMapWithoutIntersections();
        expected.put("U1", new LinkedHashSet<String>(Arrays.asList("m1")));
        expected.put("U2", new LinkedHashSet<String>(Arrays.asList("m2", "m4")));
        expected.put("U3", new LinkedHashSet<String>(Arrays.asList("m3")));
        assertThat(mailer.mergeUsers(users).equals(expected), is(true));
    }

    //---With Simple Intersection---//
    private void prepareInputMapWithSimpleIntersection() {
        users.put("U1", new LinkedHashSet<String>(Arrays.asList("m1")));
        users.put("U2", new LinkedHashSet<String>(Arrays.asList("m2", "m3")));
        users.put("U3", new LinkedHashSet<String>(Arrays.asList("m3")));
    }
    @Test
    public void mergeUsersWithSimpleIntersection() {
        this.prepareInputMapWithSimpleIntersection();
        expected.put("U1", new LinkedHashSet<String>(Arrays.asList("m1")));
        expected.put("U2", new LinkedHashSet<String>(Arrays.asList("m2", "m3")));
        assertThat(mailer.mergeUsers(users).equals(expected), is(true));
    }

    //---FullIntersection---//
    private void prepareInputMapWithFullIntersection() {
        users.put("U1", new LinkedHashSet<String>(Arrays.asList("m1", "m2", "m3")));
        users.put("U2", new LinkedHashSet<String>(Arrays.asList("m3", "m2", "m1")));
        users.put("U3", new LinkedHashSet<String>(Arrays.asList("m2", "m1", "m3")));
    }
    @Test
    public void mergeUsersWithFullIntersection() {
        this.prepareInputMapWithFullIntersection();
        expected.put("U1", new LinkedHashSet<String>(Arrays.asList("m1", "m2", "m3")));
        assertThat(mailer.mergeUsers(users).equals(expected), is(true));
    }

    //---With Multiply intersection---//
    private void prepareInputMapWithMultiplyIntersections() {
        users.put("U1", new LinkedHashSet<String>(Arrays.asList("m1")));
        users.put("U2", new LinkedHashSet<String>(Arrays.asList("m2")));
        users.put("U3", new LinkedHashSet<String>(Arrays.asList("m3")));
        users.put("U4", new LinkedHashSet<String>(Arrays.asList("m4", "m1", "m5", "m2", "m6", "m3")));
    }
    @Test
    public void mergeUsersWithMultiplyIntersections() {
        this.prepareInputMapWithMultiplyIntersections();
        expected.put("U3", new LinkedHashSet<String>(Arrays.asList("m3", "m2", "m1", "m4", "m5", "m6")));
        assertThat(mailer.mergeUsers(users).equals(expected), is(true));
    }

    //---With hard intersection---//
    private void prepareInputMapWithHardIntersections() {
        users.put("U1", new LinkedHashSet<String>(Arrays.asList("m1")));
        users.put("U2", new LinkedHashSet<String>(Arrays.asList("m1", "m2")));
        users.put("U3", new LinkedHashSet<String>(Arrays.asList("m3")));
        users.put("U4", new LinkedHashSet<String>(Arrays.asList("m4")));
        users.put("U5", new LinkedHashSet<String>(Arrays.asList("m5", "m4", "m6", "m7")));
        users.put("U6", new LinkedHashSet<String>(Arrays.asList("m2", "m6")));
    }
    @Test
    public void mergeUsersWithHardIntersections() {
        this.prepareInputMapWithHardIntersections();
        expected.put("U3", new LinkedHashSet<String>(Arrays.asList("m3")));
        expected.put("U4", new LinkedHashSet<String>(Arrays.asList("m4", "m5", "m6", "m7", "m1", "m2")));
        assertThat(mailer.mergeUsers(users).equals(expected), is(true));
    }
}