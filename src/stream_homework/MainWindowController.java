package stream_homework;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.io.*;
import java.util.List;


public class MainWindowController {

    @FXML Button countAllButton;

    @FXML private TableView<InternetTrafficObject> trafficTable;


    @FXML Label summaryNetInputLabel;

    @FXML Label summaryNetOutputLabel;

    @FXML Label summaryUiaxInputLabel;

    @FXML Label summaryUiaxOutputLabel;

    @FXML Label summaryInternetInputLabel;

    @FXML Label summaryInternetOutputLabel;

    @FXML Label summaryInputLabel;

    @FXML Label summaryOutputLabel;



    @FXML private TableColumn<InternetTrafficObject, String> dateColumn;

    @FXML private TableColumn<InternetTrafficObject, String> netInputColumn;

    @FXML private TableColumn<InternetTrafficObject,String> netOutputColumn;

    @FXML private TableColumn<InternetTrafficObject,String> uaixInputColumn;

    @FXML private TableColumn<InternetTrafficObject,String> uiaxOutputColumn;

    @FXML private TableColumn<InternetTrafficObject,String> internetInputColumn;

    @FXML private TableColumn<InternetTrafficObject,String> internetOutputColumn;


    private ObservableList<InternetTrafficObject> trafficsData = FXCollections.observableArrayList();



    /**
     * Метод нициализирует окно
     */
    public void initialize() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("formattedDate"));
        netInputColumn.setCellValueFactory(new PropertyValueFactory<>("networkInput"));
        netOutputColumn.setCellValueFactory(new PropertyValueFactory<>("networkOutput"));
        uaixInputColumn.setCellValueFactory(new PropertyValueFactory<>("uaixInput"));
        uiaxOutputColumn.setCellValueFactory(new PropertyValueFactory<>("uaixOutput"));
        internetInputColumn.setCellValueFactory(new PropertyValueFactory<>("internetInput"));
        internetOutputColumn.setCellValueFactory(new PropertyValueFactory<>("internetOutput"));
    }


    /**
     * Метод, вызываемый нажатием кнопки Подсчитать.
     * Нажатие кнопки вызывает вывод всех обьектов InternetTrafficObject в
     * таблицу и подсчет всех сумарных трафиков.
     */
    @FXML
    public void countAll() throws IOException {
        trafficTable.getItems().removeAll(trafficsData);
        List<InternetTrafficObject> trafficList = InternetTrafficWorker.getTrafficListFromFile();
        trafficList.stream()
                .forEach(a -> trafficsData.add(new InternetTrafficObject(a.getDate(), a.getNetworkInput(), a.getNetworkOutput(), a.getUaixInput(), a.getUaixOutput(), a.getInternetInput(), a.getInternetOutput())));
        trafficTable.setItems(trafficsData);

        summaryNetInputLabel.setText("Сумарный входной внутрисетевой трафик - " + InternetTrafficWorker.getChosenSum(trafficList, traffic -> traffic.getNetworkInput()));
        summaryNetOutputLabel.setText("Сумарный выходной внутрисетевой трафик - " + InternetTrafficWorker.getChosenSum(trafficList, traffic -> traffic.getNetworkOutput()));

        summaryUiaxInputLabel.setText("Сумарный входной ua-ix трафик - " + InternetTrafficWorker.getChosenSum(trafficList, traffic -> traffic.getUaixInput()));
        summaryUiaxOutputLabel.setText("Сумарный выходной ua-ix трафик - " + InternetTrafficWorker.getChosenSum(trafficList, traffic -> traffic.getUaixOutput()));

        summaryInternetInputLabel.setText("Сумарный входной интернет трафик - " + InternetTrafficWorker.getChosenSum(trafficList, traffic -> traffic.getInternetInput()));
        summaryInternetOutputLabel.setText("Сумарный выходной интернет трафик - " + InternetTrafficWorker.getChosenSum(trafficList, traffic -> traffic.getInternetOutput()));

        summaryInputLabel.setText("Сумарный входной общий трафик - " + InternetTrafficWorker.getChosenSum(trafficList, traffic -> traffic.getInternetInput() + traffic.getUaixInput() + traffic.getNetworkInput()));
        summaryOutputLabel.setText("Сумарный выходной общий трафик - " + InternetTrafficWorker.getChosenSum(trafficList, traffic -> traffic.getInternetOutput() + traffic.getUaixOutput() + traffic.getNetworkOutput()));
    }
}
