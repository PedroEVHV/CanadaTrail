package ct.game.inventories;

import java.util.HashMap;

public class Inventory extends HashMap<Item, Integer> implements InventoryInterface {


    @Override
    public void update(Item item, int amount) {
        if(this.get(item) + amount > 0) {
            this.replace(item, this.get(item) + amount);
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Inventory : \n");
        for(Item i : this.keySet()) {
            output.append("> ").append(i.getId()).append(" : ").append(this.get(i)).append("\n");
        }
        return output.toString();
    }
}
