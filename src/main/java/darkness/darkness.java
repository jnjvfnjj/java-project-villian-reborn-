package darkness;

public class darkness {

    private int darknessLevel;

    public darkness() {
        this.darknessLevel = 0;
    }

    public void increaseDarkness(int amount) {
        darknessLevel += amount;
    }

    public int getDarknessLevel() {
        return darknessLevel;
    }
}
