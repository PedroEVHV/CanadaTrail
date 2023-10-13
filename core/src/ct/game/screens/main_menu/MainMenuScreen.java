package ct.game.screens.main_menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import ct.game.Game;
import ct.game.screens.ScreenConfiguration;
import ct.game.screens.introduction.IntroductionScreen;

public class MainMenuScreen implements Screen {

    final Game game;
    private OrthographicCamera camera;


    public MainMenuScreen(final Game game, ScreenConfiguration config) {
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
        this.game.getFont().draw(this.game.getSpriteBatch(), "PLAY", this.game.getScreenConfiguration().getX()/2.f, this.game.getScreenConfiguration().getY()/2.f);

        game.getSpriteBatch().end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new IntroductionScreen(game, game.getScreenConfiguration()));
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
