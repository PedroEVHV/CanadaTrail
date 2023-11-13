package ct.game.utils;

import ct.game.Game;
import ct.game.inventories.Item;

import java.util.Objects;

public abstract class Resource {
    public static Item generateFoodItem(Game game) {
        for(Item i : game.getGameItems()) {
            if(Objects.equals(i.getItemClass(), "FOOD")) {
                return i;
            }
        }
        return null;
    }

    public static Item generateDrinkItem(Game game) {
        for(Item i : game.getGameItems()) {
            if(Objects.equals(i.getItemClass(), "DRINK")) {
                return i;
            }
        }
        return null;
    }

    public static Item generateMedicalItem(Game game) {
        for(Item i : game.getGameItems()) {
            if(Objects.equals(i.getItemClass(), "MEDICAL")) {
                return i;
            }
        }
        return null;
    }
}
