package ct.game.geographical;

import java.util.ArrayList;

public class Trail {
    private static int idCounter = 0;
    private String id;
    private ArrayList<Location> locations;

    public Trail(String saveId) {
        this.id = "TRAILID_" + saveId + "_trail:" + idCounter;
        idCounter++;
        this.locations = new ArrayList<>();
    }

    //getters

    public String getId() {
        return id;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }
}
