package Controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class APIgoogletranslate {
    @FXML
    private TextArea engtext = new TextArea();
    @FXML
    private TextArea vitext = new TextArea();
    @FXML
    private Button trans = new Button();

    public static void main(String[] args) throws IOException, URISyntaxException {
        String text = "Buồn quá";

        System.out.println("Translated text: " + translateViToEn(text));
    }
    @FXML
    private ImageView  Vn_flag_1 = new ImageView();
    @FXML
    private ImageView  Eng_flag_1 = new ImageView();
//    @FXML
//    private ImageView  Vn_flag_2 = new ImageView();
//    @FXML
//    private ImageView  Eng_flag_2 = new ImageView();

    @FXML
    private Button switch_flag_and_trans = new Button();
    //uk 140,39
    //vn 489,39




    boolean check_switch = false;

    private void Eng_to_Vi() throws IOException, URISyntaxException {
            String s = engtext.getText();
            String res = translateEnToVi(s);
            vitext.setText(res);

    }

    private void Vi_to_Eng() throws IOException, URISyntaxException {
        String s = engtext.getText();
        String res = translateViToEn(s);
        vitext.setText(res);

    }
    @FXML
    private void initialize() {
        if(check_switch == false) {
        switch_flag_and_trans.setOnMouseClicked(event ->{
            Vn_flag_1.setLayoutX(140);
            Vn_flag_1.setLayoutY(39);
            Eng_flag_1.setLayoutX(489);
            Eng_flag_1.setLayoutY(39);
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
        }
        if(check_switch == true) {
            switch_flag_and_trans.setOnMouseClicked(event ->{
                Vn_flag_1.setLayoutX(489);
                Vn_flag_1.setLayoutY(39);
                Eng_flag_1.setLayoutX(140);
                Eng_flag_1.setLayoutY(39);
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

        }

    }

    private static String translate(String langFrom, String langTo, String text) throws IOException, URISyntaxException {
        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycbw1qSfs1Hvfnoi3FzGuoDWijwQW69eGcMM_iGDF7p5vu1oN_CaFqIDFmCGzBuuGCk_N/exec" +
                "?q=" + URLEncoder.encode(text, StandardCharsets.UTF_8) +
                "&target=" + langTo +
                "&source=" + langFrom;
        URI uri = new URI(urlStr);
        URL url = uri.toURL();
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public static String translateEnToVi(String text) throws IOException, URISyntaxException {
        return translate("en", "vi", text);
    }

    public static String translateViToEn(String text) throws IOException, URISyntaxException {
        return translate("vi", "en", text);
    }
}