package ch.telegraphstudios.HangLeMan.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ch.telegraphstudios.HangLeMan.HangLeManMain;
import ch.telegraphstudios.HangLeMan.Utils.Utils;

/**
 * This awesome swing component is responsible for the whole game
 * mechanism and also for displaying it.
 */
public class HLMGame extends JPanel implements Renderable, KeyListener {
	
	public static final Color GAME_BACKGROUND = new Color(0xF2F2F2);
	public static final int BORDER_DISTANCE = 16;

	public static final int MIN_WORD_SIZE = 5;
	public static final int MAX_WORD_SIZE = 12;
	public static final String VALID_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZÜÖÄ";
	
	private ArrayList<Renderable> children = new ArrayList<Renderable>();
	
	private JLabel wordLabel = new JLabel();
	private JLabel enteredLabel = new JLabel();
	private JLabel wordTitle = new JLabel("Le Wôrt:");
	private JLabel enteredTitle = new JLabel("Les Buchstabèn:");
	private JPanel divider = new JPanel();
	
	private HLMPodest podest;
	
	HLMToolbar toolbar;
	
	public String generatedWord;
	
	//Declare, instantiate and start the main render thread.
	private RenderThread renderThread = new RenderThread(this);
	
	public HLMGame() {
		this.setBackground(GAME_BACKGROUND);
		
		this.setFocusable(true);
		this.requestFocus();
		this.setLayout(null);
		
		this.podest = new HLMPodest(this);
		this.children.add(podest);
		
		this.addKeyListener(this);
		
		this.wordLabel.setBounds(BORDER_DISTANCE, (int)(HangLeManMain.GAME_HEIGHT * 0.25), HangLeManMain.GAME_WIDTH / 2 - BORDER_DISTANCE, (int)(HangLeManMain.GAME_HEIGHT * 0.75));
		this.wordLabel.setFont(new Font("Consolas", Font.PLAIN, 30));
		this.add(this.wordLabel);
		
		this.enteredLabel.setBounds((int)(HangLeManMain.GAME_WIDTH * 0.5) + BORDER_DISTANCE, (int)(HangLeManMain.GAME_HEIGHT * 0.25), HangLeManMain.GAME_WIDTH / 2 - BORDER_DISTANCE, (int)(HangLeManMain.GAME_HEIGHT * 0.75));
		this.enteredLabel.setFont(new Font("Consolas", Font.PLAIN, 40));
		this.add(this.enteredLabel);

		this.enteredTitle.setBounds(BORDER_DISTANCE + (int)(HangLeManMain.GAME_WIDTH * 0.5), (int)(HangLeManMain.GAME_HEIGHT * 0.25) + BORDER_DISTANCE, (int)(HangLeManMain.GAME_WIDTH * 0.5) - (BORDER_DISTANCE * 2), 32);
		this.enteredTitle.setFont(new Font("Consolas", Font.PLAIN, 20));
		this.add(this.enteredTitle);

		this.wordTitle.setBounds(BORDER_DISTANCE, (int)(HangLeManMain.GAME_HEIGHT * 0.25) + BORDER_DISTANCE, (int)(HangLeManMain.GAME_WIDTH * 0.5) - (BORDER_DISTANCE * 2), 32);
		this.wordTitle.setFont(new Font("Consolas", Font.PLAIN, 20));
		this.add(this.wordTitle);
		
		this.divider.setBounds((int)(HangLeManMain.GAME_WIDTH * 0.5), (int)(HangLeManMain.GAME_HEIGHT * 0.25), 1, HangLeManMain.GAME_HEIGHT - ((int)(HangLeManMain.GAME_HEIGHT * 0.25)));
		this.divider.setBackground(Color.BLACK);
		this.add(this.divider);
		
		this.restart();
	}
	
	public void setToolbar(HLMToolbar toolbar) {
		this.toolbar = toolbar;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		//Start the rendering tree.
		this.render(g);
	}

	@Override
	public Rectangle getBounds() {
		return super.getBounds();
	}

	@Override
	public void render(Graphics g) {
		for (Renderable child : children) {
			child.render(g);
		}
	}
	
	/**
	 * Type a character to check.
	 * @param character
	 */
	public void type(String character) {
		if (!this.enteredLabel.getText().contains(character)) {
			this.enteredLabel.setText(this.enteredLabel.getText() + " " + character);
			
			if (this.generatedWord.contains(character)) {
				for (int index = 0; index < this.generatedWord.length(); index++) {
					if (this.generatedWord.substring(index, index + 1).equals(character)) {
						this.wordLabel.setText(this.wordLabel.getText().substring(0, index) + character + this.wordLabel.getText().substring(index + 1, this.generatedWord.length()));
						
						//Check for win
						if (this.wordLabel.getText().equals(this.generatedWord)) {
							JOptionPane.showMessageDialog(this, "Dude! You did it! Have another try!", "So what?", JOptionPane.INFORMATION_MESSAGE);
							this.restart();
							this.toolbar.addWon();
						}
					}
				}
			}
			else {
				this.podest.stepForward();
			}
		}
	}
	
	/**
	 * Generate and clean a word.
	 */
	private void generateWord() {
		String words = Utils.getWebPageContent("https://de.wikipedia.org/wiki/Schweiz");
		words = Utils.stripTags(words.substring(4000, 5000));
		words = Utils.cleanString(words);
		
		String[] allWords = words.split(" ");
		
		String word = "";
		
		while (word == "") {
			int random = new Random().nextInt(allWords.length - 1);
			
			word = allWords[random];
			
			if (word.length() < MIN_WORD_SIZE || word.length() > MAX_WORD_SIZE) {
				word = "";
			}
			
			//Filter out these words of the javascript stuff.
			if (word.toUpperCase().startsWith("WG")) {
				word = "";
			}
			
			//Don't use the wikipedia stuff.
			if (word.toUpperCase().contains("WIKI")) {
				word = "";
			}
		}
		
		this.generatedWord = word.toUpperCase();
		
		//Set the entered string
		String enteredString = "";
		for (int i = 0; i < this.generatedWord.length(); i++) {
			enteredString += "-";
		}
		this.wordLabel.setText(enteredString);
	}

	@Override
	public void keyTyped(KeyEvent event) {
		this.type(("" + event.getKeyChar()).toUpperCase());
	}

	@Override
	public void keyPressed(KeyEvent event) { }

	@Override
	public void keyReleased(KeyEvent event) { }

	/**
	 * Resets the game
	 */
	public void restart() {
		this.enteredLabel.setText("<html>");
		this.podest.reset();
		
		this.generateWord();
	}
	
}
