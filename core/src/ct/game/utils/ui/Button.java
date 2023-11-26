package ct.game.utils.ui;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import ct.game.Game;

public class Button {
    private Texture texture;
    private Rectangle box;

    private String text;

    public Button(Texture texture, Rectangle rectangle) {
        this.texture = texture;
        this.box = rectangle;
        this.text = null;
    }

    public Button(String text, Rectangle rectangle) {
        this.texture = null;
        this.box = rectangle;
        this.text = text;
    }

    public Rectangle getBox() {
        return box;
    }

    public Texture getTexture() {
        return texture;
    }

    public boolean isClicked(Input input) {
        return this.box.contains(input.getX(), input.getY());
    }

    public void draw(Game game) {
        if(this.text == null) {
            game.getSpriteBatch().draw(this.texture, this.box.x, game.getScreenConfiguration().getY() - this.box.y - 50f, this.box.width, this.box.height);
        } else {
            game.getFont().draw(game.getSpriteBatch(), this.text,  this.box.x - 150f, this.box.y);
        }

    }
}
