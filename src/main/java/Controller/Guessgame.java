package Controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
            String ans = getall.get(i).toLowerCase().trim();
            System.out.println(check);
            System.out.println(ans);
            if(ans.equals(check)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("CORRECT");
                alert.setHeaderText(" ");
                alert.setGraphic(new ImageView(getClass().getResource("iconandimage/kitty.png").toString()));
                alert.setContentText("try the next level");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
                if(i < qList.size() - 1) {
                    i++;
                    levelLabel.setText(Integer.toString(i));
                    score+=10;
                    scoreLabel.setText(Integer.toString(score));
                    contentLabel.setText(qList.get(i));
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INCORRECT");
                alert.setHeaderText("Try again");
                alert.setContentText("the right answer is : " + ans);
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
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
                String[] lines = line.split(" ");

                res.add(lines[1]);

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
    {
        qList.addAll(get_question("src/main/java/all.txt"));
    }
    ObservableList<String> getall = observableArrayList();
    {
        getall.addAll(get_ans("src/main/java/all.txt"));
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
