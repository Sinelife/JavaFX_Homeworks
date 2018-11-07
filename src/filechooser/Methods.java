package filechooser;

import java.io.*;

public class Methods {

    static long currentFileLength;
    private static int percentNumber = 1;
    private static int counter = 0;

    /**
     * Общий метод для копирования любого файла или директории
     */
    public static void copy(File file, File where) throws FileNotFoundException {
        if (file.isDirectory()) {
            System.out.println(file.length());
            countDirectorySize(file);
            //System.out.println(currentFileLength);
            copyDirectory(file, where, true);
        } else {
            currentFileLength = file.length();
            //System.out.println(currentFileLength);
            copyFile(file, where);
        }
    }

    /**
     * Метод для копирования директории и всего ее содержимого
     */
    public static void copyDirectory(File directory, File where, boolean isMainDirectory) throws FileNotFoundException {
        if (isMainDirectory) {
            File newWhere = new File(where.getAbsolutePath() + "\\" + directory.getName());
            new File(newWhere.getAbsolutePath()).mkdir();
            where = newWhere;
        }
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    File newWhere = new File(where.getAbsolutePath() + "\\" + file.getName());
                    new File(newWhere.getAbsolutePath()).mkdir();
                    System.out.println(newWhere.getAbsoluteFile());
                    copyDirectory(file, newWhere, false);
                } else {
                    copyFile(file, where);
                }
            }
        }
    }


    /**
     * Метод для копирования файла
     */
    public static void copyFile(File file, File where) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(file);
        String name = "\\" + file.getName();
        System.out.println("Файл " + file.getName() + " копируеться");
        File f = new File(where + name);
        OutputStream outputStream = new FileOutputStream(f);
        copyFileByBytes(inputStream, outputStream);
        System.out.println("Файл " + file.getName() + " скопирован");
    }


    /**
     * Метод непосредственого побайтового перезаписывания данных
     */
    public static void copyFileByBytes(InputStream inputStream, OutputStream outputStream) {
        try {
            StartWindowController.fileCounter++;
            while (inputStream.available() > 0) {
                int readedByte = inputStream.read();
                outputStream.write(readedByte);
                counter++;
                //System.out.println(currentFileLength);
                if(counter  == currentFileLength * percentNumber / 100) {
                    StartWindowController.alert.setTitle(percentNumber + "%(Общий размер " + Methods.getSizeInCorrectDimension(currentFileLength) + ")");
                    //System.out.println(percentNumber + "%");
                    percentNumber++;
                }
            }
            inputStream.close();
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Метод для подсчета размера папки
     */
    public static void countDirectorySize(File directory){
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                countDirectorySize(file);
            } else {
                currentFileLength += file.length();
            }
        }
    }


    public static String getSizeInCorrectDimension(long currentFileLength){
        if(currentFileLength < 1024){
            return currentFileLength + "б";
        }
        if(currentFileLength / 1024 < 1024){
            return currentFileLength / 1024 + "Кб";
        }
        return currentFileLength /(1024 * 1024) + "Мб";
    }
}
