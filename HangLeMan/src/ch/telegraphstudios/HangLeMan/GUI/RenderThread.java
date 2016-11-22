package ch.telegraphstudios.HangLeMan.GUI;

/**
 * This is just a thread which repaints the window 60 times per second.
 */
public class RenderThread extends Thread {
	
	private HLMGame game;
	
	public RenderThread(HLMGame game) {
		this.game = game;
		
		//Autostart
		this.start();
	}
	
	@Override
	public void run() {
		while (true) {
			this.game.repaint();
			
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
