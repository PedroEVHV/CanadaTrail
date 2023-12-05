package ct.game.utils;

import ct.game.Game;
import ct.game.exceptions.ClientException;
import ct.game.inventories.Item;
import ct.game.screens.event.EventScreen;

import java.util.Objects;

public abstract class Effect {
    public static boolean applyEffect(Game game, String command, EventScreen screen) throws ClientException {

        if(command == "" || command == null) {
            return true;
        }
        char c = command.charAt(0);
        if(c == 'c' && (command.contains("#x!") || command.contains("@x!"))) {
            System.out.println("replaced");
            System.out.println(screen.getTargetId());
            command = command.replace("#x!", "#" + screen.getTargetId() + "!").replace("@x!", "@" + screen.getTargetId() + "!");
        }
        //Code parsing
        String[] commands = command.split("=");
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
                        if(!game.getConvoy().getInventory().update(item, amount)) {
                            screen.setErrorText("You do not have enough of this item : " + item.getName());
                            return false;
                        }
                    } else {
                        throw new ClientException("Unknown item ID used in inventory update", game);
                    }

                }
            } else if(Objects.equals(type, "c")) {
                String[] charVars = content.split("@");

                for(int j = 0; j < charVars.length; j++) {
                    String[] var = charVars[j].split("!");

                    int id = Integer.parseInt(var[0]);
                    try{
                        if(id > game.getConvoy().getCharacters().size()) {
                            throw new ClientException("Targeted id is out of bounds", game);
                        }
                    } catch(ClientException e) {
                        e.setErrorScreen();
                    }

                    if(!game.getConvoy().getCharacters().get(id).isAlive()) {
                        int index = 0;
                        while(!game.getConvoy().getCharacters().get(index).isAlive() && index < game.getConvoy().getCharacters().size()){
                            index++;
                        }
                        id = index;
                    }

                    String affectType = var[1];

                    if(Objects.equals(affectType, "stat")) {
                        //System.out.println("stat");
                        String statType = var[2];
                        float amount = Float.parseFloat(var[3]);

                        if(Objects.equals(statType, "food")) {
                            game.getConvoy().getCharacters().get(id).getFoodBar().deltaUpdate(amount);
                        } else if(Objects.equals(statType, "water")) {
                            game.getConvoy().getCharacters().get(id).getWaterBar().deltaUpdate(amount);
                        } else if(Objects.equals(statType, "health")) {
                            game.getConvoy().getCharacters().get(id).getHealthBar().deltaUpdate(amount);
                        }


                    } else if(Objects.equals(affectType, "trait")) {
                        try{
                            System.out.println("trait");
                            String action = var[2];
                            if(Objects.equals(action, "add")) {
                                System.out.println(var[3]);
                                game.getConvoy().getCharacters().get(id).addTrait(game, var[3]);
                            } else if(Objects.equals(action, "remove")) {
                                game.getConvoy().getCharacters().get(id).removeTrait(game, var[3]);
                            }
                        } catch(ClientException e) {
                            e.setErrorScreen();
                        }
                    }
                }
            }
        }

        return true;
    }
}

    /*
        effect code :
        i#id!amount@id!amount=
        c#id!stat!type!amount@id!stat...=
        c#id!trait!add/remove!code=
     */
