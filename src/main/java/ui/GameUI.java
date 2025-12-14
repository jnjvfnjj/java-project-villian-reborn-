package ui;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameUI {

    public void start(Stage stage) {
        stage.setTitle("Villian Reborn");

        Button btn = new Button("Click me!");
        btn.setOnAction(e -> System.out.println("Button clicked!"));

        VBox root = new VBox(10);
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.show();

        // Пример: закрыть приложение через 10 секунд (для теста)
        Platform.runLater(() -> {
            //stage.close();
        });
    }
}
