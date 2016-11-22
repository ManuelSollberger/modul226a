package ch.telegraphstudios.HangLeMan.GUI;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * A renderable screen item.
 */
public interface Renderable {

	/**
	 * Returns the bounds of this renderable screen item.
	 */
	public abstract Rectangle getBounds();
	
	/**
	 * Renders the item on the given graphics context.
	 * @param g : Graphics context
	 */
	public abstract void render(Graphics g);
	
}
