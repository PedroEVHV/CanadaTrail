package ct.game.screens.error;

import ct.game.Game;

public class GraphQLErrorScreen extends ErrorScreen {
    public GraphQLErrorScreen(final Game game, String errorTypeString) {
        super(game, game.getScreenConfiguration(), errorTypeString);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
