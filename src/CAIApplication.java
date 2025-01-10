
/*
 * 
 * Name: Daniel Cheng
 * 
 * Date: 2024/01/17
 * 
 * Course: ICS3U1-05 Mr.Fernandes
 * 
 * Title: Modular Programming - Methods
 * 
 * Description:
 * This Computer-Assisted Instruction teaches users about java methods in many engaging ways.
 * 
 * Features:
 * - Interactive way of presenting information through dialogue
 * - Custom dialogue with typewriter effect
 * - Pseudo "code" editor
 * - Smooth grid-based movement of player
 * - Assessment questions presented in dialogue format
 * - Music
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
	public static final int TYPEWRITER_DURATION = 10;

	// ConceptsFrame constants
	public static final char ID_SEPARATOR = '~';
	
	// Board constants
	public static final int ROWS = 10;
	public static final int COLUMNS = 11;
	public static final int TILE_SIZE = 64;
	public static final int STEP_LENGTH = 4;
	public static final int DELAY_TICK = 10;
	public static final int DELAY_MOVE = 500;
	public static final int DELAY_END = 2000;
	public static final char ID_PLAYER = 'P';
	public static final char ID_WALL = 'X';
	public static final char ID_FINISH = 'F';
	
	// Variables to hold the progress of the user
	public static int conceptsLevel = 0;
	public static int activityLevel = 1;
	public static int assessmentLevel = 0;

	// Main method called when program is run
	public static void main (String args[]) {
		
		// Create fonts
		Fonts.createFonts();
		
		// Create Title frame
		new TitleFrame();
		
	}
	
}
