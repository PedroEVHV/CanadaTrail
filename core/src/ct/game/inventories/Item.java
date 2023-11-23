package ct.game.inventories;

public class Item {

    private String id;
    private String name;
    private String description;

    private String itemClass;

    private String spriteCode;

    public Item(String id, String name, String description, String itemClass, String spriteCode) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.itemClass = itemClass;
        this.spriteCode = spriteCode;
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

    public String getSpriteCode() {
        return spriteCode;
    }
    //Methods


}
