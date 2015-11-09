package sample;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import sample.Database.DbConnector;
import sample.Database.DbOperations;

import java.util.logging.Logger;

public class Controller {
    @FXML private javafx.scene.control.Button submitButton;
    @FXML private javafx.scene.control.ToggleButton successDay;
    @FXML private javafx.scene.control.Slider dayRating;

    private Logger lgr = Logger.getLogger(DbConnector.class.getName());

    @FXML
    public void submitForm() {
        closeWindow();
        DbOperations dbOps = new DbOperations();
        dbOps.saveToDatabase(successDay.isSelected(), dayRating.getValue());
    }

    public void closeWindow() {
        Stage submit = (Stage) submitButton.getScene().getWindow();
        submit.close();
    }

}
