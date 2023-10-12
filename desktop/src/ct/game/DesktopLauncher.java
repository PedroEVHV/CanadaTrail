package ct.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ct.game.Game;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("CanadaTrail");
		config.setWindowedMode(1150, 650);
		config.setWindowSizeLimits(1150, 650, 1150, 650); //A NE PAS REMPLACER SET WINDOWS
		new Lwjgl3Application(new Game(), config);
	}
}
