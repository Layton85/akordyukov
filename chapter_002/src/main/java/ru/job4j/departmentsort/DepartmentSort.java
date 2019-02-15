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
     * @param inputList - "weak" input List which perhaps needs to be completed.
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
     * The method completes specified input List if it is necessary.
     * @param inputList - "weak" input List which perhaps needs to be completed.
     * @return - completed input List
     */
    public List<String> complete(List<String> inputList) {
        List<String> addList = new ArrayList<>(); //лист дополнений
        for (String str : inputList) {          //цикл по входному листу
            Pattern pattern = Pattern.compile("\\\\");
            List<String> lexemes = Arrays.asList(pattern.split(str)); //получили список лексем текущей строки входного массива
            StringJoiner addStrJ = new StringJoiner("\\"); //строка-дополнение
            for (String lex : lexemes) {         //цикл по лексемам текущей строки
                addStrJ.add(lex);            //"наращивание" потенциальной строки-дополнения в процессе чтения лексем данной строки
                boolean found = false;
                if (!addList.contains(addStrJ.toString())) { //если такая строка-дополнение еще не добавлена к листу дополнений
                    for (String inputStr : inputList) {       //цикл по входному массиву
                        if (addStrJ.toString().equals(inputStr)) { //поиск текущего варианта строки-дополнения во входном списке строк
                            found = true;
                            break;
                        }
                    }
                    if (!found) { //добавление текущего строки-дополнения к листу дополнений, если она не была найдена
                        addList.add(addStrJ.toString());
                    }
                }
            }
        }
        inputList.addAll(addList); //добавление листа дополнений к входному листу
        return inputList;
    }

//    public void ascendingSort(List<String> list) {
//        list.sort(null);
//    }

    /**
     * The method sorts depatments list by lexicographical ascending considering hierarchy structure
     * @return - depatments list sorting by ascending
     */
    public List<String> ascendingSort() {
        this.departments.sort(null);
        return this.departments;
    }

    //public void descendingSort(List<String> list)
    /**
     * The method sorts depatments list by lexicographical descending considering hierarchy structure
     * @return - depatments list sorting by descending
     */
    public List<String> descendingSort() {
        this.departments.sort(new Comparator<String>() {
            @Override
            public int compare(String left, String right) {
                int result = 0;
                Pattern pat = Pattern.compile("[^\\\\]+");
                Matcher matLeft = pat.matcher(left);
                Matcher matRight = pat.matcher(right);
                boolean bL = matLeft.find();
                boolean bR = matRight.find();
                while (bL && bR) {
                    if (matLeft.group().compareTo(matRight.group()) < 0) {
                        result = 1;
                        break;
                    } else if (matLeft.group().compareTo(matRight.group()) > 0) {
                        result = -1;
                        break;
                    }
                    bL = matLeft.find();
                    bR = matRight.find();
                }
                if (result == 0) {
                    result = (bL) ? 1 : -1;
                }
                return result;
            }
        });
        return this.departments;
    }
}
