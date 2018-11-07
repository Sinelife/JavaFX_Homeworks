package filechooser;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class StartWindowController {

    Main main;

    @FXML Button choseWhatDirectoryCopyButton;

    @FXML Button choseWhereCopyButton;

    @FXML Button copyButton;

    @FXML TextField whatTextField;

    @FXML TextField whereTextField;

    public static int fileCounter;


    static Alert alert;

    /**
     * Метод срабатывающий при нажатии кнопки выбора директории, которая будет
     * копироваться. Метод вызывает FileChooser и после выбора директории
     * записывает в соответсвуюшее поле путь к этой директории
     */
    public void onClickWhatDirectoryCopy() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.showOpenDialog(null);
        String pathOfWhatCopyFile = fileChooser.getSelectedFile().toString();
        whatTextField.setText(pathOfWhatCopyFile);
    }


    /**
     * Метод срабатывающий при нажатии кнопки выбора директории, в которую будут
     * копироваться данные. Метод вызывает FileChooser и после выбора директории
     * записывает в соответсвуюшее поле путь к этой директории
     */
    public void onClickWhereCopy() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.showOpenDialog(null);
        String pathOfWhereCopyFile = fileChooser.getSelectedFile().toString();
        whereTextField.setText(pathOfWhereCopyFile);
    }



    /**
     * Метод копирующий файл или директорию в директорию(пути метод берет из
     * текстовых полей)
     */
    public void onClickCopy() throws FileNotFoundException {
        File fileFrom = new File(whatTextField.getText());
        File fileTo = new File(whereTextField.getText());
        fileCounter = 0;
        long size = 0;
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        if(fileFrom.isDirectory()){
            Methods.countDirectorySize(fileFrom);
            size = Methods.currentFileLength;
            alert.setTitle("0%(Общий размер" + Methods.getSizeInCorrectDimension(size) + ")");
            Methods.currentFileLength = 0;
        }
        else {
            size = fileFrom.length();
            alert.setTitle("0%(Общий размер" + Methods.getSizeInCorrectDimension(size) + ")");
        }
        alert.setHeaderText("");
        alert.show();
        Methods.copy(fileFrom, fileTo);
        alert.setTitle("Копирование закончено(Общий размер" + Methods.getSizeInCorrectDimension(size) + ")");
        System.out.println("Количество скопированых файлов - " + fileCounter + "\n\n\n\n\n");
    }



    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }

}
