package ch.telegraphstudios.HangLeMan.GUI;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import ch.telegraphstudios.HangLeMan.HangLeManMain;
import ch.telegraphstudios.HangLeMan.Resources.ResourceManager;

public class HLMPodest implements Renderable {
	
	private static final int MAX_DEATH_STAGE = 8;
	
	/**
	 * This is the current death step. If it reaches the maximum (which is the
	 * length of the 'images'-array), the player loses.
	 */
	private int deathStage = 0;
	
	private HLMGame instance;
	
	private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	
	/**
	 * This main constructor loads the images into the 'images'-array.
	 */
	public HLMPodest(HLMGame instance) {
		this.instance = instance;
		
		for (int i = 0; i <= MAX_DEATH_STAGE; i++) {
			BufferedImage newImage = ResourceManager.getImage("DeathImages/image" + i + ".png");
			this.images.add(newImage);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(0, 0, HangLeManMain.GAME_WIDTH, (int)(HangLeManMain.GAME_HEIGHT * 0.25));
	}

	@Override
	public void render(Graphics g) {
		if (this.deathStage < this.images.size()) {
			g.drawImage(this.images.get(this.deathStage), 0, 0, this.getBounds().width, this.getBounds().height, null);
		}
	}
	
	public void stepForward() {
		if (this.deathStage + 1 > MAX_DEATH_STAGE) {
			//Display alert
			JOptionPane.showMessageDialog(null, "Program Error 0x0001A0B3 occured during the runtime!\n\nAdditional information:\nPlayer lost\n" + this.instance.generatedWord + "\n\nDelete Windows?", "Fatal Error", JOptionPane.ERROR_MESSAGE);
			
			this.deathStage = 0;
			this.instance.restart();
			this.instance.toolbar.addLost();
		}
		else {
			this.deathStage++;
		}
	}

	public void reset() {
		this.deathStage = 0;
	}

}
