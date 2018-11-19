package json_converter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import json_converter.homework05.*;
import json_converter.homework05.Character;

import javax.swing.*;
import java.io.IOException;

public class MainWindowController {

    @FXML Button outputButton;

    @FXML Button copyButton;

    @FXML TextArea textArea;

    String result;


    public void OnClickOutput() throws IllegalAccessException {
        Modification[] m1 = {new Modification("mod1"),new Modification("mod2")};
        Modification[] m2 = {new Modification("mod10"),new Modification("mod20")};
        Jewel jewel = new Jewel("кольцо", "защита");
        Jewel jewel2 = new Jewel("амулет", "регенерация");
        Weapon[] weapons = {new Weapon("нож", 30, m1), new Weapon("кинжал", 50, m1), new Weapon("лук", 30, m2)};
        Weapon[] weapons2 = {new Weapon("меч", 200,null), new Weapon("перчатка", 150, null)};
        String[] psevdonims1 = {"принцесса","могучая"};
        String[] psevdonims2 = {"Кровавый бог","смертоносный"};
        Character[] characters = {new Character("Элейн","ельф",1000, true, "Элрис", weapons, jewel, psevdonims1)};

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
