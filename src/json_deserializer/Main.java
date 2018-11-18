package json_deserializer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchFieldException {
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
//
//        Class<?> clazz = Class.forName(c.getClass().getName());
//        Object instance = clazz.newInstance();
//        setValueInField(instance, "name", "Kron");
//        setValueInField(instance, "age", 12);
//
//        //Class<?> clazz1 = Class.forName()
//        System.out.println(instance);


    }




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
                String fieldValue = getClearValue(list.remove(0));
                setCastedFieldValue(object, field, fieldValue);
            }
            //else if(field.getType().isArray()){
            //setValueInArrayField(getClearValue(field.getType().getTypeName()),list);
            //}
            else {
                Class<?> fieldType = Class.forName(field.getType().getTypeName());
                Object o = fieldType.newInstance();
                setValueInField(o, list);
                field.set(object, o);
            }
        }
    }


    public static void setObjectFieldValue(Object object, Field field, Object fieldValue) throws IllegalAccessException {

    }


    public static void setCastedFieldValue(Object object, Field field, String fieldValue) throws IllegalAccessException {
        Class<?> fieldType = field.getType();
        if(fieldType.getName().equals("int")){
            System.out.println("INTEGER");
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


//    public static void setValueInArrayField(String className, List<String> list) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
//        Class<?> clazz = Class.forName(className);
//        Object o = clazz.newInstance();
//        String fieldName = getClearValue(list.remove(0));
//        Field field = clazz.getDeclaredField(fieldName);
//        for (int i = 0; i < objects; i++) {
//
//        }
//    }



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

    private static List<String> getParsedList(String text){
        text = text.replaceAll("\n|\r\n| |\"", "");
        List<String> list = new LinkedList<>();
        char[] chars = text.toCharArray();
        String word = "";
        for(int i = 0; i < chars.length; i++){
            if (chars[i] == ':' || chars[i] == ',') {
                word += chars[i];
                word.trim();
                list.add(word);
                word = "";
            } else {
                word += chars[i];
            }
        }
        list.add(word);
        return list;
    }


}
