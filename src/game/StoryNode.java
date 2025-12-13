package game;

import java.util.ArrayList;
import java.util.List;

public class StoryNode {
    private String id;
    private String text;
    private List<Choice> choices;

    public StoryNode(String id, String text) {
        this.id = id;
        this.text = text;
        this.choices = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void addChoice(Choice choice) {
        choices.add(choice);
    }
}
