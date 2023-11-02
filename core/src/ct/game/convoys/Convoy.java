package ct.game.convoys;

import ct.game.Game;
import ct.game.characters.Character;
import ct.game.inventories.Inventory;

import java.util.ArrayList;

public class Convoy {
    private String id;
    private String name;
    private ArrayList<Character> characters;
    private Inventory inventory;

    private int gold;

    public Convoy(String saveId, String name) {
        this.id = "CONVOYID_" + saveId + "_convoy";
        this.characters = new ArrayList<>();
        this.inventory = new Inventory();
        this.gold = 0;
    }

    //getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public Inventory getInventory() {
        return inventory;
    }


    //Methods

    public static void setStartingInventory(Convoy convoy, Game game) {


        convoy.gold = 100;

    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }
}
