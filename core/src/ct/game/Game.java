package ct.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;
import ct.game.convoys.Convoy;
import ct.game.events.Event;
import ct.game.geographical.Map;
import ct.game.geographical.Trail;
import ct.game.graphql.GraphQlClientInterface;
import ct.game.inventories.Inventory;
import ct.game.inventories.items.Item;
import ct.game.screens.ScreenConfiguration;
import ct.game.screens.main_menu.MainMenuScreen;
import org.checkerframework.checker.units.qual.A;
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

	private static String characterListQuery =
			"query ExampleQuery {\n" +
					"\n" +
					"characters {\n" +
					"    id\n" +
					"    name1\n" +
					"    name2\n" +
					"    food\n" +
					"    health\n" +
					"    water\n" +
					"    food\n" +
					"  }\n" +
					"}";

	public final static Color waterColor = new Color(102f/255f, 195f/255f, 214f/255f, 1.0f);
	public final static Color foodColor = new Color(163f/255f, 105f/255f, 63f/255f, 1.0f);
	public final static Color healthColor = new Color(145f/255f, 22f/255f, 22f/255f, 1.0f);


	//Game data
	private Convoy convoy;
	private Map map;
	private ArrayList<Item> gameItems;
	private ArrayList<Event> events;



	@Override
	public void create () {
		//Default screen settings
		this.screenConfiguration = new ScreenConfiguration(1150,650, false);

		System.out.println("creating game components");
		this.spriteBatch = new SpriteBatch();

		FreeTypeFontGenerator freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/pixelmix.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 12;
		this.font = freeTypeFontGenerator.generateFont(parameter);
		this.font.getData().setLineHeight(14f);
		this.gameItems = new ArrayList<>();
		this.events = new ArrayList<>();


		//World Generation
		Trail trail = new Trail(saveId);
		trail.setLocations(GraphQlClientInterface.listLocations(locationsListQuery, url));


		this.map = new Map(saveId, trail);

		this.convoy = new Convoy(saveId, "default");
		this.convoy.setCharacters(GraphQlClientInterface.listCharacters(characterListQuery, url));

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
