package skills;

import java.util.*;

public class MagicSkill {
    public enum MagicType {
        FIRE("Огонь", "🔥", "Огненные заклинания", "#ff6b6b"),
        ICE("Лёд", "❄️", "Ледяные заклинания", "#4cc9f0"),
        WATER("Вода", "💧", "Водные заклинания", "#4361ee"),
        AIR("Воздух", "💨", "Воздушные заклинания", "#f8f9fa"),
        EARTH("Земля", "🌍", "Земляные заклинания", "#8b4513"),
        LIGHTNING("Молния", "⚡", "Электрические заклинания", "#ffd166"),
        NATURE("Природа", "🌿", "Природные заклинания", "#06d6a0"),
        HEALING("Исцеление", "💖", "Целительные заклинания", "#ff9e6d"),
        DARK("Тьма", "🌑", "Тёмные заклинания", "#7209b7"),
        LIGHT("Свет", "☀️", "Светлые заклинания", "#ffea00");

        private final String name;
        private final String emoji;
        private final String description;
        private final String color;

        MagicType(String name, String emoji, String description, String color) {
            this.name = name;
            this.emoji = emoji;
            this.description = description;
            this.color = color;
        }

        public String getName() { return name; }
        public String getEmoji() { return emoji; }
        public String getDescription() { return description; }
        public String getColor() { return color; }
    }

    private Map<MagicType, Integer> magicLevels;
    private Set<MagicType> unlockedMagics;
    private int magicPower;      // Базовая сила заклинаний
    private int magicControl;    // Точность и контроль
    private int magicKnowledge;  // Знание магии
    private int magicResistance; // Сопротивление магии
    private int manaCapacity;    // Вместимость маны

    public MagicSkill() {
        magicLevels = new HashMap<>();
        unlockedMagics = new HashSet<>();

        // Начальные магии (можно выбрать 3)
        unlockMagic(MagicType.FIRE);
        unlockMagic(MagicType.ICE);
        unlockMagic(MagicType.HEALING);

        magicPower = 10;
        magicControl = 10;
        magicKnowledge = 5;
        magicResistance = 5;
        manaCapacity = 100;
    }

    public MagicSkill(MagicType... startingMagics) {
        magicLevels = new HashMap<>();
        unlockedMagics = new HashSet<>();

        for (MagicType magic : startingMagics) {
            if (unlockedMagics.size() < 3) {
                unlockMagic(magic);
            }
        }

        magicPower = 10;
        magicControl = 10;
        magicKnowledge = 5;
        magicResistance = 5;
        manaCapacity = 100;
    }

    // Основные методы
    public void unlockMagic(MagicType type) {
        if (unlockedMagics.size() < 10 || unlockedMagics.contains(type)) {
            unlockedMagics.add(type);
            magicLevels.putIfAbsent(type, 1);
            System.out.println("✨ Открыта магия: " + type.getName() + " " + type.getEmoji());
        } else {
            System.out.println("⚠️ Достигнут лимит видов магии!");
        }
    }

    public void increaseMagicLevel(MagicType type, int amount) {
        if (unlockedMagics.contains(type)) {
            int current = magicLevels.getOrDefault(type, 0);
            magicLevels.put(type, current + amount);
            System.out.println("📈 Уровень магии " + type.getName() + " увеличен до " + (current + amount));
        }
    }

    public boolean canUseMagic(MagicType type) {
        return unlockedMagics.contains(type);
    }

    // Тренировка характеристик
    public void trainPower(int hours) {
        magicPower += hours * 2;
        magicControl += hours;
        System.out.println("⚡ Сила магии +" + (hours * 2) + ", контроль +" + hours);
    }

    public void trainControl(int hours) {
        magicControl += hours * 2;
        magicPower += hours;
        System.out.println("🎯 Контроль магии +" + (hours * 2) + ", сила +" + hours);
    }

    public void studyMagic(int hours) {
        magicKnowledge += hours * 3;
        manaCapacity += hours * 5;
        System.out.println("📚 Знание магии +" + (hours * 3) + ", ёмкость маны +" + (hours * 5));
    }

    // Использование магии
    public int castSpell(MagicType type, int manaCost) {
        if (!canUseMagic(type)) {
            System.out.println("❌ Магия " + type.getName() + " не доступна!");
            return 0;
        }

        int level = getMagicLevel(type);
        int damage = magicPower * level / 10;
        int accuracy = magicControl * level / 10;

        System.out.println(type.getEmoji() + " Заклинание " + type.getName() + " (уровень " + level + ")");
        System.out.println("  Урон: " + damage + ", Точность: " + accuracy + "%, Ману: " + manaCost);

        // Шанс критического удара
        if (Math.random() * 100 < magicControl / 2) {
            damage *= 2;
            System.out.println("  💥 КРИТИЧЕСКИЙ УДАР! Урон удвоен!");
        }

        increaseMagicLevel(type, 1); // Получаем опыт за использование
        return damage;
    }

