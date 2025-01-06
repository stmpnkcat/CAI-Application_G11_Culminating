/*
 * 
 * Name: Daniel Cheng
 * 
 * Date: 2024/01/17
 * 
 * Course: ICS3U1-05 Mr.Fernandes
 * 
 * Title: Modular Programming - Method
 * 
 * Description:
 * 
 * 
 * 
 */


/*
 * This class acts as the application class for the project
 * It carries the global constants to be used by other classes
 * It setups the project and creates the title frame
 */
public class CAIApplication {
	
	// GUI constants
	public static final int SCREEN_WIDTH = 1440;
	public static final int SCREEN_HEIGHT = 810;

	// Dialogue constants
	public static final int TYPEWRITER_DURATION = 25;

	// ConceptsFrame constants
	public static final String ID_SEPARATOR = "^^^";
	public static final int ID_NEUTRAL = 0;
	public static final int ID_ANGRY = 1;
	
	// Board constants
	public static final int ROWS = 10;
	public static final int COLUMNS = 11;
	public static final int TILE_SIZE = 64;
	public static final int STEP_LENGTH = 4;
	public static final int TICK_DELAY = 10;
	public static final int MOVE_DELAY = 500;
	public static final int END_DELAY = 2000;
	public static final char ID_PLAYER = 'P';
	public static final char ID_WALL = 'X';
	public static final char ID_FINISH = 'F';

	// Main method called when program is run
	public static void main (String args[]) {
		
		// Create fonts
		Fonts.createFonts();
		
		// Create Title frame
		new TitleFrame();
		
	}
	
}
