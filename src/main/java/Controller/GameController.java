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
import java.util.Random;

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
        int img = randomImage();
        hint.clear();
        trueQuestion = -1;
        setupImage(img);
    }
    private int randomImage () {
        return 1;
    }
    private int randomQuestion () {
        Random random = new Random();
        // Tạo số ngẫu nhiên từ 0 đến 99
        int randomNumber = random.nextInt(100);
        return randomNumber % questionList.size();
    }
    private void setupImage(int img) {
        if (img == 1) {
            image1 = new Image(getClass().getResource("iconandimage/icons8-game-64.png").toString());
            image2 = new Image(getClass().getResource("iconandimage/icons8-game-64.png").toString());
            image3 = new Image(getClass().getResource("iconandimage/icons8-game-64.png").toString());
            image4 = new Image(getClass().getResource("iconandimage/icons8-game-64.png").toString());
            image5 = new Image(getClass().getResource("iconandimage/icons8-game-64.png").toString());
            image6 = new Image(getClass().getResource("iconandimage/icons8-game-64.png").toString());
            image7 = new Image(getClass().getResource("iconandimage/icons8-game-64.png").toString());
            image8 = new Image(getClass().getResource("iconandimage/icons8-game-64.png").toString());
            image9 = new Image(getClass().getResource("iconandimage/icons8-game-64.png").toString());
            hint.add("1");
            hint.add("2");
            hint.add("3");
        } else if (img == 2) {
            image1 = new Image(getClass().getResource("iconandimage/icons8-google-translate-100.png").toString());
            image2 = new Image(getClass().getResource("iconandimage/icons8-google-translate-100.png").toString());
            image3 = new Image(getClass().getResource("iconandimage/icons8-google-translate-100.png").toString());
            image4 = new Image(getClass().getResource("iconandimage/icons8-google-translate-100.png").toString());
            image5 = new Image(getClass().getResource("iconandimage/icons8-google-translate-100.png").toString());
            image6 = new Image(getClass().getResource("iconandimage/icons8-google-translate-100.png").toString());
            image7 = new Image(getClass().getResource("iconandimage/icons8-google-translate-100.png").toString());
            image8 = new Image(getClass().getResource("iconandimage/icons8-google-translate-100.png").toString());
            image9 = new Image(getClass().getResource("iconandimage/icons8-google-translate-100.png").toString());
            hint.add("a");
            hint.add("b");
            hint.add("c");
        }
    }
    private void setupHint(int i) {
        trueQuestion++;
        if (trueQuestion < hint.size()) {
            labelInfoHint.setText("Bạn đã trả lời đúng! Đây là gợi ý của bạn");
            labelHint.setText(hint.get(trueQuestion));
        } else {
            labelInfoHint.setText("Bạn đã hết gợi ý!");
            labelHint.setText("");
        }
        hintPane.setVisible(true);
        buttonHint.setOnAction(event -> {
            hintPane.setVisible(false);
        });
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
                temp.add(line);
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
    private void showWrongAnswerAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.UTILITY);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle("Câu trả lời sai");
        alert.setHeaderText("Bạn đã chọn sai đáp án.");
        alert.setContentText("Hãy thử lại hoặc xem đáp án đúng.");
        ButtonType closeButton = new ButtonType("Đóng", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(closeButton);
        alert.showAndWait();
    }
}
