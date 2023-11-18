package ct.game.utils;

import ct.game.Game;
import ct.game.graphql.GraphQlClientInterface;

import java.util.HashMap;

public class Setup {
    private HashMap<String, Integer> inventoryData;
    private int travelers;
    private int eventCap;

    public Setup(HashMap<String, Integer> inv, int travelers, int eventCap) {
        this.inventoryData = inv;
        this.travelers = travelers;
        this.eventCap = eventCap;
    }

    public HashMap<String, Integer> getInventoryData() {
        return inventoryData;
    }

    public int getEventCap() {
        return eventCap;
    }

    public int getTravelers() {
        return travelers;
    }
}
