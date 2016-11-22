package ch.telegraphstudios.HangLeMan.Utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import ch.telegraphstudios.HangLeMan.GUI.HLMGame;

/**
 * This class contains methods that might be useful at some point.
 */
public class Utils {
	
	private static HashMap<String, String> cache = new HashMap<String, String>();

	/**
	 * @return the size of the main screen in pixels.
	 */
	public static Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
	public static String cleanString(String string) {
		int cleanStep = 1;
		
		String newString = "";
		
		for (char character : string.toCharArray()) {
			if (HLMGame.VALID_CHARACTERS.contains(("" + character).toUpperCase())) {
				newString += character;
			}
			else {
				newString += " ";
			}
			
			cleanStep++;
		}
		
		while (newString.contains("  ")) {
			newString = newString.replace("  ", " ");
		}
		
		return newString;
	}
	
	public static String getWebPageContent(String urlString) {
		URL url;
	    InputStream is = null;
	    BufferedReader br;
	    String line;
	    String wholePage = "";

	    if (!cache.containsKey(urlString)) {
	    	try {
		        url = new URL(urlString);
		        is = url.openStream();
		        br = new BufferedReader(new InputStreamReader(is));

		        while ((line = br.readLine()) != null) {
		            wholePage += line;
		        }
		    } catch (MalformedURLException mue) {
		         mue.printStackTrace();
		    } catch (IOException ioe) {
		         ioe.printStackTrace();
		    } finally {
		        try {
		            if (is != null) is.close();
		        } catch (IOException ioe) {
		        }
		    }
	    	
	    	cache.put(urlString, wholePage);
	    }
	    else {
	    	wholePage = cache.get(urlString);
	    }
		
		return wholePage;
	}
	
	public static String stripTags(String string) {
		while (string.contains("<") && string.contains(">")) {
			int firstIndex = string.indexOf("<");
			int firstEndIndex = string.indexOf(">");
			
			string = string.substring(0, firstIndex) + string.substring(firstEndIndex + 1, string.length() - 1);
		}
		
		return string;
	}
	
}
