package ct.game.screens.location;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import ct.game.Game;
import ct.game.screens.ScreenConfiguration;

public class LocationScreen implements Screen {
    private Game game;
    private OrthographicCamera camera;
    public LocationScreen(final Game game, ScreenConfiguration config) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(config.getyDown(), config.getX(), config.getY());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
