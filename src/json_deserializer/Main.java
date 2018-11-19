package json_deserializer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static List<String> primitives;
    private static int isEndOfArray = 1;


    public static void main(String[] args) throws IOException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchFieldException {
        String[] primitivesArray = {"int","double","boolean","long","short","byte"};
        primitives = new LinkedList<>();
        for (String primitive : primitivesArray) {
            primitives.add(primitive);
        }

        File file = new File("src/json_deserializer/My.json");
        String text = getStringFromFile(file);
        System.out.println(text);
        List<String> wordList = getParsedList(text);
        int counter = 1;
        for (String s : wordList) {
            System.out.println(counter++ + ")" + s);
        }
        Character c = new Character();
        Jewel j = new Jewel();
        Weapon w = new Weapon();


        setValueInField(c, wordList);



        System.out.println("\n\n\n\n\n\n\n\n" + c);

    }




    /**
     * Метод для заполнения значениями всех полей обьекта
     */
    public static  void setValueInField(Object object, List<String> list) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, InstantiationException {
        Class<?> clazz = object.getClass();
        System.out.println(clazz);
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if(list.isEmpty()){
                break;
            }
            String fieldName = getClearValue(list.remove(0));
            Field field = clazz.getDeclaredField(fieldName);
            System.out.println("\t" + fieldName);
            field.setAccessible(true);
            if (field.getType().isPrimitive() || field.getType().getSimpleName().equals("String")) {
                System.out.println("dddddddd");
                if(list.get(0).contains("]")){
                    isEndOfArray--;
                }
                if(list.get(0).contains("[")){
                    isEndOfArray++;
                }
                String fieldValue = getClearValue(list.remove(0));
                setCastedFieldValue(object, field, fieldValue);
            }
            else if(field.getType().isArray()){
                System.out.println(field.getType().getTypeName());
                setValueInArrayField(object,field,list);
            }
            else {
                Class<?> fieldType = Class.forName(field.getType().getTypeName());
                Object o = fieldType.newInstance();
                setValueInField(o, list);
                field.set(object, o);
            }
        }
    }


    /**
     * Метод для преобразования считаного значения поля в необходимый тип
     * Этот метод необходим так как все примитивы должны преобразовываться
     * по своему из строки
     */
    public static void setCastedFieldValue(Object object, Field field, String fieldValue) throws IllegalAccessException {
        Class<?> fieldType = field.getType();
        if(fieldType.getName().equals("int")){
            field.set(object, Integer.valueOf(fieldValue));
        }
        else if(fieldType.getName().equals("boolean")){
            field.set(object, Boolean.valueOf(fieldValue));
        }
        else if(fieldType.getName().equals("double")){
            field.set(object, Double.valueOf(fieldValue));
        }
        else if(fieldType.getName().equals("long")){
            field.set(object, Long.valueOf(fieldValue));
        }
        else if(fieldType.getName().equals("short")){
            field.set(object, Short.valueOf(fieldValue));
        }
        else if(fieldType.getName().equals("byte")){
            field.set(object, Byte.valueOf(fieldValue));
        }
        else {
            field.set(object, fieldType.cast(fieldValue));
        }
    }


    /**
     * Метод для записи данных из нашего списка строк данных в поле-масив
     * обьекта
     */
    public static void setValueInArrayField(Object object, Field field, List<String> list) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        String className = field.getType().getTypeName();
        if(isArrayOfPrimitives(className)){
            setValueInPrimitiveArrayField(object,field,list);
        }
        else {
            Class<?> clazz = Class.forName(getClearValue(className));
            System.out.println(clazz);
            List<Object> listOfObjects = new LinkedList<>();
            int counter = 0;
            while (isEndOfArray != 0) {
                className = getClearValue(className);
                Class<?> clazzObject = Class.forName(className);
                Object o = clazzObject.newInstance();
                setValueInField(o, list);
                listOfObjects.add(o);
                counter++;
            }
            Object array = Array.newInstance(clazz, counter);
            counter = 0;
            for (Object o : listOfObjects) {
                Array.set(array, counter, o);
                counter++;
            }
            //isEndOfArray = false;
            field.set(object, array);
        }
    }


    /**
     * Метод, который проверяет являеться ли поле-масив масивом примитивов
     * или нет
     */
    private static boolean isArrayOfPrimitives(String className) throws ClassNotFoundException {
        if(className.equals("int[]")){
            return true;
        }
        else if(className.equals("double[]")){
            return true;
        }
        else if(className.equals("byte[]")){
            return true;
        }
        else if(className.equals("long[]")){
            return true;
        }
        else if(className.equals("short[]")){
            return true;
        }
        else if(className.equals("char[]")){
            return true;
        }
        else if(className.equals("java.lang.String[]")){
            return true;
        }
        return false;
    }


    /**
     * Метод для записи данных из нашего списка строк данных в поле-масив
     * примитив
     */
    private static void setValueInPrimitiveArrayField(Object object, Field field, List<String> list) throws ClassNotFoundException, IllegalAccessException {
        String className = field.getType().getTypeName();
        Class<?> clazz = null;
        int counter = 1;
        for (String s : list) {
            if(s.contains("]")){
                break;
            }
            counter++;
        }
        if(className.equals("java.lang.String[]")){
            setValueInStringArrayField(counter, object, field, list);
        }
        if(className.equals("int[]")){
            setValueInIntegerArrayField(counter, object, field, list);
        }
    }


    private static void setValueInStringArrayField(int counter, Object object, Field field, List<String> list) throws ClassNotFoundException, IllegalAccessException {
        Class<?> clazz = Class.forName("java.lang.String");
        Object array = Array.newInstance(clazz, counter);
        for (int i = 0; i < counter; i++) {
            String fieldValue = getClearValue(list.remove(0));
            System.out.println(fieldValue);
            Array.set(array, i, fieldValue);
        }
        field.set(object, array);
    }

    private static void setValueInIntegerArrayField(int counter, Object object, Field field, List<String> list) throws ClassNotFoundException, IllegalAccessException {
        int[] ints = new int[counter];
        for (int i = 0; i < counter; i++) {
            String fieldValue = getClearValue(list.remove(0));
            System.out.println(fieldValue);
            ints[i] = Integer.parseInt(fieldValue);
        }
        field.set(object,ints);
    }


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
     * Метод для преобразования считаной из json файла строки и
     * преобразования ее в список строк
     */
    private static List<String> getParsedList(String text){
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

}
