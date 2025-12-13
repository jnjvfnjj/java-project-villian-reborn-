package gui;

import skills.MagicSkill;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MagicPanel extends VBox {
    private MagicSkill magic;

    public MagicPanel(MagicSkill magic) {
        this.magic = magic;
        setPadding(new Insets(10));
        setSpacing(10);
        setStyle("-fx-background-color: #1a1a2e;");

        createUI();
    }

    private void createUI() {
        Label title = new Label("🔮 МАГИЧЕСКИЕ НАВЫКИ");
        title.setFont(Font.font("Arial", 18));
        title.setTextFill(Color.PLUM);

        Label powerLabel = new Label("⚡ Сила магии: " + magic.getMagicPower());
        powerLabel.setTextFill(Color.LIGHTCYAN);

        Label controlLabel = new Label("🎯 Контроль магии: " + magic.getMagicControl());
        controlLabel.setTextFill(Color.LIGHTBLUE);

        getChildren().addAll(title, powerLabel, controlLabel);
    }

    public void update(MagicSkill newMagic) {
        this.magic = newMagic;
        getChildren().clear();
        createUI();
    }
}