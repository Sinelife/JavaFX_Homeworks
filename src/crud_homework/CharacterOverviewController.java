package crud_homework;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class CharacterOverviewController {
    @FXML
    private TableView<Character> characterTable;
    @FXML
    private TableColumn<Character, String> firstNameColumn;
    @FXML
    private TableColumn<Character, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label raceLabel;
    @FXML
    private Label specializationLabel;
    @FXML
    private Label rankLabel;
    @FXML
    private Label powerLabel;

    // Ссылка на главное приложение.
    private MainApp mainApp;

    /**
     * Конструктор.
     * Конструктор вызывается раньше метода initialize().
     */
    public CharacterOverviewController() {
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        // Инициализация таблицы адресатов с двумя столбцами.
        firstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().lastNameProperty());

        // Очистка дополнительной информации об адресате.
        showCharacterDetails(null);

        // Слушаем изменения выбора, и при изменении отображаем
        // дополнительную информацию об адресате.
        characterTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCharacterDetails(newValue));
    }

    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Добавление в таблицу данных из наблюдаемого списка
        characterTable.setItems(mainApp.getCharacterData());
    }


    /**
     * Заполняет все текстовые поля, отображая подробности об адресате.
     * Если указанный адресат = null, то все текстовые поля очищаются.
     *
     * @param character — адресат типа Character или null
     */
    private void showCharacterDetails(Character character) {
        if (character != null) {
            // Заполняем метки информацией из объекта person.
            firstNameLabel.setText(character.getFirstName());
            lastNameLabel.setText(character.getLastName());
            raceLabel.setText(character.getRace());
            specializationLabel.setText(character.getSpecialization());
            rankLabel.setText(character.getRank());
            powerLabel.setText(String.valueOf(character.getPower()));
        } else {
            // Если Character = null, то убираем весь текст.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            raceLabel.setText("");
            specializationLabel.setText("");
            rankLabel.setText("");
            powerLabel.setText("");
        }
    }


    /**
     * Вызывается, когда пользователь кликает по кнопке удаления.
     */
    @FXML
    private void handleDeleteCharacter() {
        int selectedIndex = characterTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            characterTable.getItems().remove(selectedIndex);
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("Персонаж не выбран");
            alert.setContentText("Выберите персонажа из таблицы.");

            alert.showAndWait();
        }
    }


    /**
     * Вызывается, когда пользователь кликает по кнопке New...
     * Открывает диалоговое окно с дополнительной информацией нового адресата.
     */
    @FXML
    private void handleNewCharacter() {
        Character tempCharacter = new Character();
        boolean okClicked = mainApp.showCharacterEditDialog(tempCharacter);
        if (okClicked) {
            mainApp.getCharacterData().add(tempCharacter);
        }
    }

    /**
     * Вызывается, когда пользователь кликает по кнопка Edit...
     * Открывает диалоговое окно для изменения выбранного адресата.
     */
    @FXML
    private void handleEditCharacter() {
        Character selectedCharacter = characterTable.getSelectionModel().getSelectedItem();
        if (selectedCharacter != null) {
            boolean okClicked = mainApp.showCharacterEditDialog(selectedCharacter);
            if (okClicked) {
                showCharacterDetails(selectedCharacter);
            }

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("Персонаж не выбран");
            alert.setContentText("Выберите персонажа из таблицы.");

            alert.showAndWait();
        }
    }
}