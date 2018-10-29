package shipbattle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import timecounter.easy.Main;

public class ShipsPlacingController {

    @FXML Button button_0_0; @FXML Button button_0_1; @FXML Button button_0_2; @FXML Button button_0_3; @FXML Button button_0_4;
    @FXML Button button_1_0; @FXML Button button_1_1; @FXML Button button_1_2; @FXML Button button_1_3; @FXML Button button_1_4;
    @FXML Button button_2_0; @FXML Button button_2_1; @FXML Button button_2_2; @FXML Button button_2_3; @FXML Button button_2_4;
    @FXML Button button_3_0; @FXML Button button_3_1; @FXML Button button_3_2; @FXML Button button_3_3; @FXML Button button_3_4;
    @FXML Button button_4_0; @FXML Button button_4_1; @FXML Button button_4_2; @FXML Button button_4_3; @FXML Button button_4_4;


    private int shipNum = 4;

    // Ссылка на главное приложение.
    private MainApp mainApp;

    private BorderPane rootLayout = MainApp.rootLayout;


    final EventHandler<ActionEvent> myHandler = new EventHandler<ActionEvent>(){

        @Override
        public void handle(final ActionEvent event) {
            Button[] buttons = {button_0_0, button_0_1, button_0_2, button_0_3, button_0_4,
                            button_1_0, button_1_1, button_1_2, button_1_3, button_1_4,
                            button_2_0, button_2_1, button_2_2, button_2_3, button_2_4,
                            button_3_0, button_3_1, button_3_2, button_3_3, button_3_4,
                            button_4_0, button_4_1, button_4_2, button_4_3, button_4_4};

            for (int i = 0; i < buttons.length; i++) {
                if (event.getSource() == buttons[i] && shipNum > 0) {
                    disableButton(buttons[i]);
                    shipNum--;
                }
            }
            if(shipNum == 0){
                rootLayout.setCenter(null);
            }
        }
    };


    public void showFirstTable(){
        Button[] buttons = {button_0_0, button_0_1, button_0_2, button_0_3, button_0_4,
                button_1_0, button_1_1, button_1_2, button_1_3, button_1_4,
                button_2_0, button_2_1, button_2_2, button_2_3, button_2_4,
                button_3_0, button_3_1, button_3_2, button_3_3, button_3_4,
                button_4_0, button_4_1, button_4_2, button_4_3, button_4_4};
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setOnAction(myHandler);
        }
    }


    public void disableButton(Button button){
        button.setStyle("-fx-background-color: #00FF33; color: #00FF33");
        button.setDisable(true);
    }



    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        showFirstTable();
    }

    public int getShipNum(){
        return shipNum;
    }
}
