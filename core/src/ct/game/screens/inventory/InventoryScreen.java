package ct.game.screens.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import ct.game.Game;
import ct.game.inventories.Item;
import ct.game.screens.ScreenConfiguration;

import java.util.HashMap;

public class InventoryScreen implements Screen {

    private Game game;

    private OrthographicCamera camera;

    private HashMap<Item, Texture> textures;
    private Texture rightArrowTexture;
    private Texture leftArrowTexture;
    private Texture crossTexture;


    public InventoryScreen(final Game game, ScreenConfiguration config) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(config.getyDown(), config.getX(), config.getY());

        this.textures = new HashMap<>();
        for(Item item : this.game.getGameItems()) {
            this.textures.put(item, new Texture(Gdx.files.internal("item_sprites/" + item.getSpriteCode() + ".png")));
        }

        this.rightArrowTexture = new Texture(Gdx.files.internal("util_sprites/right-arrow.png"));
        this.leftArrowTexture = new Texture(Gdx.files.internal("util_sprites/left-arrow.png"));
        this.crossTexture = new Texture(Gdx.files.internal("util_sprites/crossTexture.png"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        float configX = this.game.getScreenConfiguration().getX();
        float configY = this.game.getScreenConfiguration().getY();

        this.game.getSpriteBatch().begin();
        this.game.getSpriteBatch().draw(leftArrowTexture, configX*0.01f, configY*0.99f);
        this.game.getSpriteBatch().draw(rightArrowTexture, configX*0.10f, configY*0.99f);
        this.game.getSpriteBatch().draw(crossTexture, configX*0.90f, configY*0.99f);



    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
