package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import static Controller.SceneController.dict;

public class AddWord {
    public String path = "src\\main\\java\\dictionaries.txt";
    @FXML
    public AnchorPane addAnchorPane = new AnchorPane();
    @FXML
    private Label wordLabel1 = new Label();
    @FXML
    private Label meaningLabel1 = new Label();
    @FXML
    private TextArea word = new TextArea();

    @FXML
    private TextArea meaning = new TextArea();

    @FXML
    private Button confirm = new Button();
    @FXML
    private Label loglabel = new Label();

    @FXML
    private Label logstatus = new Label();

    @FXML
    private Button update = new Button();

    @FXML
    private TextArea update_meaning = new TextArea();
    @FXML
    private TextField update_word = new TextField();

    public void initialize() {
        confirm.setOnMouseClicked(mouseEvent -> {
            addword();
        });
        update.setOnMouseClicked(mouseEvent -> {
           setUpdate();
        });
    }
    public void addword() {
        String newWord = word.getText().toString().toLowerCase().trim();
        String newMeaning = meaning.getText().toString().toLowerCase().trim();

        if (dict.getDictionary().haveWord(newWord)) {
            String l = "Word exists in dictionary, use update if you want to change the meaning of the word";
            logstatus.setText(l);

        } else {

            dict.getDictionary().insertWord(newWord,newMeaning);
            dict.exportToFile(path);
            logstatus.setText("Success adding");
        }

    }
    public void setUpdate() {
        String w = update_word.getText().toString().toLowerCase().trim();
        String m = update_meaning.getText().toString().toLowerCase().trim();
        if (!dict.getDictionary().haveWord(w)) {
            String l = "Word doesnt in dictionary ,add them to";
            logstatus.setText(l);

        }
        else {
            dict.updateWord(w,m);
            logstatus.setText("update Success");
            dict.exportToFile(path);
            System.gc();
        }
    }
}