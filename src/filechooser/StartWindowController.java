package filechooser;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


import javax.swing.*;
import java.io.*;

public class StartWindowController {

    Main main;

    @FXML Button choseWhatCopyButton;

    @FXML Button choseWhereCopyButton;

    @FXML Button copyButton;

    @FXML Button copyInNewFileButton;

    @FXML Button clear;

    @FXML TextField whatTextField;

    @FXML TextField whereTextField;

    JFileChooser fileChooser;







    public void onClickWhatCopy() throws IOException {
        fileChooser = new JFileChooser();
        choseWhatCopyButton.setOnAction(e -> fileChooser.showOpenDialog(null));
        fileChooser.showOpenDialog(null);
        String pathOfWhatCopyFile = fileChooser.getSelectedFile().toString();
        whatTextField.setText(pathOfWhatCopyFile);
    }


    public void onClickWhereCopy() throws IOException {
        fileChooser = new JFileChooser();
        choseWhereCopyButton.setOnAction(e -> fileChooser.showOpenDialog(null));
        fileChooser.showOpenDialog(null);
        String pathOfWhereCopyFile = fileChooser.getSelectedFile().toString();
        whereTextField.setText(pathOfWhereCopyFile);
    }


    public void onClickCopy() throws FileNotFoundException {
        File fileFrom = new File(whatTextField.getText());
        File fileTo = new File(whereTextField.getText());
        InputStream inputStream = new FileInputStream(fileFrom);
        OutputStream outputStream = new FileOutputStream(fileTo);
        copy(inputStream, outputStream);
    }

    public void onClickCopyInNewFile() throws FileNotFoundException {
        File fileFrom = new File(whatTextField.getText());
        File fileTo = new File(whereTextField.getText() + "_copy");
        InputStream inputStream = new FileInputStream(fileFrom);
        OutputStream outputStream = new FileOutputStream(fileTo);
        copy(inputStream, outputStream);
    }


    public void clear(){
       main.showStartWindow();
    }


    static void copy(InputStream inputStream, OutputStream outputStream) {
        try {
            while (inputStream.available() > 0) {
                int readedByte = inputStream.read();
                outputStream.write(readedByte);
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


    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }

}
