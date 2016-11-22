package ch.telegraphstudios.HangLeMan;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ch.telegraphstudios.HangLeMan.GUI.HLMButton;
import ch.telegraphstudios.HangLeMan.GUI.HLMFullScreenWindow;
import ch.telegraphstudios.HangLeMan.GUI.HLMGame;
import ch.telegraphstudios.HangLeMan.GUI.HLMToolbar;

public class HangLeManMain {

	public static final int GAME_WIDTH = 480;
	public static final int GAME_HEIGHT = 360;
	
	private static HLMToolbar toolbar;

	/**
	 * Well, this is just the main entry of this application.
	 * It sets up the main window and creates a game instance.
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Set the look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		HLMFullScreenWindow window = new HLMFullScreenWindow();
		window.setLayout(null);
		
		HLMGame game = new HLMGame();
		game.setSize(GAME_WIDTH, GAME_HEIGHT);
		
		window.addCentered(game);
		
		toolbar = new HLMToolbar(game);
		window.addCentered(toolbar);
		toolbar.setLocation(toolbar.getX(), toolbar.getY() - (HangLeManMain.GAME_HEIGHT / 2) - (toolbar.getHeight() / 2));
		
		game.setToolbar(toolbar);
		
		//Add the banner image on a button without any action
		HLMButton banner = new HLMButton("Banner.png");
		banner.setBounds(toolbar.getX(), toolbar.getY() + HLMToolbar.TOOLBAR_HEIGHT + GAME_HEIGHT, GAME_WIDTH, HLMToolbar.TOOLBAR_HEIGHT);
		window.add(banner);
		
		window.setVisible(true);
		
	}
	
}
