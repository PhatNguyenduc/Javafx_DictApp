package Controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Controller.SceneController.*;
import static Controller.Searching_Controller.*;
import static javafx.collections.FXCollections.observableArrayList;

public class Guessgame {


    @FXML
    private Label contentLabel = new Label();
    @FXML
    private TextField enterTF = new TextField();

    @FXML
    private Button guess = new Button();

    @FXML
    private AnchorPane guessPanel = new AnchorPane();
    @FXML
    private Label scoreLabel = new Label();
    @FXML
    private Label levelLabel = new Label();

    private int score = 0;
    private int i = 0;

    public void initialize() {

        contentLabel.setText(qList.get(0));
        scoreLabel.setText(Integer.toString(score));
        levelLabel.setText(Integer.toString(i + 1));
        guess.setOnMouseClicked(event ->{
            String check = enterTF.getText().toLowerCase().trim();
            String ans = aList.get(i).toLowerCase().trim();
            System.out.println(check);
            System.out.println(ans);

            if(ans.equals(check) && i <= 10) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("CORRECT");

                alert.getDialogPane().setPrefSize(200,100);
                alert.getDialogPane().setHeader(new ImageView(getClass().getResource("iconandimage/True_alert.png").toString()));
                //alert.getDialogPane().setGraphic(new ImageView(getClass().getResource("iconandimage/").toString()));
                alert.getDialogPane().getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
                alert.getDialogPane().getStyleClass().add("guess");
                alert.setContentText("              You earn 10points");


                alert.getDialogPane().getButtonTypes().forEach(buttonType -> {
                    alert.getDialogPane().lookupButton(buttonType).getStyleClass().add("custom-alert-button");
                });
                alert.showAndWait();
                if(i < 10 ) {
                    i += 1;
                    levelLabel.setText(Integer.toString(i+ 1));
                    score += 10;
                    scoreLabel.setText(Integer.toString(score));
                    contentLabel.setText(qList.get(i));
                }



            } else if(!ans.equals(check) && i <= 10) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INCORRECT");

                alert.getDialogPane().setPrefSize(200,100);
                alert.getDialogPane().setHeader(new ImageView(getClass().getResource("iconandimage/false_alert.png").toString()));
                //alert.getDialogPane().setGraphic(new ImageView(getClass().getResource("iconandimage/").toString()));
                alert.getDialogPane().getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
                alert.getDialogPane().getStyleClass().add("guess");
                alert.setContentText("           The right answer is : " + ans);


                alert.getDialogPane().getButtonTypes().forEach(buttonType -> {
                    alert.getDialogPane().lookupButton(buttonType).getStyleClass().add("custom-alert-button");
                });
                alert.showAndWait();
                if(i < qList.size()) {
                    i += 1;
                    levelLabel.setText(Integer.toString(i+ 1));
                    contentLabel.setText(qList.get(i));
                }


            }
            if(i >= 10 ) {
                Alert win = new Alert(Alert.AlertType.INFORMATION);
                win.setTitle("END");

                win.getDialogPane().setPrefSize(200,100);
                win.getDialogPane().setHeader(new ImageView(getClass().getResource("iconandimage/end.jpg").toString()));
                //alert.getDialogPane().setGraphic(new ImageView(getClass().getResource("iconandimage/").toString()));
                win.getDialogPane().getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
                win.getDialogPane().getStyleClass().add("guess");
                Button buttonOK = (Button) win.getDialogPane().lookupButton(ButtonType.OK);
                buttonOK.setText("Try again");
                win.setContentText("           Your score is : " + scoreLabel.getText());


                win.getDialogPane().getButtonTypes().forEach(buttonType -> {
                    win.getDialogPane().lookupButton(buttonType).getStyleClass().add("custom-alert-button");
                });
                i = 0;
                score = 0;
                scoreLabel.setText(Integer.toString(score));
                levelLabel.setText(Integer.toString(i));
                win.showAndWait();


            }
            enterTF.clear();
        });



    }

    public List<String> get_question(String path) {
        List<String> res = new ArrayList<String>();
        try {

            FileReader fileReader = new FileReader(path);
            BufferedReader buf = new BufferedReader(fileReader);


            String line;
            while ((line = buf.readLine()) != null) {
                String[] lines = line.split(" ");
                res.add(lines[0]);

            }
            // close file
            buf.close();
        } catch (IOException e) {
            System.out.println("An error occur with file: " + e);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
        return res;
    }

    public List<String> get_ans(String path) {
        List<String> res = new ArrayList<String>();
        try {

            FileReader fileReader = new FileReader(path);
            BufferedReader buf = new BufferedReader(fileReader);

            String line;
            while ((line = buf.readLine()) != null) {
                res.add(line);

            }
            // close file
            buf.close();
        } catch (IOException e) {
            System.out.println("An error occur with file: " + e);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
        return res;
    }


    ObservableList<String> qList = observableArrayList();
    ObservableList<String> aList = observableArrayList();
    ObservableList<String> getall = observableArrayList();
    {
        getall.addAll(get_ans("src/main/java/all.txt"));
        shuffle(getall);
        int x = 0;
        while(x < getall.size()) {
            String[] lines = getall.get(x).split(" ");
            qList.add(lines[0]);
            aList.add(lines[1]);
            x++;
        }
    }





    public static void shuffle(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            int j = (int) (Math.random() * list.size());
            String temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }
}
