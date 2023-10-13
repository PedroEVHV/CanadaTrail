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
import ct.game.screens.end.EndScreen;
import ct.game.screens.inventory.InventoryScreen;

public class LocationScreen implements Screen {
    private Game game;
    private OrthographicCamera camera;
    private Location location;
    private Texture locationTexture;
    public LocationScreen(final Game game, ScreenConfiguration config, int trailPosition) {
        this.game = game;

        this.camera = new OrthographicCamera();

        this.camera.setToOrtho(config.getyDown(), config.getX(), config.getY());

        this.location = game.getMap().getTrail().getLocations().get(trailPosition);
        this.locationTexture = new Texture(Gdx.files.internal(this.location.getSpriteCode() + ".jpg"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        float imageWidth = 450;
        float imageHeight = 600;

        float imagePosX = 20f;
        float imagePosY = 20f;

        ScreenUtils.clear(0,0,0,1);
        camera.update();
        game.getSpriteBatch().setProjectionMatrix(camera.combined);
        game.getSpriteBatch().begin();
        game.getFont().getData().setScale(2,2);
        game.getFont().draw( //Draw description text
                game.getSpriteBatch(),location.getDescription(),
                this.game.getScreenConfiguration().getX()/2f + 5f,
                this.game.getScreenConfiguration().getY() * 0.9f,
                this.game.getScreenConfiguration().getX()/2f * 0.7f,
                1, true
        );
        this.game.getSpriteBatch().draw(locationTexture, imagePosX, imagePosY, imageWidth, imageHeight);

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
