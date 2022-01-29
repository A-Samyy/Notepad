package gov.iti.jets;

import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class MyMenuBar {
    MenuBar menuBar = new MenuBar();

    MyMenuBar(Stage stage, javafx.scene.control.TextArea textArea) {

        Menus file = new FileMenu(stage, textArea);
        Menus edit = new EditFile(stage, textArea);
        Menus format = new FormatMenu(textArea);
        Menus help = new HelpMenu();

        menuBar.getMenus().addAll(file.createMenu(), edit.createMenu(), format.createMenu(), help.createMenu());

    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
