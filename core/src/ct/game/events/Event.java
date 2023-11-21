package ct.game.events;

import ct.game.Game;
import ct.game.exceptions.ItemException;
import ct.game.inventories.Item;
import ct.game.screens.error.ClientExceptionScreen;

import java.util.ArrayList;
import java.util.Objects;

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
        i#id!amount@id!amount=
        c#id!stat!type!amount@id!stat...
        c#id!trait!add/remove!
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

    public ArrayList<EventOption> getOptions() {
        return options;
    }


    public void applyOption(EventOption option, Game game) throws ItemException {
        //Code parsing
        String[] commands = option.getEffectCode().split("=");
        for(int i = 0; i < commands.length; i++) {
            String[] parsedCommand = commands[i].split("#");
            String type = parsedCommand[0];
            String content = parsedCommand[1];

            if(Objects.equals(type, "i")) {
                String[] itemVars = content.split("@");
                for(int j = 0; j < itemVars.length; j++) {
                    String[] var = itemVars[j].split("!");
                    String id = var[0];
                    int amount = Integer.parseInt(var[1]);
                    Item item = null;

                    for(Item it : game.getGameItems()) {
                        if(Objects.equals(it.getId(), id)) {
                            item = it;
                        }
                    }

                    if(item != null) {
                        game.getConvoy().getInventory().update(item, amount);
                    } else {
                        throw new ItemException("Unknown item ID used in inventory update", game);
                    }

                    //add/remove items

                }
            } else if(Objects.equals(type, "c")) {
                //char affects
            }
        }
    }
}
