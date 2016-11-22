package ch.telegraphstudios.HangLeMan.GUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import ch.telegraphstudios.HangLeMan.Resources.ResourceManager;

/**
 * This is just a button with an image.
 */
public class HLMButton extends JPanel {

	private BufferedImage image;
	
	public HLMButton(String imageName) {
		this.image = ResourceManager.getImage(imageName);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
}
