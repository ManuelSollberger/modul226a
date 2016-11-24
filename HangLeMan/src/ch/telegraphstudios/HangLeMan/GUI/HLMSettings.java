package ch.telegraphstudios.HangLeMan.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ch.telegraphstudios.HangLeMan.Settings;
import ch.telegraphstudios.HangLeMan.Utils.Utils;

/**
 * This panel provides settings to the player.
 * This is the first attempt to implement a multiton-class.
 */
public class HLMSettings extends JPanel implements MouseListener {
	
	private static final Color SETTINGS_BACKGROUND = new Color(0xDEDEFF);
	
	private static final int SETTINGS_WIDTH = 480;
	private static final int SETTINGS_HEIGHT = 360;
	private static final int GUI_WIDTH = 64;
	private static final int GUI_HEIGHT = 24;
	private static final int BORDER_DISTANCE = 16;
	
	private static HashMap<HLMGame, HLMSettings> instances = new HashMap<HLMGame, HLMSettings>();
	
	private JTextArea sourceArea = new JTextArea(12, 50);
	private JScrollPane scrollPane = new JScrollPane(sourceArea);
	private HLMButton closeButton;
	private JLabel sourceTitle = new JLabel("Sources and words: (Enter URLs or single words)");
	
	private HLMSettings() {
		this.setBackground(SETTINGS_BACKGROUND);
		this.setSize(SETTINGS_WIDTH, SETTINGS_HEIGHT);
		
		this.setVisible(false);
		this.setLayout(null);
		
		this.closeButton = new HLMButton("Buttons/back.png");
		this.closeButton.setBounds(SETTINGS_WIDTH - GUI_WIDTH, SETTINGS_HEIGHT - GUI_WIDTH, GUI_WIDTH, GUI_WIDTH);
		this.closeButton.addMouseListener(this);
		this.add(this.closeButton);
		
		this.sourceTitle.setBounds(BORDER_DISTANCE, BORDER_DISTANCE, SETTINGS_WIDTH - (BORDER_DISTANCE * 2), GUI_HEIGHT);
		this.sourceTitle.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.add(this.sourceTitle);
		
		this.sourceArea.setText("Herzlich willkommen auf dem Internetauftritt der Gemeinde Studen Auf dieser Website finden Sie alles Wichtige rund um Studen Sollten Sie weitergehende Fragen haben zögern Sie nicht und kontaktieren Sie die Gemeindeverwaltung welche Ihnen gerne weiterhilft Wir wünschen Ihnen viele lehrreiche Momente und viel Vergnügen auf unserer Website");
		this.scrollPane.setLocation(BORDER_DISTANCE, BORDER_DISTANCE * 2 + GUI_HEIGHT);
		this.scrollPane.setSize(SETTINGS_WIDTH - (BORDER_DISTANCE * 2), SETTINGS_HEIGHT - (BORDER_DISTANCE * 3) - GUI_WIDTH - GUI_HEIGHT);
		this.add(this.scrollPane);
	}
	
	public static HLMSettings getInstance(HLMGame gameInstance) {
		if (instances.containsKey(gameInstance)) {
			return instances.get(gameInstance);
		}
		else {
			HLMSettings newInstance = new HLMSettings();
			instances.put(gameInstance, newInstance);
			return newInstance;
		}
	}
	
	public void loadWords() {
		ArrayList<String> loadedWords = new ArrayList<String>();
		
		String allWordsString = this.sourceArea.getText();
		allWordsString = allWordsString.replaceAll("\n", " ");
		String[] words = allWordsString.split(" ");
		for (String word : words) {
			if (word.startsWith("http://") || word.startsWith("https://")) {
				//Load this web page.
				String webWords = Utils.getWebPageContent(word);
				
				//Clean the words.
				if (webWords.length() > 3000) {
					webWords = webWords.substring(3000);
				}
				
				if (webWords.length() > 3000) {
					webWords = webWords.substring(0, 3000);
				}
				
				webWords = Utils.stripTags(webWords);
				webWords = Utils.cleanString(webWords);
				
				String[] allWords = webWords.split(" ");
				
				for (int i = 0; i < allWords.length; i++) {
					String webWord = allWords[i];
					
					if (webWord.length() < HLMGame.MIN_WORD_SIZE || webWord.length() > HLMGame.MAX_WORD_SIZE) {
						webWord = "";
					}
					
					//Filter out these words of the javascript stuff.
					if (webWord.toUpperCase().startsWith("WG")) {
						webWord = "";
					}
					
					//Don't use the wikipedia stuff.
					if (webWord.toUpperCase().contains("WIKI")) {
						webWord = "";
					}
					
					if (!webWord.equals("")) {
						loadedWords.add(webWord);
					}
				}
			}
			else {
				word = Utils.cleanString(word);
				
				if (word.length() >= HLMGame.MIN_WORD_SIZE && word.length() <= HLMGame.MAX_WORD_SIZE) {
					loadedWords.add(word);
				}
			}
		}
		
		Settings.clearWords();
		Settings.addWords(loadedWords);
	}

	@Override
	public void mouseClicked(MouseEvent event) { }

	@Override
	public void mousePressed(MouseEvent event) { }

	@Override
	public void mouseReleased(MouseEvent event) {
		if (event.getSource().equals(this.closeButton)) {
			this.setVisible(false);
			
			//Load words and URLs
			this.loadWords();
		}
	}

	@Override
	public void mouseEntered(MouseEvent event) { }

	@Override
	public void mouseExited(MouseEvent event) { }
	
}
