package ct.game.convoys;

import ct.game.Game;
import ct.game.characters.Character;
import ct.game.graphql.GraphQlClientInterface;
import ct.game.inventories.Inventory;
import ct.game.inventories.Item;

import java.awt.font.GraphicAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

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

        Item item = null;
        String[] itemCodes = game.getSetup().getInventoryData().keySet().toArray(new String[0]);

        for (String itemCode : itemCodes) {
            for (Item i : game.getGameItems()) {
                if (Objects.equals(i.getId(), itemCode)) {
                    item = i;
                }
            }
            convoy.getInventory().put(item, game.getSetup().getInventoryData().get(itemCode));
        }



        convoy.gold = 100;



    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }
}
