package world;

public class Location {
    private String name;
    private String description;
    private String[] availableActions;

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return name + ": " + description;
    }
}