package ch.telegraphstudios.Minesweeper.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;

import ch.telegraphstudios.Minesweeper.Utils.Utils;

public class MSWindow extends Frame {

	private MSView gameView;
	private RenderingThread renderThread;
	
	public MSWindow() {
		
		//Set window properties
		this.setSize(Utils.getScreenSize());
		this.setLocation(0, 0);
		this.setUndecorated(true);
		this.setBackground(Color.BLACK);
		this.setLayout(null);
		
		Dimension screenSize = Utils.getScreenSize();
		
		//Instantiate and add the game board
		this.gameView = new MSView();
		this.gameView.setLocation(screenSize.width / 2 - (this.gameView.getWidth() / 2), screenSize.height / 2 - (this.gameView.getHeight() / 2));
		this.add(this.gameView);
		
		//Instantiate and fire the rendering thread
		this.renderThread = new RenderingThread(this.gameView);
		this.renderThread.start();
		
	}
	
}
