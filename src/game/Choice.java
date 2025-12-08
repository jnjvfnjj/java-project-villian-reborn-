package game;

import java.util.function.Consumer;

public class Choice {
    private String description;
    private String nextNodeId;
    private Consumer<Player> effect;

    public Choice(String description, String nextNodeId) {
        this(description, nextNodeId, null);
    }

    public Choice(String description, String nextNodeId, Consumer<Player> effect) {
        this.description = description;
        this.nextNodeId = nextNodeId;
        this.effect = effect;
    }

    public String getDescription() {
        return description;
    }

    public String getNextNodeId() {
        return nextNodeId;
    }

    public Consumer<Player> getEffect() {
        return effect;
    }
}
