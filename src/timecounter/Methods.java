package timecounter;

/**
 * Клас с методами утилитами
 */

public class Methods {


    /**
     * Метод, который возвращает строку( в которой выбраный символ вводиться заданое количество раз
     */
    public static String outputSymbols(char symbol, int numberOfSymbols) {
        String result = "";
        for (int i = 0; i < numberOfSymbols; i++) {
            result += symbol;
        }
        return result;
    }

    /**
     * Метод необходимый для коректного отображения таблицы. Он заполняет строку subStr пробелами
     * с обоих сторон пока длина subStr не станет равна mainStr
     */
    public static String outputForTable(String mainStr, String subStr) {
        do {
            subStr = " " + subStr;
            if (subStr.length() == mainStr.length() - 1) {
                break;
            }
            subStr = subStr + " ";
        } while (subStr.length() < mainStr.length() - 1);
        subStr = subStr + "|";
        return subStr;
    }


    /**
     * Метод, получающий имя класа без пакетов
     */
    public static String getClassName(Object o) {
        char[] chars = o.getClass().getName().toCharArray();
        String newStr = "";
        for (int i = chars.length - 1; i > 0; i--) {
            if (chars[i] == '.') {
                break;
            } else {
                newStr = chars[i] + newStr;
            }
        }
        return newStr;
    }





    /**
     * Vladimirs methods
     * */

    //метод для увеличения размера массива
    public static Object[] getBiggerArray(Object[] arrayForList) {
        int newArrayCapacity = ((arrayForList.length * 3) / 2 + 1);
        return new Object[newArrayCapacity];
    }

    //    метод для поиска последнего индекса в массиве
    public static int getLastIndexOfArray(Object[] arrayForList) {
        int lastIndexOfArray = 0;
        for (int i = 0; i < arrayForList.length; i++) {
            if (arrayForList[i] != null) {
                lastIndexOfArray = i;
            }
        }
        return lastIndexOfArray;
    }
}
