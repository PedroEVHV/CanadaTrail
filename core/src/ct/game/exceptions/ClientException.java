package ct.game.exceptions;

import ct.game.Game;
import ct.game.screens.error.ClientExceptionScreen;

public class ClientException extends Exception implements GameExceptionInterface{

    private String message;
    private Game game;
    public ClientException(String message, Game game) {
        super();
        this.message = message;
        this.game = game;
    }

    @Override
    public void setErrorScreen() {
        this.game.setScreen(new ClientExceptionScreen(game, game.getScreenConfiguration(), message));
    }
}
