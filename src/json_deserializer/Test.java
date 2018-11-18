package json_deserializer;

import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) throws IllegalAccessException {
        Character c = new Character();
        Object o;
        Field[] fields = c.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            field.setAccessible(true);
            if(field.getName().equals("name")){
                field.set(c, "Кахат");
            }
        }
        System.out.println(c);
    }
}
