package Controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static Controller.SceneController.dict;
import static javafx.collections.FXCollections.observableArrayList;

public class Searching_Controller {
    public  String path = "src\\main\\java\\dictionaries.txt";
    public String pathSavedWords = "src\\main\\java\\savedWord.txt";
    public String pathHistory = "src\\main\\java\\history.txt";
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
    @FXML
    private TextArea meaningArea = new TextArea();
    {
        meaningArea.setEditable(false);
    }
    @FXML
    private ImageView sound_image = new ImageView();
    private List<String> historyWords;
    {
        historyWords = new ArrayList<String>();
        insertWordFromFile(pathHistory, historyWords);
    }
    Voice voice = VoiceManager.getInstance().getVoice("kevin16");
    {
        voice.setPitch(120.0f);
        voice.setRate(145);
        voice.setVolume(80);
    }
    ObservableList<String> dataList = observableArrayList();
    {
        dataList.addAll(dict.getAllWords());
    }
    ObservableList<String> historyList = observableArrayList();
    {
        historyList.addAll(historyWords);
    }
    public static List<String> savedWords;
    {
        savedWords = new ArrayList<String>();
        insertWordFromFile(pathSavedWords, savedWords);
        setIsSavedWords();
    }

    public void initialize() {
        setImageSaveWord = new Image(getClass().getResource("iconandimage/star.png").toString());
        imageSaveWord.setImage(setImageSaveWord);
        listView.setItems(historyList);
        searchtext.setOnKeyTyped(keyEvent -> {
            if (searchtext.getText().isEmpty()) {
                ObservableList<String> List = observableArrayList();
                List.addAll(historyWords);
                listView.setItems(List);
            } else {
                String newText = searchtext.getText().trim();
                filterData(newText);
            }
        });

        listView.setOnMouseClicked(event -> {
            String selectedWord = listView.getSelectionModel().getSelectedItem();
            if(selectedWord != null) {
                wordLabel.setText(selectedWord);
                String meaning = getMeaning(selectedWord);
                meaningArea.setText(meaning);
                flag.setVisible(true);
                getWord = selectedWord;
                setImage();
                if (!historyWords.contains(selectedWord)) {
                    historyWords.add(0,selectedWord);
                    exportToFile(pathHistory, historyWords);
                }
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
        exportToFile(pathSavedWords, savedWords);
    }

    private void insertSavedWord() {
        dict.getDictionary().getNode(getWord).setIsSaved(true);
        savedWords.add(0,getWord);
        exportToFile(pathSavedWords, savedWords);
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
        ObservableList<String> filteredList = observableArrayList();
        List<String> prefixList = dict.getSuggest(prefix);
        filteredList.addAll(prefixList);
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
        updatedialog.getDialogPane().setPrefSize(400,250);

    }
    public void exportToFile(String path, List<String> words) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter buf = new BufferedWriter(fileWriter);
            // write to file from current dictionary
            for (String word : words) {
                buf.write(word.toLowerCase().trim());
                buf.newLine();
            }
            buf.close();
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }
    public void insertWordFromFile(String path, List<String> words) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader buf = new BufferedReader(fileReader);
            String line;
            while ((line = buf.readLine()) != null) {
                words.add(line.trim());
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
}



