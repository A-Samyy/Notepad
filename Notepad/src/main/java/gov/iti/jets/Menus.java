package gov.iti.jets;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public interface Menus {
    abstract Menu createMenu();  
    Clipboard clipboard = Clipboard.getSystemClipboard();
    ClipboardContent content = new ClipboardContent();
    ButtonType save = new ButtonType("Save");
    ButtonType dontSave = new ButtonType("Dont Save");
    ButtonType cancel = new ButtonType("cancel");
    Dialog dialog = new Dialog();
    
}
