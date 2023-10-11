package ct.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import ct.game.convoys.Convoy;
import ct.game.geographical.Map;
import ct.game.geographical.Trail;
import ct.game.graphql.GraphQlClientInterface;
import ct.game.inventories.Inventory;
import ct.game.inventories.items.Item;
import ct.game.screens.ScreenConfiguration;
import ct.game.screens.main_menu.MainMenuScreen;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Game extends com.badlogic.gdx.Game implements GraphQlClientInterface {

	private SpriteBatch spriteBatch;
	private BitmapFont font;

	private ScreenConfiguration screenConfiguration;
	private static String saveId = "SAV0";
	private static String url = "http://localhost:4000";

	private static String itemsListQuery =
			"query ExampleQuery {\n" +
			"\n" +
			"  items {\n" +
			"    id\n" +
			"    name\n" +
			"    description\n" +
			"    effectCode\n" +
			"  }\n" +
			"}";
	private static String locationsListQuery =
			"query ExampleQuery {\n" +
			"\n" +
			"  locations {\n" +
			"    id\n" +
			"    name\n" +
			"    description\n" +
			"    spriteCode\n" +
			"  }\n" +
			"}";


	//Game data
	private Convoy convoy;
	private Map map;
	private ArrayList<Item> gameItems;



	@Override
	public void create () {
		//Default screen settings
		this.screenConfiguration = new ScreenConfiguration(1150,650, false);

		System.out.println("creating game components");
		this.spriteBatch = new SpriteBatch();
		this.font = new BitmapFont();
		this.gameItems = new ArrayList<>();

		//GraphQlClientInterface.listLocations(locationsListQuery, url);


		//World Generation
		Trail trail = new Trail(saveId);
		trail.setLocations(GraphQlClientInterface.listLocations(locationsListQuery, url));


		this.map = new Map(saveId, trail);

		this.convoy = new Convoy(saveId, "default");

		this.setScreen(new MainMenuScreen(this, this.screenConfiguration));


	}

	@Override
	public void render () {
		super.render();

	}
	
	@Override
	public void dispose () {

	}


	//getters


	public Convoy getConvoy() {
		return convoy;
	}

	public Map getMap() {
		return map;
	}

	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}

	public ArrayList<Item> getGameItems() {
		return gameItems;
	}

	public static String getItemsListQuery() {
		return itemsListQuery;
	}

	public BitmapFont getFont() {
		return font;
	}

	public ScreenConfiguration getScreenConfiguration() {
		return screenConfiguration;
	}
}
