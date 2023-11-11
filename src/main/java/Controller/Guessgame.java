package Controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import static Controller.SceneController.*;
import static Controller.Searching_Controller.*;
import static javafx.collections.FXCollections.observableArrayList;

public class Guessgame {


    @FXML
    private Label titleLabel;
    @FXML
    private TextField enterTF = new TextField();

    @FXML
    private Button guess = new Button();

    @FXML
    private AnchorPane guessPanel = new AnchorPane();

    ObservableList<String> wordList = observableArrayList();
    {
        wordList.addAll(dict.getAllWords());
    }







    private String shuffleWord(String word) {
        char[] characters = word.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = (int) (Math.random() * characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }
}
