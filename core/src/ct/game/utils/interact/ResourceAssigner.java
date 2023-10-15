package ct.game.utils.interact;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import ct.game.Game;

public class ResourceAssigner {
    private Texture resourceTexture;
    private static Texture plusTexture = new Texture(Gdx.files.internal("util_sprites/plus-image.png"));
    private static Texture minusTexture = new Texture(Gdx.files.internal("util_sprites/minus-image.png"));
    private Rectangle buttonPlusBox;
    private Rectangle buttonMinusBox;
    private int value;
    private final Game game;
    private float posX;
    private float posY;


    public ResourceAssigner(final Game game, Texture texture, float posX, float posY) {
        this.game = game;
        this.resourceTexture = texture;
        this.posX = posX;
        this.posY = posY;

        this.buttonMinusBox = new Rectangle();
        this.buttonMinusBox.x = this.posX;
        this.buttonMinusBox.y = this.posY + 55f;
        this.buttonMinusBox.width = 25f;
        this.buttonMinusBox.height = 5f;

        this.buttonPlusBox =  new Rectangle();
        this.buttonPlusBox.x = this.posX;
        this.buttonPlusBox.y = this.posY - 5f;
        this.buttonPlusBox.width = 40f;
        this.buttonPlusBox.height = 40f;

        this.value = 0;

    }

    public int getValue() {
        return value;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public Game getGame() {
        return game;
    }

    public Rectangle getButtonMinusBox() {
        return buttonMinusBox;
    }

    public Rectangle getButtonPlusBox() {
        return buttonPlusBox;
    }

    public Texture getResourceTexture() {
        return resourceTexture;
    }


    public void draw() {
        this.game.getSpriteBatch().draw(this.resourceTexture, this.posX, this.posY - 25f, 35f, 35f);
        this.game.getSpriteBatch().draw(plusTexture, this.posX, this.posY + 10f, 30f, 30f);
        this.game.getSpriteBatch().draw(minusTexture, this.posX + 5f, this.posY - 40f, 25f, 5f );
        this.game.getFont().draw(this.game.getSpriteBatch(), Integer.toString(value), this.posX + 35f, this.posY);
    }
}
