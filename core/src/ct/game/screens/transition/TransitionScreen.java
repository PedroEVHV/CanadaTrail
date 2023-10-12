package ct.game.screens.transition;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import ct.game.Game;
import ct.game.screens.ScreenConfiguration;
import ct.game.screens.location.LocationScreen;

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


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        this.game.getMap().nextLocation();

        if(transitionProgress == 1.0) {
            this.game.setScreen(new LocationScreen(game, this.game.getScreenConfiguration(), this.game.getMap().getTrailPosition()));
        } else {
            //randomize event
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
