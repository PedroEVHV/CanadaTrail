package ct.game.screens.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import ct.game.Game;
import ct.game.characters.Character;
import ct.game.inventories.Item;
import ct.game.screens.ScreenConfiguration;
import ct.game.screens.transition.TransitionScreen;
import ct.game.utils.Resource;
import ct.game.utils.interact.ResourceAssigner;

import java.util.HashMap;

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

    private HashMap<Integer, ResourceAssigner> assignButtons;

    private boolean assignmentValidity;




    public InventoryScreen(final Game game, ScreenConfiguration config) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(config.getyDown(), config.getX(), config.getY());



        //Load textures
        this.foodTexture = new Texture(Gdx.files.internal("item_sprites/food-image.png"));
        this.waterTexture = new Texture(Gdx.files.internal("item_sprites/water-image.png"));
        this.healthTexture = new Texture(Gdx.files.internal("item_sprites/first-aid-kit-image.png"));

        this.assignButtons = new HashMap<>();
        this.assignmentValidity = true;
        float configX = this.game.getScreenConfiguration().getX();
        float configY = this.game.getScreenConfiguration().getY();

        for(int i = 0; i < 4; i++) {
            ResourceAssigner foodAssigner = new ResourceAssigner(this.game, foodTexture, configX*0.65f, configY*0.8f - i*125f);

            this.assignButtons.put( i * 10 + 0, foodAssigner);

            ResourceAssigner waterAssigner = new ResourceAssigner(this.game, waterTexture, configX*0.75f, configY*0.8f - i*125f);

            this.assignButtons.put(i * 10 + 1, waterAssigner);

            ResourceAssigner healthAssigner = new ResourceAssigner(this.game, healthTexture, configX*0.85f, configY*0.8f - i*125f);

            this.assignButtons.put( i * 10 + 2,healthAssigner);
        }





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

            this.assignButtons.get(count * 10 + 0).draw();
            this.assignButtons.get(count * 10 + 1).draw();
            this.assignButtons.get(count * 10 + 2).draw();

            count++;
        }

        game.getFont().draw(game.getSpriteBatch(),confirmButton, configX*0.76f, 40.f);
        this.game.getSpriteBatch().end();
        count = 0;
        for(Character c : this.game.getConvoy().getCharacters()) {
            c.getHealthBar().draw(this.game, Game.healthColor, configX * 0.3f, configY * 0.8f - count * 125f - 25f, 1f, 20f);
            c.getFoodBar().draw(this.game, Game.foodColor, configX * 0.4f, configY * 0.8f - count * 125f - 25f, 1f, 20f);
            c.getWaterBar().draw(this.game, Game.waterColor, configX * 0.5f, configY * 0.8f - count * 125f - 25f, 1f, 20f);
            count++;
        }



        if (Gdx.input.isTouched()) {
            Rectangle nextButton = new Rectangle(configX*0.8f, configY*0.9f, configX*0.2f, configY*0.1f);
            if(nextButton.contains(Gdx.input.getX(), Gdx.input.getY()) && assignmentValidity) {
                setupVitals();

                this.game.setScreen(new TransitionScreen(this.game, this.game.getScreenConfiguration(), 0.0f));
                dispose();
            } else {
                checkCorrectAssignment(Resource.generateFoodItem(this.game), 0);
                checkCorrectAssignment(Resource.generateDrinkItem(this.game), 1);
                checkCorrectAssignment(Resource.generateMedicalItem(this.game), 2);

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

    private void setupVitals() {
        for(int i = 0; i < this.game.getConvoy().getCharacters().size(); i++) {
            int deltaF = this.assignButtons.get(i * 10 + 0).getValue() * Game.baseMultiplier;
            int deltaW = this.assignButtons.get(i * 10 + 1).getValue() * Game.baseMultiplier;
            int deltaH = this.assignButtons.get(i * 10 + 2).getValue() * Game.baseMultiplier;

            this.game.getConvoy().getCharacters().get(i).getFoodBar().deltaUpdate(deltaF);
            this.game.getConvoy().getCharacters().get(i).getWaterBar().deltaUpdate(deltaW);
            this.game.getConvoy().getCharacters().get(i).getHealthBar().deltaUpdate(deltaH);

            this.updateInventory(deltaF/Game.baseMultiplier, deltaW/Game.baseMultiplier, deltaH/Game.baseMultiplier);

        }
    }

    private void updateInventory(int deltaF, int deltaW, int deltaH) {
        this.game.getConvoy().getInventory().update(Resource.generateFoodItem(this.game), - deltaF);
        this.game.getConvoy().getInventory().update(Resource.generateDrinkItem(this.game), - deltaW);
        this.game.getConvoy().getInventory().update(Resource.generateMedicalItem(this.game), - deltaH);

    }

    private void checkCorrectAssignment(Item resourceType, int column) {
        int total = 0;
        for(int i = 0; i < 4; i++) {
            //System.out.println(i);
            this.assignButtons.get(i * 10 + column).isClicked(Gdx.input);
            total += this.assignButtons.get(i * 10 + column).getValue();
        }
        if(total >= this.game.getConvoy().getInventory().get(resourceType)) {
            this.assignButtons.get(0 + column).setLock(true);
            this.assignButtons.get(10 + column).setLock(true);
            this.assignButtons.get(20 + column).setLock(true);
            this.assignButtons.get(30 + column).setLock(true);
        } else {
            this.assignButtons.get(0 + column).setLock(false);
            this.assignButtons.get(10 + column).setLock(false);
            this.assignButtons.get(20 + column).setLock(false);
            this.assignButtons.get(30 + column).setLock(false);
        }
        if(total > this.game.getConvoy().getInventory().get(resourceType)) {
            this.assignmentValidity = false;
        } else {
            this.assignmentValidity = true;
        }
    }
}
