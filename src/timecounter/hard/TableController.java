package timecounter.hard;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import timecounter.TimeCounter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Клас контроллер для таблицы подсчета времени
 */
public class TableController {

    // Ссылка на главное приложение.
    private MainApp mainApp;

    /**
     * Все Label это ячейки таюлицы, в которые после будут выводиться значения
     */
    @FXML Label label_0_0; @FXML Label label_0_1; @FXML Label label_0_2; @FXML Label label_0_3;
    @FXML Label label_1_0; @FXML Label label_1_1; @FXML Label label_1_2; @FXML Label label_1_3;
    @FXML Label label_2_0; @FXML Label label_2_1; @FXML Label label_2_2; @FXML Label label_2_3;
    @FXML Label label_3_0; @FXML Label label_3_1; @FXML Label label_3_2; @FXML Label label_3_3;
    @FXML Label label_4_0; @FXML Label label_4_1; @FXML Label label_4_2; @FXML Label label_4_3;


    /**
     * Поле для ввода размера масива
     */
    @FXML
    TextField sizeField;

    /**
     * Выпадающий список для выобра типа списка
     */
    @FXML
    ComboBox typeComboBox;

    /**
     * Выпадающий список для выбора едениц измерения времени
     */
    @FXML
    ComboBox dimensionComboBox;


    /**
     * Метод создающий базовое окно(с пустой таблицой).
     * Оба выпадающих списка заполняються нужными значениями и
     * текстовое поле заполняеться стартовым значением.
     */
    public void fillStartWindow() {
        typeComboBox.getItems().addAll(Constants.listTypeArray);
        dimensionComboBox.getItems().addAll(Constants.dimensionUnitsArray);
    }


    /**
     * Метод срабатывающий при нажатии кнопки "Вывести таблицу".
     * СОздаеться и выводиться таблица по заданым в полях и выбраным в
     * выпадающих списках значениям.
     */
    @FXML
    private void handleChose() {
        Label[] labels = {label_0_0, label_1_0, label_2_0, label_3_0, label_4_0,
                label_0_1, label_1_1, label_2_1, label_3_1, label_4_1,
                label_0_2, label_1_2, label_2_2, label_3_2, label_4_2,
                label_0_3, label_1_3, label_2_3, label_3_3, label_4_3
        };
        int size = Integer.valueOf(sizeField.getText());
        String type = typeComboBox.getSelectionModel().getSelectedItem().toString();
        String dimensionUnits = dimensionComboBox.getSelectionModel().getSelectedItem().toString();
        List<Object> list =  new ArrayList<>();
        if(type.equals("ArrayList")){
            list = new ArrayList<>();
        }
        else if(type.equals("LinkedList")){
            list = new LinkedList<>();
        }
        else if(type.equals("MyArrayList")){
            list = new MyArrayList<>();
        }
        else if(type.equals("MyLinkedList")){
            list = new MyLinkedList<>();
        }

        TimeCounter tc = new TimeCounter(list, size);
        String[][] resultMatrix = null;
        if(dimensionUnits.equals("наносекунды")){
            resultMatrix = tc.getResultMatrix("ns");
        }
        else if(dimensionUnits.equals("микросекунды")){
            resultMatrix = tc.getResultMatrix("mcs");
        }
        else if(dimensionUnits.equals("миллисекунды")){
            resultMatrix = tc.getResultMatrix("ms");
        }
        fillAndResizeColumn(resultMatrix, labels);
    }



    /**
     * Метод в котором происходит вычисление необходимого размера таблицы и
     * ее заполнение
     */
    public static void fillAndResizeColumn(String[][] resultMatrix, Label[] labels){
        int counter = 0;
        for (int i = 0; i < resultMatrix[0].length; i++) {
            Label[] labelsInColumn = new Label[resultMatrix.length];
            for (int j = 0; j < resultMatrix.length; j++, counter++) {
                labelsInColumn[j] = labels[counter];
            }
            fillColumnOfTable(i, resultMatrix, labelsInColumn);
            int size = getLongestLengthOfStrings(labelsInColumn);
            setWidthOfLabelsInColumn(size * 15, labelsInColumn);
        }
    }


    /**
     * Метод для заполнения одной колонки из результирующего масива
     */
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
     * Метод для задания заданого размера всем элементам Label в масиве Label
     */
    public static void setWidthOfLabelsInColumn(int size, Label[] labels){
        for (int i = 0; i < labels.length; i++) {
            labels[i].setPrefWidth(size);
        }
    }



    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        fillStartWindow();
    }

}
