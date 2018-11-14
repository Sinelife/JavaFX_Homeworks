package stream_homework;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InternetTraficWorker {


    public static List<InternetTraficObject> getTrafficListFromFile() throws IOException {
        List<InternetTraficObject> traficObjects = new LinkedList<>();
        InternetTraficObject i1 = new InternetTraficObject(new Date(),1,1,1,1,1,1);
        InternetTraficObject i2 = new InternetTraficObject(new Date(),10,10,10,10,10,10);
        InternetTraficObject i3 = new InternetTraficObject(new Date(),10,3,3,3,3,3);
        traficObjects.add(i1);traficObjects.add(i2);traficObjects.add(i3);
//        System.out.println(getSumAllInputTrafic(traficObjects));


//        List<Object> trafficList =
//                Files.lines(Paths.get("C:\\Users\\Ярослав\\IdeaProjects\\JavaFX_Homeworks\\src\\stream_homework\\traffic.txt"))
//                        .map(line -> Arrays.stream(line.split(" ")))
//                        .peek(line -> traficObjects.add(createObject(line)))
//                        .collect(Collectors.toList());

//
////        for (Object o : trafficList) {
////            traficObjects.add((InternetTraficObject) o);
////        }
//        for (InternetTraficObject object : traficObjects) {
//            //System.out.println(object);
//        }
        return traficObjects;
    }


    private static InternetTraficObject createObject(Stream<String> line){

        InternetTraficObject ito = new InternetTraficObject();
        List<String> list = line.collect(Collectors.toList());
        for (String s : list) {
            //System.out.println(s + "sd");
        }
//        line.filter(dateOf -> dateOf.matches("^\\S*"))
//        .forEach(dateOf -> ito.setDate(convertToDate(dateOf)));

        return ito;
    }



    /**
     * Метод для перевода строки в обьект даты
     */
    private static Date convertToDate(String str){
        Date date = new Date();
        date.setDate(Integer.valueOf(str.substring(8,10)));
        date.setMonth(Integer.valueOf(str.substring(5,7)) - 1);
        date.setYear(Integer.valueOf(str.substring(0,4)) - 1900);

        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
        return date;
    }


    /**
     * Метод для подсчета сумарного входного внутрисетевого трафика
     */
    public static double getSumNetworkInputTraffic(List<InternetTraficObject> traffics){
        return traffics.stream()
                .mapToDouble(traffic -> traffic.getNetworkInput())
                .sum();
    }


    /**
     * Метод для подсчета сумарного виходного внутресетевого трафика
     */
    public static double getSumNetworkOutputTraffic(List<InternetTraficObject> traffics){
        return traffics.stream()
                .mapToDouble(traffic -> traffic.getNetworkOutput())
                .sum();
    }


    /**
     * Метод для подсчета сумарного входного ua-ix трафика
     */
    public static double getSumUaixInputTraffic(List<InternetTraficObject> traffics){
        return traffics.stream()
                .mapToDouble(traffic -> traffic.getUaixInput())
                .sum();
    }


    /**
     * Метод для подсчета сумарного выходного ua-ix трафика
     */
    public static double getSumUaixOutputTraffic(List<InternetTraficObject> traffics){
        return traffics.stream()
                .mapToDouble(traffic -> traffic.getUaixOutput())
                .sum();
    }


    /**
     * Метод для подсчета сумарного входного интернет трафика
     */
    public static double getSumInternetInputTraffic(List<InternetTraficObject> traffics){
        return traffics.stream()
                .mapToDouble(traffic -> traffic.getInternetInput())
                .sum();
    }


    /**
     * Метод для подсчета сумарного выходного интернет трафика
     */
    public static double getSumInternetOutputTraffic(List<InternetTraficObject> traffics){
        return traffics.stream()
                .mapToDouble(traffic -> traffic.getInternetOutput())
                .sum();
    }


    /**
     * Метод для подсчета сумарного входного трафика
     */
    public static double getSumAllInputTraffic(List<InternetTraficObject> traffics){
        return traffics.stream()
                .mapToDouble(traffic -> traffic.getInternetInput()
                                    + traffic.getUaixInput()
                                    + traffic.getNetworkInput())
                .sum();
    }


    /**
     * Метод для подсчета сумарного выходного трафика
     */
    public static double getSumAllOutputTraffic(List<InternetTraficObject> traffics){
        return traffics.stream()
                .mapToDouble(traffic -> traffic.getInternetOutput()
                        + traffic.getUaixOutput()
                        + traffic.getNetworkOutput())
                .sum();
    }
}
