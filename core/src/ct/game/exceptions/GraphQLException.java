package ct.game.exceptions;

import ct.game.Game;
import ct.game.screens.error.GraphQLErrorScreen;

public class GraphQLException extends RuntimeException implements GameExceptionInterface{

    private String message;
    private Game game;
    public GraphQLException(String message, Game game) {
        super();
        this.message = message;
        this.game = game;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setErrorScreen() {
        this.game.setScreen(new GraphQLErrorScreen(this.game, this.message));
    }

}
