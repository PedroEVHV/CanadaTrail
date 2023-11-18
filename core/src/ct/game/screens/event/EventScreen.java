package ct.game.screens.event;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import ct.game.Game;
import ct.game.events.Event;
import ct.game.events.EventOption;
import ct.game.screens.ScreenConfiguration;
import ct.game.screens.transition.TransitionScreen;

public class EventScreen implements Screen {

    private Game game;
    private OrthographicCamera camera;
    private Event event;
    private TransitionScreen currentTransition;


    public EventScreen(Game game, ScreenConfiguration config, Event event, TransitionScreen screen) {
        this.game = game;
        this.event = event;
        this.currentTransition = screen;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(config.getyDown(), config.getX(), config.getY());
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


        //Init draw
        this.game.getSpriteBatch().setProjectionMatrix(camera.combined);
        this.game.getSpriteBatch().begin();

        this.game.getFont().draw(this.game.getSpriteBatch(), this.event.getName(), configX * 0.4f, configY * 0.1f);
        this.game.getFont().draw(this.game.getSpriteBatch(), this.event.getDescription(), configX*0.2f, configY * 0.2f);

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

    private void drawOptions(float configX, float configY) {
        int amount = this.event.getOptions().size();
        if(amount == 1) {
            this.game.getFont().draw(this.game.getSpriteBatch(), this.event.getOptions().get(0).getText(), configX * 0.3f, configY*0.5f);
        } else if(amount == 2) {
            this.game.getFont().draw(this.game.getSpriteBatch(), this.event.getOptions().get(0).getText(), configX * 0.3f, configY*0.5f);
            this.game.getFont().draw(this.game.getSpriteBatch(), this.event.getOptions().get(1).getText(), configX * 0.3f, configY*0.6f);
        } else if(amount == 3) {
            this.game.getFont().draw(this.game.getSpriteBatch(), this.event.getOptions().get(0).getText(), configX * 0.2f, configY*0.5f);
            this.game.getFont().draw(this.game.getSpriteBatch(), this.event.getOptions().get(1).getText(), configX * 0.6f, configY*0.5f);
            this.game.getFont().draw(this.game.getSpriteBatch(), this.event.getOptions().get(2).getText(), configX * 0.3f, configY*0.6f);
        } else if(amount == 4) {
            this.game.getFont().draw(this.game.getSpriteBatch(), this.event.getOptions().get(0).getText(), configX * 0.2f, configY*0.5f);
            this.game.getFont().draw(this.game.getSpriteBatch(), this.event.getOptions().get(1).getText(), configX * 0.2f, configY*0.6f);
            this.game.getFont().draw(this.game.getSpriteBatch(), this.event.getOptions().get(2).getText(), configX * 0.6f, configY*0.5f);
            this.game.getFont().draw(this.game.getSpriteBatch(), this.event.getOptions().get(3).getText(), configX * 0.6f, configY*0.6f);
        }
    }
}
