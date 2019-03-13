package ru.job4j.school;

import java.util.Objects;

/**
 * Student - class described students.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Student {
    /** Student`s score (marks level) */
    private int score;

    /**
     * Constructor
     * @param score - Student`s score
     */
    public Student(int score) {
        this.score = score;
    }

    /**
     * Get-method
     * @return - Student`s score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Override method equals(Object o)
     * @param o - compared object
     * @return - result of comparison
     */
    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            Student student = (Student) o;
            result = this.getScore() == student.getScore();
        }
        return result;
    }

    /**
     * Override method hashCode()
     * @return - hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getScore());
    }
}
