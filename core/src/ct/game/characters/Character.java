package ct.game.characters;


import ct.game.Game;
import ct.game.utils.status.StatusBar;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Objects;

public class Character {

    private static int idCounter = 0;
    private String id;
    private String name1;
    private String name2;
    private StatusBar foodBar;
    private StatusBar waterBar;
    private StatusBar healthBar;
    private ArrayList<Trait> traits;

    private boolean alive;

    public Character(String id, String name1, String name2, ArrayList<Trait> traits) {
        this.id = id;
        idCounter++;
        this.name1 = name1;
        this.name2 = name2;
        this.traits = traits;
        this.alive = true;

        this.foodBar = new StatusBar(100, "food");
        this.waterBar = new StatusBar( 100, "water");
        this.healthBar = new StatusBar(100, "health");
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

    public ArrayList<Trait> getTraits() {
        return traits;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean equals(Character character) {
        return Objects.equals(character.getId(), this.getId());
    }

    public void die() {
        this.alive = false;
    }

    public void addTrait(Game game, String id) {
        for(Trait trait : game.getGameTraits()) {
            if(Objects.equals(trait.getId(), id)) {
                if(this.traits.contains(trait) && trait.getDuration() > -1) {
                    this.traits.get(this.traits.indexOf(trait)).updateDuration(trait.getDuration());
                } else if(!this.traits.contains(trait)){
                    this.traits.add(trait);
                }
            }
        }
    }

    public void removeTrait(Game game, String id) {
        for(Trait trait : game.getGameTraits()) {
            if(Objects.equals(trait.getId(), id) && this.traits.contains(trait)) {
                this.traits.remove(trait);
            }
        }
    }

}
