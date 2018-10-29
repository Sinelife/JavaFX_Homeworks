package shipbattle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Random;


/**
 * Контролер для управления окном боя
 */
public class BattleFieldController {

    /**
     * Кнопки отвечающик за ячейки поля игрока
     */
    @FXML Button button_0_0; @FXML Button button_0_1; @FXML Button button_0_2; @FXML Button button_0_3; @FXML Button button_0_4;
    @FXML Button button_1_0; @FXML Button button_1_1; @FXML Button button_1_2; @FXML Button button_1_3; @FXML Button button_1_4;
    @FXML Button button_2_0; @FXML Button button_2_1; @FXML Button button_2_2; @FXML Button button_2_3; @FXML Button button_2_4;
    @FXML Button button_3_0; @FXML Button button_3_1; @FXML Button button_3_2; @FXML Button button_3_3; @FXML Button button_3_4;
    @FXML Button button_4_0; @FXML Button button_4_1; @FXML Button button_4_2; @FXML Button button_4_3; @FXML Button button_4_4;


    /**
     * Кнопки отвечающик за ячейки поля врага
     */
    @FXML Button enemyButton_0_0; @FXML Button enemyButton_0_1; @FXML Button enemyButton_0_2; @FXML Button enemyButton_0_3; @FXML Button enemyButton_0_4;
    @FXML Button enemyButton_1_0; @FXML Button enemyButton_1_1; @FXML Button enemyButton_1_2; @FXML Button enemyButton_1_3; @FXML Button enemyButton_1_4;
    @FXML Button enemyButton_2_0; @FXML Button enemyButton_2_1; @FXML Button enemyButton_2_2; @FXML Button enemyButton_2_3; @FXML Button enemyButton_2_4;
    @FXML Button enemyButton_3_0; @FXML Button enemyButton_3_1; @FXML Button enemyButton_3_2; @FXML Button enemyButton_3_3; @FXML Button enemyButton_3_4;
    @FXML Button enemyButton_4_0; @FXML Button enemyButton_4_1; @FXML Button enemyButton_4_2; @FXML Button enemyButton_4_3; @FXML Button enemyButton_4_4;


    /**
     * Текстовое поле для вывода туда сообщения в случае попадения выстрела
     * игрока рядом с кораблем компьютера
     */
    @FXML
    Label labelOfNearShot;

    // Ссылка на главное приложение.
    private MainApp mainApp;

    /**
     * Это поле хранящее в себе начальную сцену из основного класа. Это необходимо
     * для возможности закрыть сцену прямо в классе-контроллере.
     */
    Stage stage = MainApp.shipPlacingStage;


    final EventHandler<ActionEvent> myHandler = new EventHandler<ActionEvent>(){

        /**
         * Метод реагирующий на нажатие кнопки игроком.
         * Если выбраная ячейка содержит корабль врага, то ячейка оерашиваеться в
         * красный цвет, если нет, то в белый. Если выстрел игрока попал рядом с
         * кораблем, то под полем врага выводиться сообщение об этом.
         * После выстрела игрока идет проверка не победил ли игрок. И есл победил
         * то вылетает соответсвующее окно.
         *
         * Далее происходит автоматический выстрел компьютера и в случае попадения
         * по кораблю игрока ячейка с зеленого цвета окрашиваеться в красный. Если
         * ячейка была пуста, то она окрашиваеться в белый.
         * После выстрела компьютера идет проверка не победил ли компьютер. И если
         * победил то вылетает соответсвующее окно.
         */
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
                    playerShot(enemyButtons[i]);
                }
            }
            alertPlayerWin();
            enemyShot(myButtons);
            alertComputerWin();
        }
    };


    /**
     * Метод показывающий игровое поле для битвы.
     * На поле размещаються поле врага и поле игрока.
     * На поле игрока зеленым цветом отмечены те ячейки, на которых находяться
     * корабли. На поле врага все ячейки одного цвета.
     */
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
        placeShipsByComputer();
        for (int i = 0; i < enemyButtons.length; i++) {
            enemyButtons[i].setOnAction(myHandler);
        }
    }



    /**
     * Метод для разстановки компьютером кораблей на поле(координаты
     * кораблей генерируються случайным образом)
     */
    public void placeShipsByComputer(){
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


    /**
     * Метод возвращающий строку координат выстрела компьюетера(
     * координаты генерируються случайным образом)
     */
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

    /**
     * Метод для произведения выстрела компьютером
     */
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


    /**
     * Метод для произведения выстрела игроком
     */
    public void playerShot(Button button){
        labelOfNearShot.setText("");
        if(Values.listOfEnemyShipsCoordinates.contains(button.getText())) {
            button.setStyle("-fx-background-color: red");
            Values.listOfEnemyShipsCoordinates.remove(button.getText());
            Values.compShipNum--;
        }
        else if(shotIsNearShip(button)){
            button.setStyle("-fx-background-color: white");
            labelOfNearShot.setText("Вражеский корабль рядом");
        }
        else{
            button.setStyle("-fx-background-color: white");
        }
        button.setDisable(true);
    }


    /**
     * Метод для проверки попал ли выстрел игрока рядом с кораблем врага
     */
    public boolean shotIsNearShip(Button button){
        for (String coordinates : Values.listOfEnemyShipsCoordinates) {
            int shipX = getCoordinateXFromString(coordinates);
            int shipY = getCoordinateYFromString(coordinates);
            int buttonX = getCoordinateXFromString(button.getText());
            int buttonY = getCoordinateYFromString(button.getText());
            if((buttonX == shipX - 1 && buttonY == shipY) ||
                    (buttonX == shipX - 1 && buttonY == shipY - 1) ||
                    (buttonX == shipX - 1 && buttonY == shipY + 1) ||
                    (buttonX == shipX + 1 && buttonY == shipY) ||
                    (buttonX == shipX + 1 && buttonY == shipY - 1) ||
                    (buttonX == shipX + 1 && buttonY == shipY + 1) ||
                    (buttonX == shipX && buttonY == shipY - 1) ||
                    (buttonX == shipX && buttonY == shipY + 1)){
                return true;
            }
        }
        return false;
    }


    /**
     * Метод для получения из строки координат координату у
     */
    public int getCoordinateXFromString(String coordinates){
        char[] chars = coordinates.toCharArray();
        String coordX = "";
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == ','){
                break;
            }
            coordX += chars[i];
        }
        int x = Integer.valueOf(coordX);
        return x;
    }

    /**
     * Метод для получения из строки координат координату х
     */
    public int getCoordinateYFromString(String coordinates){
        char[] chars = coordinates.toCharArray();
        String coordY = "";
        for (int i = chars.length - 1; i > 0; i--) {
            if(chars[i] == ','){
                break;
            }
            coordY += chars[i];
        }
        int y = Integer.valueOf(coordY);
        return y;
    }


    /**
     * Метод для вывода окна сигнализирующего о победе компьютера
     */
    public void alertComputerWin(){
        if(Values.playerShipNum == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Игрок проиграл!!!!");
            alert.setHeaderText("Вы проиграли!!");
            alert.setContentText("Ваш флот уничтожен. Вы проиграли!");
            alert.showAndWait();
            stage.close();
        }
    }


    /**
     * Метод для вывода окна сигнализирующего о победе игрока
     */
    public void alertPlayerWin(){
        if(Values.compShipNum == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Игрок победил!!!!");
            alert.setHeaderText("Вы победили!!");
            alert.setContentText("Флот врага уничтожен. Вы победили!");
            alert.showAndWait();
            stage.close();
        }
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        showBattleField();
    }
}
