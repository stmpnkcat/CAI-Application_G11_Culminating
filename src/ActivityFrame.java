import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/*
 * This class creates the activity frame
 * The frame includes a mini-game to play by writing "code" which makes the character perform moves
 */
public class ActivityFrame extends JFrame implements ActionListener{

	// Declare the panels
	private JPanel backPanel;
	private JPanel codePanel;
	private JPanel runPanel;
	
	// Declare the components
	private JLabel codeLabel;
	private JTextArea codeTextArea;
	private JLabel runLabel;
	private JButton runButton;
	
	// Declare the current dialogue to store the current dialogue number
	private JButton currDialogue;
	
	// Create a new board
	private Board board;
	
	private Clip clip = null;
	
	// This constructor is called when a new activity frame is created
	public ActivityFrame() {
		
		// Format the frame
		Utility.formatFrame(this);
		setLayout(null);
		
		// Create the index button, used for retrieving the dialogue number
		currDialogue = new JButton();
		currDialogue.addActionListener(this);
		
		Utility.createQuickDialogue(this, currDialogue, "Help me get to the picnic basket by calling methods in the editor!", Icons.BASIL_PROFILE[1]);
		
		// Create the board
		board = new Board(this);
		
		// Add the board
		board.setBounds(50, 105, CAIApplication.COLUMNS * CAIApplication.TILE_SIZE + 10, 
				CAIApplication.ROWS * CAIApplication.TILE_SIZE + 10);
		add(board);
		
		// Create the code panel
		codePanel = new JPanel();
		Utility.formatPanel(codePanel);
		codePanel.setLayout(null);
		
		// Create the code area
		codeTextArea = new JTextArea();
		Utility.formatTextArea(codeTextArea);
		
		// Add the code area
		codeTextArea.setBounds(100, 300, 400, 200);
		codePanel.add(codeTextArea);
		
		// Create the code example label
		codeLabel = new JLabel();
		codeLabel.setIcon(Icons.TEMPLATE_CODE);
		
		// Add the code label
		codeLabel.setBounds(5, 5, 560, 540);
		codePanel.add(codeLabel);
		
		// Add the code panel
		codePanel.setBounds(800, 105, 570, 550);
		add(codePanel);
		
		// Create the run panel
		runPanel = new JPanel();
		runPanel.setOpaque(false);
		
		// Create the run label
		runLabel = new JLabel("RUN");
		runLabel.setForeground(Color.BLACK);
		runLabel.setFont(Fonts.button2);
		
		// Add the run label
		runPanel.add(runLabel);
		
		// Create the run button
		runButton = new JButton();
		runButton.setIcon(Utility.scaleImageIcon(Icons.ARROW_RIGHT, 100, 100));
		Utility.formatButton(runButton);
		runButton.addActionListener(this);
		
		// Add the run button
		runPanel.add(runButton);
		
		// Add the run panel
		runPanel.setBounds(1150, 660, 300, 100);
		add(runPanel);
		
		// Create the back panel
		backPanel = Utility.createBackPanel(this);
		
		// Add the back panel
		backPanel.setBounds(0, 0, 300, 100);
		add(backPanel);
		
		enableAll(false);
		
		// Show the frame
		setVisible(true);
		
	}

