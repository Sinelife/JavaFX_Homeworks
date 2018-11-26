package json_serializer_deserializer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import json_converter.homework05.JsonConverter;
import json_deserializer.Character;
import json_deserializer.JsonDeserializer;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainWindowController {

    @FXML Button outputButton;

    @FXML Button chooseButton;

    @FXML Button copyButton;

    @FXML TextArea textArea;

    String result;

    String pathOfWhereCopyFile = "src/json_serializer_deserializer";


    public void onClickOutput() throws IllegalAccessException {
        result = JsonConverter.toJson(DataContainer.characters);
        textArea.setText(result);
    }


    public void onClickChooseWhereCopy() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.showOpenDialog(null);
        pathOfWhereCopyFile = fileChooser.getSelectedFile().toString() + "\\My.json";
    }

    public void onClickCopy() throws IOException {
        if(pathOfWhereCopyFile.contains("\\My.json")){
            JsonConverter.copyToFile(result, pathOfWhereCopyFile);
        }
        else {
            JsonConverter.copyToFile(result, pathOfWhereCopyFile + "/My.json");
        }
    }








    @FXML Button outputDeserializedButton;

    @FXML Button chooseFromButton;

    @FXML TextArea textAreaDeaerilized;

    private File choosenFile = new File("src/json_serializer_deserializer/My.json");;



    public void onClickChooseFromWhere() {
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
        textAreaDeaerilized.setText(result);
    }
}
