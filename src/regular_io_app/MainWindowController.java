package regular_io_app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javax.swing.*;
import java.io.*;
import java.util.List;

public class MainWindowController {

    @FXML Button chooseFileButton;

    @FXML Label fileNameLabel;

    @FXML Button chooseExceptionFileButton;

    @FXML Label exceptionFileNameLabel;

    @FXML Button countButton;

    @FXML TextArea textArea;

    @FXML Label allWordsNumberLabel;

    @FXML Label uniqueWordsNumberLabel;

    @FXML Button copyWordNumberListToFileButton;



    /**
     * Метод, срабатывающий при нажатии кнопки выбора файла
     */
    public void onClickChooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.showOpenDialog(null);
        String pathOfWhereCopyFile = fileChooser.getSelectedFile().toString();
        fileNameLabel.setText(pathOfWhereCopyFile);
    }


    /**
     * Метод, срабатывающий при нажатии кнопки выбора файла исключения
     */
    public void onClickChooseExceptionFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.showOpenDialog(null);
        String pathOfWhereCopyFile = fileChooser.getSelectedFile().toString();
        exceptionFileNameLabel.setText(pathOfWhereCopyFile);
    }


    /**
     * Метод, срабатывающий при нажатии кнопки подсчета.
     * После нажатия кнопки в TextArea будут выведены все уникальные слова и
     * количество их встреч в файле.
     * Также в два Label ниже будут выведены общее количество слов и количество
     * уникальных слов.
     */
    public void onClickCount() throws IOException {
        File file = new File(fileNameLabel.getText());
        File exceptionFile = new File(exceptionFileNameLabel.getText());
        String text = TextWork.getCorrectText(file, exceptionFile);
        System.out.println("\n\n\n\n" + text);
        String wordNumList = TextWork.getStringOfWordsAdNumList(text);
        textArea.setText(wordNumList);
        allWordsNumberLabel.setText("Количество слов - " + TextWork.getWordNum(text));
        uniqueWordsNumberLabel.setText("Количество уникальных слов - " + TextWork.getUniqueWordNum(text));
    }


    /**
     * Метод, срабатывающий при нажатии кнопки копирования.
     * После нажатия кнопки все содержимое TextArea будет записано в файл,
     * размещение которого выбираеться в FileChooser.
     */
    public void onClickCopy() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.showOpenDialog(null);
        String pathOfWhereCopyFile = fileChooser.getSelectedFile().toString();
        TextWork.copyToFile(textArea.getText(), pathOfWhereCopyFile + "\\WordNum.txt");
    }
}
