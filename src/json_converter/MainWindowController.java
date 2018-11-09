package json_converter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import json_converter.homework05.Character;
import json_converter.homework05.Jewel;
import json_converter.homework05.JsonConverter;
import json_converter.homework05.Weapon;

import javax.swing.*;
import java.io.IOException;

public class MainWindowController {

    @FXML Button outputButton;

    @FXML Button copyButton;

    @FXML TextArea textArea;

    String result;


    public void OnClickOutput() throws IllegalAccessException {
        Jewel jewel = new Jewel("кольцо", "защита");
        Jewel jewel2 = new Jewel("амулет", "регенерация");
        Weapon[] weapons = {new Weapon("нож", 30), new Weapon("кинжал", 50), new Weapon("лук", 30)};
        Weapon[] weapons2 = {new Weapon("меч", 200), new Weapon("перчатка", 150)};
        Character[] characters = {new Character("Элейн","ельф",1000, true, "Элрис", weapons, jewel),
                                new Character("Кахат","демон",9999, false, "Молох", weapons2, jewel2)};

        result = JsonConverter.toJson(characters);
        textArea.setText(result);
    }


    public void OnClickCopy() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.showOpenDialog(null);
        String pathOfWhereCopyFile = fileChooser.getSelectedFile().toString();
        JsonConverter.copyToFile(result, pathOfWhereCopyFile + "\\My.json");

    }
}
