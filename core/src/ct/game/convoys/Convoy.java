package ct.game.convoys;

import ct.game.characters.Character;
import ct.game.inventories.Inventory;
import ct.game.inventories.items.Item;

import java.util.ArrayList;

public class Convoy {
    private String id;
    private String name;
    private ArrayList<Character> characters;
    private Inventory inventory;

    public Convoy(String saveId, String name) {
        this.id = "CONVOYID_" + saveId + "_convoy";
        this.characters = new ArrayList<>();
        this.inventory = new Inventory();
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
}
