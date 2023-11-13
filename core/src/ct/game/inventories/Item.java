package ct.game.inventories;

public class Item {

    private String id;
    private String name;
    private String description;

    private String itemClass;

    public Item(String id, String name, String description, String itemClass) {
        this.id = id;
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
