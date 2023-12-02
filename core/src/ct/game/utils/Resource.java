package ct.game.utils;

import ct.game.Game;
import ct.game.exceptions.ClientException;
import ct.game.inventories.Item;

import java.util.Objects;

public abstract class Resource {
    public static Item generateFoodItem(Game game) throws ClientException {
        for(Item i : game.getGameItems()) {
            if(Objects.equals(i.getItemClass(), "FOOD")) {
                return i;
            }
        }
        throw new ClientException("No item assigned to FOOD resource", game);
    }

    public static Item generateDrinkItem(Game game) throws ClientException {
        for(Item i : game.getGameItems()) {
            if(Objects.equals(i.getItemClass(), "DRINK")) {
                return i;
            }
        }
        throw new ClientException("No item assigned to DRINK resource", game);
    }

    public static Item generateMedicalItem(Game game) throws ClientException {
        for(Item i : game.getGameItems()) {
            if(Objects.equals(i.getItemClass(), "MEDICAL")) {
                return i;
            }
        }
        throw new ClientException("No item assigned to MEDICAL resource", game);
    }
}
