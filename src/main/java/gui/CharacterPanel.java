package gui;

import character.Heroine;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Separator;

public class CharacterPanel extends VBox {
    private Heroine heroine;

    // Основная информация
    private Label nameLabel;
    private Label levelLabel;
    private ProgressBar xpBar;

    // Статистика
    private Label healthLabel;
    private Label manaLabel;
    private Label magicPowerLabel;
    private Label magicControlLabel;

    // Прогресс-бары
    private ProgressBar healthBar;
    private ProgressBar manaBar;

    public CharacterPanel(Heroine heroine) {
        this.heroine = heroine;

        setSpacing(10);
        setPadding(new Insets(15));
        setStyle("-fx-background-color: rgba(52, 73, 94, 0.8); -fx-background-radius: 10;");

        createCharacterInfo();
        createStatsSection();
        createMagicSection();

        update(); // Первоначальное обновление
    }

    private void createCharacterInfo() {
        nameLabel = new Label("👸 " + heroine.getName());
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        nameLabel.setTextFill(Color.WHITE);

        levelLabel = new Label("Уровень: " + heroine.getLevel());
        levelLabel.setTextFill(Color.LIGHTGRAY);

        xpBar = new ProgressBar(0.0);
        xpBar.setPrefWidth(200);
        xpBar.setStyle("-fx-accent: #f39c12;");

        getChildren().addAll(nameLabel, levelLabel, xpBar, new Separator());
    }

    private void createStatsSection() {
        Label statsTitle = new Label("📊 ХАРАКТЕРИСТИКИ");
        statsTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        statsTitle.setTextFill(Color.WHITE);

        // Здоровье
        VBox healthBox = new VBox(3);
        healthBar = new ProgressBar();
        healthBar.setPrefWidth(180);
        healthBar.setStyle("-fx-accent: #e94560;");
        healthLabel = new Label("❤️ Здоровье: ");
        healthLabel.setTextFill(Color.LIGHTCORAL);
        healthBox.getChildren().addAll(healthLabel, healthBar);

        // Мана
        VBox manaBox = new VBox(3);
        manaBar = new ProgressBar();
        manaBar.setPrefWidth(180);
        manaBar.setStyle("-fx-accent: #4cc9f0;");
        manaLabel = new Label("🔷 Мана: ");
        manaLabel.setTextFill(Color.LIGHTCYAN);
        manaBox.getChildren().addAll(manaLabel, manaBar);

        VBox statsBox = new VBox(8);
        statsBox.getChildren().addAll(statsTitle, healthBox, manaBox);

        getChildren().add(statsBox);
        getChildren().add(new Separator());
    }

    private void createMagicSection() {
        Label magicTitle = new Label("🔮 МАГИЯ");
        magicTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        magicTitle.setTextFill(Color.PLUM);

        magicPowerLabel = new Label("⚡ Сила магии: ");
        magicPowerLabel.setTextFill(Color.LIGHTCYAN);

        magicControlLabel = new Label("🎯 Контроль магии: ");
        magicControlLabel.setTextFill(Color.LIGHTBLUE);

        VBox magicBox = new VBox(5);
        magicBox.getChildren().addAll(magicTitle, magicPowerLabel, magicControlLabel);

        getChildren().add(magicBox);
    }

    public void update() {
        // Обновление базовой информации
        nameLabel.setText("👸 " + heroine.getName());
        levelLabel.setText("Уровень: " + heroine.getLevel());

        // Опыт
        double xpProgress = (double) heroine.getExperience() / heroine.getExperienceToNextLevel();
        xpBar.setProgress(xpProgress);

        // Здоровье
        int health = heroine.getHealth();
        int maxHealth = heroine.getMaxHealth();
        double healthProgress = (double) health / maxHealth;
        healthBar.setProgress(healthProgress);
        healthLabel.setText(String.format("❤️ Здоровье: %d/%d (%.0f%%)",
                health, maxHealth, healthProgress * 100));

        // Мана
        int mana = heroine.getMana();
        int maxMana = heroine.getMaxMana();
        double manaProgress = (double) mana / maxMana;
        manaBar.setProgress(manaProgress);
        manaLabel.setText(String.format("🔷 Мана: %d/%d (%.0f%%)",
                mana, maxMana, manaProgress * 100));

        // Магические навыки - ТЕПЕРЬ РАБОТАЕТ!
        if (heroine.getMagic() != null) {
            magicPowerLabel.setText(String.format("⚡ Сила магии: %d",
                    heroine.getMagic().getMagicPower()));
            magicControlLabel.setText(String.format("🎯 Контроль магии: %d",
                    heroine.getMagic().getMagicControl()));
        } else {
            magicPowerLabel.setText("⚡ Сила магии: не доступно");
            magicControlLabel.setText("🎯 Контроль магии: не доступно");
        }
    }
}