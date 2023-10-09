package ct.game.inventories.items;

public class Item {
    private static int idCounter = 0;
    private String id;
    private String name;
    private String description;

    public Item(String saveId, String name, String description) {
        this.id = "ITEMID_" + saveId + "_item:" + idCounter;
        idCounter++;
        this.name = name;
        this.description = description;
    }
}
