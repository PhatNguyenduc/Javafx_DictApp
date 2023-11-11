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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import trie.TrieNode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static Controller.SceneController.dict;


public class Searching_Controller {

    public  String path = "src\\main\\java\\dictionaries.txt";
    public String pathSavedWords = "src\\main\\java\\savedWord.txt";
    public static String getWord = " ";


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
    @FXML
    private Button save_word = new Button();
    private Image setImageSaveWord;
    @FXML
    private ImageView imageSaveWord = new ImageView();


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






    ObservableList<String> dataList = FXCollections.observableArrayList();
    {
        dataList.addAll(dict.getAllWords());
    }
    public static List<String> savedWords;
    {
        savedWords = new ArrayList<String>();
        insertWordFromFile(pathSavedWords);
        print();
        setIsSavedWords();
    }

    public void initialize() {
//    setImage(0);
        setImageSaveWord = new Image(getClass().getResource("iconandimage/star.png").toString());
        imageSaveWord.setImage(setImageSaveWord);
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
                setImage();
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
                alert.setHeaderText("missing a word to change the meaing");
                alert.setContentText("click in list to choose your word you want to update");
                alert.getButtonTypes().setAll(ButtonType.OK);
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

        save_word.setOnMouseClicked(event -> {
            if (dict.getDictionary().getNode(getWord).getIsSaved()) {
                deleteSavedWord();
            } else {
                insertSavedWord();
            }
            setImage();
        });


    }
    private void deleteSavedWord() {
        dict.getDictionary().getNode(getWord).setIsSaved(false);
        savedWords.remove(getWord);
        exportToFile(pathSavedWords);
    }

    private void insertSavedWord() {
        dict.getDictionary().getNode(getWord).setIsSaved(true);
        savedWords.add(getWord);
        exportToFile(pathSavedWords);
    }
    private void setImage() {
        if (dict.getDictionary().getNode(getWord).getIsSaved()) {
            setImageSaveWord = new Image(getClass().getResource("iconandimage/star1.png").toString());
        } else {
            setImageSaveWord = new Image(getClass().getResource("iconandimage/star.png").toString());
        }
        imageSaveWord.setImage(setImageSaveWord);
    }
    private void filterData(String prefix) {
        ObservableList<String> filteredList = FXCollections.observableArrayList();
        List<String> prefixList = dict.getSuggest(prefix);
        filteredList.addAll(prefixList);
        listView.setItems(filteredList);
    }

    private void listviewdefault() {
        ObservableList<String> dlist = FXCollections.observableArrayList();
        int size = 0;
        for(String item : dataList) {
            if(size > 15) {
                break;
            }
            dlist.add(item);
            size++;
        }
        listView.setItems(dlist);
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
        updatedialog.getDialogPane().setPrefSize(400,250);

    }

    public void exportToFile(String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter buf = new BufferedWriter(fileWriter);
            // write to file from current dictionary
            for (String word : savedWords) {
                buf.write(word.toLowerCase());
                buf.newLine();
            }
            buf.close();
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }
    public void insertWordFromFile(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader buf = new BufferedReader(fileReader);
            String line;
            while ((line = buf.readLine()) != null) {
                savedWords.add(line);
            }
            // close file
            buf.close();
        } catch (IOException e) {
            System.out.println("An error occur with file: " + e);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }
    private void setIsSavedWords() {
        for (String word : savedWords) {
            if (dict.getDictionary().haveWord(word)) {
                dict.getDictionary().getNode(word).setIsSaved(true);
            }
        }
    }

    private void print() {
        for (String word : savedWords) {
            System.out.println(word);
        }
    }
}



