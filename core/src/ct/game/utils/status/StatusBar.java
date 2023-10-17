package ct.game.utils.status;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ct.game.Game;

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
        this.value = maxValue;
    }

    public String getName() {
        return name;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if(this.value - value < 0) {
            this.value = 0;
        } else {
            this.value -= value;
        }


    }

    //Methods
    public void draw(Game game, Color color, float x, float y, float width, float height) {
        System.out.println("value: " + value);
        ShapeRenderer renderer = new ShapeRenderer();
        renderer.setColor(color);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.rect(x, y, value * width, height);
        renderer.end();
        renderer.dispose();
    }
}
