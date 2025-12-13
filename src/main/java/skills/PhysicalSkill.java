package skills;

public class PhysicalSkill {
    private int strength;      // Сила - влияет на физический урон
    private int agility;       // Ловкость - влияет на скорость и уклонение
    private int stamina;       // Выносливость - влияет на HP и усталость
    private int defense;       // Защита - уменьшает получаемый урон
    private int vitality;      // Живучесть - скорость восстановления
    private int dexterity;     // Ловкость рук - для крафта и точных действий

    public PhysicalSkill() {
        this.strength = 10;
        this.agility = 10;
        this.stamina = 10;
        this.defense = 10;
        this.vitality = 10;
        this.dexterity = 10;
    }

    // Конструктор с начальными значениями
    public PhysicalSkill(int strength, int agility, int stamina, int defense) {
        this.strength = Math.max(1, strength);
        this.agility = Math.max(1, agility);
        this.stamina = Math.max(1, stamina);
        this.defense = Math.max(1, defense);
        this.vitality = 10;
        this.dexterity = 10;
    }

    // ========== ГЕТТЕРЫ ==========
    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getStamina() {
        return stamina;
    }

    public int getDefense() {
        return defense;
    }

    public int getVitality() {
        return vitality;
    }

    public int getDexterity() {
        return dexterity;
    }

    // ========== МЕТОДЫ УЛУЧШЕНИЯ ==========

    // Тренировка силы (тяжелая атлетика, удары)
    public void trainStrength(int hours) {
        int gain = hours * 2;
        strength += gain;
        stamina -= hours; // Тратится выносливость
        System.out.println("💪 Тренировка силы: +" + gain + " силы, -" + hours + " выносливости");
    }

    // Тренировка ловкости (бег, акробатика)
    public void trainAgility(int hours) {
        int gain = hours * 2;
        agility += gain;
        stamina -= hours;
        System.out.println("🏃 Тренировка ловкости: +" + gain + " ловкости, -" + hours + " выносливости");
    }

    // Тренировка выносливости (кардио, длительные нагрузки)
    public void trainStamina(int hours) {
        int gain = hours * 3;
        stamina += gain;
        vitality += hours; // Улучшается живучесть
        System.out.println("🏋️ Тренировка выносливости: +" + gain + " выносливости, +" + hours + " живучести");
    }

    // Тренировка защиты (блоки, уклонения)
    public void trainDefense(int hours) {
        int gain = hours * 2;
        defense += gain;
        agility += hours; // Улучшается ловкость
        System.out.println("🛡️ Тренировка защиты: +" + gain + " защиты, +" + hours + " ловкости");
    }

    // Тренировка живучести (закаливание, медитация)
    public void trainVitality(int hours) {
        vitality += hours * 2;
        stamina += hours;
        System.out.println("❤️ Тренировка живучести: +" + (hours*2) + " живучести, +" + hours + " выносливости");
    }

    // Тренировка ловкости рук (крафт, работа с мелкими предметами)
    public void trainDexterity(int hours) {
        dexterity += hours * 2;
        agility += hours;
        System.out.println("✋ Тренировка ловкости рук: +" + (hours*2) + " ловкости рук, +" + hours + " ловкости");
    }

    // Комплексная тренировка (все показатели немного)
    public void trainAll(int hours) {
        strength += hours;
        agility += hours;
        stamina += hours;
        defense += hours;
        vitality += hours;
        dexterity += hours;
        System.out.println("⚡ Комплексная тренировка: все показатели +" + hours);
    }

    // ========== РАСЧЕТЫ И ФОРМУЛЫ ==========

    // Расчет физического урона
    public int calculatePhysicalDamage() {
        int baseDamage = strength * 2 + agility;
        return baseDamage;
    }

    // Расчет защиты (сколько урона блокируется)
    public int calculateDamageReduction() {
        int reduction = defense + stamina / 2;
        return reduction;
    }

    // Расчет максимального HP
    public int calculateMaxHP() {
        int hp = stamina * 10 + vitality * 5;
        return hp;
    }

