package character;

import skills.MagicSkill;

public class Heroine {
    private String name;
    private int level;
    private int health;
    private int maxHealth;
    private int mana;
    private int maxMana;
    private int experience;
    private int experienceToNextLevel;
    private MagicSkill magic;  // <-- MagicSkill уже здесь

    public Heroine(String name) {
        this.name = name;
        this.level = 1;
        this.maxHealth = 100;
        this.health = 100;
        this.maxMana = 50;
        this.mana = 50;
        this.experience = 0;
        this.experienceToNextLevel = 100;
        this.magic = new MagicSkill();  // <-- Создаем объект MagicSkill
    }

    public Heroine() {
        this("Неизвестная");
    }

    // Геттеры
    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getMana() { return mana; }
    public int getMaxMana() { return maxMana; }
    public int getExperience() { return experience; }
    public int getExperienceToNextLevel() { return experienceToNextLevel; }

    // Геттер для MagicSkill - ВОТ ОН!
    public MagicSkill getMagic() {
        return magic;
    }

    // Методы
    public void rest() {
        health = maxHealth;
        mana = maxMana;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public void useMana(int amount) {
        mana -= amount;
        if (mana < 0) mana = 0;
    }

    // Добавим метод для опыта
    public void addExperience(int exp) {
        this.experience += exp;
        if (experience >= experienceToNextLevel) {
            levelUp();
        }
    }

    private void levelUp() {
        level++;
        experience -= experienceToNextLevel;
        experienceToNextLevel = (int)(experienceToNextLevel * 1.5);
        maxHealth += 20;
        maxMana += 15;
        health = maxHealth;
        mana = maxMana;
    }
}