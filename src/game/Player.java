package game;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private String name;
    private int charisma;
    private int strength;
    private Set<String> flags;

    public Player(String name, int charisma, int strength) {
        this.name = name;
        this.charisma = charisma;
        this.strength = strength;
        this.flags = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getStrength() {
        return strength;
    }

    public Set<String> getFlags() {
        return flags;
    }

    public void changeCharisma(int delta) {
        charisma += delta;
        System.out.println("Харизма изменена на " + delta + "!");
    }

    public void changeStrength(int delta) {
        strength += delta;
        System.out.println("Сила изменена на " + delta + "!");
    }

    public void addFlag(String flag) {
        flags.add(flag);
        System.out.println("Добавлен флаг: " + flag);
    }
}
