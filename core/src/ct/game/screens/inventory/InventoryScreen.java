package ct.game.screens.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import ct.game.Game;
import ct.game.screens.ScreenConfiguration;
import ct.game.screens.end.EndScreen;
import ct.game.screens.transition.TransitionScreen;

public class InventoryScreen implements Screen {

    private Game game;
    private OrthographicCamera camera;

    //Next round confirmation button
    private Rectangle confirmButtonBox;
    private final String confirmButton = "Continue Trip !";

    public InventoryScreen(final Game game, ScreenConfiguration config) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(config.getyDown(), config.getX(), config.getY());
        this.confirmButtonBox = new Rectangle(game.getScreenConfiguration().getX()*0.9f, 40.f, 100f, 50f);
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
        this.game.getFont().getData().setScale(1f, 1f);
        game.getFont().draw(game.getSpriteBatch(),confirmButton, game.getScreenConfiguration().getX()*0.9f, 40.f);
        this.game.getSpriteBatch().end();

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
