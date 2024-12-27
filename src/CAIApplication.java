/*
 * 
 */


/*
 * This class acts as the application class for the project
 * It carries the global constants to be used by other classes
 * It setups the project and creates the title frame
 */
public class CAIApplication {

	// Store global constants
	public static final int SCREEN_WIDTH = 1440;
	public static final int SCREEN_HEIGHT = 810;
	public static final int TYPEWRITER_DURATION = 25;

	public static void main (String args[]) {
		
		// Create fonts
		Fonts.createFonts();
		
		// Create Title frame
		new TitleFrame();
		
	}
	
}
