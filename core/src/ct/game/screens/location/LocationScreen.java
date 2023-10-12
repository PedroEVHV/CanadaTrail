package ct.game.screens.location;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import ct.game.Game;
import ct.game.geographical.Location;
import ct.game.screens.ScreenConfiguration;
import ct.game.screens.game_screen.GameScreen;
import ct.game.screens.transition.TransitionScreen;

public class LocationScreen implements Screen {
    private Game game;
    private OrthographicCamera camera;
    private Location location;
    private Texture locationTexture;
    public LocationScreen(final Game game, ScreenConfiguration config, int trailPosition) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(config.getyDown(), config.getX(), config.getY());

        this.location = game.getMap().getTrail().getLocations().get(trailPosition);
        this.locationTexture = new Texture(Gdx.files.internal(this.location.getSpriteCode() + ".png"));


        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            game.setScreen(new GameScreen(game, game.getScreenConfiguration()));
            dispose();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        camera.update();
        game.getSpriteBatch().setProjectionMatrix(camera.combined);

        game.getSpriteBatch().begin();
        //game.getFont().draw(game.getSpriteBatch(),location.getDescription(), game.getScreenConfiguration().getX(), 150 );
        game.getSpriteBatch().end();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            game.setScreen(new TransitionScreen(game, game.getScreenConfiguration()));
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
