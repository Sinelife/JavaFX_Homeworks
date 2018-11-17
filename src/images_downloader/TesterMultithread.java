package images_downloader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TesterMultithread {
    public static void multithreadCopy(String pathWhere) throws IOException {
        Document doc = Jsoup.connect("http://flangex.herokuapp.com/io/load").get();
        List<Element> images = doc.getElementsByTag("a");
        List<Element> images1 = new ArrayList<>();
        images1.add(images.get(0));
        images1.add(images.get(1));
        images1.add(images.get(2));
        List<Element> images2 = new ArrayList<>();
        images2.add(images.get(3));
        images2.add(images.get(4));

        Thread thread1 = new Thread(() -> copyAllImagesInThread(images1, "Первый поток ", pathWhere));
        Thread thread2 = new Thread(() -> copyAllImagesInThread(images2, "Второй поток ", pathWhere));

        thread2.start();
        thread1.start();
    }



    /**
     * Метод для скачивания всех картинок из списка картинок
     */
    private static void copyAllImagesInThread(List<Element> images, String threadName, String pathWhere){
        for (Element image : images) {
            String imagePath = "http://flangex.herokuapp.com" + image.attr("href");
            System.out.println(threadName + imagePath);
            try {
                copyImage(imagePath, pathWhere);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод для скачивания картинки по url в папку проекта
     */
    private static void copyImage(String imageUrl, String pathWhere) throws IOException {
        int imageStartNameIndex = imageUrl.lastIndexOf("/");
        String imageName = imageUrl.substring(imageStartNameIndex);

        URL url = new URL(imageUrl);
        InputStream in = url.openStream();
        OutputStream out;
        if(pathWhere == null) {
            out = new FileOutputStream("src/images_downloader/images" + imageName);
        }
        else{
            File f = new File(pathWhere + "\\images");
            new File(f.getAbsolutePath()).mkdir();
            out = new FileOutputStream(f.getPath() + imageName);
        }
        int b;
        while ((b = in.read()) != -1) {
            out.write(b);
        }
        out.close();
        in.close();
    }
}
