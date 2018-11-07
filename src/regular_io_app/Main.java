package regular_io_app;

import sun.awt.image.ImageWatched;

import java.io.*;
import java.util.LinkedList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Ярослав\\IdeaProjects\\JavaFX_Homeworks\\src\\regular_io_app\\text");

        /**1)считать файл*/
        String text = getStringFromFile(file);


        /**2)отбросить все символы кроме букв*/
        text = getTextOnlyWithLetters(text);


        /**3)отбросить слова меньше 3 символов*/
        text = getTextOnlyWithWordsBiggerThanTwo(text);


        /**4)отбросить слова, которые определены в отдельном файле(слова-исключения)*/
        text = getTextWithOutExceptionWords(text);


        /**5)подсчитать количество повторений слов*/
        text = MyString.trim(text, true);
        text = text.toLowerCase();
        String[] words = getWordArayFromString(text);
        String[][] uniquewordNumberList = getUniqueElementsNumber(words);
        uniquewordNumberList = bubbleSort(uniquewordNumberList);
        for (int i = 0; i < uniquewordNumberList.length; i++) {
            System.out.println(uniquewordNumberList[i][0] + " - " + uniquewordNumberList[i][1] + " раз");
        }


        /**6)подсчитать общее количество слов*/
        System.out.println("Общее количество строк - " + getWordNum(text));
        System.out.println("Число уикальных строк - " + uniquewordNumberList.length);

        //System.out.println(text);

    }


    /**
     * 1)Прочитать посимвольно файл и записать его в строку
     */
    public static String getStringFromFile(File file) throws IOException {
        Reader reader = new FileReader(file);
        String text = "";
        int c;
        while ((c = reader.read()) != -1) {
            text += (char) c;
        }
        return text;
    }


    /**
     * 2)Отбросить все символы кроме букв из данной строки
     */
    public static String getTextOnlyWithLetters(String text) {
        text = text.replaceAll("[^ \nA-Za-z]", " ");
        return text;
    }

    /**
     * 3)Отбросить все слова, в которых меньше 3 символов, из данной строки
     */
    public static String getTextOnlyWithWordsBiggerThanTwo(String text) {
        text = text.replaceAll("\\b\\w{1,2}\\b", " ");
        return text;
    }


    /**
     * 4)Отбросить все слова, которые находяться в файле с исключениями
     */
    public static String getTextWithOutExceptionWords(String text) throws IOException {
        File file = new File("C:\\Users\\Ярослав\\IdeaProjects\\JavaFX_Homeworks\\src\\regular_io_app\\fileOfExceptions");
        List<String> exceptionWordList = getStringListFromFile(file);
        for (String word : exceptionWordList) {
            text = text.replaceAll(word, "");
        }
        return text;
    }

    /**
     * Прочитать посимвольно файл и записать его по словам в список строк(слов)
     */
    public static List<String> getStringListFromFile(File file) throws IOException {
        Reader reader = new FileReader(file);
        List<String> wordList = new LinkedList<>();
        String word = "";
        int c;
        while ((c = reader.read()) != -1) {
            if ((char) c == ',') {
                wordList.add(word);
                word = "";
            } else {
                word += (char) c;
            }
        }
        return wordList;
    }

//    /**
//     * Прочитать посимвольно файл и записать его по словам в масив строк(слов)
//     */
//    public static String[] getStringArrayFromFile(File file) throws IOException {
//        Reader reader = new FileReader(file);
//        List<String> wordList = new LinkedList<>();
//        String word = "";
//        int c;
//        while ((c = reader.read()) != -1) {
//            if((char)c == ','){
//                wordList.add(word);
//                word = "";
//            }
//            else {
//                word += (char) c;
//            }
//        }
//        String[] wordArray = new String[wordList.size()];
//        int i = 0;
//        for(String s : wordList){
//            wordArray[i] = s;
//            i++;
//        }
//        return wordArray;
//    }


    /**
     * 5)Находит уникальные слова и количество их повторений
     */
    public static String[][] getUniqueElementsNumber(String[] array) {
        String[] uniqueElements = new String[array.length];
        uniqueElements[0] = array[0];
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j <= counter; j++) {
                if (array[i].equals(uniqueElements[j])) {
                    break;
                }
                if (!array[i].equals(uniqueElements[j]) && j == counter) {
                    counter++;
                    uniqueElements[counter] = array[i];
                }
            }
        }
        System.out.println("COUNTER" + counter);
        counter++;
        String[][] result = new String[counter][2];
        for (int i = 0; i < counter; i++) {
            result[i][0] = uniqueElements[i];
            result[i][1] = String.valueOf(0);
            for (int j = 0; j < array.length; j++) {
                if (uniqueElements[i].equals(array[j])) {
                    int temp = Integer.valueOf(result[i][1]);
                    temp++;
                    result[i][1] = String.valueOf(temp);
                }
            }
        }

        return result;
    }


    /**
     * 6)Подсчитывает общее количество слов в строке
     */
    public static int getWordNum(String text) {
        char[] chars = text.toCharArray();
        int counter = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ' || chars[i] == '\n') {
                counter++;
            }
        }
        return counter;
    }

    public static String[] getWordArayFromString(String text) {
        char[] chars = text.toCharArray();

        List<String> list = new LinkedList<>();
        String word = "";
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ' || chars[i] == '\n') {
                list.add(word);
                word = "";
            } else {
                word += chars[i];
            }
        }
        String[] wordArray = new String[list.size()];
        int i = 0;
        for(String s : list){
            wordArray[i] = s;
            i++;
        }
        return wordArray;
    }


    static String[][] bubbleSort(String[][] array) {
        String[] temp;
        System.out.println(array.length + "TYFJJJJJJJJJJJJJJJJ");
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (Integer.valueOf(array[j - 1][1]) > Integer.valueOf(array[j][1])) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }

            }
        }
        return array;
    }


}