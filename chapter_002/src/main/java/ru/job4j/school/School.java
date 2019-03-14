package ru.job4j.school;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * School - class intended for operations at students, classes etc.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class School {
    /**
     * The method filtered students corresponding specified predicat and grouped them into List.
     * @param students - specified list of students
     * @param predicate - specified group condition
     * @return - filtered student`s list.
     */
    public List<Student> collect(List<Student> students, Predicate<Student> predicate) {
        return students.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * The method transforms students list into students map.
     * @param students - specified list of students
     * @return - Map of students with surnames as a keys and students as a values
     */
    public Map<String, Student> toMap(List<Student> students) {
        return students.stream().collect(Collectors.toMap(Student::getSurname, (Student s) -> s));
    }
}
