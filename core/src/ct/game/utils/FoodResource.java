package ct.game.utils;

import ct.game.Game;
import ct.game.inventories.Item;

import java.util.Objects;

public abstract class FoodResource {
    public static Item generateFoodITem(Game game) {
        for(Item i : game.getGameItems()) {
            if(Objects.equals(i.getItemClass(), "food")) {
                return i;
            }
        }
        return null;
    }
}