    // Расчет уклонения (шанс избежать атаки)
    public double calculateDodgeChance() {
        double chance = agility * 0.5 + dexterity * 0.3;
        return Math.min(chance, 70); // Максимум 70%
    }

    // Расчет скорости атаки
    public double calculateAttackSpeed() {
        double speed = 1.0 + (agility * 0.02) + (dexterity * 0.01);
        return speed;
    }

    // Расчет усталости от действий
    public int calculateFatigueCost(int actionCost) {
        int cost = actionCost - (stamina / 10) - (vitality / 20);
        return Math.max(1, cost); // Минимум 1
    }

    // ========== СПЕЦИАЛЬНЫЕ МЕТОДЫ ==========

    // Проверка возможности поднять тяжелый предмет
    public boolean canLift(int weight) {
        return strength * 10 >= weight;
    }

    // Проверка возможности пробежать дистанцию
    public boolean canRun(int distance) {
        return stamina * 100 >= distance;
    }

    // Проверка точности при броске/стрельбе
    public boolean checkAccuracy(int difficulty) {
        int chance = dexterity * 5 + agility * 3;
        return chance >= difficulty;
    }

    // Восстановление после отдыха
    public void rest(int hours) {
        stamina += hours * 3;
        stamina = Math.min(stamina, 100); // Ограничение
        System.out.println("💤 Отдых " + hours + " часов: выносливость +" + (hours*3));
    }

    // ========== ИНФОРМАЦИОННЫЕ МЕТОДЫ ==========

    public void displayStats() {
        System.out.println("\n=== ФИЗИЧЕСКИЕ НАВЫКИ ===");
        System.out.println("💪 Сила: " + strength);
        System.out.println("🏃 Ловкость: " + agility);
        System.out.println("🏋️ Выносливость: " + stamina);
        System.out.println("🛡️ Защита: " + defense);
        System.out.println("❤️ Живучесть: " + vitality);
        System.out.println("✋ Ловкость рук: " + dexterity);

        System.out.println("\n--- РАСЧЕТНЫЕ ПОКАЗАТЕЛИ ---");
        System.out.println("⚔️ Физический урон: " + calculatePhysicalDamage());
        System.out.println("🛡️ Снижение урона: " + calculateDamageReduction());
        System.out.println("❤️ Макс. HP: ~" + calculateMaxHP());
        System.out.println("🌀 Шанс уклонения: " + String.format("%.1f", calculateDodgeChance()) + "%");
        System.out.println("⚡ Скорость атаки: " + String.format("%.2f", calculateAttackSpeed()) + "x");
    }

    public String getStatsAsString() {
        return String.format(
                "💪 Сила: %d | 🏃 Ловкость: %d | 🏋️ Выносливость: %d\n" +
                        "🛡️ Защита: %d | ❤️ Живучесть: %d | ✋ Ловкость рук: %d",
                strength, agility, stamina, defense, vitality, dexterity
        );
    }

    public String getCombatStats() {
        return String.format(
                "⚔️ Урон: %d | 🛡️ Защита: %d | ❤️ HP: ~%d | 🌀 Уклонение: %.1f%%",
                calculatePhysicalDamage(), calculateDamageReduction(),
                calculateMaxHP(), calculateDodgeChance()
        );
    }

    // ========== СЕТТЕРЫ (с проверкой) ==========

    public void setStrength(int strength) {
        this.strength = Math.max(1, Math.min(100, strength));
    }

    public void setAgility(int agility) {
        this.agility = Math.max(1, Math.min(100, agility));
    }

    public void setStamina(int stamina) {
        this.stamina = Math.max(1, Math.min(100, stamina));
    }

    public void setDefense(int defense) {
        this.defense = Math.max(1, Math.min(100, defense));
    }

    public void setVitality(int vitality) {
        this.vitality = Math.max(1, Math.min(100, vitality));
    }

    public void setDexterity(int dexterity) {
        this.dexterity = Math.max(1, Math.min(100, dexterity));
    }
}