package ch.telegraphstudios.Minesweeper.UI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

/**
 * An MSView is the whole game view on the MSWindow. It contains the main game board
 * with all its tiles. Only add this to a super component without any layout manager.
 */
public class MSView extends JPanel implements MouseListener {
	
	public static final int COLUMNS = 16;
	public static final int ROWS = 16;
	public static final int MINES = 32;
	
	public GameOverDialog gameOverDialog;
	
	/**
	 * Every content on the game board will be rendered onto this image.
	 * This will then finally be painted onto the readyGame in the rendering thread.
	 */
	private BufferedImage gameBoard;
	
	private ArrayList<MSTile> tiles = new ArrayList<MSTile>();
	
	/**
	 * This constructor also initializes its tiles.
	 */
	public MSView() {
		this.setSize(MSTile.TILE_SIZE * COLUMNS, MSTile.TILE_SIZE * ROWS);
		
		//Add the mouse listener
		this.addMouseListener(this);
		
		//Instantiate game board
		this.gameBoard = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		//Instantiate game over dialog
		this.gameOverDialog = new GameOverDialog(this);
		this.gameOverDialog.setVisible(false);
		this.add(this.gameOverDialog);
		
		//Start the game
		this.startGame();
	}
	
	public void startGame() {
		this.gameOverDialog.setVisible(false);
		
		MSTile.firstClick = true;
		MSTile.revealed = 0;
		
		this.tiles = new ArrayList<MSTile>();
		
		//Init tiles
		for (int y = 0; y < ROWS; y++) {
			for (int x = 0; x < COLUMNS; x++) {
				
				MSTile tile = new MSTile(this, x, y);
				this.tiles.add(tile);
				
			}
		}

		this.placeMines();
	}
	
	/**
	 * This method generates the positions of the mines
	 */
	private void placeMines() {
		//This is used to check if the generated tile is already mined
		ArrayList<Integer> mines = new ArrayList<Integer>();
		
		for (int i = 0; i < MINES; i++) {
			Random random = new Random();
			
			Integer index = random.nextInt(this.tiles.size() - 1);
			
			//If this tile is already mined pick another one
			while (mines.contains(index)) {
				index = random.nextInt(this.tiles.size() - 1);
			}
			
			mines.add(index);
			
			this.tiles.get(index).setMined(true);
		}
	}
	
	/**
	 * This is called to render the whole game. Don't do that too often.
	 */
	public void render() {
		if (this.gameBoard != null) {
			Graphics g = this.gameBoard.getGraphics();
			
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			//Draw the tiles
			for (MSTile tile : tiles) {
				tile.render(g);
			}
		}
	}
	
	/**
	 * Render before repainting.
	 */
	@Override
	public void repaint() {
		if (MSTile.revealed <= (COLUMNS * ROWS) - MINES) {
			this.render();
		}
		super.repaint();
	}
	
	/**
	 * This method only paints the game onto the window component
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawImage(this.gameBoard, 0, 0, null);
	}

	@Override
	public void mouseClicked(MouseEvent event) { }

	@Override
	public void mouseEntered(MouseEvent event) { }

	@Override
	public void mouseExited(MouseEvent event) { }

	@Override
	public void mousePressed(MouseEvent event) { }

	/**
	 * Check every tile if it is clicked and, if so, fire that action.
	 */
	@Override
	public void mouseReleased(MouseEvent event) {
		for (MSTile tile : tiles) {
			if (tile.getBounds().contains(event.getPoint())) {
				if (event.getButton() == MouseEvent.BUTTON1) {
					tile.click();
				}
				else if (event.getButton() == MouseEvent.BUTTON3) {
					tile.setFlag();
				}
			}
		}
	}

	public MSTile getTile(int x, int y) {
		for (MSTile tile : tiles) {
			if (tile.getX() == x && tile.getY() == y) {
				return tile;
			}
		}
		
		return null;
	}
	
}
