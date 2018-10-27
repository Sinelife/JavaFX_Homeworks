package timecounter;

//import java_essential.homeworks.homework06.MyArrayList;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Клас тестер, вызывающий метод run класа TimeCounter
 */
public class Tester {
    public static final int LIST_SIZE = 1_000_000;


    public static void main(String[] args) {
        List<Object> arrayList = new ArrayList<>();
        TimeCounter tc = new TimeCounter(arrayList, LIST_SIZE);
        //tc.run();

        List<Object> linkedList = new LinkedList<>();
        tc = new TimeCounter(linkedList, LIST_SIZE);
        tc.run();

        linkedList = new LinkedList<>();
        tc = new TimeCounter(linkedList, LIST_SIZE);
        tc.run();

        linkedList = new LinkedList<>();
        tc = new TimeCounter(linkedList, LIST_SIZE);
        tc.run();

        linkedList = new LinkedList<>();
        tc = new TimeCounter(linkedList, LIST_SIZE);
        tc.run();


    }
}
