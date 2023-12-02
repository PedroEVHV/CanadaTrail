package ct.game.events;

import ct.game.Game;
import ct.game.exceptions.ClientException;
import ct.game.screens.event.EventScreen;
import ct.game.utils.Effect;

import java.util.ArrayList;

public class Event {
    private String id;
    private String name;
    private String description;
    private String effectCode;
    private ArrayList<EventOption> options;

    private String spriteCode;

    public Event(String id, String name, String description, ArrayList<EventOption> options, String spriteCode) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.options = options;
        this.spriteCode = spriteCode;


    }

    /*
        effect code :
        i#id!amount@id!amount=
        c#id!stat!type!amount@id!stat...=
        c#id!trait!add/remove!=
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

    public ArrayList<EventOption> getOptions() {
        return options;
    }


    public void applyOption(EventOption option, Game game, EventScreen screen) throws ClientException {
        Effect.applyEffect(game, option.getEffectCode(), screen);
    }
}
