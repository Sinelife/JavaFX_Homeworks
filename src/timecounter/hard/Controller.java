package timecounter.hard;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import timecounter.TimeCounter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Controller {

    // Ссылка на главное приложение.
    private MainApp mainApp;


    String [][] resultMatrix;

    @FXML
    TextField textField;

    @FXML
    Label label_0_0;
    @FXML
    Label label_0_1;
    @FXML
    Label label_0_2;
    @FXML
    Label label_0_3;
    @FXML
    Label label_1_0;
    @FXML
    Label label_1_1;
    @FXML
    Label label_1_2;
    @FXML
    Label label_1_3;
    @FXML
    Label label_2_0;
    @FXML
    Label label_2_1;
    @FXML
    Label label_2_2;
    @FXML
    Label label_2_3;
    @FXML
    Label label_3_0;
    @FXML
    Label label_3_1;
    @FXML
    Label label_3_2;
    @FXML
    Label label_3_3;
    @FXML
    Label label_4_0;
    @FXML
    Label label_4_1;
    @FXML
    Label label_4_2;
    @FXML
    Label label_4_3;


    @FXML
    ComboBox comboBox;

    private int size = 100_000;



    public void fillGridTable(int size) {
        List<Object> arrayList = new ArrayList<>();
        TimeCounter tc = new TimeCounter(arrayList, size);
        String[][] resultMatrix = tc.getResultMatrix();


        comboBox = new ComboBox<String>();
        comboBox.getItems().addAll("A","B","C","D","E");
        //comboBox.setItems(FXCollections.observableArrayList("x"));
    }



    @FXML
    private void handleChose() {
        size = Integer.valueOf(textField.getText());
        List<Object> arrayList = new LinkedList<>();
        TimeCounter tc = new TimeCounter(arrayList, size);
        String[][] resultMatrix = tc.getResultMatrix();
        label_0_0.setText(resultMatrix[0][0]);
        label_0_1.setText(resultMatrix[0][1]);
        label_0_2.setText(resultMatrix[0][2]);
        label_0_3.setText(resultMatrix[0][3]);
        label_1_0.setText(resultMatrix[1][0]);
        label_1_1.setText(resultMatrix[1][1]);
        label_1_2.setText(resultMatrix[1][2]);
        label_1_3.setText(resultMatrix[1][3]);
        label_2_0.setText(resultMatrix[2][0]);
        label_2_1.setText(resultMatrix[2][1]);
        label_2_2.setText(resultMatrix[2][2]);
        label_2_3.setText(resultMatrix[2][3]);
        label_3_0.setText(resultMatrix[3][0]);
        label_3_1.setText(resultMatrix[3][1]);
        label_3_2.setText(resultMatrix[3][2]);
        label_3_3.setText(resultMatrix[3][3]);
        label_4_0.setText(resultMatrix[4][0]);
        label_4_1.setText(resultMatrix[4][1]);
        label_4_2.setText(resultMatrix[4][2]);
        label_4_3.setText(resultMatrix[4][3]);
    }





    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        fillGridTable(size);
    }

}
