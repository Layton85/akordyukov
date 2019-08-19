package ru.job4j.analysis;

import java.util.HashMap;
import java.util.List;

/**
 * Analysis - class for analysis collections modifications.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Analysis {
    /**
     * The method analysis list modifications from the previous to the current condition.
     * @param previous - previous list.
     * @param current - current list.
     * @return - Info object with the information about changes
     * (a number of the added, changed and deleted elements).
     */
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info(0, 0, 0);
        HashMap<Integer, User> map = new HashMap<>();
        for (User u : current) {
            map.put(u.id, u);
        }
        for (User u : previous) {
            User temp = map.get(u.id);
            if (temp == null) {
                info.deleted++;
            } else if (!temp.name.equals(u.name)) {
                info.changed++;
            }
        }
        info.added = current.size() - (previous.size() - info.deleted);
        return info;
    }

    /**
     * User object.
     * id - unique user identifier
     */
    public static class User {
        int id;
        String name;
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    /**
     * Info class.
     * Info contains information about a number of the:
     *      added elements,
     *      changed elements.
     *      deleted elements.
     */
    public static class Info {
        int added;
        int changed;
        int deleted;
        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }
        @Override
        public boolean equals(Object o) {
            boolean result = false;
            if (this == o) {
                result = true;
            } else {
                if (!(o == null || getClass() != o.getClass())) {
                    Info info = (Info) o;
                    if (added == info.added && changed == info.changed) {
                        result = (deleted == info.deleted);
                    }
                }
            }
            return result;
        }
        @Override
        public int hashCode() {
            int result = added;
            result = 31 * result + changed;
            result = 31 * result + deleted;
            return result;
        }
    }
}