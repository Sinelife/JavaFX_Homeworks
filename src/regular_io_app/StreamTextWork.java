package regular_io_app;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class StreamTextWork {


    /**
     * Метод, который возвращает список элементов WordNode(уникальные слова
     * и количество их повторений) сформированый из списка всех слов текста
     */
    public static List<WordNode> getWordNodeList(List<String> list){
        //Вычисление списка уникальных слов
        List<String> uniqueWordList = list
                .stream()
                .distinct()
                .collect(Collectors.toList());

        List<WordNode> wordNodeList = new LinkedList<>();
        //Заполнение списка слов-количеств встреч словами
        uniqueWordList
                .stream()
                .forEach(wordNode -> wordNodeList.
                        add(0,new WordNode(wordNode,0)));
        //Подсчет количства встреч слов в списке слов-количеств
        wordNodeList
                .forEach(wordNode -> list
                    .stream()
                    .filter(word -> wordNode.getWord().equals(word))
                    .forEach(word -> wordNode.incrementNumber()));
        wordNodeList.sort((o1, o2) -> o2.getNumber() - o1.getNumber());

        return wordNodeList;
    }


    /**
     * Метод, который возвращает строку, созданную из списка WordNode
     * (уникальные слова и количество их повторений)
     */
    public static String convertWordNodeListToString(List<String> list){
        List<WordNode> wordNodeList = getWordNodeList(list);
        String result = "";
        for(WordNode node : wordNodeList){
            result += node + ",\n";
        }
        return result;
    }


    /**
     * Метод, для фильтрации списка слов. Все слова меньше трех символов и слова,
     * которые встречаються в файле исключений удаляються.
     */
    public static List<String> getFilteredWordList(File file, File exceptionFile) throws IOException {
        List<String> list = getWordListFromFile(file);
        List<String> exceptionList = getWordListFromFile(exceptionFile);
        list = list.stream()
                .filter(word -> word.length() > 2)
                .filter(word -> !exceptionList.contains(word))
                .collect(Collectors.toList());
        return list;
    }


    /**
     * Метод, который получает список всех слов(только с буквами) из
     * текста переданого файла
     */
    private static List<String> getWordListFromFile(File file) throws IOException {
        Reader reader = new FileReader(file);
        List<String> list = new LinkedList<>();
        String word = "";
        int c;
        while ((c = reader.read()) != -1) {
            if(!isLetter(c)) {
                list.add(word.toLowerCase());
                word = "";
            }
            else {
                word += (char) c;
            }
        }
        list.add(word);
        return list;
    }


    /**
     * Проверка являеться ли символ буквой
     */
    private static boolean isLetter(int symbol){
        if((char)symbol > 64 && (char)symbol < 91){
            return true;
        }
        if((char)symbol > 96 && (char)symbol < 123){
            return true;
        }
        return false;
    }



    /**
     * Метод, который подсчитывает общее количество слов в строке
     */
    public static int getWordNum(List<String> list) {
        return list.size();
    }


    /**
     * Метод, который подсчитывает количество уникальных слов в строке
     */
    public static int getUniqueWordNum(List<String> list) {
        long counter = list
                .stream()
                .distinct()
                .count();
        return (int)counter;
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
    public static class WordNode {
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