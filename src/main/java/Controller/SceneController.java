package Controller;

import Dictionary_commandline.DictionaryManagement;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class SceneController {
    @FXML
    AnchorPane utilitiesAnchorPane = new AnchorPane();
    @FXML
    private Button AddButton = new Button();
    @FXML
    private Button SearchButton = new Button();
    @FXML
    private Button gameButton = new Button();
    @FXML
    private Button APIButton = new Button();

    @FXML
    private AnchorPane mainAnchorPane = new AnchorPane();
    @FXML
    private Button guessButton = new Button();
    @FXML
    private Button ultilityButton = new Button();

    @FXML
    private Button flashCard = new Button();
    @FXML
    private Button reviewVocabButton = new Button();
    public static DictionaryManagement dict;
    {
        dict = new DictionaryManagement();
        dict.insertWordFromFile("src\\main\\java\\dictionaries.txt");
    }

    public void initialize() {
        System.gc();
        AddButton.setOnMouseClicked(event ->{
            showComponent("AddWord.fxml");
        });
        SearchButton.setOnMouseClicked(event ->{
            showComponent("Searching.fxml");
        });
        APIButton.setOnMouseClicked(event ->{
            showComponent("APIggt.fxml");
        });
        showComponent("Searching.fxml");
        ultilityButton.setOnAction(event ->{
            showComponent("GameController.fxml");
        });
        flashCard.setOnMouseClicked(event -> {
            showComponent("FlashCard.fxml");
        });
        gameButton.setOnMouseClicked(event -> {
            showGame("Game.fxml");
        });
        guessButton.setOnMouseClicked(event -> {
             showGame("Guessgame.fxml");
        });
        reviewVocabButton.setOnMouseClicked(event ->{
           showGame("ReviewVocab.fxml");
        });
        gameButton.setOnMouseEntered(event ->{
            scale(gameButton);
        });
        guessButton.setOnMouseEntered(event ->{
            scale(guessButton);
        });
        reviewVocabButton.setOnMouseEntered(event ->{
            scale(reviewVocabButton);
        });


    }


    private void scale(Button button) {
        ScaleTransition st = new ScaleTransition(Duration.millis(1000), button);
        //st.setDuration(Duration.INDEFINITE);
        st.setFromX(1.5);
        st.setFromY(1.5);
        st.setToX(1.0);
        st.setToY(1.0);
        st.play();
    }

    private void setNode( Node node ) {
        mainAnchorPane.getChildren().clear();
        mainAnchorPane.getChildren().add((Node) node);
    }

    private void setNode_( Node node ) {
        utilitiesAnchorPane.getChildren().clear();
        utilitiesAnchorPane.getChildren().add((Node) node);
    }

    @FXML
    public void showComponent(String path) {
        try {
            AnchorPane Component = FXMLLoader.load(getClass().getResource(path));
            setNode(Component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showGame(String path) {
        try {
            AnchorPane Component = FXMLLoader.load(getClass().getResource(path));
            setNode_(Component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}