package ct.game.characters;

public class Trait {

    private String id;
    private String name;
    private String description;
    private String effectCommand;

    private float duration;

    public Trait(String id, String name, String description, String duration, String effectCommand) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = (float) Integer.parseInt(duration);
        this.effectCommand = effectCommand;
    }

    //Getters

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    public float getDuration() {
        return duration;
    }

    public String getEffectCommand() {
        return effectCommand;
    }

    public void updateDuration(float delta) {
        if(this.duration + delta < 0) {
            this.duration += 0;
        } else {
            this.duration += delta;
        }
    }
}
