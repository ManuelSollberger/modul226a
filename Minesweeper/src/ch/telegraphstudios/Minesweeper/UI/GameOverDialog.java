package ch.telegraphstudios.Minesweeper.UI;

import java.awt.Button;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.UIManager;

public class GameOverDialog extends MSDialog implements MouseListener {
	
	public static final String LOSE_STRING = "Uh-oh! That hurts.";
	public static final String WIN_STRING = "Well done mate.";
	
	private Button restartButton;
	private Button exitButton;
	private Label feedbackLabel;
	
	private MSView gameInstance;

	/**
	 * Also adds and instantiates the necessary subcomponents
	 */
	public GameOverDialog(MSView gameInstance) {
		super("Game Over!");
		
		this.gameInstance = gameInstance;
		
		this.setLayout(null);
		
		this.restartButton = new Button("Restart");
		this.restartButton.setBounds(BORDER_DISTANCE, DIALOG_HEIGHT - BORDER_DISTANCE - BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
		this.add(this.restartButton);
		this.restartButton.addMouseListener(this);
		
		this.exitButton = new Button("Exit");
		this.exitButton.setBounds(BORDER_DISTANCE * 2 + BUTTON_WIDTH, DIALOG_HEIGHT - BORDER_DISTANCE - BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
		this.add(this.exitButton);
		this.exitButton.addMouseListener(this);
		
		this.feedbackLabel = new Label();
		this.feedbackLabel.setBounds(BORDER_DISTANCE, DIALOG_HEIGHT / 2 - BUTTON_HEIGHT, DIALOG_WIDTH - (BORDER_DISTANCE * 2), BUTTON_HEIGHT);
		this.add(this.feedbackLabel);
		
	}
	
	public void setText(String text) {
		this.feedbackLabel.setText(text);
	}

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource().equals(this.exitButton)) {
			System.exit(0);
		}
		else if (e.getSource().equals(this.restartButton)) {
			this.gameInstance.startGame();
		}
	}
	
}
