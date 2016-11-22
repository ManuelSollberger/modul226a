package ch.telegraphstudios.HangLeMan.GUI;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;

import ch.telegraphstudios.HangLeMan.Utils.Utils;

/**
 * This window is always in full screen mode by simply covering the windows taskbar.
 */
public class HLMFullScreenWindow extends JFrame {

	public HLMFullScreenWindow() {
		
		this.setSize(Utils.getScreenSize());
		this.setUndecorated(true);
		this.getContentPane().setBackground(Color.BLACK);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	public void addCentered(Component component) {
		component.setLocation(this.getWidth() / 2 - (component.getWidth() / 2), this.getHeight() / 2 - (component.getHeight() / 2));
		
		super.add(component);
	}
	
}
