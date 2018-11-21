package json_deserializer;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;

public class PrimitiveFieldDeserializer {

    /**
     * Метод для записи данных из нашего списка строк данных в поле, тип
     * которого это масив примитивов или строк
     */
    static void setValueInPrimitiveArrayField(Object object, Field field, List<String> list) throws ClassNotFoundException, IllegalAccessException {
        String className = field.getType().getTypeName();
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
        if(className.equals("long[]")){
            setValueInLongArrayField(counter, object, field, list);
        }
        if(className.equals("short[]")){
            setValueInShortArrayField(counter, object, field, list);
        }
        if(className.equals("byte[]")){
            setValueInByteArrayField(counter, object, field, list);
        }
        if(className.equals("double[]")){
            setValueInDoubleArrayField(counter, object, field, list);
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
     * Метод для записи данных из нашего списка строк данных в поле, тип
     * которого это масив типа String
     */
    private static void setValueInStringArrayField(int counter, Object object, Field field, List<String> list) throws ClassNotFoundException, IllegalAccessException {
        Class<?> clazz = Class.forName("java.lang.String");
        Object array = Array.newInstance(clazz, counter);
        for (int i = 0; i < counter; i++) {
            String fieldValue = WordListWorker.getClearValue(list.remove(0));
            //System.out.println(fieldValue);
            Array.set(array, i, fieldValue);
        }
        field.set(object, array);
    }

    /**
     * Метод для записи данных из нашего списка строк данных в поле, тип
     * которого это масив типа double
     */
    private static void setValueInDoubleArrayField(int counter, Object object, Field field, List<String> list) throws IllegalAccessException {
        double[] doubles = new double[counter];
        for (int i = 0; i < counter; i++) {
            String fieldValue = WordListWorker.getClearValue(list.remove(0));
            //System.out.println(fieldValue);
            doubles[i] = Double.parseDouble(fieldValue);
        }
        field.set(object,doubles);
    }

    /**
     * Метод для записи данных из нашего списка строк данных в поле, тип
     * которого это масив типа long
     */
    private static void setValueInLongArrayField(int counter, Object object, Field field, List<String> list) throws IllegalAccessException {
        long[] longs = new long[counter];
        for (int i = 0; i < counter; i++) {
            String fieldValue = WordListWorker.getClearValue(list.remove(0));
            //System.out.println(fieldValue);
            longs[i] = Long.parseLong(fieldValue);
        }
        field.set(object,longs);
    }

    /**
     * Метод для записи данных из нашего списка строк данных в поле, тип
     * которого это масив типа short
     */
    private static void setValueInShortArrayField(int counter, Object object, Field field, List<String> list) throws IllegalAccessException {
        short[] shorts = new short[counter];
        for (int i = 0; i < counter; i++) {
            String fieldValue = WordListWorker.getClearValue(list.remove(0));
            //System.out.println(fieldValue);
            shorts[i] = Short.parseShort(fieldValue);
        }
        field.set(object,shorts);
    }

    /**
     * Метод для записи данных из нашего списка строк данных в поле, тип
     * которого это масив типа byte
     */
    private static void setValueInByteArrayField(int counter, Object object, Field field, List<String> list) throws IllegalAccessException {
        byte[] bytes = new byte[counter];
        for (int i = 0; i < counter; i++) {
            String fieldValue = WordListWorker.getClearValue(list.remove(0));
            //System.out.println(fieldValue);
            bytes[i] = Byte.parseByte(fieldValue);
        }
        field.set(object,bytes);
    }

    /**
     * Метод для записи данных из нашего списка строк данных в поле, тип
     * которого это масив типа int
     */
    private static void setValueInIntegerArrayField(int counter, Object object, Field field, List<String> list) throws IllegalAccessException {
        int[] ints = new int[counter];
        for (int i = 0; i < counter; i++) {
            String fieldValue = WordListWorker.getClearValue(list.remove(0));
            //System.out.println(fieldValue);
            ints[i] = Integer.parseInt(fieldValue);
        }
        field.set(object,ints);
    }

    /**
     * Метод, который проверяет являеться ли поле-масив масивом примитивов
     * или нет
     */
    static boolean isArrayOfPrimitives(String className) {
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
}
