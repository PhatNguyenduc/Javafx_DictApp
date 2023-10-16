package Controller;

import Dictionary_commandline.DictionaryManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;



public class Searching_Controller {


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
    public static AnchorPane anchorPane = new AnchorPane();
    @FXML
    private Button delete_word = new Button();

//    @FXML
//    private Button API = new Button();
    @FXML
    private TextArea meaningArea = new TextArea();
    {
        meaningArea.setEditable(false);
    }

    Voice voice = VoiceManager.getInstance().getVoice("kevin16");
    {
        voice.setPitch(120.0f);
        voice.setRate(145);
        voice.setVolume(80);

    }

    {
        listView.setVisible(false);
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

        searchtext.setOnMouseClicked(mouseEvent -> {
            listView.setVisible(true);
        });

        searchtext.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue && anchorPane.isFocused()) {
                listView.setVisible(false);
            }
        });


        anchorPane.setOnMouseClicked(event -> {
            listView.setVisible(false);
        });

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

        Add.setOnMouseClicked(event -> {
            try {
                switch_add(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        delete_word.setOnMouseClicked(event -> {
            deleteword();
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


    public void switch_add(MouseEvent event) throws IOException {
        try  {
            Parent root = FXMLLoader.load(getClass().getResource("AddWord.fxml"));

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(null);
            stage.setScene(new Scene(root));
            stage.show();


            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteword() {
        if(getWord!= null) {
            dataList.remove(getWord);
            dict.deleteWord(getWord);
            dict.exportToFile(path);
        }
    }









}



