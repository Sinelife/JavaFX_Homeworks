package shipbattle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;



/**
 * Контролер для управления окном размещения кораблей
 */
public class ShipsPlacingController {

    /**
     * Кнопки отвечающик за ячейки поля игрока
     */
    @FXML Button button_0_0; @FXML Button button_0_1; @FXML Button button_0_2; @FXML Button button_0_3; @FXML Button button_0_4;
    @FXML Button button_1_0; @FXML Button button_1_1; @FXML Button button_1_2; @FXML Button button_1_3; @FXML Button button_1_4;
    @FXML Button button_2_0; @FXML Button button_2_1; @FXML Button button_2_2; @FXML Button button_2_3; @FXML Button button_2_4;
    @FXML Button button_3_0; @FXML Button button_3_1; @FXML Button button_3_2; @FXML Button button_3_3; @FXML Button button_3_4;
    @FXML Button button_4_0; @FXML Button button_4_1; @FXML Button button_4_2; @FXML Button button_4_3; @FXML Button button_4_4;


    private int shipNum = MainApp.SHIP_NUM;

    // Ссылка на главное приложение.
    private MainApp mainApp;

    /**
     * Это поля необходимо для того чтоб по оконачанию этапа разстановки кораблей
     * заполнить BorderPane новым окном
     */
    private BorderPane rootLayout = MainApp.rootLayout;


    final EventHandler<ActionEvent> myHandler = new EventHandler<ActionEvent>(){

        /**
         * Метод реагирующий на нажатие кнопки игроком.
         * Если игрок нажимает на ячейку, то она окращиваеться в зеленый цвет и
         * туда ставиться корабль игрока. Когда все корабли будут розставлены,
         * то окно разстановки кораблей сменяться окном боя.
         */
        @Override
        public void handle(final ActionEvent event) {
            Button[] buttons = {button_0_0, button_0_1, button_0_2, button_0_3, button_0_4,
                            button_1_0, button_1_1, button_1_2, button_1_3, button_1_4,
                            button_2_0, button_2_1, button_2_2, button_2_3, button_2_4,
                            button_3_0, button_3_1, button_3_2, button_3_3, button_3_4,
                            button_4_0, button_4_1, button_4_2, button_4_3, button_4_4};

            for (int i = 0; i < buttons.length; i++) {
                if (event.getSource() == buttons[i] && shipNum > 0) {
                    placeShip(buttons[i]);
                    shipNum--;
                }
            }
            if(shipNum == 0){
                rootLayout.setCenter(null);
                mainApp.showBattleField();
            }
        }
    };


    /**
     * Метод показывающий поле для размещения кораблей игроком
     * На поле размещаються поле игрока.
     * Все ячейки поля серые так как еще не один корабль игрок не розставил.
     */
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


    /**
     * Метод устанавливающий корабль(красит ячейку и заносит координаты в
     * список координат кораблей игрока)
     */
    public void placeShip(Button button){
        button.setStyle("-fx-background-color: #00FF33; color: #00FF33");
        String s = button.getText();
        Values.listOfYourShipsCoordinates.add(s);
        button.setDisable(true);
    }



    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        showFirstTable();
    }
}
