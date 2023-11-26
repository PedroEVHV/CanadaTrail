package ct.game.screens.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import ct.game.Game;
import ct.game.inventories.Item;
import ct.game.screens.ScreenConfiguration;
import ct.game.utils.ui.ItemDisplay;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryScreen implements Screen {

    private Game game;

    private OrthographicCamera camera;
    private Screen nextScreen;

    private HashMap<Item, Texture> textures;

    private ArrayList<ItemDisplay> displays;
    private Texture rightArrowTexture;
    private Texture leftArrowTexture;
    private Texture crossTexture;


    private int index;
    private int maxIndex;
    private static int gridSizeX = 6;
    private static int gridSizeY = 4;
    //Displayed grid : 6 * 4


    public InventoryScreen(final Game game, ScreenConfiguration config, Screen screen) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(config.getyDown(), config.getX(), config.getY());
        this.nextScreen = screen;
        this.textures = new HashMap<>();
        for(Item item : this.game.getGameItems()) {
            this.textures.put(item, new Texture(Gdx.files.internal("item_sprites/" + item.getSpriteCode() + ".png")));
        }
        int page = 0;
        int x = 0;
        int y = 0;
        this.displays = new ArrayList<>();
        for(Item item : this.game.getGameItems()) {
            if(this.game.getConvoy().getInventory().get(item) != null) {
                this.displays.add(new ItemDisplay(item, this.game.getConvoy().getInventory().get(item), x, y, page));
                if(x >= gridSizeX) {
                    x = 0;
                    y++;
                }
                if(y >= gridSizeY) {
                    y = 0;
                    page++;
                } else {
                    x++;
                }

            }
        }


        this.rightArrowTexture = new Texture(Gdx.files.internal("util_sprites/right-arrow.png"));
        this.leftArrowTexture = new Texture(Gdx.files.internal("util_sprites/left-arrow.png"));
        this.crossTexture = new Texture(Gdx.files.internal("util_sprites/cross.png"));

        this.index = 0;
        this.maxIndex = this.textures.size()/(gridSizeX * gridSizeY);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        float configX = this.game.getScreenConfiguration().getX();
        float configY = this.game.getScreenConfiguration().getY();
        try{
            this.game.getSpriteBatch().end();
        } catch (Exception e) {

        }
        this.game.getSpriteBatch().begin();
        this.game.getSpriteBatch().draw(leftArrowTexture, configX*0.01f, configY*0.9f, 50f, 50f);
        this.game.getSpriteBatch().draw(rightArrowTexture, configX*0.10f, configY*0.9f, 50f, 50f);
        this.game.getSpriteBatch().draw(crossTexture, configX*0.90f, configY*0.9f, 50f, 50f);
        this.drawItems(configX, configY);
        this.game.getSpriteBatch().end();

        //Process input
        if(Gdx.input.isTouched()) {
            Rectangle left = new Rectangle(configX*0.01f, configY - configY*0.9f - 50f, 50f, 50f);
            Rectangle right = new Rectangle(configX*0.10f, configY - configY*0.9f - 50f, 50f, 50f);
            Rectangle cross = new Rectangle(configX*0.90f, configY - configY*0.9f - 50f, 50f, 50f);

            if(cross.contains(Gdx.input.getX(), Gdx.input.getY())) {
                this.game.setScreen(this.nextScreen);
                this.dispose();
            } else if(left.contains(Gdx.input.getX(), Gdx.input.getY())) {
                this.changePage(-1);
            } else if (right.contains(Gdx.input.getX(), Gdx.input.getY())) {
                this.changePage(1);
            }
        }

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

    private void changePage(int delta) {
        if(this.index + delta < 0) {
            this.index = 0;
        } else if(this.index + delta > this.maxIndex){
            this.index = this.maxIndex;
        } else {
            this.index += delta;
        }
    }

    private void drawItems(float configX, float configY) {
        for(ItemDisplay i : this.displays) {
            if(i.getPage() == this.index) {
                i.draw(this.game, textures.get(i.getItem()), configX, configY);
            }
        }
    }
}
