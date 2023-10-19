package ct.game.screens.error;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import ct.game.Game;
import ct.game.screens.ScreenConfiguration;

class ErrorScreen implements Screen {

    private final Game game;
    private OrthographicCamera camera;
    private String errorTypeString;

    ErrorScreen(final Game game, ScreenConfiguration config, String errorTypeString) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(config.getyDown(), config.getX(), config.getY());
        this.errorTypeString = errorTypeString;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0,0,0,1);

        float configX = this.game.getScreenConfiguration().getX();
        float configY = this.game.getScreenConfiguration().getY();

        this.camera.update();

        this.game.getSpriteBatch().setProjectionMatrix(camera.combined);
        this.game.getSpriteBatch().begin();

        this.game.getFont().getData().setScale(2,2);
        this.game.getFont().draw(this.game.getSpriteBatch(), "ERROR !", configX * 0.1f, configY*0.8f);
        this.game.getFont().draw(this.game.getSpriteBatch(), this.errorTypeString, configX * 0.1f, configY*0.6f);



        this.game.getSpriteBatch().end();

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
