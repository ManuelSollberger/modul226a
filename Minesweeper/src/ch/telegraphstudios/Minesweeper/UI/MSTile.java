package ch.telegraphstudios.Minesweeper.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * An MSTile is a single tile on the game board.
 */
public class MSTile implements Renderable {
	
	public static boolean firstClick = true;
	public static int revealed = 0;

	public static final int TILE_SIZE = 64;
	public static final Color TILE_COLOR = Color.GRAY;
	public static final Color FLAG_COLOR = Color.BLUE;
	public static final Color TILE_COLOR_ACTIVE = Color.DARK_GRAY;
	public static final Color MINE_NUMBER_COLOR = Color.RED;
	private static final Color REVEALED_MINE_COLOR = Color.GREEN;
	
	private static final int MAX_MINE_DISTANCE = 1;

	private int x;
	private int y;
	private boolean active = false;
	private boolean flag = false;
	
	private MSView gameBoard;
	
	/**
	 * If this tile has a mine under it or not.
	 */
	private boolean mined;
	
	/**
	 * Pass a MSView instance and the position of this tile.
	 */
	public MSTile(MSView gameBoard, int x, int y) {
		this.gameBoard = gameBoard;
		
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	/**
	 * Call this when the tile is clicked.
	 */
	public void click() {
		this.reveal();
	}
	
	/**
	 * This reveals this tile and (if it is an 'empty' tile) reveal its neighbors.
	 */
	public void reveal(/*boolean recursive, int sourceX, int sourceY*/) {
		if (!this.active) {
			if (firstClick) {
				this.mined = false;
				firstClick = false;
			}
			
			if (!this.mined) {
				this.active = true;
				revealed++;
				
				//Check if this is the last tile
				if (revealed >= MSView.COLUMNS * MSView.ROWS - MSView.MINES) {
					this.gameBoard.gameOverDialog.setText(GameOverDialog.WIN_STRING);
					this.gameBoard.gameOverDialog.setVisible(true);
				}
				
				if (this.countOfNearMines() == 0) {
					for (int y = this.y - MAX_MINE_DISTANCE; y <= this.y + MAX_MINE_DISTANCE; y++) {
						for (int x = this.x - MAX_MINE_DISTANCE; x <= this.x + MAX_MINE_DISTANCE; x++) {
							if (x >= 0 && y >= 0 && x <= MSView.COLUMNS && y <= MSView.ROWS) {
								MSTile thisTile = this.gameBoard.getTile(x, y);
								if (thisTile != null) {
									if (!thisTile.active) {
										if (!thisTile.mined) {
											thisTile.reveal();
										}
									}
								}
							}
						}
					}
				}
			}
			else {
				this.gameBoard.gameOverDialog.setText(GameOverDialog.LOSE_STRING);
				this.gameBoard.gameOverDialog.setVisible(true);
			}
		}
	}
	
	/**
	 * The non-recursive equivalent of the recursive method
	 */
	/*public void reveal() {
		this.reveal(false, 0, 0);
	}*/
	
	/**
	 * @return the scaled bounds of this tile as it appears on its MSView.
	 */
	public Rectangle getBounds() {
		return new Rectangle(this.x * TILE_SIZE, this.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
	}
	
	@Override
	public void render(Graphics g) {
		if (this.active) {
			g.setColor(TILE_COLOR_ACTIVE);
		}
		else{
			g.setColor(TILE_COLOR);
			
			if (this.flag) {
				g.setColor(FLAG_COLOR);
			}
		}
		
		//Draw mine green if the player won
		if (this.mined && revealed >= MSView.COLUMNS * MSView.ROWS - MSView.MINES) {
			g.setColor(REVEALED_MINE_COLOR);
		}
		
		Rectangle bounds = this.getBounds();
		g.fillRect(bounds.x, bounds.y, bounds.width - 1, bounds.height - 1);
		
		//Draw the number of nearest mines when active
		if (this.active) {
			int minesCount = this.countOfNearMines();
			
			if (minesCount > 0) {
				g.setColor(MINE_NUMBER_COLOR);
				g.setFont(new Font("Consolas", Font.PLAIN, (int)(TILE_SIZE * 0.75)));
				g.drawString("" + minesCount, x * TILE_SIZE, y * TILE_SIZE + TILE_SIZE);
			}
		}
	}
	
	private int countOfNearMines() {
		int count = 0;
		
		for (int y = this.y - MAX_MINE_DISTANCE; y <= this.y + MAX_MINE_DISTANCE; y++) {
			for (int x = this.x - MAX_MINE_DISTANCE; x <= this.x + MAX_MINE_DISTANCE; x++) {
				if (x >= 0 && y >= 0 && x < MSView.COLUMNS && y < MSView.ROWS) {
					MSTile thisTile = this.gameBoard.getTile(x, y);
					
					if (thisTile != null) {
						if (thisTile.isMined()) {
							count++;
						}
					}
				}
			}
		}
		
		return count;
	}

	public void setMined(boolean mined) {
		this.mined = mined;
	}
	
	public boolean isMined() {
		return this.mined;
	}

	public void setFlag() {
		this.flag = !this.flag;
	}
	
}
