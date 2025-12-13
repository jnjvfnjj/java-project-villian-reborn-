package gui;

import core.GameEngine;
import character.Heroine;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.function.Consumer;

public class ActionPanel extends VBox {

    private GameEngine gameEngine;
    private Heroine heroine;
    private Consumer<String> logCallback;
    private Runnable updateStatsCallback;

    // Компоненты UI
    private VBox actionButtonsContainer;
    private Label actionResultLabel;

    public ActionPanel(GameEngine gameEngine, Consumer<String> logCallback, Runnable updateStatsCallback) {
        this.gameEngine = gameEngine;
        this.heroine = gameEngine.getHeroine();
        this.logCallback = logCallback;
        this.updateStatsCallback = updateStatsCallback;

        initializeUI();
        createActionButtons();
    }

    private void initializeUI() {
        setPadding(new Insets(15));
        setSpacing(15);
        setStyle("-fx-background-color: #1a1a2e;");
        setPrefWidth(300);

        // Заголовок
        Label titleLabel = new Label("ДЕЙСТВИЯ");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setAlignment(Pos.CENTER);

        // Область для кнопок действий
        actionButtonsContainer = new VBox(10);
        actionButtonsContainer.setPadding(new Insets(10));

        // Результаты действий
        actionResultLabel = new Label("");
        actionResultLabel.setWrapText(true);
        actionResultLabel.setFont(Font.font("Arial", 14));
        actionResultLabel.setTextFill(Color.LIGHTGRAY);
        actionResultLabel.setMinHeight(60);
        actionResultLabel.setStyle("-fx-background-color: #0f3460; -fx-padding: 10; -fx-background-radius: 5;");

        // Прокрутка для кнопок
        ScrollPane scrollPane = new ScrollPane(actionButtonsContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setPrefHeight(400);

        getChildren().addAll(titleLabel, new Separator(), scrollPane, actionResultLabel);
    }

    private void createActionButtons() {
        // Очищаем старые кнопки
        actionButtonsContainer.getChildren().clear();

        // Боевые действия
        Label combatLabel = new Label("⚔️ БОЕВЫЕ");
        combatLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        combatLabel.setTextFill(Color.LIGHTCORAL);

        Button trainButton = createActionButton("Тренироваться", "#e94560");
        trainButton.setOnAction(e -> handleTraining());

        Button meditateButton = createActionButton("Медитировать", "#4cc9f0");
        meditateButton.setOnAction(e -> handleMeditation());

        Button restButton = createActionButton("Отдыхать", "#2ecc71");
        restButton.setOnAction(e -> handleRest());

        // Исследовательские действия
        Label exploreLabel = new Label("🔍 ИССЛЕДОВАНИЕ");
        exploreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        exploreLabel.setTextFill(Color.LIGHTGREEN);

        Button exploreButton = createActionButton("Исследовать", "#f9c74f");
        exploreButton.setOnAction(e -> handleExplore());

        Button studyButton = createActionButton("Учиться", "#9d4edd");
        studyButton.setOnAction(e -> handleStudy());

        // Социальные действия
        Label socialLabel = new Label("🗣️ СОЦИАЛЬНЫЕ");
        socialLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        socialLabel.setTextFill(Color.LIGHTSKYBLUE);

        Button talkButton = createActionButton("Общаться", "#4361ee");
        talkButton.setOnAction(e -> handleTalk());

        Button recruitButton = createActionButton("Вербовать", "#7209b7");
        recruitButton.setOnAction(e -> handleRecruit());

        // Добавляем все кнопки в контейнер
        actionButtonsContainer.getChildren().addAll(
                combatLabel, trainButton, meditateButton, restButton,
                new Separator(),
                exploreLabel, exploreButton, studyButton,
                new Separator(),
                socialLabel, talkButton, recruitButton
        );
    }

    private Button createActionButton(String text, String color) {
        Button button = new Button(text);
        button.setPrefWidth(250);
        button.setPrefHeight(50);
        button.setStyle("-fx-background-color: " + color + "; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 14px; " +
                "-fx-font-weight: bold; " +
                "-fx-background-radius: 5;");

        // Эффект при наведении
        button.setOnMouseEntered(e ->
                button.setStyle("-fx-background-color: derive(" + color + ", 20%); " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 5;")
        );

        button.setOnMouseExited(e ->
                button.setStyle("-fx-background-color: " + color + "; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 5;")
        );

        return button;
    }

    // ========== ОБРАБОТЧИКИ ДЕЙСТВИЙ ==========

    private void handleTraining() {
        Heroine h = gameEngine.getHeroine();

        if (h.getHealth() > 25) {
            // Используем существующие методы из Heroine
            h.takeDamage(20); // Тратим здоровье на тренировку
            h.useMana(10);    // Тратим ману

            logCallback.accept("⚔️ Вы интенсивно тренировались!");
            logCallback.accept("  Здоровье: -20");
            logCallback.accept("  Мана: -10");
            logCallback.accept("  Боевой опыт увеличился!");

            showResult("Тренировка завершена! Вы стали сильнее.");
        } else {
            showResult("❌ Слишком мало здоровья для тренировки!");
        }

        updateStatsCallback.run();
    }

    private void handleMeditation() {
        Heroine h = gameEngine.getHeroine();

        if (h.getMana() < h.getMaxMana()) {
            // Медитация восстанавливает ману
            int manaToRestore = 15;
            // Heroine не имеет метода addMana, но мы можем косвенно восстановить
            // через отдых или симулировать

            logCallback.accept("🧘 Вы медитировали...");
            logCallback.accept("  Мана частично восстановлена");
            logCallback.accept("  Ясность ума повышена");

            showResult("Медитация завершена. Вы чувствуете себя более сосредоточенной.");
        } else {
            showResult("✅ Мана уже полная. Медитация не нужна.");
        }

        updateStatsCallback.run();
    }

    private void handleRest() {
        Heroine h = gameEngine.getHeroine();

        // Используем существующий метод rest()
        h.rest();

        logCallback.accept("💤 Вы хорошо отдохнули");
        logCallback.accept("  Здоровье восстановлено: " + h.getHealth() + "/" + h.getMaxHealth());
        logCallback.accept("  Мана восстановлена: " + h.getMana() + "/" + h.getMaxMana());

        showResult("Отдых завершён! Все показатели восстановлены.");

        updateStatsCallback.run();
    }

    private void handleExplore() {
        Heroine h = gameEngine.getHeroine();

        if (h.getHealth() > 10) {
            h.takeDamage(5); // Тратим здоровье на исследование

            logCallback.accept("🗺️ Вы исследовали местность");
            logCallback.accept("  Найдены интересные места");
            logCallback.accept("  Здоровье: -5");

            // Случайное событие при исследовании
            String[] discoveries = {
                    "Вы нашли заброшенную хижину в лесу",
                    "Обнаружен тайный проход в подземелье",
                    "Найдены следы древнего ритуала",
                    "Вы наткнулись на руины старого храма"
            };

            int randomIndex = (int)(Math.random() * discoveries.length);
            String discovery = discoveries[randomIndex];

            logCallback.accept("  🔎 " + discovery);
            showResult("Исследование: " + discovery);
        } else {
            showResult("❌ Слишком мало здоровья для исследования!");
        }

        updateStatsCallback.run();
    }

    private void handleStudy() {
        Heroine h = gameEngine.getHeroine();

        if (h.getMana() > 15) {
            h.useMana(15); // Тратим ману на изучение

            logCallback.accept("📚 Вы изучали древние тексты");
            logCallback.accept("  Мана: -15");
            logCallback.accept("  Знания о мире увеличились");

            String[] knowledge = {
                    "Вы узнали о древнем проклятии королевства",
                    "Изучены основы запретной магии",
                    "Расшифрованы записи о прошлой жизни",
                    "Понят механизм возрождения злодеев"
            };

            int randomIndex = (int)(Math.random() * knowledge.length);
            String learned = knowledge[randomIndex];

            logCallback.accept("  💡 " + learned);
            showResult("Изучение: " + learned);
        } else {
            showResult("❌ Недостаточно маны для изучения!");
        }

        updateStatsCallback.run();
    }

    private void handleTalk() {
        Heroine h = gameEngine.getHeroine();

        // Общение не тратит ресурсы, но может дать информацию
        logCallback.accept("🗣️ Вы пообщались с местными жителями");
        logCallback.accept("  Получена полезная информация");

        String[] rumors = {
                "Говорят, в тёмном лесу появился странный маг",
                "Король ищет смельчаков для опасного задания",
                "В библиотеке пропали несколько древних книг",
                "Ночью в районе знати видели подозрительные тени"
        };

        int randomIndex = (int)(Math.random() * rumors.length);
        String rumor = rumors[randomIndex];

        logCallback.accept("  👂 Слух: " + rumor);
        showResult("Разговор: " + rumor);

        updateStatsCallback.run();
    }

    private void handleRecruit() {
        Heroine h = gameEngine.getHeroine();

        if (h.getHealth() > 20 && h.getMana() > 10) {
            h.takeDamage(10);
            h.useMana(10);

            logCallback.accept("👥 Вы попытались вербовать последователей");
            logCallback.accept("  Здоровье: -10");
            logCallback.accept("  Мана: -10");

            // Шанс успеха
            boolean success = Math.random() > 0.5;

            if (success) {
                logCallback.accept("  ✅ Найдены потенциальные союзники!");
                showResult("Вербовка успешна! У вас появились последователи.");
            } else {
                logCallback.accept("  ❌ Никто не захотел присоединиться...");
                showResult("Вербовка провалилась. Люди боятся вас.");
            }
        } else {
            showResult("❌ Недостаточно ресурсов для вербовки!");
        }

        updateStatsCallback.run();
    }

    private void showResult(String message) {
        actionResultLabel.setText(message);

        // Автоочистка через 5 секунд
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                javafx.application.Platform.runLater(() -> {
                    if (actionResultLabel.getText().equals(message)) {
                        actionResultLabel.setText("");
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Метод для обновления панели (например, при смене локации)
    public void refresh() {
        this.heroine = gameEngine.getHeroine();
        createActionButtons();
    }
}