package ru.job4j.matrix;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Matrix - class describes Matrix.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Matrix {
    /**
     * The method transforms Integer matrix into list of integer elements.
     * @param matrix - specified Integer matrix.
     * @return - specified matrix in the form of "Flat" list.
     */
    public List<Integer> matrixToList(Integer[][] matrix) {
        return Arrays.stream(matrix)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }
}
