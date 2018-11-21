package json_deserializer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainWindowController {

    @FXML Button outputButton;

    @FXML Button chooseButton;

    @FXML
    TextArea textArea;

    private File choosenFile = new File("src/json_deserializer/My.json");;


    public void onClickChooseButton() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.showOpenDialog(null);
        choosenFile = fileChooser.getSelectedFile();
    }


    public void getFileDeserializedData() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Character c = new Character();
        List<Character> characterList = JsonDeserializer.getDeserializedObjectList(c, choosenFile);
        String result = "";
        for (Character character : characterList) {
            result += character + "\n\n";
        }
        textArea.setText(result);
    }
}
