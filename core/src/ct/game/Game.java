package ct.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import ct.game.characters.Character;
import ct.game.convoys.Convoy;
import ct.game.events.Event;
import ct.game.exceptions.GraphQLException;
import ct.game.geographical.Map;
import ct.game.geographical.Trail;
import ct.game.graphql.GraphQlClientInterface;
import ct.game.inventories.Item;
import ct.game.screens.ScreenConfiguration;
import ct.game.screens.main_menu.MainMenuScreen;
import ct.game.utils.Setup;

import java.util.ArrayList;

public class Game extends com.badlogic.gdx.Game implements GraphQlClientInterface {

	private SpriteBatch spriteBatch;
	private BitmapFont font;

	private ScreenConfiguration screenConfiguration;
	private static String saveId = "SAV0";

	public static int baseMultiplier = 10;
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

	private static String inventorySetupQuery =
			"query ExampleQuery {\n" +
					"\n" +
					"  setup {\n" +
					"    id\n" +
					"    food\n" +
					"    water\n" +
					"    medical\n" +
					"    travelers\n" +
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

	private Setup setup;




	@Override
	public void create () {
		//test area




		//Default screen settings
		this.screenConfiguration = new ScreenConfiguration(1150,650, false);
		this.setup = new Setup(inventorySetupQuery, url, this);

		System.out.println("creating game components");
		this.spriteBatch = new SpriteBatch();

		FreeTypeFontGenerator freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/pixelmix.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 12;
		this.font = freeTypeFontGenerator.generateFont(parameter);
		this.font.getData().setLineHeight(14f);

		try{
			this.gameItems = GraphQlClientInterface.listItems(itemsListQuery, url);
			if(this.gameItems.isEmpty()) {
				System.out.println("empty");
				throw new GraphQLException("Item data not loaded !", this);
			}



			this.events = new ArrayList<>();



			//World Generation
			Trail trail = new Trail(saveId);
			trail.setLocations(GraphQlClientInterface.listLocations(locationsListQuery, url));
			if(trail.getLocations().isEmpty()) {
				throw new GraphQLException("Location data not loaded !", this);
			}
			this.map = new Map(saveId, trail);

			//Convoy generation
			this.convoy = new Convoy(saveId, "default");
			this.convoy.setCharacters(GraphQlClientInterface.listCharacters(characterListQuery, url));
			Convoy.setStartingInventory(this.convoy, this);
			if(this.convoy.getCharacters().isEmpty()) {
				throw new GraphQLException("Character data not loaded !", this);
			}
			if(this.convoy.getInventory().isEmpty()) {
				throw new GraphQLException("Inventory setup not loaded !", this);
			}

			this.setScreen(new MainMenuScreen(this, this.screenConfiguration));
		} catch (GraphQLException exception) {
			exception.setErrorScreen();
		}




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

	public Setup getSetup() {
		return setup;
	}

	public BitmapFont getFont() {
		return font;
	}

	public ScreenConfiguration getScreenConfiguration() {
		return screenConfiguration;
	}


	// Methods

	public void checkForDeaths() {
		ArrayList<Character> deadCharacters = new ArrayList<>();
		for(Character c : this.convoy.getCharacters()) {
			if(c.getHealthBar().getValue() == 0 || c.getFoodBar().getValue() == 0 || c.getWaterBar().getValue() == 0) {
				deadCharacters.add(c);
			}
		}
		for(Character c : deadCharacters) {
			c.die(this);
		}
	}
}
