package ru.job4j.mail;

import java.util.*;

/**
 * Mailer - class for merging users which have at least one similar e-mail.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Mailer {
    /**
     * The method merges Users which have at least one similar e-mail.
     * During the merging the method gives to the resulting user the name of the first element of the intersection list.
     * In the worst case (all users have intersections with all e-mails of the all other users) the time complexity of an algorithm is O(n*m).
     * The asymptotic time complexity is linear.
     * @param users - users map: keys - user`s names, values - sets of user`s e-mails.
     * @return - merged users map. The set of values of each key in resulting map is unique and have no any intersections
     * (equals mails) with the other sets.
     */
    public LinkedHashMap<String, LinkedHashSet<String>> mergeUsers(LinkedHashMap<String, LinkedHashSet<String>> users) {
        LinkedHashMap<String, LinkedHashSet<String>> resMap = new LinkedHashMap<>();
        LinkedHashMap<String, String> emails = new LinkedHashMap<>();
        for (String uName : users.keySet()) {
            String currUser = uName;
            String mergeUser = uName;
            for (String mail : users.get(uName)) {
                if (!emails.containsKey(mail)) {
                    String name = currUser.equals(mergeUser) ? currUser : mergeUser;
                    emails.putIfAbsent(mail, name);
                    if (resMap.containsKey(name)) {
                        resMap.get(name).add(mail);
                    } else {
                        resMap.putIfAbsent(name, new LinkedHashSet<>(Arrays.asList(mail)));
                    }
                } else {
                    mergeUser = emails.get(mail);
                    if (!mergeUser.equals(currUser) && resMap.containsKey(currUser)) {
                        resMap.get(mergeUser).addAll(resMap.get(currUser));
                        for (String m : resMap.get(currUser)) {
                            emails.put(m, mergeUser);
                        }
                        resMap.remove(currUser);
                    }
                    currUser = mergeUser;
                }
            }
        }
        return resMap;
    }
}