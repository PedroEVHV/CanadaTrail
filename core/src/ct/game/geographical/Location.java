package ct.game.geographical;

import java.util.ArrayList;

public class Location {
    private static int idCounter = 0;
    private String id;
    private String name;
    private String description;
    private String spriteCode;
    private ArrayList<Integer> eventCodes;

    public Location(String saveId, String name, String description, String spriteCode, ArrayList<Integer> eventCodes) {
        this.id = "LOCID_" + saveId + "_location:" + idCounter;
        idCounter++;
        this.name = name;
        this.description = description;
        this.spriteCode = spriteCode;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Integer> getEventCodes() {
        return eventCodes;
    }

    public String getSpriteCode() {
        return spriteCode;
    }
}
