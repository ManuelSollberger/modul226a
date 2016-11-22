package ch.telegraphstudios.HangLeMan.Resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ResourceManager {
	
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
