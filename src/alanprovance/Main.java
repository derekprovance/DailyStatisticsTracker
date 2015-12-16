package alanprovance;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @FXML private javafx.scene.control.Label mainText;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Controller controller = new Controller();

        primaryStage.setTitle("Daily Progress Tracker");
        primaryStage.setScene(new Scene(controller.getRoot(), 500, 200));
        primaryStage.setMaxHeight(151);
        primaryStage.setMaxWidth(476);
        primaryStage.setMinHeight(151);
        primaryStage.setMinWidth(476);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
