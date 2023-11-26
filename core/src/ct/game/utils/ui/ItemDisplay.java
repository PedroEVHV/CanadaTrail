package ct.game.utils.ui;

import com.badlogic.gdx.graphics.Texture;
import ct.game.Game;
import ct.game.inventories.Item;

public class ItemDisplay {
    private Item item;
    private int amount;
    private int gridPosX;
    private int gridPosY;
    private int page;

    public ItemDisplay(Item item, int amount, int x, int y, int page) {
        this.item = item;
        this.amount = amount;
        this.gridPosX = x;
        this.gridPosY = y;
        this.page = page;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public int getGridPosX() {
        return gridPosX;
    }

    public int getGridPosY() {
        return gridPosY;
    }

    public int getPage() {
        return page;
    }

    public void draw(Game game, Texture texture, float configX, float configY) {
        game.getSpriteBatch().draw(texture, configX*0.1f + 200f*this.gridPosX, configY*0.80f - 100f*this.gridPosY, 50f, 50f);
        game.getFont().draw(game.getSpriteBatch(),": " + this.amount, configX*0.1f + 200*this.gridPosX + 70f, configY*0.85f - 100f*this.gridPosY);
    }
}
