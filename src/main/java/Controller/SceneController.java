package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SceneController {
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
    private Button flashCard = new Button();

    public  AnchorPane getAnchorPane() {return mainAnchorPane;}
    public void initialize() {
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
        flashCard.setOnMouseClicked(event -> {
            showComponent("FlashCard.fxml");
        });
        gameButton.setOnMouseClicked(event -> {
            showComponent("Game.fxml");
        });
    }

    private void setNode( Node node ) {
        mainAnchorPane.getChildren().clear();
        mainAnchorPane.getChildren().add((Node) node);
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

}