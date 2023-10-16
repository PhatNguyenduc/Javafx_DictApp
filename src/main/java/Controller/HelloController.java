package Controller;

import Dictionary_commandline.DictionaryCommandline;
import Dictionary_commandline.DictionaryManagement;
import Dictionary_commandline.Word;
import com.sun.speech.freetts.jsapi.FreeTTSEngineCentral;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloController {
    public static String getWord = new String();

    @FXML
    private ListView<String> listView = new ListView<String>();

    @FXML
    private TextField searchtext = new TextField();
    @FXML
    private Label wordLabel = new Label();
    @FXML
    private Label flag = new Label();
    {
        flag.setVisible(false);
    }

    @FXML
    private Button sound = new Button();

    @FXML
    private Button game = new Button();
    @FXML
    private Button Add = new Button();

    @FXML
    private Button API = new Button();
    @FXML
    private TextArea meaningArea = new TextArea();
    Voice voice = VoiceManager.getInstance().getVoice("kevin16");
    {
        voice.setPitch(120.0f);
        voice.setRate(145);
        voice.setVolume(80);

    }





    public  String path = "src\\main\\java\\dictionaries.txt";
    private DictionaryManagement dict = new DictionaryManagement();
    {
        dict.insertWordFromFile(path);

    }


    ObservableList<String> dataList = FXCollections.observableArrayList();
    {
        dataList.addAll(dict.getAllWords());
    }

public void initialize() {

    listView.setItems(dataList);

    // Sử dụng TextFormatter để lắng nghe sự kiện ngay khi nhập
    searchtext.setTextFormatter(new TextFormatter<String>((TextFormatter.Change change) -> {
        String newText = change.getControlNewText();
        filterData(newText);
        return change;
    }));

    listView.setOnMouseClicked(event -> {
        String selectedWord = listView.getSelectionModel().getSelectedItem();
        if(selectedWord != null) {
            wordLabel.setText(selectedWord);
            String meaning = getMeaning(selectedWord);

            meaningArea.setText(meaning);
            flag.setVisible(true);
            getWord = selectedWord;
        }
    });
    sound.setOnMouseClicked(event -> {
        if(getWord!=null) {
            sayword(getWord);
        }
    });


}
    private void filterData(String prefix) {
        ObservableList<String> filteredList = FXCollections.observableArrayList();
        for (String item : dataList) {
            if (item.toLowerCase().trim().startsWith(prefix.toLowerCase())) {
                filteredList.add(item);
            }
        }
        listView.setItems(filteredList);
    }
    private String getMeaning(String word) {
        return dict.getWordMeaning(word);
    }
public void sayword(String word) {
        if(voice != null) {
        voice.allocate();
        voice.speak(word);
        }
        else {
            voice.deallocate();
        }

}






}