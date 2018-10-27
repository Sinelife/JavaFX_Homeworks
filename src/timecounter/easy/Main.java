package timecounter.easy;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import timecounter.TimeCounter;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public static final int LIST_SIZE = 1000000;

    @Override
    public void start(Stage primaryStage) throws Exception{
        List<Object> arrayList = new ArrayList<>();
        TimeCounter tc = new TimeCounter(arrayList, LIST_SIZE);
        String[][] resultMatrix = tc.getResultMatrix("dw");
        GridPane pane = new GridPane();
        int width = resultMatrix[0][0].length();
        int height = 50;
        for(int i = 0; i < resultMatrix.length; i++){
            for (int j = 0; j < resultMatrix[0].length; j++) {
                TextArea textField = new TextArea(resultMatrix[i][j]);
                pane.add(textField, i, j);
                textField.setPrefHeight(height);
                textField.setPrefWidth(width * 10);
            }
        }
        Parent root = new BorderPane(pane);
        primaryStage.setTitle("Time Counter");
        primaryStage.setScene(new Scene(root, width * 10 * resultMatrix.length, height * resultMatrix[0].length));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
