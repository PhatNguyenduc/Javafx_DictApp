package Controller;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class GameController {
    @FXML
    private ImageView imageView1 = new ImageView();
    @FXML
    private ImageView imageView2 = new ImageView();
    @FXML
    private ImageView imageView3 = new ImageView();
    @FXML
    private ImageView imageView4 = new ImageView();
    @FXML
    private ImageView imageView5 = new ImageView();
    @FXML
    private ImageView imageView6 = new ImageView();
    @FXML
    private ImageView imageView7 = new ImageView();
    @FXML
    private ImageView imageView8 = new ImageView();
    @FXML
    private ImageView imageView9 = new ImageView();
    @FXML
    private AnchorPane questionPane = new AnchorPane();
    @FXML
    private Label labelQuestion = new Label();
    @FXML
    private Label labelA = new Label();
    @FXML
    private Label labelB = new Label();
    @FXML
    private Label labelC = new Label();
    @FXML
    private Label labelD = new Label();
    @FXML
    private Pane paneA = new Pane();
    @FXML
    private Pane paneB = new Pane();
    @FXML
    private Pane paneC = new Pane();
    @FXML
    private Pane paneD = new Pane();
    @FXML
    private ImageView fake1 = new ImageView();
    @FXML
    private ImageView fake2 = new ImageView();
    @FXML
    private ImageView fake3 = new ImageView();
    @FXML
    private ImageView fake4 = new ImageView();
    @FXML
    private ImageView fake5 = new ImageView();
    @FXML
    private ImageView fake6 = new ImageView();
    @FXML
    private ImageView fake7 = new ImageView();
    @FXML
    private ImageView fake8 = new ImageView();
    @FXML
    private ImageView fake9 = new ImageView();
    @FXML
    private Pane hintPane = new Pane();
    @FXML
    private Label labelHint = new Label();
    @FXML
    private Button buttonHint = new Button();
    @FXML
    private Label labelInfoHint = new Label();
    @FXML
    private Button cfAnswer = new Button();
    @FXML
    private TextField answerTextField = new TextField();
    @FXML
    private Button playAgain = new Button();
    private int image = 0;
    private String key = "";
    private String path = "src\\main\\java\\question.txt";
    private List<List<String>> questionList = questions(path);
    private boolean checkFake1 = true;
    private boolean checkFake2 = true;
    private boolean checkFake3 = true;
    private boolean checkFake4 = true;
    private boolean checkFake5 = true;
    private boolean checkFake6 = true;
    private boolean checkFake7 = true;
    private boolean checkFake8 = true;
    private boolean checkFake9 = true;
    private Image image1;
    private Image image2;
    private Image image3;
    private Image image4;
    private Image image5;
    private Image image6;
    private Image image7;
    private Image image8;
    private Image image9;
    private List<String> hint = new ArrayList<>();
    private int trueQuestion = 0;

    private void setFakeVisibleFalse() {
        fake1.setVisible(false);
        fake2.setVisible(false);
        fake3.setVisible(false);
        fake4.setVisible(false);
        fake5.setVisible(false);
        fake6.setVisible(false);
        fake7.setVisible(false);
        fake8.setVisible(false);
        fake9.setVisible(false);
    }

    private void setVisibleCheck() {
        fake1.setVisible(checkFake1);
        fake2.setVisible(checkFake2);
        fake3.setVisible(checkFake3);
        fake4.setVisible(checkFake4);
        fake5.setVisible(checkFake5);
        fake6.setVisible(checkFake6);
        fake7.setVisible(checkFake7);
        fake8.setVisible(checkFake8);
        fake9.setVisible(checkFake9);
        cfAnswer.setVisible(true);
        answerTextField.setVisible(true);
    }

    private void setUpInitialize() {
        questionPane.setVisible(false);
        hintPane.setVisible(false);
        imageView1.setVisible(false);
        imageView2.setVisible(false);
        imageView3.setVisible(false);
        imageView4.setVisible(false);
        imageView5.setVisible(false);
        imageView6.setVisible(false);
        imageView7.setVisible(false);
        imageView8.setVisible(false);
        imageView9.setVisible(false);
        hint.clear();
        trueQuestion = 0;
        playAgain.setVisible(true);
        answerTextField.clear();
        setupImage();
    }

    private int randomQuestion () {
        Random random = new Random();
        // Tạo số ngẫu nhiên từ 0 đến 99
        int randomNumber = random.nextInt(100);
        return randomNumber % questionList.size();
    }

    private void setupImage() {
        if (image == 1) {
            image1 = new Image(getClass().getResource("iconandimage/1.1.png").toString());
            image2 = new Image(getClass().getResource("iconandimage/1.2.png").toString());
            image3 = new Image(getClass().getResource("iconandimage/1.3.png").toString());
            image4 = new Image(getClass().getResource("iconandimage/1.4.png").toString());
            image5 = new Image(getClass().getResource("iconandimage/1.5.png").toString());
            image6 = new Image(getClass().getResource("iconandimage/1.6.png").toString());
            image7 = new Image(getClass().getResource("iconandimage/1.7.png").toString());
            image8 = new Image(getClass().getResource("iconandimage/1.8.png").toString());
            image9 = new Image(getClass().getResource("iconandimage/1.9.png").toString());
            hint.add("Keyword is a noun which contains 7 characters.");
            hint.add("Keyword contains the name of an animal, which is very friendly to humans and has a very good nose.");
            hint.add("keyword contains a noun that is the product of a tree.");
            key = "dogwood";
        } else if (image == 2) {
            image1 = new Image(getClass().getResource("iconandimage/2.1.png").toString());
            image2 = new Image(getClass().getResource("iconandimage/2.2.png").toString());
            image3 = new Image(getClass().getResource("iconandimage/2.3.png").toString());
            image4 = new Image(getClass().getResource("iconandimage/2.4.png").toString());
            image5 = new Image(getClass().getResource("iconandimage/2.5.png").toString());
            image6 = new Image(getClass().getResource("iconandimage/2.6.png").toString());
            image7 = new Image(getClass().getResource("iconandimage/2.7.png").toString());
            image8 = new Image(getClass().getResource("iconandimage/2.8.png").toString());
            image9 = new Image(getClass().getResource("iconandimage/2.9.png").toString());
            hint.add("The keyword is a seven-letter noun.");
            hint.add("The keyword contains a noun that refers to something emitting heat and light.");
            hint.add("The keyword contains a verb that represents a characteristic activity of birds.");
            key = "firefly";
        } else if (image == 3) {
            image1 = new Image(getClass().getResource("iconandimage/3.1.png").toString());
            image2 = new Image(getClass().getResource("iconandimage/3.2.png").toString());
            image3 = new Image(getClass().getResource("iconandimage/3.3.png").toString());
            image4 = new Image(getClass().getResource("iconandimage/3.4.png").toString());
            image5 = new Image(getClass().getResource("iconandimage/3.5.png").toString());
            image6 = new Image(getClass().getResource("iconandimage/3.6.png").toString());
            image7 = new Image(getClass().getResource("iconandimage/3.7.png").toString());
            image8 = new Image(getClass().getResource("iconandimage/3.8.png").toString());
            image9 = new Image(getClass().getResource("iconandimage/3.9.png").toString());
            hint.add("The keyword is a ten-letter noun.");
            hint.add("The keyword is related to a part of an animal used for chewing.");
            hint.add("The keyword contains a verb that is invoked when pressing the Ctrl + V key combination.");
            key = "toothpaste";
        } else if (image == 4) {
            image1 = new Image(getClass().getResource("iconandimage/4.1.png").toString());
            image2 = new Image(getClass().getResource("iconandimage/4.2.png").toString());
            image3 = new Image(getClass().getResource("iconandimage/4.3.png").toString());
            image4 = new Image(getClass().getResource("iconandimage/4.4.png").toString());
            image5 = new Image(getClass().getResource("iconandimage/4.5.png").toString());
            image6 = new Image(getClass().getResource("iconandimage/4.6.png").toString());
            image7 = new Image(getClass().getResource("iconandimage/4.7.png").toString());
            image8 = new Image(getClass().getResource("iconandimage/4.8.png").toString());
            image9 = new Image(getClass().getResource("iconandimage/4.9.png").toString());
            hint.add("The keyword is a eight-letter noun.");
            hint.add("The keyword is a phenomenon that causes the surrounding space to become darker.");
            hint.add("The keyword is related to a type of energy used by humans in their daily lives.");
            key = "blackout";
        } else if (image == 5) {
            image1 = new Image(getClass().getResource("iconandimage/5.1.png").toString());
            image2 = new Image(getClass().getResource("iconandimage/5.2.png").toString());
            image3 = new Image(getClass().getResource("iconandimage/5.3.png").toString());
            image4 = new Image(getClass().getResource("iconandimage/5.4.png").toString());
            image5 = new Image(getClass().getResource("iconandimage/5.5.png").toString());
            image6 = new Image(getClass().getResource("iconandimage/5.6.png").toString());
            image7 = new Image(getClass().getResource("iconandimage/5.7.png").toString());
            image8 = new Image(getClass().getResource("iconandimage/5.8.png").toString());
            image9 = new Image(getClass().getResource("iconandimage/5.9.png").toString());
            hint.add("The keyword is a eight-letter noun.");
            hint.add("The keyword contains a noun that refers to a rolling part when various types of vehicles are in motion.");
            hint.add("The keyword is a noun that refers to a toy that children enjoy, spinning when exposed to the wind.");
            key = "pinwheel";
        }
    }

    private void setupHint(int i) {
        trueQuestion++;
        if (trueQuestion <= hint.size()) {
            labelInfoHint.setText("Bạn đã trả lời đúng! Đây là gợi ý của bạn");
            labelHint.setText(hint.get(trueQuestion - 1));
        } else {
            labelInfoHint.setText("Bạn đã hết gợi ý!");
            labelHint.setText("");
        }
        hintPane.setVisible(true);
        buttonHint.setOnAction(event -> {
            hintPane.setVisible(false);
        });
        playAgain.setVisible(true);
        if (i == 1) {
            checkFake1 = false;
            imageView1.setVisible(true);
            imageView1.setImage(image1);
        }
        if (i == 2) {
            checkFake2 = false;
            imageView2.setVisible(true);
            imageView2.setImage(image2);
        }
        if (i == 3) {
            checkFake3 = false;
            imageView3.setVisible(true);
            imageView3.setImage(image3);
        }
        if (i == 4) {
            checkFake4 = false;
            imageView4.setVisible(true);
            imageView4.setImage(image4);
        }
        if (i == 5) {
            checkFake5 = false;
            imageView5.setVisible(true);
            imageView5.setImage(image5);
        }
        if (i == 6) {
            checkFake6 = false;
            imageView6.setVisible(true);
            imageView6.setImage(image6);
        }
        if (i == 7) {
            checkFake7 = false;
            imageView7.setVisible(true);
            imageView7.setImage(image7);
        }
        if (i == 8) {
            checkFake8 = false;
            imageView8.setVisible(true);
            imageView8.setImage(image8);
        }
        if (i == 9) {
            checkFake9 = false;
            imageView9.setVisible(true);
            imageView9.setImage(image9);
        }
    }

    private void setActionAnswer(int j) {
        cfAnswer.setVisible(false);
        answerTextField.setVisible(false);
        playAgain.setVisible(false);
        int i = randomQuestion();
        setQuestionList(i);
        questionPane.setVisible(true);
        setFakeVisibleFalse();
        paneA.setOnMouseClicked(event1 ->{
            questionPane.setVisible(false);
            if (questionList.get(i).get(5).equals("A")) {
                setupHint(j);
            } else {
                showWrongAnswerAlert();
            }
            setVisibleCheck();
        });
        paneB.setOnMouseClicked(event1 ->{
            questionPane.setVisible(false);
            if (questionList.get(i).get(5).equals("B")) {
                setupHint(j);
            } else {
                showWrongAnswerAlert();
            }
            setVisibleCheck();
        });
        paneC.setOnMouseClicked(event1 ->{
            questionPane.setVisible(false);
            if (questionList.get(i).get(5).equals("C")) {
                setupHint(j);
            } else {
                showWrongAnswerAlert();
            }
            setVisibleCheck();
        });
        paneD.setOnMouseClicked(event1 ->{
            questionPane.setVisible(false);
            if (questionList.get(i).get(5).equals("D")) {
                setupHint(j);
            } else {
                showWrongAnswerAlert();
            }
            setVisibleCheck();
        });
    }

    public void initialize() {
        image = randomImage();
        setUpInitialize();
        fake1.setOnMouseClicked(event -> {
            setActionAnswer(1);
        });
        fake2.setOnMouseClicked(event -> {
            setActionAnswer(2);
        });
        fake3.setOnMouseClicked(event -> {
            setActionAnswer(3);
        });
        fake4.setOnMouseClicked(event -> {
            setActionAnswer(4);
        });
        fake5.setOnMouseClicked(event -> {
            setActionAnswer(5);
        });
        fake6.setOnMouseClicked(event -> {
            setActionAnswer(6);
        });
        fake7.setOnMouseClicked(event -> {
            setActionAnswer(7);
        });
        fake8.setOnMouseClicked(event -> {
            setActionAnswer(8);
        });
        fake9.setOnMouseClicked(event -> {
            setActionAnswer(9);
        });
        cfAnswer.setOnMouseClicked(event ->{
            if (answerTextField.getText().toLowerCase().equals(key)) {
                showTrueAnswerAlert();
                checkFake1 = true;
                checkFake2 = true;
                checkFake3 = true;
                checkFake4 = true;
                checkFake5 = true;
                checkFake6 = true;
                checkFake7 = true;
                checkFake8 = true;
                checkFake9 = true;
                setVisibleCheck();
                if (image != 3) {
                    image = 6 - image;
                } else {
                    image = 4;
                }
                setUpInitialize();
            } else {
                showWrongAnswerAlert();
            }
        });
        playAgain.setOnMouseClicked(event -> {
            if (showPlayAgainAlert() == 0) {
                checkFake1 = true;
                checkFake2 = true;
                checkFake3 = true;
                checkFake4 = true;
                checkFake5 = true;
                checkFake6 = true;
                checkFake7 = true;
                checkFake8 = true;
                checkFake9 = true;
                setVisibleCheck();
                image = 3 - image;
                setUpInitialize();
            }
        });
    }

    private List<List<String>> questions (String path) {
        List<List<String>> questions = new ArrayList<List<String>>();
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader buf = new BufferedReader(fileReader);
            String line;
            int cnt = 0;
            List<String> temp = new ArrayList<String>();
            while ((line = buf.readLine()) != null) {
                temp.add(line.trim());
                cnt++;
                if (cnt == 6) {
                    cnt = 0;
                    questions.add(temp);
                    temp = new ArrayList<String>();
                }
            }
            buf.close();
        } catch (IOException e) {
            System.out.println("An error occur with file: " + e);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
        return questions;
    }

    public void setQuestionList (int i) {
        labelQuestion.setText(questionList.get(i).get(0));
        labelA.setText(questionList.get(i).get(1));
        labelB.setText(questionList.get(i).get(2));
        labelC.setText(questionList.get(i).get(3));
        labelD.setText(questionList.get(i).get(4));
    }

    private int randomImage() {
        Random random = new Random();
        // Sinh số ngẫu nhiên từ 1 đến 5 (bao gồm cả 1 và 5)
        int randomNumber = random.nextInt(5) + 1;
        return randomNumber;
    }

    private void showWrongAnswerAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("WRONG");
        alert.getDialogPane().setPrefSize(200,100);
        alert.getDialogPane().setHeader(new ImageView(getClass().getResource("iconandimage/false_alert.png").toString()));
        alert.getDialogPane().getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("guess");
        alert.setContentText("Hãy thử lại hoặc xem đáp án đúng.");
        alert.getDialogPane().getButtonTypes().forEach(buttonType -> {
            alert.getDialogPane().lookupButton(buttonType).getStyleClass().add("custom-alert-button");
        });
        alert.showAndWait();
        playAgain.setVisible(true);
    }

    private void showTrueAnswerAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CONGRATULATIONS!");
        alert.getDialogPane().setPrefSize(200,100);
        alert.getDialogPane().setHeader(new ImageView(getClass().getResource("iconandimage/True_alert.png").toString()));
        alert.getDialogPane().getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("guess");
        alert.setContentText("   Chúc mừng, bạn đã trả lời đúng!");
        ButtonType closeButton = new ButtonType("Chơi lại", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(closeButton);
        alert.getDialogPane().getButtonTypes().forEach(buttonType -> {
            alert.getDialogPane().lookupButton(buttonType).getStyleClass().add("custom-alert-button");
        });
        alert.showAndWait();
    }

    private int showPlayAgainAlert() {
        AtomicInteger i = new AtomicInteger();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.UTILITY);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle("Câu trả lời đúng");
        alert.setHeaderText("Bạn chắc chắn muốn chơi lại?");
        alert.setContentText("Chúc mừng! Hãy chơi thêm ván mới nào");
        ButtonType closeButton = new ButtonType("Chơi lại", ButtonBar.ButtonData.OK_DONE);
        ButtonType continueButton = new ButtonType("Chơi tiếp", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(closeButton, continueButton);
        alert.showAndWait().ifPresent(response -> {
            if (response == closeButton) {
                i.set(0);
            } else if (response == continueButton) {
                i.set(1);
            }
        });
        return i.get();
    }
}
