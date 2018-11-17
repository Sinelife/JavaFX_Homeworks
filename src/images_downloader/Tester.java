package images_downloader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.URL;
public class Tester {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://flangex.herokuapp.com/io/load").get();
        Elements images = doc.getElementsByTag("a");
        for (Element image : images) {
            String imagePath = "http://flangex.herokuapp.com" + image.attr("href");
            System.out.println(imagePath);
            copyImage(imagePath);
        }
    }
    private static void copyImage(String imageUrl) throws IOException {
        int imageStartNameIndex = imageUrl.lastIndexOf("/");
        String imageName = imageUrl.substring(imageStartNameIndex);
        URL url = new URL(imageUrl);
        InputStream in = url.openStream();
        OutputStream out = new FileOutputStream("C:\\Users\\Ярослав\\IdeaProjects\\JavaFX_Homeworks\\src\\images_downloader\\images" + imageName);
        int b;
        while ((b = in.read()) != -1) {
            out.write(b);
        }
        out.close();
        in.close();
    }
}