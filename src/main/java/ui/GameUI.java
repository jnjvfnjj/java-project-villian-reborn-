package ui;

import core.GameEngine;

public class GameUI {

    private final GameEngine gameEngine;

    // 🔹 ВОТ ЭТОГО КОНСТРУКТОРА НЕ ХВАТАЛО
    public GameUI(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void startGame() {
        System.out.println("🖥 UI запущен");
        // позже здесь будет JavaFX Stage / Scene
    }
}
