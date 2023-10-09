package ct.game.utils.status;

public class StatusBar {
    private static int idCounter = 0;
    private String id;
    private String name;
    private int maxValue;
    private int value;

    public StatusBar(String saveId, int maxValue, String name) {
        this.id = "STATUSID_" + saveId + "status:" + idCounter;
        idCounter++;
        this.maxValue = maxValue;
        this.name = name;
        this.value = 0;
    }
}
