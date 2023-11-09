package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primarystage) throws IOException {
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

        Parent root =  FXMLLoader.load(getClass().getResource("SceneController.fxml"));

        Scene scene = new Scene(root);


        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primarystage.setTitle("Dictionary Application");
        String s = String.valueOf(getClass().getResource("iconandimage/kitty.png"));
        Image icon = new Image(s);
        primarystage.getIcons().add(icon);
        primarystage.setScene(scene);


            primarystage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}