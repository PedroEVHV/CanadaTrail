package ct.game.screens.end;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import ct.game.Game;
import ct.game.screens.ScreenConfiguration;

public class EndScreen implements Screen {
    private final Game game;
    private OrthographicCamera camera;
    private boolean success;

    public EndScreen(final Game game, ScreenConfiguration config, boolean success) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(config.getyDown(), config.getX(), config.getY());
        this.success = success;
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

        game.getSpriteBatch().begin();
        game.getFont().getData().setScale(4f,4f);
        if(this.success) {
            drawSuccess(configX, configY);
        } else {
            drawFailure(configX, configY);
        }
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

    private void drawFailure(float configX, float configY) {
        this.game.getFont().draw(this.game.getSpriteBatch(), "GAME OVER",configX*0.35f, configY*0.5f);
    }

    private void drawSuccess(float configX, float configY) {
        this.game.getFont().draw(this.game.getSpriteBatch(), "WELL DONE !", configX*0.35f, configY*0.5f);
    }
}
