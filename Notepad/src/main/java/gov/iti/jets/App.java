package gov.iti.jets;

import java.util.StringTokenizer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
    StringTokenizer check;
    static int wordCount;
    static int charCount;

    public App() {
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TextArea textArea = new TextArea();

        MyMenuBar myMenuBar = new MyMenuBar(primaryStage, textArea);
        Label textField = new Label();
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(myMenuBar.getMenuBar(), textArea, textField);

        AnchorPane.setTopAnchor(myMenuBar.getMenuBar(), 0.0);
        AnchorPane.setLeftAnchor(myMenuBar.getMenuBar(), 0.0);
        AnchorPane.setRightAnchor(myMenuBar.getMenuBar(), 0.0);
        AnchorPane.setBottomAnchor(textField, 5.0);
        AnchorPane.setLeftAnchor(textField, 5.0);
        AnchorPane.setRightAnchor(textField, 0.0);
        AnchorPane.setTopAnchor(textArea, 25.0);
        AnchorPane.setBottomAnchor(textArea, 45.0);
        AnchorPane.setRightAnchor(textArea, 0.0);
        AnchorPane.setLeftAnchor(textArea, 0.0);
    
        Scene scene = new Scene(root, 600, 400, Color.LIMEGREEN);

        Stage stage = new Stage();
        stage.getIcons().add(new Image(App.class.getResourceAsStream("../../../notepad.png")));
        stage.setTitle("Notepad");
        primaryStage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();

        textField.setText(
                "the Words count : " + getWordsCount(textArea) +
                        "\nthe Characters count : "
                        + getCharCount(textArea) +
                        "\tNumber of lines= " +
                        lineCounting(textArea));
        textArea.setOnKeyTyped(ActionEvent -> {
            textField.setText(
                    "the Words count : " + getWordsCount(textArea) +
                            "\nthe Characters count : "
                            + getCharCount(textArea) +
                            "\tNumber of lines= " +
                            lineCounting(textArea));
            FileMenu.isSaved = false;
        });
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    private int getWordsCount(TextArea textArea) {
        check = new StringTokenizer(textArea.getText(), " \n");
        int wordCount = check.countTokens();
        return wordCount;
    }

    private int getCharCount(TextArea textArea) {
        int charCount = textArea.getText().trim().replace(" ", "").replaceAll("\\d", "").replaceAll("\\n", "").length();
        return charCount;
    }

    private int lineCounting(TextArea textArea) {
         String[] lines = textArea.getText().split("\r\n|\r|\n");
        return lines.length;

    }

}
