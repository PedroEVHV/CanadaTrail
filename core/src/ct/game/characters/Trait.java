package ct.game.characters;

public class Trait {

    private String id;
    private String name;
    private String description;

    private float duration;

    public Trait(String id, String name, String description, String duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = (float) Integer.parseInt(duration);
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
}
