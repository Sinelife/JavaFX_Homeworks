package json_converter.homework05;


import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;


public class JsonConverter {

    /**
     * Метод возвращающий переданый как параметр массив объектов в json
     * представлении
     */
    public static String toJson(Object[] elements) throws IllegalAccessException {
        String result = "[";
        int counter = 0;
        for(Object object : elements){
            String temp = toJson(object);
            result += temp.substring(1, temp.length() - 2);
            if(counter != elements.length - 1){
                result += ",\r\n";
            }
            counter++;
        }
        result += "\r\n]";
        return result;
    }



    /**
     * Метод возвращающий переданый как параметр объект в json представлении
     * Вызывает рекурсивный метод, в котором и происходит перевод объекта.
     */
    public static String toJson(Object elem) throws IllegalAccessException {
        return "[" + toJson(elem, 1) + "\r\n]";
    }



    /**
     * Рекурсивный метод непосредствено переводящий объект в json представление
     *
     * Вначале происходит проверка содержит ли клас необходимую анотацию.
     * Если содержит, то мы получаем все поля класа. Далее пробегаем по всем
     * полям, которые содержат необходимую анотацию.
     * Сначала мы заносим в нашу результируюшую строку имя поля, затем
     * получаем значение поля. Если значение примитив или строчка, то
     * значение сразу заносим в результирующую строку, а если нет, то
     * вызываем метод getFieldValue, который уже в зависимости от того масив
     * это или просто не примитив будет снова вызывать toJson(elem,num) и
     * все будет повторяться до тех пор пока метод не пробежиться по всем
     * полям нашего класа и всем полям не примитивных полей, помеченых
     * необходимой анотацией.
     */
    public static String toJson(Object elem, int num) throws IllegalAccessException {
        String result = "";
        Class aClass = elem.getClass();
        if (aClass.isAnnotationPresent(JsonEntity.class)) {
            result += "\r\n" + getSpaceNum(num) + "{" + "\r\n";
            Field[] fields = aClass.getDeclaredFields();
            int counter = 0;
            for(Field field : fields) {
                if(field.isAnnotationPresent(JsonField.class)) {
                    result += getFieldValue(field, elem, num);
                    if(field.getType().isPrimitive() || field.get(elem) instanceof String) {
                        result += getPrimitiveFieldValue(field, elem);
                    }
                    else{
                        result += toJson(field, num);
                    }

                    if(counter != getNumOfFieldsWithAnnotation(fields, JsonField.class) - 1){
                        result += ",\r\n";
                    }
                    else{
                        result += "\r\n";
                    }
                    counter++;
                }
            }
            result += getSpaceNum(num) + "}";
        }
        return result;
    }




    /**
     * Метод для получения имени поля
     * (Параметр num необходим для правильного количества отсупов
     * при большом количестве не примитивных полей)
     */
    public static String getFieldName(Field field, int num){
        String result = "";
        field.setAccessible(true);
        Annotation annotation = field.getAnnotation(JsonField.class);
        if (!((JsonField) annotation).name().equals("")) {
            String newFieldName = ((JsonField) annotation).name();
            result += getSpaceNum(num) + " \"" + newFieldName + "\": ";
        }
        else {
            result += getSpaceNum(num) + " \"" + field.getName() + "\": ";
        }
        return result;
    }



    /**
     * Метод для получения значений полей примитивных или строкового типов
     */
    public static String getPrimitiveFieldValue(Field field, Object elem) throws IllegalAccessException {
        String result = "";
        if(field.get(elem) instanceof String){
            result += "\"" + field.get(elem) + "\"";
        }
        else {
            result += field.get(elem);
        }
        return result;
    }



    /**
     * Метод для получения значения полей для не примитивных полей.
     *
     * Если поле не масив то получаем обьект поля и вызываем метод
     * toJson(object, num) и туда передаем наш обьект.
     * Если поле масив элементов, то будем по элементно вызывать метод
     * oJson(object, num) для каждого элемента масива.
     * (Параметр num необходим для правильного количества отсупов
     * при большом количестве не примитивных полей)
     */
    public static String getFieldValue(Field field, Object elem,int num) throws IllegalAccessException {
        String result = "";
        result += getFieldName(field, num);
        if(!field.getType().isArray()){
            Object object = field.get(elem);
            result += toJson(object, ++num);
        }
        else if(isArrayPrimitiveFieldValue(field)){
            result += setArrayPrimitiveFieldValue(field, elem);
        }
        else {
            Object[] objects = (Object[]) field.get(elem);
            result += "[";
            for (int i = 0; i < objects.length; i++) {
                if(i == 0){
                    num++;
                }
                result += toJson(objects[i], num);
                if (i != objects.length - 1) {
                    result += ", ";
                }
            }
            result += "]";
        }
        return result;
    }



