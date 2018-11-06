package filechooser;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.*;

public class StartWindowController {

    Main main;

    @FXML Button choseWhatDirectoryCopyButton;

    @FXML Button choseWhereCopyButton;

    @FXML Button copyButton;

    @FXML TextField whatTextField;

    @FXML TextField whereTextField;

    public static int fileCounter;




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
        //System.out.println(fileFrom.length());
        Methods.copy(fileFrom, fileTo);
        System.out.println("Количество скопированых файлов - " + fileCounter + "\n\n\n\n\n");
    }



    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }

}
