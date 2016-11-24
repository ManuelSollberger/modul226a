package ch.telegraphstudios.HangLeMan.Resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * The resource manager is here to load images from the project's resources.
 */
public class ResourceManager {
	
	/**
	 * Loads an image at the given path and name.
	 * @param name
	 * @return
	 */
	public static BufferedImage getImage(String name) {
		try {
			URL resource = ResourceManager.class.getResource(name);
			return ImageIO.read(resource);
		} catch (IOException e) {
			System.out.println("Error loading image at location " + name);
			e.printStackTrace();
		}
		
		return null;
	}

}
