package core;

import character.Heroine;
import world.Location;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameEngine {
    private Heroine heroine;
    private TimeManager timeManager;
    private Location currentLocation;
    private Map<String, Location> locations;
    private Random random;

    public GameEngine() {
        this.timeManager = new TimeManager();
        this.random = new Random();
        initializeGame();
    }

    private void initializeGame() {
        // Создание героини со случайным именем
        String[] names = {"Алиса", "Виктория", "Катрин", "Элизабет", "Моргана"};
        String name = names[random.nextInt(names.length)];

        // ИСПРАВЛЕНО: Создаем героиню с именем
        this.heroine = new Heroine(name);  // <-- ВАЖНО: Heroine должен быть КЛАССОМ, а не интерфейсом

        // Создание локаций
        createLocations();

        // Начальная локация
        currentLocation = locations.get("city_center");

        System.out.println("🎮 Игра Villian Reborn началась!");
        System.out.println("👸 Имя героини: " + name);
    }

    private void createLocations() {
        locations = new HashMap<>();
        locations.put("city_center", new Location("Центр города", "Главная площадь с фонтаном"));
        locations.put("library", new Location("Королевская библиотека", "Древние фолианты и свитки"));
        locations.put("academy", new Location("Магическая академия", "Место обучения магов"));
        locations.put("forest", new Location("Тёмный лес", "Обиталище монстров и тайн"));
        locations.put("market", new Location("Рынок", "Шумный торговый район"));
        locations.put("tavern", new Location("Таверна", "Место встреч и слухов"));
        locations.put("training_ground", new Location("Тренировочный двор", "Место для физических тренировок"));
        locations.put("noble_district", new Location("Район знати", "Резиденции богатых семей"));
    }

    public void processDayCycle() {
        timeManager.advanceTime();

        // Случайные события в зависимости от времени суток
        if (random.nextInt(100) < 30) { // 30% шанс события
            triggerRandomEvent();
        }

        // Восстановление показателей ночью
        if (timeManager.getTimeOfDay() == TimeManager.TimeOfDay.MORNING) {
            heroine.rest();
            System.out.println("🌅 Наступило утро. Силы восстановлены!");
        }
    }

    private void triggerRandomEvent() {
        String[] events = {
                "На рынке появился новый торговец с редкими товарами",
                "Гильдия магов объявила набор учеников",
                "В лесу замечена активность монстров",
                "Знатная семья устраивает бал",
                "Библиотека получила новую партию книг",
                "В таверне кто-то ищет напарника для задания"
        };

        String event = events[random.nextInt(events.length)];
        System.out.println("📢 Событие: " + event);
    }

    public void moveToLocation(String locationId) {
        if (locations.containsKey(locationId)) {
            currentLocation = locations.get(locationId);
            System.out.println("📍 Вы переместились в: " + currentLocation.getName());
        }
    }

    // Геттеры
    public Heroine getHeroine() { return heroine; }
    public TimeManager getTimeManager() { return timeManager; }
    public Location getCurrentLocation() { return currentLocation; }
    public Map<String, Location> getLocations() { return locations; }
}