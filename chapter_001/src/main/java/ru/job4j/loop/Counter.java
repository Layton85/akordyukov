package ru.job4j.loop;
//1. Необходимо создать класс Counter;
//        2. В классе Counter написать метод public int add(int start, int finish) {};
//        3. Метод должен вычислять сумму четныx чисел в диапазоне от start до finish;
//        4. Класс Counter должен находиться в chapter_001\src\main\java\ru\job4j\loop\Counter.java;
//        5. Тест-класс CounterTest должен находиться в chapter_001\src\test\java\ru\job4j\loop\CounterTest.java;
//        6. В классе CounterTest напишите тест-метод, который будет проверять вашу программу.
/**
 * Class Counter.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Counter {
    /**
     * the method calculates the sum of even numbers in the range from start to finish
     * @param start first value.
     * @param finish second value.
     * @return the sum of even numbers in the range from start to finish.
     */
    public int add(int start, int finish) {
        int sum = 0;

        for (int cnt = start; cnt <= finish; cnt++)
            if (cnt % 2 == 0)
                sum += cnt;

        return sum;
    }
}
