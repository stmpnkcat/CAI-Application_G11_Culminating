import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

/*
 * This class creates the fonts for other classes to use
 * It has to set the font size for those classes to use
 */
public class Fonts {

	// Declare custom fonts
	public static Font handwriting;
	
	// Declare fonts
	public static Font header;
	public static Font title;
	public static Font button;
	public static Font rb;
	public static Font name;
	public static Font dialogue;
	public static Font code;
	
	// This method creates the fonts and is called at the start of the program
	public static void createFonts () {
		
		// Try/Catch for reading the file
		// https://stackoverflow.com/questions/5652344/how-can-i-use-a-custom-font-in-java
		try {
			
			// Create a new graphics environment for the font
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			
			// Set the font using the file
			handwriting = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/handwriting.ttf"));
			
			// Register the font using the graphics environment
			ge.registerFont(handwriting);
			
		} catch (IOException e) {
			
		    e.printStackTrace();
		    
		} catch(FontFormatException e) {
			
		    e.printStackTrace();
		    
		}
		
		// Create the fonts
		header = new Font(Font.MONOSPACED, Font.PLAIN, 40);
		title = handwriting.deriveFont(200f);
		button = new Font(Font.SANS_SERIF, Font.PLAIN, 40);
		rb = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
		name = new Font(Font.SANS_SERIF, Font.PLAIN, 40);
		dialogue = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
		code = new Font(Font.MONOSPACED, Font.PLAIN, 15);
		
	}
	
}
