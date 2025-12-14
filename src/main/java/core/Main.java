package core;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.GameUI;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        System.out.println("START CALLED");
        new GameUI().start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
