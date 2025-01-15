import java.util.ArrayList;
import javax.sound.sampled.Clip;

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
 * - Interactive dialogue with typewriter effect and changing profile icons
 * - Pseudo "code" editor
 * - Smooth grid-based player movement
 * - Multiple activity levels
 * - Assessment questions presented in dialogue format
 * - Assessment feedback based on user answer
 * - Custom fonts
 * - Music
 * - File reading for easy content expansion
 * 
 * Major Skills:
 * - Basic swing GUI components
 * 		- frame, panel, label, button, text area, text field, radio button
 * - Other swing elements
 * 		- border factory, timer, layouts
 * - Data types
 * 		- int, float, string, char
 * - Data structures
 * 		- array, array list, double-ended queue
 * - Action listening
 * 		- action events, action listener
 * - Try/catch
 * 		- exceptions
 * - Objects
 * 		- Dialogue object
 * 		- Mover object
 * - Fonts
 * - Color
 * - Enumeration
 * - File reading
 * - Sound
 * 
 * Areas of Concern:
 * - Comic Sans MS may not be available due to the version of Java.
 * - In the Assessment frame, the dialogue next button may disappear, when this happens, click where the next button would be if it was visible.
 * - For the Assessment frame, you must move the character into the picnic basket instead of one tile in front of it.
 * 
 * Activity Solutions:

Level 1:

move(6);

Level 2:

move(3);
rotate_clockwise();
move(5);
rotate_clockwise();
move(4);

Level 3:

move(6);
rotate_clockwise();
move(5);
rotate_counter_clockwise();
move(1);

Level 4:

move(5);
rotate_clockwise();
move(5);
rotate_clockwise();
move(2);

Level 5:

move(3);
rotate_counter_clockwise();
move(2);
rotate_counter_clockwise();
move(2);
rotate_clockwise();
move(2);
rotate_counter_clockwise();
move(1);

 * Credits:
 * - Methods: https://www.geeksforgeeks.org/methods-in-java/
 * - Playing sound: https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
 * - Enumeration: https://www.w3schools.com/java/java_enums.asp
 * - Custom fonts: https://stackoverflow.com/questions/5652344/how-can-i-use-a-custom-font-in-java
 * - Sprites + sounds: OMORI
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
	public static final int DELAY_TYPEWRITER = 20;

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
	
	// List to hold the currently looping clips (sounds)
	public static ArrayList<Clip> clipList = new ArrayList<>();

	// Main method called when program is run
	public static void main (String args[]) {
		
		// Create fonts
		Fonts.createFonts();
		
		// Create Title frame
		new TitleFrame();
		
	}
	
}
