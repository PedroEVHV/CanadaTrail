package ct.game.screens.transition;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import ct.game.Game;
import ct.game.characters.Character;
import ct.game.screens.ScreenConfiguration;
import ct.game.screens.location.LocationScreen;


import java.util.Random;

public class TransitionScreen implements Screen {
    private final Game game;
    private OrthographicCamera camera;

    private Texture transitionImage;

    private Float transitionProgress;

    private long lastTickTime;



    public TransitionScreen(final Game game, ScreenConfiguration config, Float transitionProgress) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(config.getyDown(), config.getX(), config.getY());
        this.transitionProgress = transitionProgress;

        System.out.println("transition");

        this.lastTickTime = TimeUtils.nanoTime();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        float configX = this.game.getScreenConfiguration().getX();
        float configY = this.game.getScreenConfiguration().getY();

        ScreenUtils.clear(0,0,0,1);
        camera.update();
        game.getSpriteBatch().setProjectionMatrix(camera.combined);

        System.out.println("transition : " + transitionProgress);

        if(TimeUtils.nanoTime() - this.lastTickTime > 1500000000) {
            if(this.transitionProgress >= 1.0f) {
                this.game.getMap().nextLocation();
                this.game.setScreen(new LocationScreen(game, this.game.getScreenConfiguration(), this.game.getMap().getTrailPosition()));
                this.dispose();
            } else  {
                //randomize event
                Random random = new Random();
                float randomFloat = random.nextFloat();
                System.out.println("random : " + randomFloat);
                if(randomFloat < 0.2) {
                    //event
                }
                this.transitionProgress += 0.1f;

                //Update vitals
                for(Character c : this.game.getConvoy().getCharacters()) {
                    this.updateVitals(c, 5 );
                }

            }
            lastTickTime = TimeUtils.nanoTime();
        }



        //Draw
        drawTransitionBar(configX * 0.1f, configY *0.5f);

        int count = 0;
        for(Character c : this.game.getConvoy().getCharacters()) {
            c.getHealthBar().draw(this.game, Game.healthColor, configX * 0.3f, configY - count * 30f - 25f, 1f, 20f);
            c.getFoodBar().draw(this.game, Game.foodColor, configX * 0.4f, configY - count * 30f - 25f, 1f, 20f);
            c.getWaterBar().draw(this.game, Game.waterColor, configX * 0.5f, configY - count * 30f - 25f, 1f, 20f);
            count++;



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

    private void updateVitals(Character c, int value) {

        c.getFoodBar().setValue(value/2);
        c.getWaterBar().setValue(value);
    }

    private void drawTransitionBar(float x, float y) {
        ShapeRenderer renderer = new ShapeRenderer();
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        int blocs = (int) (transitionProgress/0.1f);
        for(int i = 0; i < blocs; i++) {
            renderer.rect(x + i * 70f, y, 60f, 20f);
        }
        renderer.end();
        renderer.dispose();
    }
}
