package economy;

public class Bank {
    private String name;
    private double balance;
    private boolean isRestricted; // Ограничен ли доступ
    private double dailyLimit;
    private double withdrawnToday;

    public Bank(String name, double initialBalance, boolean isRestricted) {
        this.name = name;
        this.balance = initialBalance;
        this.isRestricted = isRestricted;
        this.dailyLimit = isRestricted ? 500 : Double.MAX_VALUE;
        this.withdrawnToday = 0;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) return false;

        if (isRestricted) {
            if (withdrawnToday + amount > dailyLimit) {
                System.out.println("⚠️ Превышен дневной лимит снятия!");
                return false;
            }
            if (amount > balance * 0.3) { // Не больше 30% от баланса
                System.out.println("⚠️ Семья не разрешает снимать больше 30%!");
                return false;
            }
        }

        if (amount > balance) {
            System.out.println("⚠️ Недостаточно средств!");
            return false;
        }

        balance -= amount;
        withdrawnToday += amount;
        System.out.println("💰 Снято " + amount + " из " + name);
        return true;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("💰 Внесено " + amount + " на счет " + name);
        }
    }

    public void resetDailyLimit() {
        withdrawnToday = 0;
    }

    public double getBalance() { return balance; }
    public String getName() { return name; }
    public boolean isRestricted() { return isRestricted; }
}