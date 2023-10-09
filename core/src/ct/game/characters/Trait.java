package ct.game.characters;

public class Trait {

    private String id;
    private String name;
    private String description;

    Trait(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
}
