package ct.game.events;

public class EventOption {
    private int number;
    private String text;
    private String effectCode;

    public EventOption(int number, String text, String effectCode) {
        this.number = number;
        this.text = text;
        this.effectCode = effectCode;
    }

    //getters

    public int getNumber() {
        return number;
    }

    public String getEffectCode() {
        return effectCode;
    }

    public String getText() {
        return text;
    }
}
