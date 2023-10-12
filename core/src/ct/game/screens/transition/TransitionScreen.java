package ct.game.screens.transition;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import ct.game.Game;
import ct.game.screens.ScreenConfiguration;
import ct.game.screens.location.LocationScreen;

import java.util.Random;

public class TransitionScreen implements Screen {
    private final Game game;
    private OrthographicCamera camera;

    private Texture transitionImage;

    private Float transitionProgress;


    public TransitionScreen(final Game game, ScreenConfiguration config, Float transitionProgress) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(config.getyDown(), config.getX(), config.getY());
        this.transitionProgress = transitionProgress;

        System.out.println("transition");


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        camera.update();
        game.getSpriteBatch().setProjectionMatrix(camera.combined);

        System.out.println("transition : " + transitionProgress);

        game.getSpriteBatch().begin();
        game.getSpriteBatch().end();


        if(this.transitionProgress >= 1.0f) {
            this.game.getMap().nextLocation();
            this.game.setScreen(new LocationScreen(game, this.game.getScreenConfiguration(), this.game.getMap().getTrailPosition()));
            this.dispose();
        } else {
            //randomize event
            Random random = new Random();
            float randomFloat = random.nextFloat();
            System.out.println("random : " + randomFloat);
            if(randomFloat < 0.2) {
                //event
            } else {
                this.transitionProgress += 0.1f;
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
}
