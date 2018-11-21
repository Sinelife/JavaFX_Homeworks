package json_deserializer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

public class WordListWorker {

    /**
     * Метод, который убирает из строки лишние символы вроде
     * ':',',','{','}','[',']
     */
    public static String getClearValue(String str){
        char[] chars = str.toCharArray();
        str = "";
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == ',' || chars[i] == ':' ||chars[i] == '[' ||chars[i] == ']' ||chars[i] == '{' || chars[i] == '}'){

            }
            else {
                str += chars[i];
            }
        }
        return str;
    }


    /**
     * Прочитать посимвольно файл и записать его в строку
     */
    static String getStringFromFile(File file) throws IOException {
        Reader reader = new FileReader(file);
        String text = "";
        int c;
        while ((c = reader.read()) != -1) {
            text += (char) c;
        }
        return text;
    }

    /**
     * Метод, для проставления в списке слов меток по которым при считывании
     * данных из списка слов, по которым будет определяться конец считывания
     * одного елемента и начало считывания другого
     */
    private static List<String> getListWithMarksForElements(List<String> list){
        List<String> newList = new LinkedList<>();
        int counter = 0;
        for (String word : list) {
            newList.add(word);
            if(word.contains("{")){
                counter++;
            }
            if(word.contains("}")){
                counter--;
            }
            if(counter == 0){
                String markWord = ",";
                newList.add(markWord);
            }
        }
        return newList;
    }

    /**
     * Метод для преобразования считаной из json файла строки и
     * преобразования ее в список строк
     */
    static List<String> getParsedList(String text){
        text = text.replaceAll("\n|\r\n|", "");
        List<String> list = new LinkedList<>();
        char[] chars = text.toCharArray();
        String word = "";
        for(int i = 0; i < chars.length; i++){
            if (chars[i] == ':' || chars[i] == ',') {
                word += chars[i];
                word = getWordWithOutSpaces(word);
                list.add(word);
                word = "";
            }
            else {
                word += chars[i];
            }
        }
        word = getWordWithOutSpaces(word);
        list.add(word);
        list = getListWithMarksForElements(list);
        return list;
    }


    /**
     * Метод, который убирает из строки все пробелы кроме пробелов
     * в кавычках
     */
    private static String getWordWithOutSpaces(String word){
        int counter = 0;
        char[] chars = word.toCharArray();
        String newWord = "";
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '"'){
                counter++;
            }
            if(chars[i] == ' ' && counter == 1){
                newWord += chars[i];
            }
            if(chars[i] != ' ' && chars[i] != '"'){
                newWord += chars[i];
            }
        }
        return newWord;
    }



    /**
     * Метод необходимый для вычисления конца поля масива обьектов.
     * Метод, находит елемент списка слов, который будет в конце поля масива
     * обьектов и возвращает его. В дальнейшем на него будет ориентироваться
     * метод последовательно считывающий данные с списка слов.
     */
    static String getElemOfEndOfArrayData(List<String> list){
        int counter = 1;
        for (int i = 1; i < list.size(); i++) {
            if(list.get(i).contains("[")){
                int num = getNumOfSymbolInString(list.get(i), '[');
                counter += num;
            }
            if(list.get(i).contains("]")){
                int num = getNumOfSymbolInString(list.get(i), ']');
                counter -= num;
            }
            if(counter <= 0){
                return list.get(i + 1);
            }
        }
        return null;
    }



    /**
     * Метод для подсчета количества заданого символа в строке
     */
    private static int getNumOfSymbolInString(String str, char symbol){
        char[] chars = str.toCharArray();
        int counter = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == symbol){
                counter++;
            }
        }
        return counter;
    }
}
