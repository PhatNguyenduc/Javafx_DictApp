package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

        Parent root =  FXMLLoader.load(getClass().getResource("Searching.fxml"));

        Scene scene = new Scene(root, 750, 560);
        stage.setTitle("Dictionary Application");
        stage.setScene(scene);


            stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}