package skills;

public class CombatSkill {

    private int weaponSkill;      // Владение оружием
    private int battleTactics;    // Тактика боя
    private int reflexes;         // Рефлексы

    public CombatSkill() {
        this.weaponSkill = 1;
        this.battleTactics = 1;
        this.reflexes = 1;
    }

    // Увеличение владения оружием
    public void increaseWeaponSkill(int amount) {
        weaponSkill += amount;
        System.out.println("⚔️ Навык владения оружием увеличен на " + amount +
                ". Текущее значение: " + weaponSkill);
    }

    // Увеличение тактики боя
    public void increaseBattleTactics(int amount) {
        battleTactics += amount;
        System.out.println("🎯 Тактика боя увеличена на " + amount +
                ". Текущее значение: " + battleTactics);
    }

    // Увеличение рефлексов
    public void increaseReflexes(int amount) {
        reflexes += amount;
        System.out.println("⚡ Рефлексы увеличены на " + amount +
                ". Текущее значение: " + reflexes);
    }

    // Геттеры
    public int getWeaponSkill() {
        return weaponSkill;
    }

    public int getBattleTactics() {
        return battleTactics;
    }

    public int getReflexes() {
        return reflexes;
    }
}
