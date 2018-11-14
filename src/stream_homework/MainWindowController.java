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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class MainWindowController {

    @FXML Button countAllButton;

    @FXML private TableView<InternetTraficObject> trafficTable;


    @FXML Label summaryNetInputLabel;

    @FXML Label summaryNetOutputLabel;

    @FXML Label summaryUiaxInputLabel;

    @FXML Label summaryUiaxOutputLabel;

    @FXML Label summaryInternetInputLabel;

    @FXML Label summaryInternetOutputLabel;

    @FXML Label summaryInputLabel;

    @FXML Label summaryOutputLabel;



    @FXML private TableColumn<InternetTraficObject,String> dateColumn;

    @FXML private TableColumn<InternetTraficObject, String> netInputColumn;

    @FXML private TableColumn<InternetTraficObject,String> netOutputColumn;

    @FXML private TableColumn<InternetTraficObject,String> uaixInputColumn;

    @FXML private TableColumn<InternetTraficObject,String> uiaxOutputColumn;

    @FXML private TableColumn<InternetTraficObject,String> internetInputColumn;

    @FXML private TableColumn<InternetTraficObject,String> internetOutputColumn;


    private ObservableList<InternetTraficObject> trafficsData = FXCollections.observableArrayList();


    public void initialize() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        netInputColumn.setCellValueFactory(new PropertyValueFactory<>("networkInput"));
        netOutputColumn.setCellValueFactory(new PropertyValueFactory<>("networkOutput"));
        uaixInputColumn.setCellValueFactory(new PropertyValueFactory<>("uaixInput"));
        uiaxOutputColumn.setCellValueFactory(new PropertyValueFactory<>("uaixOutput"));
        internetInputColumn.setCellValueFactory(new PropertyValueFactory<>("internetInput"));
        internetOutputColumn.setCellValueFactory(new PropertyValueFactory<>("internetOutput"));
    }





    @FXML
    public void countAll() throws IOException {
        List<InternetTraficObject> trafficList = InternetTraficWorker.getTrafficListFromFile();
        trafficList.stream()
                .forEach(a -> trafficsData.add(new InternetTraficObject(a.getDate(),a.getNetworkInput(),a.getNetworkOutput(),a.getUaixInput(),a.getUaixOutput(),a.getInternetInput(),a.getInternetOutput())));

        trafficTable.setItems(trafficsData);

        summaryNetInputLabel.setText(addResultToLabel(summaryNetInputLabel,String.valueOf(InternetTraficWorker.getSumNetworkInputTraffic(trafficList))));
        summaryNetOutputLabel.setText(addResultToLabel(summaryNetOutputLabel,String.valueOf(InternetTraficWorker.getSumNetworkOutputTraffic(trafficList))));

        summaryUiaxInputLabel.setText(addResultToLabel(summaryUiaxInputLabel,String.valueOf(InternetTraficWorker.getSumUaixInputTraffic(trafficList))));
        summaryUiaxOutputLabel.setText(addResultToLabel(summaryUiaxOutputLabel,String.valueOf(InternetTraficWorker.getSumUaixOutputTraffic(trafficList))));

        summaryInternetInputLabel.setText(addResultToLabel(summaryInternetInputLabel,String.valueOf(InternetTraficWorker.getSumInternetInputTraffic(trafficList))));
        summaryInternetOutputLabel.setText(addResultToLabel(summaryInternetOutputLabel,String.valueOf(InternetTraficWorker.getSumInternetOutputTraffic(trafficList))));

        summaryInputLabel.setText(addResultToLabel(summaryInputLabel,String.valueOf(InternetTraficWorker.getSumAllInputTraffic(trafficList))));
        summaryOutputLabel.setText(addResultToLabel(summaryOutputLabel,String.valueOf(InternetTraficWorker.getSumAllOutputTraffic(trafficList))));

    }


    public String addResultToLabel(Label label, String extraText){
        String res = label.getText() + " - " + extraText;
        return res;
    }


}
