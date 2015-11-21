package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {

    @FXML private javafx.scene.control.Label mainText;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Controller controller = new Controller();

        primaryStage.setTitle("Daily Progress Tracker");
        primaryStage.setScene(new Scene(controller.getRoot(), 500, 200));
        primaryStage.setMaxHeight(210);
        primaryStage.setMaxWidth(500);
        primaryStage.setMinHeight(210);
        primaryStage.setMinWidth(500);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
