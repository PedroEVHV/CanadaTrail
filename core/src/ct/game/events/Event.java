package ct.game.events;

import java.util.ArrayList;

public class Event {
    private static int idCounter = 0;
    private String id;
    private String name;
    private String description;
    private String effectCode;
    private ArrayList<EventOption> options;

    private String spriteCode;

    public Event(String saveId, String name, String description, String effectCode, ArrayList<EventOption> options, String spriteCode) {
        this.id = "EVENTID_" + saveId + "_event:" + idCounter;
        idCounter++;
        this.name = name;
        this.description = description;
        this.effectCode = effectCode;
        this.options = options;
        this.spriteCode = spriteCode;
    }

    //getters

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getEffectCode() {
        return effectCode;
    }
}
