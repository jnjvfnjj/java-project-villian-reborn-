package gui;

import character.Heroine;
import core.GameEngine;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainWindow extends Application {

    private GameEngine gameEngine;
    private Heroine heroine;

    private BorderPane mainLayout;
    private VBox leftPanel;
    private VBox centerPanel;
    private VBox rightPanel;

    private TextArea gameLog;
    private Label timeLabel;
    private Label locationLabel;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Инициализация движка (если есть классы) или создание заглушек
            initializeGameEngine();

            primaryStage.setTitle("🎮 Villian Reborn - RPG");

            mainLayout = new BorderPane();
            mainLayout.setStyle("-fx-background-color: #1a1a2e;");
            Scene scene = new Scene(mainLayout, 1200, 800);
            primaryStage.setScene(scene);

            // Создаём все панели
            createTopBar();
            createLeftPanel();
            createCenterPanel();
            createRightPanel();
            createBottomBar();

            // Добавляем начальное сообщение
            addToLog("Добро пожаловать в Villian Reborn!");
            addToLog("Вы просыпаетесь в забытом храме...");
            addToLog("Воспоминания о прошлой жизни медленно возвращаются.");

            primaryStage.show();
            startGameLoop();

        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("Ошибка запуска", "Проверьте наличие всех классов:\n"
                    + "1. GameEngine\n"
                    + "2. Heroine\n"
                    + "3. TimeManager\n\n"
                    + "Создайте эти классы или закомментируйте их использование.");
        }
    }

    private void initializeGameEngine() {
        // Заглушки для классов, если их нет
        try {
            gameEngine = new GameEngine();
            heroine = gameEngine.getHeroine();
        } catch (Exception e) {
            // Если классов нет, создаём заглушки
            createStubClasses();
        }
    }

    private void createStubClasses() {
        // Временные заглушки для тестирования GUI
        heroine = new Heroine() {
            private String name = "Алисия";
            private int level = 1;
            private int health = 100;
            private int mana = 50;

            @Override
            public String getName() { return name; }
            @Override
            public int getLevel() { return level; }
            @Override
            public int getHealth() { return health; }
            @Override
            public int getMaxHealth() { return 100; }
            @Override
            public int getMana() { return mana; }
            @Override
            public int getMaxMana() { return 100; }
            @Override
            public int getExperience() { return 0; }
            @Override
            public int getExperienceToNextLevel() { return 100; }
        };

        gameEngine = new GameEngine() {
            @Override
            public Heroine getHeroine() { return heroine; }
        };
    }

    private void createTopBar() {
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10));
        topBar.setSpacing(20);
        topBar.setStyle("-fx-background-color: #162447;");
        topBar.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label("🔥 VILLIAN REBORN");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.WHITE);

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Меню
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: transparent;");

        Menu fileMenu = new Menu("Файл");
        MenuItem saveItem = new MenuItem("Сохранить");
        MenuItem loadItem = new MenuItem("Загрузить");
        MenuItem exitItem = new MenuItem("Выход");
        exitItem.setOnAction(e -> Platform.exit());

        fileMenu.getItems().addAll(saveItem, loadItem, new SeparatorMenuItem(), exitItem);

        Menu gameMenu = new Menu("Игра");
        MenuItem settingsItem = new MenuItem("Настройки");
        MenuItem statsItem = new MenuItem("Статистика");

        gameMenu.getItems().addAll(settingsItem, statsItem);

        menuBar.getMenus().addAll(fileMenu, gameMenu);

        topBar.getChildren().addAll(titleLabel, spacer, menuBar);
        mainLayout.setTop(topBar);
    }

    private void createLeftPanel() {
        leftPanel = new VBox();
        leftPanel.setPadding(new Insets(15));
        leftPanel.setSpacing(15);
        leftPanel.setPrefWidth(250);
        leftPanel.setStyle("-fx-background-color: #0f3460;");
        leftPanel.setAlignment(Pos.TOP_CENTER);

        // Статистика персонажа
        Label charLabel = new Label("ПЕРСОНАЖ");
        charLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        charLabel.setTextFill(Color.LIGHTGRAY);

        VBox charInfo = new VBox(10);
        charInfo.setPadding(new Insets(10));
        charInfo.setStyle("-fx-background-color: #1a1a2e; -fx-background-radius: 10;");
        charInfo.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Имя: " + heroine.getName());
        nameLabel.setTextFill(Color.WHITE);

        Label levelLabel = new Label("Уровень: " + heroine.getLevel());
        levelLabel.setTextFill(Color.WHITE);

        ProgressBar healthBar = new ProgressBar((double) heroine.getHealth() / heroine.getMaxHealth());
        healthBar.setPrefWidth(200);
        healthBar.setStyle("-fx-accent: #e94560;");
        Label healthLabel = new Label("Здоровье: " + heroine.getHealth() + "/" + heroine.getMaxHealth());
        healthLabel.setTextFill(Color.LIGHTCORAL);

        ProgressBar manaBar = new ProgressBar((double) heroine.getMana() / heroine.getMaxMana());
        manaBar.setPrefWidth(200);
        manaBar.setStyle("-fx-accent: #4cc9f0;");
        Label manaLabel = new Label("Мана: " + heroine.getMana() + "/" + heroine.getMaxMana());
        manaLabel.setTextFill(Color.LIGHTCYAN);

        ProgressBar expBar = new ProgressBar((double) heroine.getExperience() / heroine.getExperienceToNextLevel());
        expBar.setPrefWidth(200);
        expBar.setStyle("-fx-accent: #f9c74f;");
        Label expLabel = new Label("Опыт: " + heroine.getExperience() + "/" + heroine.getExperienceToNextLevel());
        expLabel.setTextFill(Color.LIGHTYELLOW);

        charInfo.getChildren().addAll(nameLabel, levelLabel, healthBar, healthLabel,
                manaBar, manaLabel, expBar, expLabel);

        leftPanel.getChildren().addAll(charLabel, charInfo);
        mainLayout.setLeft(leftPanel);
    }

    private void createCenterPanel() {
        centerPanel = new VBox();
        centerPanel.setPadding(new Insets(15));
        centerPanel.setSpacing(15);
        centerPanel.setStyle("-fx-background-color: #1a1a2e;");

        // Локация
        locationLabel = new Label("📍 ЗАБЫТЫЙ ХРАМ");
        locationLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        locationLabel.setTextFill(Color.WHITE);

        // Лог игры
        gameLog = new TextArea();
        gameLog.setPrefHeight(400);
        gameLog.setEditable(false);
        gameLog.setWrapText(true);
        gameLog.setStyle("-fx-control-inner-background: #0f3460; -fx-text-fill: white; -fx-font-size: 14px;");

        // Действия
        Label actionsLabel = new Label("ДОСТУПНЫЕ ДЕЙСТВИЯ");
        actionsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        actionsLabel.setTextFill(Color.LIGHTGRAY);

        FlowPane actionsPane = new FlowPane();
        actionsPane.setHgap(10);
        actionsPane.setVgap(10);
        actionsPane.setPrefWrapLength(600);

        String[] actions = {
                "🔍 Осмотреться", "🗣️ Поговорить с духом", "📖 Изучить древние тексты",
                "⚔️ Тренироваться", "🧪 Создать зелье", "🗺️ Исследовать местность",
                "💰 Проверить карманы", "🎭 Воспользоваться памятью прошлой жизни"
        };

        for (String action : actions) {
            Button btn = new Button(action);
            btn.setPrefWidth(190);
            btn.setPrefHeight(60);
            btn.setStyle("-fx-background-color: #162447; -fx-text-fill: white; -fx-font-size: 13px;");
            btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #e94560; -fx-text-fill: white;"));
            btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: #162447; -fx-text-fill: white;"));

            btn.setOnAction(e -> {
                String actionText = action.replaceAll(".*?\\s+", ""); // Убираем эмодзи
                addToLog("Вы выбрали: " + actionText);
                // Здесь будет логика действий
            });

            actionsPane.getChildren().add(btn);
        }

        centerPanel.getChildren().addAll(locationLabel, gameLog, actionsLabel, actionsPane);
        mainLayout.setCenter(centerPanel);
    }

    private void createRightPanel() {
        rightPanel = new VBox();
        rightPanel.setPadding(new Insets(15));
        rightPanel.setSpacing(15);
        rightPanel.setPrefWidth(300);
        rightPanel.setStyle("-fx-background-color: #0f3460;");
        rightPanel.setAlignment(Pos.TOP_CENTER);

        // Время и дата
        Label timeHeader = new Label("⏰ ВРЕМЯ");
        timeHeader.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        timeHeader.setTextFill(Color.LIGHTGRAY);

        timeLabel = new Label("День 1, 08:00");
        timeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        timeLabel.setTextFill(Color.WHITE);

        VBox timeBox = new VBox(5);
        timeBox.setPadding(new Insets(10));
        timeBox.setStyle("-fx-background-color: #1a1a2e; -fx-background-radius: 10;");
        timeBox.setAlignment(Pos.CENTER);
        timeBox.getChildren().addAll(timeHeader, timeLabel);

        // Инвентарь
        Label invHeader = new Label("🎒 ИНВЕНТАРЬ");
        invHeader.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        invHeader.setTextFill(Color.LIGHTGRAY);

        ListView<String> inventoryList = new ListView<>();
        inventoryList.getItems().addAll(
                "📜 Записка с воспоминаниями",
                "🔑 Ржавый ключ",
                "💊 Простое зелье лечения",
                "💰 50 золотых",
                "🗡️ Сломанный кинжал",
                "📖 Дневник прошлой жизни"
        );
        inventoryList.setPrefHeight(200);
        inventoryList.setStyle("-fx-control-inner-background: #1a1a2e; -fx-text-fill: white;");

        Button useItemBtn = new Button("Использовать предмет");
        useItemBtn.setPrefWidth(200);
        useItemBtn.setStyle("-fx-background-color: #e94560; -fx-text-fill: white;");

        VBox invBox = new VBox(10);
        invBox.setPadding(new Insets(10));
        invBox.setStyle("-fx-background-color: #1a1a2e; -fx-background-radius: 10;");
        invBox.setAlignment(Pos.CENTER);
        invBox.getChildren().addAll(invHeader, inventoryList, useItemBtn);

        rightPanel.getChildren().addAll(timeBox, invBox);
        mainLayout.setRight(rightPanel);
    }

    private void createBottomBar() {
        HBox bottomBar = new HBox();
        bottomBar.setPadding(new Insets(10));
        bottomBar.setSpacing(10);
        bottomBar.setStyle("-fx-background-color: #162447;");
        bottomBar.setAlignment(Pos.CENTER);

        Button quickSaveBtn = new Button("💾 Быстрое сохранение");
        Button quickLoadBtn = new Button("📂 Быстрая загрузка");
        Button helpBtn = new Button("❓ Помощь");
        Button quitBtn = new Button("🚪 Выйти");

        // Стили кнопок
        String buttonStyle = "-fx-background-color: #0f3460; -fx-text-fill: white; -fx-padding: 8 15;";
        for (Button btn : new Button[]{quickSaveBtn, quickLoadBtn, helpBtn, quitBtn}) {
            btn.setStyle(buttonStyle);
            btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #e94560; -fx-text-fill: white;"));
            btn.setOnMouseExited(e -> btn.setStyle(buttonStyle));
        }

        quitBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Выход");
            alert.setHeaderText("Вы уверены, что хотите выйти?");
            alert.setContentText("Несохранённый прогресс будет потерян.");

            if (alert.showAndWait().get() == ButtonType.OK) {
                Platform.exit();
            }
        });

        bottomBar.getChildren().addAll(quickSaveBtn, quickLoadBtn, helpBtn, quitBtn);
        mainLayout.setBottom(bottomBar);
    }

    private void startGameLoop() {
        // Игровой цикл - обновление времени и UI
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000); // Обновление каждую секунду

                    Platform.runLater(() -> {
                        updateTime();
                        updateUI();
                    });
                } catch (InterruptedException e) {
                    break;
                }
            }
        }).start();
    }

    private void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = "День 1, " + sdf.format(new Date());
        timeLabel.setText(time);
    }

    private void updateUI() {
        // Здесь будет обновление статистики персонажа
        // Пока просто заглушка
    }

    private void addToLog(String message) {
        String timeStamp = new SimpleDateFormat("[HH:mm]").format(new Date());
        gameLog.appendText(timeStamp + " " + message + "\n");
        // Автопрокрутка вниз
        gameLog.setScrollTop(Double.MAX_VALUE);
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}