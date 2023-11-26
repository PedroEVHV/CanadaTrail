package ct.game.inventories;

import java.util.HashMap;

public class Inventory extends HashMap<Item, Integer> implements InventoryInterface {


    @Override
    public boolean update(Item item, int amount) {
        System.out.println("update : " + item.getName() + " by " + amount);
        try{
            if(this.get(item) + amount < 0) {
                System.out.println("no item");
                throw new Exception("You don't have enough of this item");
            } else {
                this.replace(item, this.get(item) + amount);
                return true;
            }
        } catch(Exception e) {
            System.out.println("false");
            return false;
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
