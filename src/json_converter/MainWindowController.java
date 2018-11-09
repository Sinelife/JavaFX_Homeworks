package json_converter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import json_converter.homework05.Character;
import json_converter.homework05.Jewel;
import json_converter.homework05.Main;
import json_converter.homework05.Weapon;

import javax.swing.*;
import java.io.IOException;

public class MainWindowController {

    @FXML Button outputButton;

    @FXML Button copyButton;

    @FXML TextArea textArea;



    public void OnClickOutput() throws IllegalAccessException {
        Jewel jewel = new Jewel("кольцо", "защита");
        Weapon[] weapons = {new Weapon("нож", 30), new Weapon("кинжал", 50), new Weapon("лук", 30)};
        Character character = new Character("Элейн","ельф",1000, true, "Элрис", weapons, jewel);
        String result = Main.toJson(character);
        textArea.setText(result);
    }


    public void OnClickCopy() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.showOpenDialog(null);
        String pathOfWhereCopyFile = fileChooser.getSelectedFile().toString();
        String text = textArea.getText();
        Main.copyToFile(text, pathOfWhereCopyFile + "\\My.json");

    }
}
