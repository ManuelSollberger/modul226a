package ch.telegraphstudios.Minesweeper.Utils;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * This class contains methods that might be useful at some point.
 */
public class Utils {

	/**
	 * @return the size of the main screen in pixels.
	 */
	public static Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
}
