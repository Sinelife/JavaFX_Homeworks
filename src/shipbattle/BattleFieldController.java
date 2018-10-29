package shipbattle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Random;

public class BattleFieldController {

    @FXML Button button_0_0; @FXML Button button_0_1; @FXML Button button_0_2; @FXML Button button_0_3; @FXML Button button_0_4;
    @FXML Button button_1_0; @FXML Button button_1_1; @FXML Button button_1_2; @FXML Button button_1_3; @FXML Button button_1_4;
    @FXML Button button_2_0; @FXML Button button_2_1; @FXML Button button_2_2; @FXML Button button_2_3; @FXML Button button_2_4;
    @FXML Button button_3_0; @FXML Button button_3_1; @FXML Button button_3_2; @FXML Button button_3_3; @FXML Button button_3_4;
    @FXML Button button_4_0; @FXML Button button_4_1; @FXML Button button_4_2; @FXML Button button_4_3; @FXML Button button_4_4;


    @FXML Button enemyButton_0_0; @FXML Button enemyButton_0_1; @FXML Button enemyButton_0_2; @FXML Button enemyButton_0_3; @FXML Button enemyButton_0_4;
    @FXML Button enemyButton_1_0; @FXML Button enemyButton_1_1; @FXML Button enemyButton_1_2; @FXML Button enemyButton_1_3; @FXML Button enemyButton_1_4;
    @FXML Button enemyButton_2_0; @FXML Button enemyButton_2_1; @FXML Button enemyButton_2_2; @FXML Button enemyButton_2_3; @FXML Button enemyButton_2_4;
    @FXML Button enemyButton_3_0; @FXML Button enemyButton_3_1; @FXML Button enemyButton_3_2; @FXML Button enemyButton_3_3; @FXML Button enemyButton_3_4;
    @FXML Button enemyButton_4_0; @FXML Button enemyButton_4_1; @FXML Button enemyButton_4_2; @FXML Button enemyButton_4_3; @FXML Button enemyButton_4_4;


    // Ссылка на главное приложение.
    private MainApp mainApp;
    Stage stage = MainApp.shipPlacingStage;


    final EventHandler<ActionEvent> myHandler = new EventHandler<ActionEvent>(){

        @Override
        public void handle(final ActionEvent event) {
            Button[] enemyButtons = {enemyButton_0_0, enemyButton_0_1, enemyButton_0_2, enemyButton_0_3, enemyButton_0_4,
                                    enemyButton_1_0, enemyButton_1_1, enemyButton_1_2, enemyButton_1_3, enemyButton_1_4,
                                    enemyButton_2_0, enemyButton_2_1, enemyButton_2_2, enemyButton_2_3, enemyButton_2_4,
                                    enemyButton_3_0, enemyButton_3_1, enemyButton_3_2, enemyButton_3_3, enemyButton_3_4,
                                    enemyButton_4_0, enemyButton_4_1, enemyButton_4_2, enemyButton_4_3, enemyButton_4_4};
            Button[] myButtons = {button_0_0, button_0_1, button_0_2, button_0_3, button_0_4,
                                button_1_0, button_1_1, button_1_2, button_1_3, button_1_4,
                                button_2_0, button_2_1, button_2_2, button_2_3, button_2_4,
                                button_3_0, button_3_1, button_3_2, button_3_3, button_3_4,
                                button_4_0, button_4_1, button_4_2, button_4_3, button_4_4};
            for (int i = 0; i < enemyButtons.length; i++) {
                if (event.getSource() == enemyButtons[i]) {
                    shoot(enemyButtons[i]);
                }
            }
            if(Values.compShipNum == 0) {
                stage.close();
            }
            enemyShot(myButtons);


        }
    };


    public void showBattleField(){
        Button[] buttons = {button_0_0, button_0_1, button_0_2, button_0_3, button_0_4,
                        button_1_0, button_1_1, button_1_2, button_1_3, button_1_4,
                        button_2_0, button_2_1, button_2_2, button_2_3, button_2_4,
                        button_3_0, button_3_1, button_3_2, button_3_3, button_3_4,
                        button_4_0, button_4_1, button_4_2, button_4_3, button_4_4};
        Button[] enemyButtons = {enemyButton_0_0, enemyButton_0_1, enemyButton_0_2, enemyButton_0_3, enemyButton_0_4,
                            enemyButton_1_0, enemyButton_1_1, enemyButton_1_2, enemyButton_1_3, enemyButton_1_4,
                            enemyButton_2_0, enemyButton_2_1, enemyButton_2_2, enemyButton_2_3, enemyButton_2_4,
                            enemyButton_3_0, enemyButton_3_1, enemyButton_3_2, enemyButton_3_3, enemyButton_3_4,
                            enemyButton_4_0, enemyButton_4_1, enemyButton_4_2, enemyButton_4_3, enemyButton_4_4};
        for (int i = 0; i < buttons.length; i++) {
            for (String s : Values.listOfYourShipsCoordinates) {
                if (buttons[i].getText().equals(s)){
                    buttons[i].setStyle("-fx-background-color: green;");
                }
            }
        }
        randomShipCoordinates();
        for (int i = 0; i < enemyButtons.length; i++) {
            enemyButtons[i].setOnAction(myHandler);
        }
    }


    public void randomShipCoordinates(){
        for (int i = 0; i < 4; i++) {
            Random rand = new Random();
            int x = rand.nextInt(5);
            int y = rand.nextInt(5);
            String coordinates = x + "," + y;
            if(Values.listOfEnemyShipsCoordinates.contains(coordinates)){
                i--;
            }
            else {
                Values.listOfEnemyShipsCoordinates.add(coordinates);
            }
        }
    }

    public String getRandomShipShot(Button[] buttons){
        Random rand = new Random();
        while(true){
            int x = rand.nextInt(5);
            int y = rand.nextInt(5);
            String coordinates = x + "," + y;
            System.out.println(coordinates);
            for (int i = 0; i < buttons.length; i++) {
                if(buttons[i].getText().equals(coordinates)){
                    return coordinates;
                }
            }
        }
    }


    public void enemyShot(Button[] buttons){
        String coordinates = getRandomShipShot(buttons);
        for(int i = 0; i < buttons.length; i++){
            if(buttons[i].getText().equals(coordinates)){
                if(Values.listOfYourShipsCoordinates.contains(coordinates)){
                    buttons[i].setStyle("-fx-background-color: red;");
                    Values.playerShipNum--;
                }
                else{
                    buttons[i].setStyle("-fx-background-color: white;");
                }
                buttons[i].setText("(already shot)");
                System.out.println(buttons[i].getText() + "-----");
            }
        }
    }


    public void shoot(Button button){
        if(Values.listOfEnemyShipsCoordinates.contains(button.getText())) {
            button.setStyle("-fx-background-color: red");
            Values.compShipNum--;
        }
        else{
            button.setStyle("-fx-background-color: white");
        }
        button.setDisable(true);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        showBattleField();
    }
}
