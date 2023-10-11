package ct.game.screens.game_screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import ct.game.Game;
import ct.game.screens.ScreenConfiguration;

public class GameScreen implements Screen {

    final Game game;
    OrthographicCamera camera;


    public GameScreen(final Game game, ScreenConfiguration config) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(config.getyDown(), config.getX(), config.getY());
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,0);
        camera.update();
        game.getSpriteBatch().setProjectionMatrix(camera.combined);


        game.getSpriteBatch().begin();
        game.getSpriteBatch().end();
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