    public int heal(int manaCost) {
        if (!canUseMagic(MagicType.HEALING)) {
            System.out.println("❌ Исцеление не доступно!");
            return 0;
        }

        int level = getMagicLevel(MagicType.HEALING);
        int healAmount = magicPower * level / 5;
        int accuracy = magicControl * level / 10;

        System.out.println("💖 Исцеление (уровень " + level + ")");
        System.out.println("  Восстановлено: " + healAmount + " HP, Точность: " + accuracy + "%, Ману: " + manaCost);

        increaseMagicLevel(MagicType.HEALING, 1);
        return healAmount;
    }

    // Геттеры
    public int getMagicLevel(MagicType type) {
        return magicLevels.getOrDefault(type, 0);
    }

    public int getMagicPower() {
        return magicPower;
    }

    public int getMagicControl() {
        return magicControl;
    }

    public int getMagicKnowledge() {
        return magicKnowledge;
    }

    public int getMagicResistance() {
        return magicResistance;
    }

    public int getManaCapacity() {
        return manaCapacity;
    }

    public Set<MagicType> getUnlockedMagics() {
        return new HashSet<>(unlockedMagics);
    }

    public Map<MagicType, Integer> getMagicLevels() {
        int magLevels = 0;
        return new HashMap<>(magLevels);
    }

    // Информационные методы
    public void displayMagicInfo() {
        System.out.println("\n=== МАГИЧЕСКИЕ НАВЫКИ ===");
        System.out.println("⚡ Сила магии: " + magicPower);
        System.out.println("🎯 Контроль: " + magicControl);
        System.out.println("📚 Знание: " + magicKnowledge);
        System.out.println("🛡️ Сопротивление: " + magicResistance);
        System.out.println("🔋 Ёмкость маны: " + manaCapacity);

        System.out.println("\n--- Открытые виды магии ---");
        for (MagicType type : unlockedMagics) {
            int level = getMagicLevel(type);
            System.out.println(type.getEmoji() + " " + type.getName() +
                    " (уровень " + level + ") - " + type.getDescription());
        }
    }

    public List<String> getMagicInfoAsList() {
        List<String> info = new ArrayList<>();
        info.add("⚡ Сила магии: " + magicPower);
        info.add("🎯 Контроль: " + magicControl);
        info.add("📚 Знание: " + magicKnowledge);
        info.add("🛡️ Сопротивление: " + magicResistance);
        info.add("🔋 Ёмкость маны: " + manaCapacity);

        info.add("");
        info.add("--- Открытые виды магии ---");
        for (MagicType type : unlockedMagics) {
            int level = getMagicLevel(type);
            info.add(type.getEmoji() + " " + type.getName() +
                    " (уровень " + level + ")");
        }

        return info;
    }

    // Методы для улучшения
    public void learnNewMagic(MagicType type, int studyHours) {
        if (unlockedMagics.contains(type)) {
            System.out.println("✅ Магия " + type.getName() + " уже изучена!");
            return;
        }

        if (magicKnowledge >= studyHours * 10) {
            unlockMagic(type);
            magicKnowledge -= studyHours * 5;
            System.out.println("🎓 Изучена новая магия: " + type.getName());
        } else {
            System.out.println("📚 Недостаточно знаний для изучения " + type.getName());
            System.out.println("   Требуется знаний: " + (studyHours * 10) + ", есть: " + magicKnowledge);
        }
    }

    // Улучшение общей магии
    public void meditate(int hours) {
        magicControl += hours;
        magicResistance += hours / 2;
        manaCapacity += hours * 3;
        System.out.println("🧘 Медитация " + hours + " часов");
        System.out.println("  Контроль +" + hours + ", Сопротивление +" + (hours/2) + ", Ёмкость маны +" + (hours*3));
    }

    // Проверка на возможность использования заклинания
    public boolean canCastSpell(int requiredMana) {
        return true; // В реальной игре проверяли бы текущую ману
    }

    // Получение урона от магии (для сопротивления)
    public int calculateMagicDamage(int baseDamage, MagicType type) {
        int resistance = magicResistance;

        // Бонус сопротивления к определенным типам магии
        if (unlockedMagics.contains(type)) {
            resistance += getMagicLevel(type) * 2;
        }

        int damageReduction = resistance / 10;
        int finalDamage = Math.max(0, baseDamage - damageReduction);

        return finalDamage;
    }
}