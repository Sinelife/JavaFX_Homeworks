package regular_io_app;

import sun.awt.image.ImageWatched;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
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
        System.out.println(text);
        String[] allWords = getWordArayFromString(text);
        List<WordNode> uniquewordNumberList = getUniqueElementsNumber(allWords);
        uniquewordNumberList = sort(uniquewordNumberList);
        for(WordNode node : uniquewordNumberList){
            System.out.println(node);
        }


        /**6)подсчитать общее количество слов*/
        System.out.println("Общее количество строк - " + getWordNum(text));

        /**7)подсчитать количество униальных слов*/
        System.out.println("Число уикальных строк - " + uniquewordNumberList.size());

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
    public static List<WordNode> getUniqueElementsNumber(String[] array) {
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
        counter++;
        List<WordNode> result = new LinkedList<>();
        for (int i = 0; i < counter; i++) {
            WordNode node = new WordNode(uniqueElements[i],0);
            result.add(node);
            for (int j = 0; j < array.length; j++) {
                if (node.getWord().equals(array[j])) {
                    node.incrementNumber();
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


    static List<WordNode> sort(List<WordNode> list) {
        Comparator<WordNode> comparator = new Comparator<WordNode>() {
            @Override
            public int compare(WordNode o1, WordNode o2) {
                return o2.getNumber() - o1.getNumber();
            }
        };
        Collections.sort(list,comparator);
        return list;
    }




    private static class WordNode {
        String word;
        int number;

        WordNode(String word, int number){
            this.word = word;
            this.number = number;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public int getNumber() {
            return number;
        }

        public void incrementNumber() {
            this.number++;
        }

        @Override
        public String toString() {
            return word + " - " + number + " раз";
        }
    }

}