    private static boolean isArrayPrimitiveFieldValue(Field field){
        if(field.getType().getName().equals("[I")){
            System.out.println("int[] ---------------");
            return true;
        }
        System.out.println(field.getType().getName());
        if(field.getType().getName().equals("[Ljava.lang.String;")){
            System.out.println("String[] ---------------");
            return true;
        }
        if(field.getType().getName().equals("[D")){
            System.out.println("double[] ---------------");
            return true;
        }
        if(field.getType().getName().equals("[I")){
            System.out.println("int[] ---------------");
            return true;
        }
        if(field.getType().getName().equals("[J")){
            System.out.println("long[] ---------------");
            return true;
        }
        if(field.getType().getName().equals("[B")){
            System.out.println("byte[] ---------------");
            return true;
        }
        if(field.getType().getName().equals("[S")){
            System.out.println("short[] ---------------");
            return true;
        }
        if(field.getType().getName().equals("[C")){
            System.out.println("char[] ---------------");
            return true;
        }
        return false;
    }


    private static String setArrayPrimitiveFieldValue(Field field, Object elem) throws IllegalAccessException {
        String result = "[";
        System.out.println(field.getType().getName());
        if(field.getType().getName().equals("[I")){
            result += setIntArrayFieldValue(field,elem);
        }
        if(field.getType().getName().equals("[D")){
            result += setDoubleArrayFieldValue(field,elem);
        }
        if(field.getType().getName().equals("[J")){
            result += setLongArrayFieldValue(field,elem);
        }
        if(field.getType().getName().equals("[B")){
            result += setByteArrayFieldValue(field,elem);
        }
        if(field.getType().getName().equals("[S")){
            result += setShortArrayFieldValue(field,elem);
        }
        if(field.getType().getName().equals("[C")){
            result += setCharArrayFieldValue(field,elem);
        }
        if(field.getType().getName().equals("[Ljava.lang.String;")){
            result += setStringArrayFieldValue(field, elem);
        }

        result += "]";
        return result;
    }




    private static String setStringArrayFieldValue(Field field, Object elem) throws IllegalAccessException {
        String result = "";
        String[] strings = (String[]) field.get(elem);
        for (int i = 0; i < strings.length; i++) {
            result += "\"" + strings[i] + "\"";
            if (i != strings.length - 1) {
                result += ", ";
            }
        }
        return result;
    }

    private static String setCharArrayFieldValue(Field field, Object elem) throws IllegalAccessException {
        String result = "";
        char[] chars = (char[]) field.get(elem);
        for (int i = 0; i < chars.length; i++) {
            result += "\"" + chars[i] + "\"";
            if (i != chars.length - 1) {
                result += ", ";
            }
        }
        return result;
    }

    private static String setShortArrayFieldValue(Field field, Object elem) throws IllegalAccessException {
        String result = "";
        short[] ints = (short[]) field.get(elem);
        for (int i = 0; i < ints.length; i++) {
            result += ints[i];
            if (i != ints.length - 1) {
                result += ", ";
            }
        }
        return result;
    }

    private static String setByteArrayFieldValue(Field field, Object elem) throws IllegalAccessException {
        String result = "";
        byte[] ints = (byte[]) field.get(elem);
        for (int i = 0; i < ints.length; i++) {
            result += ints[i];
            if (i != ints.length - 1) {
                result += ", ";
            }
        }
        return result;
    }

    private static String setIntArrayFieldValue(Field field, Object elem) throws IllegalAccessException {
        String result = "";
        int[] ints = (int[]) field.get(elem);
        for (int i = 0; i < ints.length; i++) {
            result += ints[i];
            if (i != ints.length - 1) {
                result += ", ";
            }
        }
        return result;
    }

    private static String setDoubleArrayFieldValue(Field field, Object elem) throws IllegalAccessException {
        String result = "";
        double[] ints = (double[]) field.get(elem);
        for (int i = 0; i < ints.length; i++) {
            result += ints[i];
            if (i != ints.length - 1) {
                result += ", ";
            }
        }
        return result;
    }

    private static String setLongArrayFieldValue(Field field, Object elem) throws IllegalAccessException {
        String result = "";
        long[] ints = (long[]) field.get(elem);
        for (int i = 0; i < ints.length; i++) {
            result += ints[i];
            if (i != ints.length - 1) {
                result += ", ";
            }
        }
        return result;
    }



    /**
     * Метод необходимый для вычесления полей, содеражащих необходимую
     * нам анотацию.
     */
    public static int getNumOfFieldsWithAnnotation(Field[] fields, Class<JsonField> annotation){
        int counter = 0;
        for(Field field : fields){
            if(field.isAnnotationPresent(annotation)){
                counter++;
            }
        }
        return counter;
    }


    /**
     * Метод возвращающий строку из заданого количества пробелов
     */
    public static String getSpaceNum(int num){
        String result = "";
        for (int i = 0; i < num; i++) {
            result += "  ";
        }
        return result;
    }


    /**
     * Метод, который копирует строку в файл
     */
    public static void copyToFile(String text, String fileName) throws IOException {
        Writer writer = new FileWriter(fileName,false);
        char[] chars = text.toCharArray();
        List<java.lang.Character> symbolsForMoveToNextLine = new LinkedList<>();
        symbolsForMoveToNextLine.add(',');
        symbolsForMoveToNextLine.add('[');
        symbolsForMoveToNextLine.add('{');
        for(int i = 0; i < chars.length; i++){
            writer.append(chars[i]);
        }
        writer.flush();
    }
}
