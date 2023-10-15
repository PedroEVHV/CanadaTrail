package ct.game.screens.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import ct.game.Game;
import ct.game.characters.Character;
import ct.game.screens.ScreenConfiguration;
import ct.game.screens.end.EndScreen;
import ct.game.screens.transition.TransitionScreen;
import ct.game.utils.interact.ResourceAssigner;

import java.util.HashMap;
import java.util.Map;

public class InventoryScreen implements Screen {

    private Game game;
    private OrthographicCamera camera;

    //Next round confirmation button
    private Rectangle confirmButtonBox;
    private final String confirmButton = "Continue Trip !";
    private final String inventoryTitleText = ">> Assign resources : ";

    //Textures
    private Texture foodTexture;
    private Texture waterTexture;

    private Texture healthTexture;

    private HashMap<ResourceAssigner, Integer> assignButtons;




    public InventoryScreen(final Game game, ScreenConfiguration config) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(config.getyDown(), config.getX(), config.getY());

        //Load textures
        this.foodTexture = new Texture(Gdx.files.internal("item_sprites/food-image.png"));
        this.waterTexture = new Texture(Gdx.files.internal("item_sprites/water-image.png"));
        this.healthTexture = new Texture(Gdx.files.internal("item_sprites/first-aid-kit-image.png"));

        this.assignButtons = new HashMap<>();




        this.confirmButtonBox = new Rectangle(game.getScreenConfiguration().getX()*0.9f, 40.f, 100f, 50f);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        float configX = this.game.getScreenConfiguration().getX();
        float configY = this.game.getScreenConfiguration().getY();

        this.camera.update();


        //Init draw
        this.game.getSpriteBatch().setProjectionMatrix(camera.combined);
        this.game.getSpriteBatch().begin();

        //Draw title
        this.game.getFont().draw(this.game.getSpriteBatch(), this.inventoryTitleText, configX*0.01f, configY*0.95f);


        //Draw character info
        int count = 0;
        for(Character c : this.game.getConvoy().getCharacters()) {
            this.game.getFont().draw(this.game.getSpriteBatch(), c.getName1() + " " + c.getName2(), configX*0.01f, configY*0.8f - count*125f);

            ResourceAssigner foodAssigner = new ResourceAssigner(this.game, foodTexture, configX*0.65f, configY*0.8f - count*125f);
            foodAssigner.draw();
            this.assignButtons.put(foodAssigner, count * 10 + 10);

            ResourceAssigner waterAssigner = new ResourceAssigner(this.game, waterTexture, configX*0.75f, configY*0.8f - count*125f);
            waterAssigner.draw();
            this.assignButtons.put(waterAssigner, count * 10 + 11);

            ResourceAssigner healthAssigner = new ResourceAssigner(this.game, healthTexture, configX*0.85f, configY*0.8f - count*125f);
            healthAssigner.draw();
            this.assignButtons.put(healthAssigner, count * 10 + 12);

            count++;
        }

        game.getFont().draw(game.getSpriteBatch(),confirmButton, configX*0.76f, 40.f);
        this.game.getSpriteBatch().end();
        count = 0;
        for(Character c : this.game.getConvoy().getCharacters()) {
            c.getHealthBar().draw(this.game, Game.healthColor, configX * 0.3f, configY * 0.8f - count * 125f - 25f);
            c.getFoodBar().draw(this.game, Game.foodColor, configX * 0.4f, configY * 0.8f - count * 125f - 25f);
            c.getWaterBar().draw(this.game, Game.waterColor, configX * 0.5f, configY * 0.8f - count * 125f - 25f);
            count++;



        }
        //Draw food and water assigners


        if (Gdx.input.isTouched()) {
            this.game.setScreen(new TransitionScreen(this.game, this.game.getScreenConfiguration(), 0.0f));

            dispose();
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
}
