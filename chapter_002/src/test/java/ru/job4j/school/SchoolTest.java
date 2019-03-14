package ru.job4j.school;

import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * SchoolTest.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SchoolTest {
    /**
     * Service method preparing input student`s list for the filtration.
     * @param start - minimum student`s score in list
     * @param end - maximum student`s score in list
     * @param step - iteration step of student`s score
     * @return - Student`s list prepared for use.
     */
    public List<Student> prepareStudentList(int start, int end, int step) {
        List<Student> studentList = new ArrayList<>();
        for (int i = start; i <= end; i += step) {
            studentList.add(new Student(i));
        }
        return studentList;
    }

    @Test
    public void getListA() {
        List<Student> studentList = this.prepareStudentList(5, 100, 5);
        List<Student> expectedList = this.prepareStudentList(70, 100, 5);
        assertThat(
                new School().collect(studentList, (Student s) -> s.getScore() >= 70).equals(expectedList),
                is(true)
        );
    }

    @Test
    public void getListB() {
        List<Student> studentList = this.prepareStudentList(5, 100, 5);
        List<Student> expectedList = this.prepareStudentList(50, 65, 5);
        assertThat(
                new School().collect(studentList, (Student s) -> s.getScore() >= 50 && s.getScore() < 70).equals(expectedList),
                is(true)
        );
    }

    @Test
    public void getListC() {
        List<Student> studentList = this.prepareStudentList(5, 100, 5);
        List<Student> expectedList = this.prepareStudentList(5, 45, 5);
        assertThat(
                new School().collect(studentList, (Student s) -> s.getScore() < 50).equals(expectedList),
                is(true)
        );
    }

    @Test
    public void studentsListToMap() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Sidorov", 66));
        studentList.add(new Student("Ivanov", 56));
        studentList.add(new Student("Petrov", 84));
        Map<String, Student> expectedMap = new HashMap<>();
        expectedMap.put("Sidorov", new Student("Sidorov", 66));
        expectedMap.put("Ivanov", new Student("Ivanov", 56));
        expectedMap.put("Petrov", new Student("Petrov", 84));
        assertThat(
                new School().toMap(studentList).equals(expectedMap),
                is(true)
        );
    }
}
