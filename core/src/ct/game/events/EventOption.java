package ct.game.events;

import com.badlogic.gdx.math.Rectangle;
import ct.game.Game;
import ct.game.utils.ui.Button;

public class EventOption {
    private int number;
    private String text;
    private String description;
    private String effectCode;

    private Button button;

    public EventOption(int number, String text, String effectCode, String description) {
        this.number = number;
        this.text = text;
        this.effectCode = effectCode;
        this.description = description;
    }

    //getters

    public int getNumber() {
        return number;
    }

    public String getEffectCode() {
        return effectCode;
    }

    public String getText() {
        return text;
    }

    public Button getButton() {
        return button;
    }

    public String getDescription() {
        return description;
    }

    public void updateDescriptionForCharacterEvent(Game game) {
        for(int i = 0; i < game.getConvoy().getCharacters().size(); i++) {
            if(this.description.contains("target:" + i)) {
                this.description = this.description.replace("target:" + i, game.getConvoy().getCharacters().get(i).getName1() + game.getConvoy().getCharacters().get(i).getName2());
            }
        }
    }

    public void generateButton(String text, Rectangle rectangle) {
        this.button = new Button(text, rectangle);
    }

    public void draw(Game game) {
        game.getFont().getData().setScale(1.5f, 1.5f);
        this.button.draw(game);
        game.getFont().getData().setScale(1f, 1f);
        game.getFont().draw(game.getSpriteBatch(), this.description, this.button.getBox().x, game.getScreenConfiguration().getY() - this.button.getBox().y - 20f, this.button.getBox().width, 1, true);
    }
}
