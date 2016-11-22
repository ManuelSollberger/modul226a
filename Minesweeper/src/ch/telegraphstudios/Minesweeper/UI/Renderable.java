package ch.telegraphstudios.Minesweeper.UI;

import java.awt.Graphics;

public interface Renderable {

	/**
	 * This method has to paint the implementing component onto
	 * the passed graphics context.
	 */
	public abstract void render(Graphics g);
	
}
