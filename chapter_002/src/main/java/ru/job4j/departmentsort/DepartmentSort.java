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
    /** departments tree */
    private TreeSet<String> departments = null;

    /**
     * Constructor
     * @param inputTree - "weak" input tree which perhaps needs to be completed.
     */
    public DepartmentSort(TreeSet<String> inputTree) {
        this.departments = this.complete(inputTree);
    }

    /**
     * The method completes specified input Tree if it is necessary and fills in department tree.
     * The method iterates input tree and for each element looks for a completing lexemes
     * which have to be an independent strings (corresponding hierarchy logic) but they haven`t.
     * Example: input - "K1", "K1\SK1\SSK2". Lexeme "K1\SK1" is missed and needs to be added.
     * Then the method adds all missed lexemes in the specified tree.
     * @param inputTree - "weak" input Tree which perhaps needs to be completed.
     * @return - completed input Tree
     */
    private TreeSet<String> complete(TreeSet<String> inputTree) {
        List<String> addList = new ArrayList<>();
        for (String str : inputTree) {
            Pattern pattern = Pattern.compile("\\\\");
            List<String> lexemes = Arrays.asList(pattern.split(str));
            StringJoiner addStrJ = new StringJoiner("\\");
            for (String lex : lexemes) {
                addStrJ.add(lex);
                if (!addList.contains(addStrJ.toString()) && !inputTree.contains(addStrJ.toString())) {
                    addList.add(addStrJ.toString());
                }
            }
        }
        inputTree.addAll(addList);
        return inputTree;
    }

    /**
     * The method sorts departments tree by lexicographical ascending considering hierarchy structure.
     * Example:
     * input list - "K2\SK1", "K1", "K1\SK2", "K1\SK1", "K2"
     * sorted list - "K1", "K1\SK1", "K1\SK2", "K2", "K2\SK1"
     * @return - departments list sorting by ascending
     */
    public List<String> ascendingSort() {
        return new ArrayList<>(this.departments);
    }

    /**
     * The method sorts departments tree by lexicographical descending considering hierarchy structure.
     * Example:
     * input list - "K2\SK1", "K1", "K1\SK2", "K1\SK1", "K2"
     * sorted list - "K2", "K2\SK1", "K1", "K1\SK2", "K1\SK1"
     * @return - departments list sorting by descending
     */
    public List<String> descendingSort() {
        TreeSet<String> tree = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String left, String right) {
                int minLen = Math.min(left.length(), right.length());
                int result = -left.substring(0, minLen).compareTo(right.substring(0, minLen));
                return result != 0 ? result : Integer.compare(left.length(), right.length());
            }
        });
        tree.addAll(this.departments);
        return new ArrayList<>(tree);
    }
}