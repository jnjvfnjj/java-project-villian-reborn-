package skills;

public class BusinessSkill {

    private int trading;        // Торговля
    private int management;     // Управление бизнесом
    private int negotiation;    // Навык переговоров

    public BusinessSkill() {
        this.trading = 1;
        this.management = 1;
        this.negotiation = 1;
    }

    // Увеличение навыка торговли
    public void increaseTrading(int amount) {
        trading += amount;
        System.out.println("💰 Навык торговли увеличен на " + amount +
                ". Текущее значение: " + trading);
    }

    // Увеличение навыка управления
    public void increaseManagement(int amount) {
        management += amount;
        System.out.println("📊 Навык управления увеличен на " + amount +
                ". Текущее значение: " + management);
    }

    // Увеличение навыка переговоров
    public void increaseNegotiation(int amount) {
        negotiation += amount;
        System.out.println("🗣️ Навык переговоров увеличен на " + amount +
                ". Текущее значение: " + negotiation);
    }

    // Геттеры
    public int getTrading() {
        return trading;
    }

    public int getManagement() {
        return management;
    }

    public int getNegotiation() {
        return negotiation;
    }
}
