package ct.game.screens.location;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import ct.game.Game;
import ct.game.geographical.Location;
import ct.game.screens.ScreenConfiguration;
import ct.game.screens.end.EndScreen;
import ct.game.screens.game_screen.GameScreen;
import ct.game.screens.inventory.InventoryScreen;
import ct.game.screens.transition.TransitionScreen;

public class LocationScreen implements Screen {
    private Game game;
    private Stage stage;
    private OrthographicCamera camera;
    private Location location;
    private Texture locationTexture;
    public LocationScreen(final Game game, ScreenConfiguration config, int trailPosition) {
        this.game = game;

        this.camera = new OrthographicCamera();

        this.camera.setToOrtho(config.getyDown(), config.getX(), config.getY());

        this.location = game.getMap().getTrail().getLocations().get(trailPosition);
        //this.locationTexture = new Texture(Gdx.files.internal(this.location.getSpriteCode() + ".png"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0.1f,0,1);
        camera.update();
        game.getSpriteBatch().setProjectionMatrix(camera.combined);
        game.getSpriteBatch().begin();
        game.getFont().getData().setScale(3,3);
        game.getFont().draw(game.getSpriteBatch(),location.getDescription(), 1000, 200);
        //Label.LabelStyle style = new Label.LabelStyle();
        //style.font = game.getFont();
        //Label label = new Label(location.getDescription(), style);
        //label.setScale(3,1);
        //label.draw(game.getSpriteBatch(), 1);
        game.getSpriteBatch().end();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if(this.game.getMap().getTrailPosition() >= this.game.getMap().getTrail().getLocations().size()-1) {
                this.game.setScreen(new EndScreen(this.game, this.game.getScreenConfiguration()));
            } else {
                game.setScreen(new InventoryScreen(this.game, this.game.getScreenConfiguration()));
            }

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
