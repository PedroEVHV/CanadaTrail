package ct.game.events;

import java.util.ArrayList;

public class Event {
    private String id;
    private String name;
    private String description;
    private String effectCode;
    private ArrayList<EventOption> options;

    private String spriteCode;

    public Event(String id, String name, String description, String effectCode, ArrayList<EventOption> options, String spriteCode) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.effectCode = effectCode;
        this.options = options;
        this.spriteCode = spriteCode;
    }

    /*
        effect code :
        i#id$amount@id$amount
        c#id$stat$type$amount@id$stat...
        c#id$trait$add/remove$
     */

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
