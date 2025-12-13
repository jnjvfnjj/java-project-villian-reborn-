package core;

import ui.GameUI;
import core.GameEngine;



public class Main {
    public static void main(String[] args) {
        System.out.println("🎮 Запуск Villian Reborn...");

        GameEngine gameEngine = new GameEngine();
        GameUI gameUI = new GameUI(gameEngine);

        gameUI.startGame();
    }
}
