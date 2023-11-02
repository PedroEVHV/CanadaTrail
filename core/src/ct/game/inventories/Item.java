package ct.game.inventories;

public class Item {
    private static int idCounter = 0;
    private String id;
    private String name;
    private String description;

    private String itemClass;

    public Item(String saveId, String name, String description, String itemClass) {
        this.id = "ITEMID_" + saveId + "_item:" + idCounter;
        idCounter++;
        this.name = name;
        this.description = description;
        this.itemClass = itemClass;
    }

    //getters

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getItemClass() {
        return itemClass;
    }

    //Methods


}