	// This method is called when an action occurs
	@Override
	public void actionPerformed(ActionEvent e) {

		// Check the action source
		if (e.getSource() == runButton) {
			
			Utility.playSound("sounds/select.wav", false);
			
			// Disable to run button
			runButton.setEnabled(false);
			
			// Get the user's code
			String text = codeTextArea.getText();
			String[] lines = text.split("\n"); // Convert to array of lines
			
			// Create a new commands deque
			Deque<Integer> commandsQueue = new ArrayDeque<>();
			
			// Create a variable for if the code is valid or not
			int validityID = 0;
			
			// Iterate through every line of the array
			for (String line : lines) {

				// Check if the line is the move command
				if ((line.indexOf("move(") == 0 || line.indexOf("move (") == 0)
						&& line.indexOf(");") == line.length() - 2) {
					
					// Declare a string for the valid numbers
					final String numbers = "1234567890";
					
					// Get the start index and the end index
					int startIndex = line.indexOf('(') + 1;
					int endIndex = line.lastIndexOf(')');
					
					// Declare the number string
					String numberString = "";
					
					// Loop through each character in the command argument
					for (int i = startIndex; i < endIndex; i++) {
						
						// Get the character at that position
						char currChar = line.charAt(i);
						
						// Check if the string contains a character that is not a character
						if (!numbers.contains("" + currChar)) 
							
							// Invalidate it with error code -1
							validityID = -1;
						
						else {
							
							// Add the current number to the string
							numberString += currChar;
							
						}
						
					}
					
					// Check if the number string is empty
					if (numberString.equals("")) 
						validityID = -2; // invalidate it with error code -2
					
					// Check if there are no errors
					else if (validityID == 0)
						commandsQueue.add(Integer.parseInt(numberString)); // Add the number to the command queue
				
				// Check if the next command is rotate clockwise
				} else if (line.indexOf("rotate_clockwise();") == 0 || line.indexOf("rotate_clockwise ();") == 0) {
					
					// Add the command
					commandsQueue.add(-2);
				
				// Check if the next command is rotate counter clockwise
				} else if (line.indexOf("rotate_counter_clockwise();") == 0 || line.indexOf("rotate_counter_clockwise ();") == 0) {
					
					// Add the command
					commandsQueue.add(-1);
					
				// Otherwise it is invalid
				} else {

					// Invalidate it with error code -3
					validityID = -3;
					
				}

			}
			
			// Check the validity ID
			switch (validityID) {
			
			case 0: // It is valid
				
				// Run the commands
				board.run(commandsQueue);
				return;
				
			case -1: // Invalid: letters in argument
				
				// Disable run button
				enableAll(false);
				
				// Create a new dialogue
				Utility.createQuickDialogue(this, currDialogue, "your code doesn't work, maybe you put letters instead of numbers in the move arguments?", Icons.BASIL_PROFILE[2]);
				
				break;
				
			case -2: // Invalid: missing argument

				// Disable run button
				enableAll(false);
				
				// Create a new dialogue
				Utility.createQuickDialogue(this, currDialogue, "your code doesn't work, i think you need to put arguments in the move command.", Icons.BASIL_PROFILE[2]);
				
				break;
				
			case -3: // Invalid: typo

				// Disable run button
				enableAll(false);
				
				// Create a new dialogue
				Utility.createQuickDialogue(this, currDialogue, "your code doesn't work, maybe you made a typo or something.", Icons.BASIL_PROFILE[2]);
				
				break;
			
			}
			
			Utility.playSound("sounds/buzzer.wav", false);
			
		} else if (e.getSource() == currDialogue) {
			
			// Get the current index
			int currIndex = Integer.parseInt(currDialogue.getText());
			
			// Check if there are no more dialogue
			if (currIndex == -1) {
				
				// Load the level if the board is finished
				if (board.isFinished()) {
					
					if (CAIApplication.activityLevel > 5) {
						
						Utility.createQuickDialogue(this, currDialogue, 
								"Congrats! You finished the activity, you can play again or check out the assessment.", 
								Icons.BASIL_PROFILE[1]);
						
						CAIApplication.activityLevel = 1;
						
					} else {

						codeTextArea.setText("");
						board.loadLevel();
						
					}
					
				}
				
				// Enable the run button
				enableAll(true);
				
			}
			
		}
		
	}
	
	// This method enables all the interactive components
	public void enableAll(boolean bool) {
		
		runButton.setEnabled(bool);
		codeTextArea.setEnabled(bool);
		
	}

	public JButton getCurrDialogue() {
		
		return currDialogue;
		
	}
	
	public Clip getClip() {
		return clip;
	}
	
	public void setClip(String soundFile) {

		if (clip != null)
			clip.stop();
		clip = Utility.playSound(soundFile, true);
		
	}
	
}
