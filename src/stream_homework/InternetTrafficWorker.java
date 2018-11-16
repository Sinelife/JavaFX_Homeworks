package stream_homework;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InternetTrafficWorker {


    /**
     * Метод, который возвращает список объектов InternetTrafficObject,
     * сформированый из текста в файле
     */
    public static List<InternetTrafficObject> getTrafficListFromFile() throws IOException {
        List<InternetTrafficObject> trafficList = new LinkedList<>();
        Files.lines(Paths.get("traffic.txt"))
                .forEach(line -> trafficList.add(getTrafficObjectFromLine(Arrays.stream(line.split("\\s")))));
        return trafficList;
    }


    /**
     * Метод, который возвращает объект InternetTrafficObject, сформированый
     * из строчки текста в файле
     */
    private static InternetTrafficObject getTrafficObjectFromLine(Stream<String> line) {
        List<String> wordsInLine = line
                .filter(word -> word.matches("[-.:0-9]+"))
                .filter(word -> !word.contains(":"))
                .collect(Collectors.toList());

        InternetTrafficObject trafficObject = new InternetTrafficObject(
                convertToDate(wordsInLine.get(0)),
                Double.valueOf(wordsInLine.get(1)),
                Double.valueOf(wordsInLine.get(2)),
                Double.valueOf(wordsInLine.get(3)),
                Double.valueOf(wordsInLine.get(4)),
                Double.valueOf(wordsInLine.get(5)),
                Double.valueOf(wordsInLine.get(6)));

        return trafficObject;
    }


    /**
     * Метод для перевода строки в обьект даты
     */
    private static Date convertToDate(String str){
        String[] dateArray = str.split("-");
        Date date = new Date();
        date.setYear(Integer.valueOf(dateArray[0]) - 1900);
        date.setMonth(Integer.valueOf(dateArray[1]) - 1);
        date.setDate(Integer.valueOf(dateArray[2]));
        return date;
    }

    /**
     * Метод для сумирования полей обьектов-трафиков по полям, которые задаються
     * лямбда-выражением
     */
    public static double getChosenSum(List<InternetTrafficObject> traffics, ToDoubleFunction<InternetTrafficObject> func){
        return traffics.stream()
                .mapToDouble(func)
                .sum();
    }

}
