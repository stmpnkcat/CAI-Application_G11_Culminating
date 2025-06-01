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
 * 
 * This Computer-Assisted Instruction teaches users about java methods in many engaging ways.
 * The concepts are presented in an interactive way using a typewriter animation as well as with diagrams.
 * Users can navigate through each topic in the concepts with the back and forward button. 
 * In the activity, the user can call methods using the code editor with feedback if there are errors in the code.
 * The goal of the activity is to call methods to move the character to the picnic basket. 
 * In the assessment, the user gets a variety of multiple choice, true/false, and fill in the blanks.
 * If the user gets a question wrong, they will get feedback on the correct answer.
 * 
 * Features:
 * 
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
 * - For the Activity frame, you must move the character into the picnic basket instead of one tile in front of it.
 * - In the Activity frame, inputting methods onto the same line will not work, as each line can only interpret one code.
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
