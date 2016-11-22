package ch.telegraphstudios.Minesweeper.UI;

import java.awt.Component;

/**
 * A rendering thread is responsible for rendering a component at a framerate
 * of 60 frames per second.
 */
public class RenderingThread extends Thread {

	private Component component;
	private boolean running = true;
	
	public RenderingThread(Component component) {
		this.component = component;
	}
	
	/**
	 * Stops the thread.
	 */
	public void halt() {
		this.running = false;
	}
	
	@Override
	public void run() {
		while (this.running) {
			this.component.repaint();
			
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
