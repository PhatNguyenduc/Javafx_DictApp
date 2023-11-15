package Controller;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static Controller.SceneController.dict;
import static Controller.Searching_Controller.savedWords;

public class ReviewVocab {
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private Label meaningLabel = new Label();
    @FXML
    private Label answerLabel = new Label();
    @FXML
    private Label curLabel = new Label();
    @FXML
    private int index = 0;
    private List<StackPane> stackPaneList = new ArrayList<>();
    private List<Pair<Double, Double>> transitionList = new ArrayList<>();
    private List<Integer> indexList = new ArrayList<>();
    private int size = 0;
    private int trueAnswer = 0;
    private String word = "";
    private String answer = "";
    private boolean check = false;

    private void setUpInitialize() {
        System.gc();
        if (trueAnswer == savedWords.size()) {
            showTrueAnswerAlert();
            trueAnswer = 0;
        }
        for (int i = 0; i < size; i++) {
            mainAnchorPane.getChildren().remove(stackPaneList.get(i));
        }
        stackPaneList.clear();
        transitionList.clear();
        indexList.clear();
        word = savedWords.get(trueAnswer);
        curLabel.setText(Integer.toString(trueAnswer + 1) + " / " + Integer.toString(savedWords.size()));
        System.out.println(word);
        String shuffle = shuffleString(word);
        size = word.length();
        index = 0;
        meaningLabel.setText(dict.getWordMeaning(word));
        answer = "";
        answerLabel.setText("");
        for (int i = 0; i < size; i++) {
            StackPane stackPane = createStackPane(shuffle.charAt(i), i);
            stackPaneList.add(stackPane);
            transitionList.add(new Pair<Double, Double>(0.0, 0.0));
            indexList.add(i);
        }
        mainAnchorPane.getChildren().addAll(stackPaneList);
    }

    public void initialize() {
        setUpInitialize();
        for (int i = 0; i < stackPaneList.size(); i++) {
            int finalI = i;
            stackPaneList.get(i).setOnMouseClicked(event -> handleStackPaneClick(event, finalI));
        }
        waitForClick();
    }

    private void handleStackPaneClick(MouseEvent event, int stackPaneIndex) {
        if (transitionList.get(stackPaneIndex).getKey() == 0 && transitionList.get(stackPaneIndex).getValue() == 0) {
            slideTo(stackPaneList.get(stackPaneIndex), stackPaneIndex);
        } else {
            for (int j = 0; j < stackPaneList.size(); j++) {
                if (indexList.get(j) >= indexList.get(stackPaneIndex)) {
                    slideBack(stackPaneList.get(j), j);
                }
            }
            index = (int) (transitionList.get(stackPaneIndex).getKey() + stackPaneList.get(stackPaneIndex).getLayoutX() - 200) / 45;
            System.out.println(index);
            answer = answer.substring(0, index);
            answerLabel.setText(answer);
        }
    }

    private void waitForClick() {
        new Thread(() -> {
            while (true) {
                if (check) {
                    System.out.println("fuck");
                    Platform.runLater(() -> initialize());
                    check = false;
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private StackPane createStackPane(Character labelText, int i) {
        StackPane stackPane = new StackPane();
        Label label = new Label(labelText.toString());
        Font customFont = Font.font("Arial", 14);
        label.setFont(customFont);
        stackPane.getChildren().add(label);
        stackPane.setPrefSize(40,40);
        stackPane.setLayoutX(200 + i * 45);
        stackPane.setLayoutY(420);
        stackPane.setStyle("-fx-border-color: black; -fx-background-color: white;");
        return stackPane;
    }

    private void slideTo (StackPane stackPane, int i) {
        TranslateTransition transition = new TranslateTransition(new Duration(0.5),stackPane);
        transition.setToX(200 + 45 * index - stackPane.getLayoutX());
        transition.setToY(250 - stackPane.getLayoutY());
        transition.play();
        transition.setOnFinished(event -> {
            transitionList.set(i, new Pair<Double, Double>(stackPane.getTranslateX(), stackPane.getTranslateY()));
            indexList.set(i, index);
            index++;
            Label label = (Label) stackPane.getChildren().get(0);
            answer += label.getText();
            answerLabel.setText(answer);
            check();
        });
    }

    private void slideBack(StackPane stackPane, int i) {
        TranslateTransition transition = new TranslateTransition(new Duration(0.5),stackPane);
        transition.setToX(0);
        transition.setToY(0);
        transition.setOnFinished(event -> {
            transitionList.set(i, new Pair<Double, Double>(0.0, 0.0));
        });
        transition.play();

    }

    private void check() {
        if (answerLabel.getText().equals(word)) {
            check =  true;
            trueAnswer++;
        } else {
            check = false;
        }
    }

    private   String shuffleString(String input) {
        char[] charArray = input.toCharArray();
        long seed = System.nanoTime();
        Random random = new Random(seed);
        for (int i = charArray.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = charArray[index];
            charArray[index] = charArray[i];
            charArray[i] = temp;
        }
        return new String(charArray);
    }

    private void showTrueAnswerAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CONGRATULATIONS!");
        alert.getDialogPane().setPrefSize(200,100);
        alert.getDialogPane().setHeader(new ImageView(getClass().getResource("iconandimage/True_alert.png").toString()));
        alert.getDialogPane().getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("guess");
        alert.setContentText("   Chúc mừng, bạn đã trả lời đúng!");
        ButtonType closeButton = new ButtonType("Ôn lại", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(closeButton);
        alert.getDialogPane().getButtonTypes().forEach(buttonType -> {
            alert.getDialogPane().lookupButton(buttonType).getStyleClass().add("custom-alert-button");
        });
        alert.showAndWait();
    }
}