package Controller;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import static Controller.SceneController.dict;
import static Controller.Searching_Controller.savedWords;

public class FlashCardController {
    @FXML
    private StackPane flashcardPane = new StackPane();
    @FXML
    private Label cardLabel = new Label();
    private boolean isFront = true;
    private String selected = "";
    private int cur = 1;

    @FXML
    private Button leftButton = new Button();
    @FXML
    private Button rightButton = new Button();
    @FXML
    private Label curLabel = new Label();


    public void initialize() {
        cardLabel.setStyle("-fx-font-size: 20px;");
        cardLabel.setText(savedWords.get(0));
        curLabel.setText(Integer.toString(cur) + "/" + Integer.toString(savedWords.size()));
        flashcardPane.setOnMouseClicked(event -> {
            rotatePane();
        });
        rightButton.setOnMouseClicked(event -> {
            if (cur < savedWords.size()) {
                right_trans();
                cur++;
                selected = savedWords.get(cur - 1);
                cardLabel.setText(selected);
                curLabel.setText(Integer.toString(cur) + "/" + Integer.toString(savedWords.size()));
            }
        });
        leftButton.setOnMouseClicked(event -> {
            if (cur > 1) {
                left_trans();
                cur--;
                selected = savedWords.get(cur - 1);
                cardLabel.setText(selected);
                curLabel.setText(Integer.toString(cur) + "/" + Integer.toString(savedWords.size()));
            }
        });
    }
    public void rotatePane() {
        cardLabel.setVisible(false);
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.3), flashcardPane);
        rotateTransition.setAxis(Rotate.Y_AXIS);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(180);
        rotateTransition.play();
        RotateTransition rotate = new RotateTransition(Duration.seconds(0.3), cardLabel);
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.setFromAngle(0);
        rotate.setToAngle(180);
        rotate.play();
        rotate.setOnFinished(e -> {
            if (isFront) {
                selected = cardLabel.getText();
                cardLabel.setText(dict.getWordMeaning(selected));
            } else {
                cardLabel.setText(selected);
            }
            cardLabel.setVisible(true);
            isFront = !isFront;
        });
    }
    private void fadeTrans() {
        FadeTransition fade = new FadeTransition(Duration.seconds(0.5), flashcardPane);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }
    private void left_trans() {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(400), flashcardPane);
        translateTransition.setFromX(-flashcardPane.getWidth());
        translateTransition.setToX(0);
        translateTransition.setAutoReverse(true);
        fadeTrans();

        translateTransition.play();
    }
    private void right_trans() {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(400), flashcardPane);
        translateTransition.setFromX(flashcardPane.getWidth());
        translateTransition.setToX(0);
        translateTransition.setAutoReverse(true);
        fadeTrans();
        translateTransition.play();
    }
}


