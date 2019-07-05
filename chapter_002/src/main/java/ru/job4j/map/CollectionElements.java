package ru.job4j.map;

/**
 * CollectionElements - class shows different types of the collections elements:
 * User - with default hashCode() and equals();
 * UserHash - with default equals() and business-logic-oriented hashCode();
 * UserEquals - with default hashCode() and business-logic-oriented equals();
 * UserHashEquals - with business-logic-oriented hashCode() and equals();
 * UserEquals committed because of the checkstyle requirements.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class CollectionElements {
    public static class User {
        private String name;
        public User(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    public static class UserHash {
        private String name;
        public UserHash(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
        @Override
        public int hashCode() {
            return getName() != null ? getName().hashCode() : 0;
        }
    }

    //Сommitted because of the checkstyle requirements.
//    public static class UserEquals {
//        private String name;
//        public UserEquals(String name) {
//            this.name = name;
//        }
//        public String getName() {
//            return name;
//        }
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) {
//                return true;
//            }
//            if (o == null || getClass() != o.getClass()) {
//                return false;
//            }
//            UserEquals that = (UserEquals) o;
//            return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
//        }
//    }

    public static class UserHashEquals {
        private String name;
        public UserHashEquals(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            UserHashEquals that = (UserHashEquals) o;
            return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
        }
        @Override
        public int hashCode() {
            return getName() != null ? getName().hashCode() : 0;
        }
    }
}
