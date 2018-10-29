package shipbattle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApp extends Application {


    private Stage primaryStage;
    public static BorderPane rootLayout;
    public int shipNum;


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
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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
            System.out.println(controller.getShipNum());
            if(controller.getShipNum() == 0){
                rootLayout.setCenter(null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ShipBattleApp");
        initRootLayout();
        showStartWindow();

    }


    public static void main(String[] args) {
        launch(args);
    }

}

