package images_downloader;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javax.swing.*;
import java.io.IOException;

public class MainWindowController {


    @FXML Button copyButton;

    @FXML Button choseButton;

    String pathOfWhereCopyFile;


    public void OnClickChooseButton() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.showOpenDialog(null);
        pathOfWhereCopyFile = fileChooser.getSelectedFile().toString();
    }


    public void OnClickCopyButton() throws IOException {
        MultithreadDownloader.multithreadCopy(pathOfWhereCopyFile);
    }
}
