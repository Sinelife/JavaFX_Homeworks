package json_deserializer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class JsonDeserializer {

    public static void main(String[] args) throws IOException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchFieldException {
        File file = new File("src/json_deserializer/My.json");
        Character c = new Character();
        List<Character> characterList = getDeserializedObjectList(c,file);

        //System.out.println(characterList.size());
        for (Character character : characterList) {
            System.out.println(character + "\n\n");
        }

    }



    /**
     * Метод, который считывает поэлементно обьекты из списка слов. Тип обьекта
     * береться по типу переданого обьекта elem
     */
    public static <E> List<E> getDeserializedObjectList(E elem, File file) throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException, IOException {
        String text = WordListWorker.getStringFromFile(file);
        List<String> wordList = WordListWorker.getParsedList(text);

        List<E> objectsList = new LinkedList<>();
        while(!wordList.isEmpty()) {
            Class<E> objectType = (Class<E>) Class.forName(elem.getClass().getName());
            E element = objectType.newInstance();
            setValueInField(element,wordList);
            objectsList.add(element);
        }
        return objectsList;
    }


    /**
     * Метод для заполнения значениями всех полей обьекта
     */
    public static void setValueInField(Object object, List<String> list) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, InstantiationException {
        Class<?> clazz = object.getClass();
        //System.out.println(clazz);
        Field[] fields = clazz.getDeclaredFields();
        for (Field ignored : fields) {
            if(list.isEmpty()){
                break;
            }
            if(list.get(0).equals(",")){
                list.remove(0);
                break;
            }
            String fieldName = WordListWorker.getClearValue(list.remove(0));
            Field field = clazz.getDeclaredField(fieldName);
            //System.out.println("\t" + fieldName);
            field.setAccessible(true);
            if (field.getType().isPrimitive() || field.getType().getSimpleName().equals("String")) {
                String fieldValue = WordListWorker.getClearValue(list.remove(0));
                PrimitiveFieldDeserializer.setCastedFieldValue(object, field, fieldValue);
            }
            else if(field.getType().isArray()){
                //System.out.println(field.getType().getTypeName());
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
     * Метод для записи данных из нашего списка строк данных в поле-масив
     * обьекта
     */
    public static void setValueInArrayField(Object object, Field field, List<String> list) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        String className = field.getType().getTypeName();
        if(PrimitiveFieldDeserializer.isArrayOfPrimitives(className)){
            PrimitiveFieldDeserializer.setValueInPrimitiveArrayField(object,field,list);
        }
        else {
            Class<?> clazz = Class.forName(WordListWorker.getClearValue(className));
            //System.out.println(clazz);
            List<Object> listOfObjects = new LinkedList<>();
            int counter = 0;
            String endArrayDataElem = WordListWorker.getElemOfEndOfArrayData(list);
            while (true) {
                if(list.get(0).equals(endArrayDataElem)){
                    break;
                }
                className = WordListWorker.getClearValue(className);
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
            field.set(object, array);
        }
    }
}
