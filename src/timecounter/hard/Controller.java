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
import timecounter.Methods;
import timecounter.TimeCounter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Controller {

    // Ссылка на главное приложение.
    private MainApp mainApp;


    String [][] resultMatrix;

    @FXML
    TextField sizeField;

    @FXML
    TextField typeField;

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
    private String type = "ArrayList";

    @FXML
    private int firstColumnLength;


    public void fillGridTable(int size) {
        List<Object> arrayList = new ArrayList<>();
        TimeCounter tc = new TimeCounter(arrayList, size);
        String[][] resultMatrix = tc.getResultMatrix();

        ObservableList lists = FXCollections.observableArrayList(
                "LinkedList", "ArrayList", "MyLinkedList", "MyArrayList");
        comboBox = new ComboBox(lists);
    }





    @FXML
    private void handleChose() {
        size = Integer.valueOf(sizeField.getText());
        type = typeField.getText();
        List<Object> list =  new ArrayList<>();
        if(type.equals("ArrayList")){
            list = new ArrayList<>();
        }
        else if(type.equals("LinkedList")){
            list = new LinkedList<>();
        }
        else if(type.equals("MyArrayList")){
            list = new ArrayList<>();
        }
        else if(type.equals("MyLinkedList")){
            list = new ArrayList<>();
        }
        TimeCounter tc = new TimeCounter(list, size);
        String[][] resultMatrix = tc.getResultMatrix();
        Label[] labels = {label_0_0, label_1_0, label_2_0, label_3_0, label_4_0,
                label_0_1, label_1_1, label_2_1, label_3_1, label_4_1,
                label_0_2, label_1_2, label_2_2, label_3_2, label_4_2,
                label_0_3, label_1_3, label_2_3, label_3_3, label_4_3
        };
        fillAndResizeColumn(resultMatrix, labels);
    }

    public static void fillAndResizeColumn(String[][] resultMatrix, Label[] labels){
        int counter = 0;
        for (int i = 0; i < resultMatrix[0].length; i++) {
            Label[] labelsInColumn = new Label[resultMatrix.length];
            for (int j = 0; j < resultMatrix.length; j++, counter++) {
                labelsInColumn[j] = labels[counter];
                System.out.println(counter);
            }
            fillColumnOfTable(i, resultMatrix, labelsInColumn);
            int size = getLongestLengthOfStrings(labelsInColumn);
            setWidthOfLabelsInColumn(size * 15, labelsInColumn);
        }
    }


    public static void fillColumnOfTable(int num, String[][] resultMatrix, Label[] labels){
        for (int i = 0; i < labels.length; i++) {
            labels[i].setText(resultMatrix[i][num]);
        }
    }


    /**
     * Метод для нахождения длины самой длинной строки из масива строк
     */
    public static int getLongestLengthOfStrings(Label[] labels){
        int max = 0;
        for (int i = 0; i < labels.length; i++) {
            if(labels[i].getText().length() > max){
                max = labels[i].getText().length() + 5;
            }
        }
        return max;
    }


    /**
     * Метод для задания заданого размера всем элементам Label в строке
     */
    public static void setWidthOfLabelsInColumn(int size, Label[] labels){
        for (int i = 0; i < labels.length; i++) {
            labels[i].setPrefWidth(size);
        }
    }



    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        fillGridTable(size);
    }

}
