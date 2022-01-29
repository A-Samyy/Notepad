package gov.iti.jets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileMenu implements Menus {
    Menu fileMenu = new Menu("_File");
    public static boolean isSaved = false;
    @Override
    public Menu createMenu() {
        return fileMenu;
    }

    FileMenu(Stage stage, TextArea textArea) {
        MenuItem newFile = new MenuItem("_New File");
        MenuItem openFile = new MenuItem("_Open File");
        MenuItem saveFile = new MenuItem("_Save");
        MenuItem exit = new MenuItem("Exi_t");

        newFile.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        openFile.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        saveFile.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        exit.setAccelerator(KeyCombination.keyCombination("Alt+Shift+F4"));

        fileMenu.getItems().addAll(newFile, openFile, saveFile, exit);

        newFile.setOnAction(ActionEvent -> {
            if (saveDialog(stage, textArea, saveFile))
                textArea.setText("");

        });
        openFile.setOnAction(ActionEvent -> {

            if (saveDialog(stage, textArea, saveFile)) {
                openingFile(stage, textArea);
                isSaved = true;
            }
        });

        saveFile.setOnAction(ActionEvent -> {
            savingFile(stage, textArea);

        });

        exit.setOnAction(ActionEvent -> {
            if (saveDialog(stage, textArea, saveFile)) {
                Platform.exit();
            }
        });
        dialog.setTitle("Save");
        dialog.setContentText("Do you want to save last changes?");
        dialog.getDialogPane().getButtonTypes().addAll(save, dontSave, cancel);
        
        textArea.setOnKeyTyped(ActionEvent -> {
            isSaved = false;
        });
    }

    private void openingFile(Stage stage, TextArea textArea) {
        FileChooser fileChooser = new FileChooser();
        File opnFile = fileChooser.showOpenDialog(stage);
        if (opnFile != null) {
            try {
                Scanner myReader = new Scanner(opnFile);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    textArea.appendText(data);
                    textArea.appendText("\n");
                }
                myReader.close();

            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
            }
        }
    }

    private void savingFile(Stage stage, TextArea textArea) {
        FileChooser fileChooser = new FileChooser();
        File saveFile = fileChooser.showSaveDialog(stage);
        if (saveFile != null) {
            try {
                FileWriter fileWriter = new FileWriter(saveFile);
                fileWriter.write(textArea.getText());
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Something went wrong!");
            }
        }
    }

    private boolean saveDialog(Stage stage, TextArea textArea, MenuItem saveFile) {
        boolean check = true;
        if (!isSaved && !textArea.getText().isEmpty()) {
            Optional<ButtonType> result = dialog.showAndWait();
            if (!result.isPresent()) {
            } else if (result.get() == save) {
                saveFile.fire();
                if (isSaved) {
                    check = true;
                } else
                    check = false;
            } else if (result.get() == dontSave) {
                check = true;
            } else if (result.get() == cancel) {
                check = false;
                dialog.close();
            } else
                check = false;
        }
        return check;
    }

}
