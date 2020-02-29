package ru.itmo.java;

import java.lang.reflect.Array;
import java.util.*;

public class Task3 {

    /**
     * Напишите функцию, которая принимает массив целых чисел и циклически сдвигает элементы этого массива вправо:
     * A[0] -> A[1], A[1] -> A[2] .. A[length - 1] -> A[0].
     * Если инпут равен null - вернуть пустой массив
     */
    int[] getShiftedArray(int[] inputArray) {

        if (inputArray == null)
            return new int[0];

        int[] answer = new int[inputArray.length];
        for (int i = 0; i < answer.length; i++) {
            answer[(i + 1) % answer.length] = inputArray[i];
        }

        return answer;
    }

    /**
     * Напишите функцию, которая принимает массив целых чисел и возвращает максимальное значение произведения двух его элементов.
     * Если массив состоит из одного элемента, то функция возвращает этот элемент.
     *
     * Если входной массив пуст или равен null - вернуть 0
     *
     * Пример: 2 4 6 -> 24
     */
    int getMaxProduct(int[] inputArray) {

        if (inputArray == null || inputArray.length <= 1)
            return 0;

        if (inputArray.length == 2)
            return inputArray[0] * inputArray[1];

        int[] maxPositiv = { Integer.MIN_VALUE, Integer.MIN_VALUE };
        int[] minNegativ = { Integer.MAX_VALUE, Integer.MAX_VALUE };

        int powPositiv =  0;
        int powNegativ =  0;

        for (int i : inputArray) {

            // for positive
            if (i >= 0){
                if (i > maxPositiv[0]){
                    maxPositiv[1] = maxPositiv[0];
                    maxPositiv[0] = i;
                } else if (i > maxPositiv[1]){
                    maxPositiv[1] = i;
                }
            }
            // for negative
            else{
                if (i < minNegativ[0]){
                    minNegativ[1] = minNegativ[0];
                    minNegativ[0] = i;
                } else if (i < minNegativ[1]){
                    minNegativ[1] = i;
                }
            }
        }

        if (maxPositiv[0] != Integer.MIN_VALUE && maxPositiv[1] != Integer.MIN_VALUE)
            powPositiv = maxPositiv[0] * maxPositiv[1];

        if (minNegativ[0] != Integer.MAX_VALUE && minNegativ[1] != Integer.MAX_VALUE)
            powNegativ = minNegativ[0] * minNegativ[1];

        return Math.max(powPositiv, powNegativ);
    }

    /**
     * Напишите функцию, которая вычисляет процент символов 'A' и 'B' (латиница) во входной строке.
     * Функция не должна быть чувствительна к регистру символов.
     * Результат округляйте путем отбрасывания дробной части.
     *
     * Пример: acbr -> 50
     */
    int getABpercentage(String input) {
        if (input == null || input.length() == 0)
            return 0;

        return (int) input
                .toLowerCase()
                .chars()
                .filter(x -> x == 'a' || x == 'b')
                .count() * 100 / input.length();
    }

    /**
     * Напишите функцию, которая определяет, является ли входная строка палиндромом
     */
    boolean isPalindrome(String input) {
        if (input == null) return false;

        for (int i = 0; i < input.length() / 2; i++) {
            if (input.charAt(i) != input.charAt(input.length() - 1 - i))
                return false;
        }
        return true;
    }

    /**
     * Напишите функцию, которая принимает строку вида "bbcaaaak" и кодирует ее в формат вида "b2c1a4k1",
     * где группы одинаковых символов заменены на один символ и кол-во этих символов идущих подряд в строке
     */
    String getEncodedString(String input) {
        StringBuilder answer = new StringBuilder();

        if (input == null || input.length() == 0)
            return answer.toString();

        for (int i = 0; i < input.length(); i++) {
            var ch = input.charAt(i);
            var count = 1;
            while(i < input.length() - 1 && input.charAt(i) == input.charAt(i + 1)){
                count++;
                i++;
            }

            answer.append(ch);
            answer.append(count);
        }

        return answer.toString();
    }

    /**
     * Напишите функцию, которая принимает две строки, и возвращает true, если одна может быть перестановкой (пермутатсией) другой.
     * Строкой является последовательность символов длинной N, где N > 0
     * Примеры:
     * isPermutation("abc", "cba") == true;
     * isPermutation("abc", "Abc") == false;
     */
    boolean isPermutation(String one, String two) {
        if (one == null ||
            two == null ||
            one.length() != two.length() ||
            one.length() == 0)
            return false;

        var firstSorted = one.toCharArray();
        Arrays.sort(firstSorted);
        var secondSorted = two.toCharArray();
        Arrays.sort(secondSorted);

        for (var i = 0; i < firstSorted.length; i++){
            if (firstSorted[i] != secondSorted[i])
                return false;
        }

        return true;
    }

    /**
     * Напишите функцию, которая принимает строку и возвращает true, если она состоит из уникальных символов.
     * Из дополнительной памяти (кроме примитивных переменных) можно использовать один массив.
     * Строкой является последовательность символов длинной N, где N > 0
     */
    boolean isUniqueString(String s) {
        if (s == null || s.length() == 0)
            return false;

        Map<Character, Boolean> hash = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            var ch = s.charAt(i);
            if (hash.containsKey(ch))
                return false;
            hash.put(ch, true);
        }

        return true;
    }

    /**
     * Напишите функцию, которая транспонирует матрицу. Только квадратные могут быть на входе.
     *
     * Если входной массив == null - вернуть пустой массив
     */
    int[][] matrixTranspose(int[][] m) {
        if (m == null || m.length == 0 || m.length != m[0].length)
            return new int[][]{{}, {}};

        var answer = new int[m[0].length][m.length];
        for (int i = 0; i < m[0].length; i++) {
            for (int j = 0; j < m.length; j++) {
                answer[i][j] = m[j][i];
            }
        }

        return answer;
    }

    /**
     * Напиишите функцию, принимающую массив строк и символ-разделитель,
     * а возвращает одну строку состоящую из строк, разделенных сепаратором.
     *
     * Запрещается пользоваться функций join
     *
     * Если сепаратор == null - считайте, что он равен ' '
     * Если исходный массив == null -  вернуть пустую строку
     */
    String concatWithSeparator(String[] inputStrings, Character separator) {
        if (inputStrings == null)
            return "";
        if (separator == null)
            separator = ' ';

        StringBuilder answer = new StringBuilder();
        for(var i = 0; i < inputStrings.length; i++){
            answer.append(inputStrings[i]);
            if (i != inputStrings.length - 1)
                answer.append(separator);
        }

        return answer.toString();
    }

    /**
     * Напишите функцию, принимающую массив строк и строку-перфикс и возвращающую кол-во строк массива с данным префиксом
     */
    int getStringsStartWithPrefix(String[] inputStrings, String prefix) {
        if (inputStrings == null || prefix == null)
            return 0;

        var answer = 0;

        for (String inputString : inputStrings) {
            if (inputString.startsWith(prefix))
                answer++;
        }

        return answer;
    }
}
