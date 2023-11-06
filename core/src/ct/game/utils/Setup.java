package ct.game.utils;

import ct.game.Game;
import ct.game.graphql.GraphQlClientInterface;

import java.util.HashMap;

public class Setup {
    private HashMap<String, Integer> inventoryData;

    public Setup(String query, String url, Game game) {
        this.inventoryData = GraphQlClientInterface.loadInventorySetup(query, url);
    }

    public HashMap<String, Integer> getInventoryData() {
        return inventoryData;
    }
}
