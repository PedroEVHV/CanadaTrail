package ct.game.geographical;

import java.util.ArrayList;

public class Map {
    private static int idCounter = 0;
    private String id;
    private Trail trail;
    private int trailPosition;

    public Map(String saveId, Trail trail) {
        this.id = "MAPID_" + saveId + "_map:" + idCounter;
        idCounter++;
        this.trail = trail;
        this.trailPosition = 0;
    }

    //getters

    public String getId() {
        return id;
    }

    public int getTrailPosition() {
        return trailPosition;
    }

    public Trail getTrail() {
        return trail;
    }

    //Methods

    public void nextLocation() {
        this.trailPosition++;
    }
}
