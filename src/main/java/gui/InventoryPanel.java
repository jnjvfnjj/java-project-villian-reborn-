package gui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.List;

public class InventoryPanel extends VBox {

    private List<Item> inventory;

    public InventoryPanel(List<Item> inventory) {
        this.inventory = inventory;
        setPadding(new Insets(10));
        setSpacing(10);
        setStyle("-fx-background-color: #34495e;");

        Label title = new Label("🎒 Ваш инвентарь");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
        getChildren().add(title);

        if (inventory.isEmpty()) {
            Label empty = new Label("Инвентарь пуст");
            empty.setStyle("-fx-text-fill: white;");
            getChildren().add(empty);
        } else {
            for (Item item : inventory) {
                HBox itemBox = new HBox(10);
                Label itemName = new Label(item.getName());
                itemName.setStyle("-fx-text-fill: white;");
                Label itemQty = new Label("x" + item.getQuantity());
                itemQty.setStyle("-fx-text-fill: white;");
                itemBox.getChildren().addAll(itemName, itemQty);
                getChildren().add(itemBox);
            }
        }

        Button closeBtn = new Button("Закрыть");
        closeBtn.setOnAction(e -> this.getScene().getWindow().hide());
        getChildren().add(closeBtn);
    }
}
