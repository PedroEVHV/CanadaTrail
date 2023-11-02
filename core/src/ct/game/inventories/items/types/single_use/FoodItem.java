package ct.game.inventories.items.types.single_use;

import ct.game.inventories.items.Item;
import ct.game.inventories.items.UsableItemInterface;

public class FoodItem extends Item implements UsableItemInterface {

    private int value;


    public FoodItem(String saveId, String name, String description, int value) {
        super(saveId, name, description);
        this.value = value;

    }

    //getters
    public int getValue() {
        return value;
    }

    @Override
    public String getType() {
        return "food";
    }
}
