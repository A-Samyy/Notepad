package gov.iti.jets;

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;

public class FormatMenu implements Menus{
    Menu formatMenu = new Menu("For_mate");
    @Override
    public Menu createMenu() {
        return formatMenu;
    }   
    FormatMenu(TextArea textArea){
        CheckMenuItem wordWrab = new CheckMenuItem("Word Wrap");
        formatMenu.getItems().addAll(wordWrab);

        formatMenu.setOnAction(ActionEvent -> {
            if (wordWrab.isSelected() == true)
                textArea.setWrapText(true);
            else
                textArea.setWrapText(false);
        });
    }
    
}
