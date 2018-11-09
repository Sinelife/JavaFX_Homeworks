package regular_io_app;


import java.io.*;
import java.util.*;


public class TextWork {

    /**
     * Метод, который возвращает список всех слов текста, полученого из файла
     * кроме всех слов текста файла-исключений
     */
    public static List<String> getWordList(File file, File exceptionFile) throws IOException {
        List<String> mainList = getWordListFromFile(file);
        List<String> exceptionList = getWordListFromFile(exceptionFile);
        for(String word : exceptionList){
            while(mainList.contains(word)) {
                mainList.remove(word);
            }
        }
        return mainList;
    }


    /**
     * Метод, который возвращает список уникальных слов сформированый из
     * переданого списка всех слов
     */
    private static List<String> getUniqueWordList(List<String> list){
        Set<String> set = new HashSet();
        for(String word : list){
            set.add(word);
        }
        List<String> uniqueWordList = new LinkedList<>();
        for(String word : set){
            uniqueWordList.add(word);
        }
        return uniqueWordList;
    }


    /**
     * Метод, который возвращает список элементов WordNode(уникальные слова
     * и количество их повторений) сформированый из списка всех слов текста
     */
    public static List<WordNode> getWordNodeList(List<String> list){
        List<String> uniqueWordList = getUniqueWordList(list);
        List<WordNode> wordNodeList = new LinkedList<>();
        for(String word : uniqueWordList){
            wordNodeList.add(new WordNode(word,0));
        }
        for(WordNode wordNode : wordNodeList){
            for(String word : list){
                if(wordNode.word.equals(word)){
                    wordNode.incrementNumber();
                }
            }
        }
        sort(wordNodeList);
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
     * Прочитать посимвольно файл и записать его в строку
     */
    private static String getStringFromFile(File file) throws IOException {
        Reader reader = new FileReader(file);
        String text = "";
        int c;
        while ((c = reader.read()) != -1) {
            text += (char) c;
        }
        return text;
    }


    /**
     * Отбросить все символы кроме букв из данной строки
     */
    private static String getTextOnlyWithLetters(String text) {
        text = text.replaceAll("[^ \nA-Za-z]", " ");
        return text;
    }

    /**
     * Отбросить все слова, в которых меньше 3 символов, из данной строки
     */
    private static String getTextOnlyWithWordsBiggerThanTwo(String text) {
        text = text.replaceAll("\\b\\w{1,2}\\b", " ");
        return text;
    }


    /**
     * Метод возвращает список слов из текста прочитаного из файла
     * Перед записью в список происходит удаления
     * не буквеных символов,
     * слов, которые состоят из меньше чем 3 символов
     * и лишних пробелов и переходов на новую строчку
     */
    private static List<String> getWordListFromFile(File file) throws IOException {
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
     * Метод, который подсчитывает общее количество слов в строке
     */
    public static int getWordNum(List<String> list) {
        return list.size();
    }


    /**
     * Метод, который подсчитывает количество уникальных слов в строке
     */
    public static int getUniqueWordNum(List<String> list) {
        Set<String> set = new HashSet<>();
        for(String word : list){
            set.add(word);
        }
        return set.size();
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