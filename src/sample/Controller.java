package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Database.DbConnector;
import sample.Database.DbOperations;

import java.io.IOException;
import java.util.logging.Logger;

public class Controller {
    @FXML private javafx.scene.control.Button submitButton;
    @FXML private javafx.scene.control.ToggleButton successDay;
    @FXML private javafx.scene.control.Slider dayRating;
    @FXML private javafx.scene.control.Label mainText;
    @FXML private javafx.scene.control.Label errorMessage;

    private Parent root;

    private Logger lgr = Logger.getLogger(DbConnector.class.getName());

    public Controller() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("sample.fxml")
        );
        loader.setController(this);

        root = (Parent) loader.load();
    }

    public Parent getRoot() {
        return root;
    }

    @FXML
    public void submitForm() {
        DbOperations dbOps = new DbOperations();
        if(dbOps.saveToDatabase(successDay.isSelected(), dayRating.getValue())) {
            closeWindow();
        } else {
            displayError();
            disableForm();
        }
    }

    public void disableForm() {
        submitButton.setDisable(true);
        dayRating.setDisable(true);
        successDay.setDisable(true);
    }

    public void closeWindow() {
        Stage submit = (Stage) submitButton.getScene().getWindow();
        submit.close();
    }

    public void displayError() {
        errorMessage.setVisible(true);
    }

}
