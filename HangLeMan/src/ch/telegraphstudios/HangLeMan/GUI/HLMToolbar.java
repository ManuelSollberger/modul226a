package ch.telegraphstudios.HangLeMan.GUI;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.telegraphstudios.HangLeMan.HangLeManMain;

/**
 * This component contains multiple actions and information labels.
 */
public class HLMToolbar extends JPanel implements MouseListener {
	
	public static final int TOOLBAR_HEIGHT = 64;

	private HLMButton skipButton = new HLMButton("Buttons/skip.png");
	private HLMButton exitButton = new HLMButton("Buttons/exit.png");
	private HLMButton settingsButton = new HLMButton("Buttons/settings.png");
	private JLabel statsLabel = new JLabel();

	//Counters
	private int won = 0;
	private int skipped = 0;
	private int lost = 0;
	
	private HLMGame gameInstance;
	
	public HLMToolbar(HLMGame gameInstance) {
		
		this.gameInstance = gameInstance;
		
		this.setLayout(null);
		
		this.setSize(HangLeManMain.GAME_WIDTH, TOOLBAR_HEIGHT);
		
		this.skipButton.setBounds(TOOLBAR_HEIGHT, 0, TOOLBAR_HEIGHT, TOOLBAR_HEIGHT);
		this.skipButton.addMouseListener(this);
		this.add(this.skipButton);
		
		this.exitButton.setBounds(0, 0, TOOLBAR_HEIGHT, TOOLBAR_HEIGHT);
		this.exitButton.addMouseListener(this);
		this.add(this.exitButton);
		
		this.settingsButton.setBounds(TOOLBAR_HEIGHT * 2, 0, TOOLBAR_HEIGHT, TOOLBAR_HEIGHT);
		this.settingsButton.addMouseListener(this);
		this.add(this.settingsButton);
		
		this.statsLabel = new JLabel();
		this.statsLabel.setBounds(TOOLBAR_HEIGHT * 3 + HLMGame.BORDER_DISTANCE, 0, this.getWidth() - (TOOLBAR_HEIGHT * 3) - HLMGame.BORDER_DISTANCE, TOOLBAR_HEIGHT);
		this.statsLabel.setFont(new Font("Consolas", Font.PLAIN, 15));
		this.add(this.statsLabel);
		
		this.updateStats();
		
	}
	
	/**
	 * Sets the counters and stats to 0.
	 */
	public void resetCounters() {
		this.won = 0;
		this.skipped = 0;
		this.lost = 0;
	}
	
	public void addWon() {
		this.won++;
		this.updateStats();
	}
	
	public void addSkipped() {
		this.skipped++;
		this.updateStats();
	}
	
	public void addLost() {
		this.lost++;
		this.updateStats();
	}
	
	/**
	 * Used to update the stats label.
	 */
	private void updateStats() {
		this.statsLabel.setText("Won: " + this.won + ", Skipped: " + this.skipped + ", Lost: " + this.lost);
	}

	@Override
	public void mouseClicked(MouseEvent event) { }

	@Override
	public void mousePressed(MouseEvent event) { }

	@Override
	public void mouseReleased(MouseEvent event) {
		if (event.getSource().equals(this.skipButton)) {
			this.gameInstance.restart();
			this.addSkipped();
		}
		else if (event.getSource().equals(this.exitButton)) {
			System.exit(0);
		}
		else if (event.getSource().equals(this.settingsButton)) {
			if (this.gameInstance.settings.isVisible()) {
				this.gameInstance.settings.setVisible(false);
			}
			else {
				this.gameInstance.settings.setVisible(true);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent event) { }

	@Override
	public void mouseExited(MouseEvent event) { }
	
}
