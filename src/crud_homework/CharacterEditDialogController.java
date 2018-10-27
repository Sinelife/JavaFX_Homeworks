package crud_homework;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * Окно для изменения информации об адресате.
 */
public class CharacterEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField raceField;
    @FXML
    private TextField specializationField;
    @FXML
    private TextField rankField;
    @FXML
    private TextField powerField;


    private Stage dialogStage;
    private Character character;
    private boolean okClicked = false;

    /**
     * Инициализирует класс-контроллер. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Устанавливает сцену для этого окна.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Задаёт адресата, информацию о котором будем менять.
     *
     * @param character
     */
    public void setCharacter(Character character) {
        this.character = character;

        firstNameField.setText(character.getFirstName());
        lastNameField.setText(character.getLastName());
        raceField.setText(character.getRace());
        specializationField.setText(character.getSpecialization());
        rankField.setText(character.getRank());
        powerField.setText(String.valueOf(character.getPower()));
    }

    /**
     * Returns true, если пользователь кликнул OK, в другом случае false.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке OK.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            character.setFirstName(firstNameField.getText());
            character.setLastName(lastNameField.getText());
            character.setRace(raceField.getText());
            character.setSpecialization(specializationField.getText());
            character.setRank(rankField.getText());
            character.setPower(Integer.valueOf(powerField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }


    /**
     * Вызывается, когда пользователь кликнул по кнопке Cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Проверяет пользовательский ввод в текстовых полях.
     *
     * @return true, если пользовательский ввод корректен
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (raceField.getText() == null || raceField.getText().length() == 0) {
            errorMessage += "No valid race!\n";
        }
        if (specializationField.getText() == null || specializationField.getText().length() == 0) {
            errorMessage += "No valid specialization!\n";
        }
        if (rankField.getText() == null || rankField.getText().length() == 0) {
            errorMessage += "No valid rank!\n";
        }
        if (powerField.getText() == null || powerField.getText().length() == 0) {
            errorMessage += "No valid power!\n";
        } else {
            // пытаемся power в int.
            try {
                Integer.parseInt(powerField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid power (must be an integer)!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
