package skills;

public class SocialSkill {

    // Базовые социальные параметры
    private int charisma;        // Харизма
    private int empathy;         // Эмпатия
    private int persuasion;      // Убеждение

    public SocialSkill() {
        this.charisma = 1;
        this.empathy = 1;
        this.persuasion = 1;
    }

    /**
     * Рассчитывает изменение симпатии NPC в зависимости от:
     * - типа взаимодействия
     * - уровня тьмы героини
     */
    public int calculateAffectionChange(String interactionType, int darknessLevel) {
        int change = 0;

        switch (interactionType.toLowerCase()) {
            case "доброта":
            case "помощь":
            case "подарок":
                change = 5 + charisma + empathy;
                break;

            case "общение":
            case "разговор":
                change = 2 + charisma;
                break;

            case "флирт":
                change = 4 + charisma + persuasion;
                break;

            case "угроза":
            case "давление":
                change = -5 - darknessLevel;
                break;

            case "манипуляция":
                change = persuasion - darknessLevel;
                break;

            default:
                change = 1; // нейтральное действие
        }

        // Тьма снижает все положительные эффекты
        if (change > 0) {
            change -= darknessLevel / 2;
        }

        return change;
    }

    // Методы развития
    public void increaseCharisma(int amount) {
        charisma += amount;
        System.out.println("✨ Харизма увеличена на " + amount + " → " + charisma);
    }

    public void increaseEmpathy(int amount) {
        empathy += amount;
        System.out.println("💖 Эмпатия увеличена на " + amount + " → " + empathy);
    }

    public void increasePersuasion(int amount) {
        persuasion += amount;
        System.out.println("🗣️ Убеждение увеличено на " + amount + " → " + persuasion);
    }

    // Геттеры
    public int getCharisma() { return charisma; }
    public int getEmpathy() { return empathy; }
    public int getPersuasion() { return persuasion; }
}
