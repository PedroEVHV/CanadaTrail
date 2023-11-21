package ct.game.screens.main_menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import ct.game.Game;
import ct.game.screens.ScreenConfiguration;
import ct.game.screens.introduction.IntroductionScreen;

public class MainMenuScreen implements Screen {

    final Game game;
    private OrthographicCamera camera;

    private long lastTickTime;
    private boolean colorSwitch;

    private Rectangle titleBox;


    public MainMenuScreen(final Game game, ScreenConfiguration config) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(config.getyDown(), config.getX(), config.getY());
        this.lastTickTime = TimeUtils.nanoTime();
        this.colorSwitch = false;

        this.titleBox = new Rectangle(config.getX()*0.5f - 320f, config.getY()*0.5f - 40f, 650f, 100f);
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
        this.displayBackgroundImage();
        this.drawPlayButton();
        game.getSpriteBatch().end();

        if (Gdx.input.isTouched() && this.titleBox.contains(Gdx.input.getX(), Gdx.input.getY())) {
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

    private void displayBackgroundImage() {
        try {

            Texture background = new Texture(Gdx.files.internal(this.game.getSetup().getAssets().get("main_menu_bg")));
            this.game.getSpriteBatch().draw(background, 0, 0, this.game.getScreenConfiguration().getX(), this.game.getScreenConfiguration().getY());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void drawPlayButton() {
        if(TimeUtils.nanoTime() - this.lastTickTime > 1000000000) {
            this.colorSwitch = !this.colorSwitch;
            this.lastTickTime = TimeUtils.nanoTime();
        }
        this.game.getFont().getData().setScale(2.0f, 2.0f);

        if(this.colorSwitch) {
            this.game.getFont().setColor(Color.RED);
        } else {
            this.game.getFont().setColor(Color.BLACK);
        }

        this.game.getFont().draw(this.game.getSpriteBatch(), "CLICK TITLE TO PLAY", this.game.getScreenConfiguration().getX()/2.f - 175f, this.game.getScreenConfiguration().getY()/2.f - 50f);


        this.game.getFont().getData().setScale(1.0f, 1.0f);
        this.game.getFont().setColor(Color.WHITE);
    }
}
