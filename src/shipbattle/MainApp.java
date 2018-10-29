package shipbattle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Основной клас для запуска програмы
 */
public class MainApp extends Application {

    public static Stage shipPlacingStage;
    public static BorderPane rootLayout;

    /**
     * Константа отвечающая за количество кораблей
     */
    public static final int SHIP_NUM = 4;


    /**
     * Инициализирует корневой макет.
     */
    public void initRootLayout() {
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(shipbattle.MainApp.class.getResource("Root.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            shipPlacingStage.setScene(scene);
            shipPlacingStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Инициализирует окно для розстановки кораблей игроком.
     */
    public void showStartWindow() {
        try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ShipsPlacing.fxml"));
            AnchorPane startField = (AnchorPane) loader.load();

            // Помещаем сведения об адресатах в центр корневого макета.
            rootLayout.setCenter(startField);

            // Даём контроллеру доступ к главному приложению.
            ShipsPlacingController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Инициализирует поле для боя.
     */
    public void showBattleField() {
        try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("BattleField.fxml"));
            AnchorPane startField = (AnchorPane) loader.load();

            // Помещаем сведения об адресатах в центр корневого макета.
            rootLayout.setCenter(startField);

            // Даём контроллеру доступ к главному приложению.
            BattleFieldController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        this.shipPlacingStage = primaryStage;
        this.shipPlacingStage.setTitle("ShipBattleApp");
        initRootLayout();
        showStartWindow();
    }


    public static void main(String[] args) {
        launch(args);
    }

}

