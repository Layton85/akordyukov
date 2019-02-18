package ru.job4j.departmentsort;

import java.util.*;
import java.util.regex.*;

/**
 * DepartmentSort - class getting usability of ascending and descending sorting of tree-oriented department structures
 * with saving hierarchy.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class DepartmentSort {
    /** departments list */
    private List<String> departments = null;

    /**
     * Constructor
     * @param inputList - "weak" input list which perhaps needs to be completed.
     */
    public DepartmentSort(List<String> inputList) {
        this.departments = this.complete(inputList);
    }

    /**
     * Get-method
     * @return - departments list
     */
    public List<String> getDepartments() {
        return this.departments;
    }

    /**
     * The method completes specified input List if it is necessary and fills in department list.
     * The method iterates input list and for each element looks for a completing lexemes
     * which have to be an independent strings (corresponding hierarchy logic) but they haven`t.
     * Example: input - "K1", "K1\SK1\SSK2". Lexeme "K1\SK1" is missed and needs to be added.
     * Then the method adds all missed lexemes in the specified list.
     * @param inputList - "weak" input List which perhaps needs to be completed.
     * @return - completed input List
     */
    private List<String> complete(List<String> inputList) {
        List<String> addList = new ArrayList<>();
        for (String str : inputList) {
            Pattern pattern = Pattern.compile("\\\\");
            List<String> lexemes = Arrays.asList(pattern.split(str));
            StringJoiner addStrJ = new StringJoiner("\\");
            for (String lex : lexemes) {
                addStrJ.add(lex);
                if (!addList.contains(addStrJ.toString())) {
                    if (!inputList.contains(addStrJ.toString())) {
                        addList.add(addStrJ.toString());
                    }
                }
            }
        }
        inputList.addAll(addList);
        return inputList;
    }

    /**
     * The method sorts departments list by lexicographical ascending considering hierarchy structure.
     * Example:
     * input list - "K2\SK1", "K1", "K1\SK2", "K1\SK1", "K2"
     * sorted list - "K1", "K1\SK1", "K1\SK2", "K2", "K2\SK1"
     * @return - departments list sorting by ascending
     */
    public void ascendingSort() {
        this.departments.sort(null);
    }

    /**
     * The method sorts departments list by lexicographical descending considering hierarchy structure.
     * Example:
     * input list - "K2\SK1", "K1", "K1\SK2", "K1\SK1", "K2"
     * sorted list - "K2", "K2\SK1", "K1", "K1\SK2", "K1\SK1"
     * @return - departments list sorting by descending
     */
    public void descendingSort() {
        this.departments.sort(new Comparator<String>() {
            @Override
            public int compare(String left, String right) {
                int minLen = Math.min(left.length(), right.length());
                int result = -left.substring(0, minLen).compareTo(right.substring(0, minLen));
                return result != 0 ? result : Integer.compare(left.length(), right.length());
            }
        });
    }
}