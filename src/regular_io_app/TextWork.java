package regular_io_app;

import sun.awt.image.ImageWatched;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class TextWork {


    /**
     * Метод, который обрабатывает строку(удаления всех не буквеных символов,
     * отбрасывания слов меньше трех букв, удаления слов из файла исключения и
     * удаления лишних пробелов) и возвращает обработаную строку
     */
    public static String getCorrectText(File file, File exceptionFile) throws IOException {
        /**1)считать файл*/
        String text = getStringFromFile(file);


        /**2)отбросить все символы кроме букв*/
        text = getTextOnlyWithLetters(text);


        /**3)отбросить слова меньше 3 символов*/
        text = getTextOnlyWithWordsBiggerThanTwo(text);



        text = MyString.trim(text, true);
        text = text.replaceAll("\n"," ");
        text = text.toLowerCase();

        /**4)отбросить слова, которые определены в отдельном файле(слова-исключения)*/
        text = getTextWithOutExceptionWords(text,exceptionFile);


        return text;
    }


    /**
     * Метод, который возвращает строку, в которую правильно записаны
     * обькты WordNode
     */
    public static String getStringOfWordsAdNumList(String text){
        if(text.isEmpty()){
            return null;
        }
        String result = "";
        String[] allWords = getWordArrayFromString(text);
        List<WordNode> uniquewordNumberList = getUniqueElementsNumber(allWords);
        uniquewordNumberList = sort(uniquewordNumberList);
        for(WordNode node : uniquewordNumberList){
            result += node + ",\n";
        }
        return result;
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
     * 4)Метод, для удаления из строки всех слов которые находяться в файле с
     * исключениями
     */
    public static String getTextWithOutExceptionWords(String text, File exceptionFile) throws IOException {
        List<String> exceptionWordList = getStringListFromFile(exceptionFile);
        if(exceptionWordList.isEmpty()){
            return text;
        }
        for (String word : exceptionWordList) {
            System.out.println(word);
            text = text.replaceAll(word, "");
            text = MyString.trim(text,true);
        }
        return text;
    }

    /**
     * Метод,читает чтоб посимвольно прочитать файл и записать его по
     * словам в список строк(слов)
     */
    public static List<String> getStringListFromFile(File file) throws IOException {
        String text = getStringFromFile(file);
        text = getTextOnlyWithLetters(text);
        text = getTextOnlyWithWordsBiggerThanTwo(text);
        text = MyString.trim(text, true);
        text = text.replaceAll("\n"," ");
        text = text.toLowerCase();
        List<String> wordList = new LinkedList<>();
        String word = "";
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == ' '){
                wordList.add(word);
                word = "";
            }
            else{
                word += chars[i];
            }
        }
        return wordList;
    }



    /**
     * 5)Метод, который получает из масива строк-слов список, состоящий из элкментов
     * WordNode(в которых находяться уникальные слова и частота их встреч в маисве)
     */
    public static List<WordNode> getUniqueElementsNumber(String[] array) {
        if(array.length == 0){
            return null;
        }
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
     * 6)Метод, который подсчитывает общее количество слов в строке
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


    /**
     * 7)Метод, который подсчитывает количество уникальных слов в строке
     */
    public static int getUniqueWordNum(String text) {
        char[] chars = text.toCharArray();
        List uniqueWordList = new LinkedList();
        String word = "";
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                if(!uniqueWordList.contains(word)) {
                    uniqueWordList.add(word);
                }
                word = "";
            }
            word += chars[i];
        }
        //System.out.println(uniqueWordList.size());
        return uniqueWordList.size();
    }



    /**
     * Метод для перевода строки текста в масив строк-слов
     */
    public static String[] getWordArrayFromString(String text) {
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


    /**
     * Метод для сортировки переданого списка, состоящего из WordNode
     */
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


    /**
     * Метод, который копирует строку в файл
     */
    public static void copyToFile(String text, String fileName) throws IOException {
        Writer writer = new FileWriter(fileName,false);
        char[] chars = text.toCharArray();
        for(int i = 0; i < chars.length; i++){
            if(chars[i] == ','){
                String str = "\r\n";
                writer.write(str);
            }
            else {
                writer.append(chars[i]);
            }
        }
        writer.flush();

    }



    /**
     * Клас представляющий ноду, которая хранит в себе значение слова и количество
     * встреч этого слова в файле
     */
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