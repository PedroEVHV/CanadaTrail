package ct.game.screens.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import ct.game.Game;
import ct.game.events.Event;
import ct.game.events.EventOption;
import ct.game.exceptions.ClientException;
import ct.game.screens.ScreenConfiguration;
import ct.game.screens.inventory.InventoryScreen;
import ct.game.screens.transition.TransitionScreen;
import ct.game.utils.Effect;
import ct.game.utils.ui.Button;

public class EventScreen implements Screen {

    private Game game;
    private OrthographicCamera camera;
    private Event event;
    private TransitionScreen currentTransition;
    private Button openInventory;
    private String errorText;

    public EventScreen(Game game, ScreenConfiguration config, Event event, TransitionScreen screen) {
        this.game = game;
        this.event = event;
        for(EventOption option : this.event.getOptions()) {
            option.updateDescriptionForCharacterEvent(this.game);
        }
        this.currentTransition = screen;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(config.getyDown(), config.getX(), config.getY());
        this.openInventory = new Button(new Texture(Gdx.files.internal("util_sprites/wagon-image.png")), new Rectangle(config.getX()*0.01f, config.getY()*0.90f, 50f, 50f));
        this.errorText = "";
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
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
        this.game.getFont().getData().setScale(3f,3f);
        this.game.getFont().draw(this.game.getSpriteBatch(), this.event.getName(), configX * 0.1f, configY * 0.9f, configX*0.8f, 1, true);
        this.game.getFont().getData().setScale(1.5f,1.5f);
        this.game.getFont().draw(this.game.getSpriteBatch(), this.event.getDescription(), configX*0.1f, configY * 0.7f, configX*0.8f, 1, true);
        this.drawOptions(configX, configY);
        this.game.getFont().getData().setScale(1f,1f);
        this.game.getFont().draw(this.game.getSpriteBatch(), this.errorText, configX*0.1f, configY*0.2f);
        this.openInventory.draw(this.game);
        this.game.getSpriteBatch().end();


        if(Gdx.input.isTouched()) {
            if(this.openInventory.isClicked(Gdx.input)) {
                this.game.setScreen(new InventoryScreen(this.game, this.game.getScreenConfiguration(), this));
            }
            for(EventOption option : this.event.getOptions()) {
                if(option.getButton().isClicked(Gdx.input)) {
                    try {
                        boolean e = Effect.applyEffect(this.game, option.getEffectCode(), this);
                        if(e) {
                            this.game.setScreen(this.currentTransition);
                        }
                    } catch (ClientException ex) {
                        ex.setErrorScreen();
                    }
                }
            }
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

    private void drawOptions(float configX, float configY) {
        int amount = this.event.getOptions().size();

        if(amount == 1) {
            this.event.getOptions().get(0).generateButton(">> " + this.event.getOptions().get(0).getText(), new Rectangle(configX * 0.35f, configY*0.5f, configX*0.3f, 50f));
        } else if(amount == 2) {
            this.event.getOptions().get(0).generateButton(">> " + this.event.getOptions().get(0).getText(), new Rectangle(configX * 0.1f, configY*0.5f, configX*0.3f, 50f));
            this.event.getOptions().get(1).generateButton(">> " + this.event.getOptions().get(1).getText(), new Rectangle(configX * 0.5f, configY*0.5f, configX*0.3f, 50f));
        } else if(amount == 4) {
            this.event.getOptions().get(0).generateButton(">> " + this.event.getOptions().get(0).getText(), new Rectangle(configX * 0.1f, configY*0.5f, configX*0.3f, 50f));
            this.event.getOptions().get(1).generateButton(">> " + this.event.getOptions().get(1).getText(), new Rectangle(configX * 0.5f, configY*0.5f, configX*0.3f, 50f));
            this.event.getOptions().get(2).generateButton(">> " + this.event.getOptions().get(2).getText(), new Rectangle(configX * 0.1f, configY*0.7f, configX*0.3f, 50f));
            this.event.getOptions().get(3).generateButton(">> " + this.event.getOptions().get(3).getText(), new Rectangle(configX * 0.5f, configY*0.7f, configX*0.3f, 50f));
        }

        for(EventOption option : this.event.getOptions()) {
            option.draw(this.game);
        }
    }
}
