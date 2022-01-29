package gov.iti.jets;

import javafx.scene.control.IndexRange;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class EditFile implements Menus {
    Menu editMenu =new Menu("_Edit");
    @Override
    public Menu createMenu() {
        return editMenu;
    }   
    EditFile(Stage stage ,TextArea textArea){
        MenuItem undo = new MenuItem("Undo");
        MenuItem redo = new MenuItem("Redo");
        MenuItem cut = new MenuItem("Cut");
        MenuItem copy = new MenuItem("Copy");
        MenuItem paste = new MenuItem("Paste");
        MenuItem delete = new MenuItem("Delete");
        MenuItem selectAll = new MenuItem("Select All");

        undo.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+Z"));
        redo.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+Y"));
        cut.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+X"));
        copy.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+C"));
        paste.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+V"));
        delete.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+D"));
        selectAll.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+A"));
        paste.setDisable(true);
        editMenu.getItems().addAll(undo, redo, cut, copy, paste, delete, selectAll);

        copy.setOnAction(ActionEvent -> {
            copy(textArea);
            if (paste.isDisable())
                paste.setDisable(false);
        });
        paste.setOnAction(ActionEvent -> {
            textArea.appendText(getPastedText());
        });
        cut.setOnAction(ActionEvent -> {
            cut(textArea);
            if (paste.isDisable())
                paste.setDisable(false);
        });
        selectAll.setOnAction(ActionEvent -> {
            textArea.selectAll();
        });
        delete.setOnAction(ActionEvent -> {
            deleteSelectedText(textArea);
        });
        undo.setOnAction(ActionEvent -> {
            if (textArea.isUndoable() == true)
                textArea.undo();
        });
            redo.setOnAction(ActionEvent -> {
                if (textArea.isRedoable() == true)
                    textArea.redo();
            });
    }
    
    public void copy(TextArea textArea) {
        String copied = textArea.getSelectedText();
        content.putString(copied);
        clipboard.setContent(content);
    }

    private String getPastedText() {
        String pastedText = clipboard.getString();
        if (pastedText != null) {
            return pastedText;
        } else {
            pastedText = "";
            return pastedText;
        }

    }

    private void cut(TextArea textArea) {
        copy(textArea);
        IndexRange selection = textArea.getSelection();
        textArea.replaceText(selection.getStart(), selection.getEnd(), "");
    }

    private void deleteSelectedText(TextArea textArea) {
        IndexRange selection = textArea.getSelection();
        if (textArea.getSelectedText() != null)
            textArea.replaceText(selection.getStart(), selection.getEnd(), "");
    }


}
