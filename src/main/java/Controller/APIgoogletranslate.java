package Controller;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class APIgoogletranslate {

    @FXML
    private AnchorPane api = new AnchorPane();
    @FXML
    private TextArea engtext = new TextArea();
    @FXML
    private TextArea vitext = new TextArea();
    @FXML
    private Button trans = new Button();

//    @FXML
//    private ImageView  Vn_flag_1 = new ImageView();
//    @FXML
//    private ImageView  Eng_flag_1 = new ImageView();
//    @FXML
//    private ImageView  Vn_flag_2 = new ImageView();
//    @FXML
//    private ImageView  Eng_flag_2 = new ImageView();

    @FXML
    AnchorPane Eng_Flag = new AnchorPane();
    @FXML
    AnchorPane Vn_flag = new AnchorPane();


    @FXML
    private Button switch_flag_and_trans = new Button();
    //uk 93/147
    //vn 322/147




    boolean check_switch = false;

    private void Eng_to_Vi() throws IOException, URISyntaxException {
            String s = engtext.getText();
            String res = translateE_V(s);
            vitext.setText(res);

    }

    private void Vi_to_Eng() throws IOException, URISyntaxException {
        String s = engtext.getText();
        String res = translateV_E(s);
        vitext.setText(res);

    }
    @FXML
    private void initialize() {
        if(check_switch == false) {
        switch_flag_and_trans.setOnMouseClicked(event ->{
            Eng_Flag.setLayoutX(322);
            Eng_Flag.setLayoutY(147);
            Vn_flag.setLayoutX(93);
            Vn_flag.setLayoutY(147);

            check_switch = true;

        });

            trans.setOnMouseClicked(event -> {
                try {
                    Eng_to_Vi();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });

//            engtext.setTextFormatter(new TextFormatter<String>((TextFormatter.Change change) -> {
//                if(!engtext.getText().isEmpty()) {
//                    try {
//                    Eng_to_Vi();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                } catch (URISyntaxException e) {
//                    throw new RuntimeException(e);
//                }
//                }
//                return change;
//            }));
        }
        if(check_switch == true) {
            switch_flag_and_trans.setOnMouseClicked(event ->{
                Eng_Flag.setLayoutX(93);
                Eng_Flag.setLayoutY(147);
                Vn_flag.setLayoutX(322);
                Vn_flag.setLayoutY(147);


                check_switch = false;

            });



            trans.setOnMouseClicked(event -> {
                try {
                    Vi_to_Eng();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });

//            engtext.focusedProperty().addListener((obs, oldValue, newValue) -> {
//                if(newValue) {
//                    try {
//                        Eng_to_Vi();
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    } catch (URISyntaxException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            });

        }

    }

    public static String translateE_V(String text) throws IOException, URISyntaxException {
        return APItranslate("en", "vi", text);
    }

    public static String translateV_E(String text) throws IOException, URISyntaxException {
        return APItranslate("vi", "en", text);
    }

    private static String APItranslate(String langFrom, String langTo, String text) throws IOException, URISyntaxException {
        // INSERT YOU URL HERE
        String APIurl = "https://script.google.com/macros/s/AKfycbw1qSfs1Hvfnoi3FzGuoDWijwQW69eGcMM_iGDF7p5vu1oN_CaFqIDFmCGzBuuGCk_N/exec" +
                "?q=" + URLEncoder.encode(text, StandardCharsets.UTF_8) +
                "&target=" + langTo +
                "&source=" + langFrom;

        URL url = new URI(APIurl).toURL();
        StringBuilder respond_status_ = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader bf = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String input;
        while ((input = bf.readLine()) != null) {
            respond_status_.append(input);
        }
        bf.close();
        return respond_status_.toString();
    }


}