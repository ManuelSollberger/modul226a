package ch.telegraphstudios.Minesweeper.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Rectangle;

/**
 * A MSDialog is a kind of a small window that can be displayed inside the main game window.
 */
public class MSDialog extends Panel {

	public static final int DIALOG_WIDTH = 512;
	public static final int DIALOG_HEIGHT = 256;
	public static final int TITLE_BAR_HEIGHT = 32;
	
	public static final int BORDER_DISTANCE = 16;
	public static final int BUTTON_HEIGHT = 32;
	public static final int BUTTON_WIDTH = 96;
	
	private static final Color DIALOG_BACKGROUND = new Color(0xF2F2F2);
	private static final Color TITLE_BAR_COLOR = new Color(0xDE2323);
	private static final Color TITLE_COLOR = new Color(0xF2F2F2);
	private static final Color TEXT_COLOR = new Color(0x111111);
	
	private static final Rectangle TITLE_BAR_BOUNDS = new Rectangle(0, 0, DIALOG_WIDTH, TITLE_BAR_HEIGHT);
	
	private static final Font FONT = new Font("Verdana", Font.PLAIN, 13);
	
	/**
	 * The title to display on the title bar
	 */
	private String title = "Message";

	/**
	 * Default constructor
	 */
	public MSDialog() {
		this.setBounds((MSView.COLUMNS * MSTile.TILE_SIZE) / 2 - (DIALOG_WIDTH / 2), (MSView.ROWS * MSTile.TILE_SIZE) / 2 - (DIALOG_HEIGHT / 2), DIALOG_WIDTH, DIALOG_HEIGHT);
	}
	
	/**
	 * @param title is the title to display on the title bar
	 */
	public MSDialog(String title) {
		this();
		this.title = title;
	}
	
	@Override
	public void paint(Graphics g) {
		//Draw background
		g.setColor(DIALOG_BACKGROUND);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//Draw title bar
		g.setColor(TITLE_BAR_COLOR);
		g.fillRect(TITLE_BAR_BOUNDS.x, TITLE_BAR_BOUNDS.y, TITLE_BAR_BOUNDS.width, TITLE_BAR_BOUNDS.height);
		
		//Draw the title
		g.setFont(FONT);
		g.setColor(TITLE_COLOR);
		g.drawString(this.title, BORDER_DISTANCE, BORDER_DISTANCE);
		
		super.paint(g);
	}
	
}
