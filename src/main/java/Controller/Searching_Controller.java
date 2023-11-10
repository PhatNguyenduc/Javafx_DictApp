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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;



public class Searching_Controller {


    public static String getWord = null;

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
    private AnchorPane head = new AnchorPane();

    @FXML
    private Button sound = new Button();

    @FXML
    private Button game = new Button();
    @FXML
    private Button Add = new Button();
    @FXML
    public  AnchorPane anchorPane = new AnchorPane();
    @FXML
    private Button delete_word = new Button();
    @FXML
    private Button update_word = new Button();

//    @FXML
//    private Button API = new Button();
    @FXML
    private TextArea meaningArea = new TextArea();
    {
        meaningArea.setEditable(false);
    }

    @FXML
    private ImageView sound_image = new ImageView();


    Voice voice = VoiceManager.getInstance().getVoice("kevin16");
    {
        voice.setPitch(120.0f);
        voice.setRate(145);
        voice.setVolume(80);

    }







    public  String path = "dictionaries_copy.txt";
    public static DictionaryManagement dict = new DictionaryManagement();
    {
        dict.insertWordFromFile(path);

    }


    ObservableList<String> dataList = FXCollections.observableArrayList();
    {
        dataList.addAll(dict.getAllWords());
    }

    public void initialize() {
        System.gc();


    listView.setItems(dataList);



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

        delete_word.setOnMouseClicked(event -> {
            deleteword();
        });

        update_word.setOnMouseClicked(event -> {
            if(getWord!=null) {
                updatedialog.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("missing a word to change the meaning");
                alert.setContentText("click in list to choose your word you want to update");
                alert.getButtonTypes().setAll(ButtonType.OK);
                DialogPane dialogPane = alert.getDialogPane();
                ImageView i = new ImageView(getClass().getResource("iconandimage/icons8-error-64.png").toString());
                alert.setGraphic(i);
                dialogPane.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                dialogPane.getStyleClass().add("custom-alert");
                alert.showAndWait();

            }
        });
        updatedialog.setResultConverter(buttonType -> {
            if(buttonType == ButtonType.OK) {
                String s = updateTF.getText().trim().toLowerCase();
                dict.updateWord(getWord,s);
                dict.exportToFile(path);
                getWord = null;
            }
            return null;
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




    public void deleteword() {
        if(getWord!= null) {
            dataList.remove(getWord);
            dict.deleteWord(getWord);
            dict.exportToFile(path);
            getWord = null;
        }
    }
    @FXML
    private TextArea updateTF = new TextArea();

    ButtonType update_confirm = ButtonType.OK;
    @FXML
    Dialog updatedialog = new Dialog();
    {
        updatedialog.setTitle("Update");
        updatedialog.setHeaderText("Update meaning to your word");
        updatedialog.getDialogPane().getButtonTypes().addAll(update_confirm,ButtonType.CANCEL);
        updatedialog.getDialogPane().setContent(updateTF);
        updatedialog.getDialogPane().setGraphic(new ImageView(getClass().getResource("iconandimage/icons8-pencil-48.png").toString()));
        updatedialog.getDialogPane().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        updatedialog.getDialogPane().getStyleClass().add("updatedialog");

        updatedialog.getDialogPane().setPrefSize(400,250);


    }




}



