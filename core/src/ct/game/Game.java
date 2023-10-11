package ct.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import ct.game.convoys.Convoy;
import ct.game.geographical.Map;
import ct.game.geographical.Trail;
import ct.game.graphql.GraphQlClientInterface;
import ct.game.inventories.Inventory;
import ct.game.inventories.items.Item;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Game extends ApplicationAdapter implements GraphQlClientInterface {

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


	//Game data
	private Convoy convoy;
	private Map map;
	private ArrayList<Item> gameItems;



	@Override
	public void create () {
		System.out.println("creating game components");
		this.gameItems = new ArrayList<>();
		try {
			GraphQlClientInterface.request(itemsListQuery, url);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}

		Trail trail = new Trail(saveId);
		this.map = new Map(saveId, trail);

		this.convoy = new Convoy(saveId, "default");


	}

	@Override
	public void render () {

	}
	
	@Override
	public void dispose () {

	}
}
