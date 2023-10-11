package ct.game.screens.introduction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import ct.game.Game;
import ct.game.screens.ScreenConfiguration;
import ct.game.screens.game_screen.GameScreen;

public class IntroductionScreen implements Screen {
    private final Game game;
    private OrthographicCamera camera;

    public IntroductionScreen(final Game game, ScreenConfiguration config) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(config.getyDown(), config.getX(), config.getY());
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        game.getSpriteBatch().setProjectionMatrix(camera.combined);

        game.getSpriteBatch().begin();
        game.getFont().draw(game.getSpriteBatch(),"Intro text", 100, 150 );
        game.getSpriteBatch().end();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            game.setScreen(new GameScreen(game, game.getScreenConfiguration()));
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
