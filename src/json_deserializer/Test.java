package json_deserializer;

import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, NoSuchFieldException {
        int[] array = new int[8];
        Integer k = 100;
        int x = k;


        A a = new A();
        Field f = a.getClass().getDeclaredField("a");
        Class<?> clazz = Class.forName("java.lang.String[]");
        System.out.println(clazz.getName());
    }


    public static class A{
        Integer[] a;

    }
}
