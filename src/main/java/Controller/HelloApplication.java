package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.Voice;
import java.io.IOException;

import static Controller.HelloController.getWord;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

        Parent root =  FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root, 750, 560);
        stage.setTitle("Hello!");
        stage.setScene(scene);


            stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}