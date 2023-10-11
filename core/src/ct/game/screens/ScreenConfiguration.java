package ct.game.screens;

public class ScreenConfiguration {
    private int x;
    private int y;
    private Boolean yDown;

    public ScreenConfiguration(int x, int y, Boolean yDown) {
        this.x = x;
        this.y = y;
        this.yDown = yDown;
    }

    //Getters

    public Boolean getyDown() {
        return yDown;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
