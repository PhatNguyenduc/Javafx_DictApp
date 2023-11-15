package Controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class APIgoogletranslate {
    @FXML
    private TextArea src_text = new TextArea();
    @FXML
    private TextArea trans_text = new TextArea();
    @FXML
    AnchorPane Eng_Flag = new AnchorPane();
    @FXML
    AnchorPane Vn_flag = new AnchorPane();
    @FXML
    private Button switch_flag_and_trans = new Button();
    boolean check_switch = false;
    private String src = "en";
    private String tran = "vi";

    @FXML
    private void initialize() {
        setUpInitialize();
        switch_flag_and_trans.setOnMouseClicked(event -> {
            if (check_switch) {
                Eng_Flag.setLayoutX(93);
                Eng_Flag.setLayoutY(147);
                Vn_flag.setLayoutX(322);
                Vn_flag.setLayoutY(147);
                check_switch = false;
            } else {
                Eng_Flag.setLayoutX(322);
                Eng_Flag.setLayoutY(147);
                Vn_flag.setLayoutX(93);
                Vn_flag.setLayoutY(147);
                check_switch = true;
            }
            setUpInitialize();
        });
    }

    private void setUpInitialize() {
        if (check_switch) {
            src = "vi";
            tran = "en";
        } else {
          src = "en";
          tran = "vi";
        }
        src_text.setOnKeyTyped(keyEvent -> {
            String text = src_text.getText().trim();
            Task<String> task = new Task<String>() {
                @Override
                protected String call() throws Exception {
                    String res = APItranslate(src, tran, text);
                    res = outputTextFormatted(res);
                    return res;
                }
            };
            task.setOnSucceeded(e -> {
                Platform.runLater(() -> {
                    String result = task.getValue();
                    Platform.runLater(() -> trans_text.setText(result));
                });
            });
            new Thread(task).start();
        });
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

    public String outputTextFormatted(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c) || c == ',' || c == ' ' ) {
                result.append(c);
            }
        }
        String[] ans = result.toString().split(",");
        return ans[0];
    }
}


