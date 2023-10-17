package ct.game.characters;


import ct.game.utils.status.StatusBar;

import java.util.ArrayList;

public class Character {

    private static int idCounter = 0;
    private String id;
    private String name1;
    private String name2;
    private StatusBar foodBar;
    private StatusBar waterBar;
    private StatusBar healthBar;
    private ArrayList<Trait> traits;

    public Character(String saveId, String name1, String name2, ArrayList<Trait> traits) {
        this.id = "CHARID_" + saveId + "_char:" + idCounter;
        idCounter++;
        this.name1 = name1;
        this.name2 = name2;
        this.traits = traits;

        this.foodBar = new StatusBar(saveId, 100, "food");
        this.waterBar = new StatusBar(saveId, 100, "water");
        this.healthBar = new StatusBar(saveId, 100, "health");
    }

    public String getId() {
        return id;
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    public StatusBar getFoodBar() {
        return foodBar;
    }

    public StatusBar getHealthBar() {
        return healthBar;
    }

    public StatusBar getWaterBar() {
        return waterBar;
    }

}
