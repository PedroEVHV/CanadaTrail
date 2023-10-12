package ct.game.screens.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import ct.game.Game;
import ct.game.screens.ScreenConfiguration;
import ct.game.screens.end.EndScreen;
import ct.game.screens.transition.TransitionScreen;

public class InventoryScreen implements Screen {

    private Game game;
    private OrthographicCamera camera;

    public InventoryScreen(final Game game, ScreenConfiguration config) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(config.getyDown(), config.getX(), config.getY());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        this.camera.update();
        this.game.getSpriteBatch().setProjectionMatrix(camera.combined);

        this.game.getSpriteBatch().begin();
        //game.getFont().draw(game.getSpriteBatch(),location.getDescription(), game.getScreenConfiguration().getX(), 150 );
        this.game.getSpriteBatch().end();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
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
