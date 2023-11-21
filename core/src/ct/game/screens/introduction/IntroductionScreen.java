package ct.game.screens.introduction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import ct.game.Game;
import ct.game.screens.ScreenConfiguration;

import ct.game.screens.location.LocationScreen;

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
        float configX = this.game.getScreenConfiguration().getX();
        float configY = this.game.getScreenConfiguration().getY();

        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        game.getSpriteBatch().setProjectionMatrix(camera.combined);

        game.getSpriteBatch().begin();
        this.drawContext(configX, configY);
        game.getSpriteBatch().end();

        if (Gdx.input.isTouched() && Gdx.input.getY() < this.game.getScreenConfiguration().getY()/3) {
            game.setScreen(new LocationScreen(game, game.getScreenConfiguration(), 0));
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

    private void drawContext(float configX, float configY) {
        String s = "You are four travelers looking for a better life. (As children, you've seen too much of the wars of New World.) During a conversation with a British soldier, you learn the existence of the Chicoutimi colony. You decide to leave together off to a bright future. ";

        this.game.getFont().getData().setScale(2.0f, 2.0f);
        this.game.getFont().draw(this.game.getSpriteBatch(), "CONTEXT :", configX*0.05f, configY - configY*0.05f);
        this.game.getFont().getData().setScale(1.3f, 1.3f);
        this.game.getFont().draw(this.game.getSpriteBatch(), s, configX*0.05f, configY - configY*0.1f, configX*0.4f, 1, true);
        this.game.getFont().getData().setScale(1.0f, 1.0f);
    }

    private void drawGameRules(float configX, float configY) {
        String s = "Between each round, a series of random events (beneficial or not) might occur, and the player will have to choose between different possible decisions. \n" +
                "When an event occurs, the player must, among other things: \n" +
                "- Assign supplies to each traveler (water, food) \n" +
                "- Manage their inventory of items \n" +
                "- Treat any injuries or illnesses affecting travelers \n" +
                "\n" +
                "Good luck, and may the odds be ever in your favor !";

        this.game.getFont().getData().setScale(2.0f, 2.0f);
        this.game.getFont().draw(this.game.getSpriteBatch(), "Rules :", configX*0.5f + configX*0.05f, configY - configY*0.05f);
        this.game.getFont().getData().setScale(1.3f, 1.3f);
        this.game.getFont().draw(this.game.getSpriteBatch(), s, configX*0.05f, configY - configY*0.1f, configX*0.4f, 1, true);
        this.game.getFont().getData().setScale(1.0f, 1.0f);
    }

    private void drawMap() {
        try {
            Texture background = new Texture(Gdx.files.internal(this.game.getSetup().getAssets().get("map")));
            this.game.getSpriteBatch().draw(background, 0, 0, this.game.getScreenConfiguration().getX(), this.game.getScreenConfiguration().getY());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
