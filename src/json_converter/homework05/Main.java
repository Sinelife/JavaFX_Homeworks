package json_converter.homework05;


import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;


public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Jewel jewel = new Jewel("кольцо", "защита");
        Weapon[] weapons = {new Weapon("нож", 30), new Weapon("кинжал", 50), new Weapon("лук", 30)};
        Character character = new Character("Элейн","ельф",1000, true, "Элрис", weapons, jewel);
        System.out.println(toJson(character));


        Weapon[] allWeapons = {new Weapon("нож", 30), new Weapon("кинжал", 50), new Weapon("лук", 30)};
        System.out.println(toJson(allWeapons));
    }




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
                result += ",\n";
            }
            counter++;
        }
        result += "\n]";
        return result;
    }



    /**
     * Метод возвращающий переданый как параметр объект в json представлении
     * Вызывает рекурсивный метод, в котором и происходит перевод объекта.
     */
    public static String toJson(Object elem) throws IllegalAccessException {
        return "[" + toJson(elem, 1) + "\n]";
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
            result += "\n" + getSpaceNum(num) + "{" + "\n";
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
                        result += ",\n";
                    }
                    else{
                        result += "\n";
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
}
