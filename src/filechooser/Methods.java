package filechooser;

import java.io.*;

public class Methods {

    /**
     * Общий метод для копирования любого файла или директории
     */
    public static void copy(File file, File where) throws FileNotFoundException {
        if (file.isDirectory()) {
            copyDirectory(file, where, true);
        } else {
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


    /**Метод непосредственого побайтового перезаписывания данных*/
    static void copyFileByBytes(InputStream inputStream, OutputStream outputStream) {
        try {
            StartWindowController.fileCounter++;
            int counter = 0;
            while (inputStream.available() > 0) {
                int readedByte = inputStream.read();
                outputStream.write(readedByte);
                counter++;
                if(counter % 1024 == 0) {
                    System.out.println(counter / 1024 + "КБ");
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
}
