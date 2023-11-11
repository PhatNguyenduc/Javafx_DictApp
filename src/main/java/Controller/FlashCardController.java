package Controller;

import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FlashCardController {
    @FXML
    private StackPane flashcardPane = new StackPane();
    @FXML
    private Label cardLabel = new Label();
    private boolean isFront = true;
    private String selected = "";
    @FXML
    private ListView<String> listView = new ListView<String>();
    ObservableList<String> dataList = FXCollections.observableArrayList();
    {
        dataList.add("1");
        dataList.add("2");
        dataList.add("3");
    }
    public void initialize() {
        cardLabel.setStyle("-fx-font-size: 20px;");
        listView.setItems(dataList);
        flashcardPane.setStyle("-fx-background-color: lightgreen;");
        flashcardPane.setOnMouseClicked(event -> {
            rotatePane();
        });
        listView.setOnMouseClicked(event -> {
            String selectedWord = listView.getSelectionModel().getSelectedItem();
            if(selectedWord!= null) {
                cardLabel.setText(selectedWord);
                selected = selectedWord;
                isFront = true;
            }
        });
    }
    public void rotatePane() {
        cardLabel.setVisible(false);
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), flashcardPane);
        rotateTransition.setAxis(Rotate.Y_AXIS);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(180);
        rotateTransition.play();
        RotateTransition rotate = new RotateTransition(Duration.seconds(1), cardLabel);
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.setFromAngle(0);
        rotate.setToAngle(180);
        rotate.play();
        rotate.setOnFinished(e -> {

            if (isFront) {
                cardLabel.setText("Nghĩa");
            } else {
                cardLabel.setText(selected);
            }
            cardLabel.setVisible(true);
            isFront = !isFront;
        });
    }
}
