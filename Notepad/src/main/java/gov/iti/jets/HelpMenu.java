package gov.iti.jets;

import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;

public class HelpMenu implements Menus {
    Menu helpMenu = new Menu("_Help");
    HelpMenu(){
        MenuItem about = new MenuItem("About");
        helpMenu.getItems().addAll(about);

        about.setOnAction(ActionEvent -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("About");
      //      alert.getGraphic(new ImageView(this.getClass().getResource(name)).toString());
            alert.setHeaderText("FX NotePad");
            alert.setContentText(
                    "Developed by Abdelrahman Samy. \nDepartment of Java.\nInstructors: Eng.Ahmed Aboulroos , Eng.Mona Mahrous");
            alert.showAndWait();
        });
    }

    @Override
    public Menu createMenu() {
        return helpMenu;
    }      
}
