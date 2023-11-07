package ct.game.utils.interact;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
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

    private long timer;
    private boolean lock;


    public ResourceAssigner(final Game game, Texture texture, float posX, float posY) {
        this.game = game;
        this.resourceTexture = texture;
        this.posX = posX;
        this.posY = posY;
        this.lock = false;
        this.buttonMinusBox = new Rectangle(posX, this.game.getScreenConfiguration().getY() - posY + 20f, 25f, 25f);


        this.buttonPlusBox =  new Rectangle(posX, this.game.getScreenConfiguration().getY() - posY - 40f, 25f, 25f);


        this.value = 0;
        this.timer = TimeUtils.nanoTime();

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

    public void setLock(boolean b) {
        this.lock = b;
    }


    public void draw() {
        this.game.getSpriteBatch().draw(this.resourceTexture, this.posX, this.posY - 25f, 35f, 35f);
        this.game.getSpriteBatch().draw(plusTexture, this.posX, this.posY + 10f, 30f, 30f);
        this.game.getSpriteBatch().draw(minusTexture, this.posX + 5f, this.posY - 40f, 25f, 5f );
        this.game.getFont().draw(this.game.getSpriteBatch(), Integer.toString(value), this.posX + 35f, this.posY);
    }

    public void isClicked(Input input) {

        if(Math.abs(this.timer - TimeUtils.nanoTime()) > 100000000) {
            if(this.buttonMinusBox.contains(input.getX(), input.getY()) && value > 0) {
                //System.out.println("minus : " + this.resourceTexture.toString() + " -- " + this.value);
                this.value--;
            } else if(this.buttonPlusBox.contains(input.getX(), input.getY()) && value < 10 && !lock)  {
                //System.out.println("plus : "  + this.resourceTexture.toString() + " -- " + this.value);
                this.value++;
            }
            this.timer = TimeUtils.nanoTime();
        }

    }
}
