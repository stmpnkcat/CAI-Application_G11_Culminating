import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.Scanner;

import javax.swing.*;

/*
 * This class creates the minigame board
 * The panel includes the background and the player
 */
public class Board extends JPanel implements ActionListener {

	// Get the width and height from the main class
	private final int WIDTH = CAIApplication.COLUMNS * CAIApplication.TILE_SIZE;
	private final int HEIGHT = CAIApplication.ROWS * CAIApplication.TILE_SIZE;
	
	// Declare parent frame
	private ActivityFrame frame;
	
	// Declare background label
	private JLabel backgroundLabel = new JLabel();
	
	// Declare the player mover
	private Mover player;
	
	// Create the run and end timer
	private Timer runTimer = new Timer(CAIApplication.DELAY_MOVE, this);
	private Timer endTimer = new Timer(CAIApplication.DELAY_END, this);
	
	// Declare variable for if the level is finished
	private boolean isFinished;
	
	// Declare matrix for level data
	private char[][] levelMatrix = new char[CAIApplication.ROWS][CAIApplication.COLUMNS];;
	
	// Declare deque for commands
	private Deque<Integer> commandsQueue;
	
	// This constructor is called when a new board is created
	public Board(ActivityFrame frame) {
		
		// Set fields
		this.frame = frame;

		// Format the frame
		Utility.formatPanel(this);
		setLayout(null);
		loadLevel();
		
		// Add the background label
		backgroundLabel.setBounds(5, 5, WIDTH, HEIGHT);
		add(backgroundLabel);
		
	}
	
	// This method reads the level from a file
	private void loadBoard(String filePath) {
		
		// Declaring the row variable to keep track of the row number while traversing the file
		int row = 0;
		
		// Declaring the scanner
		Scanner inputFile;
		
		// Trying to get the image, if it fails, send error message
		try {
			
			// Setting the scanner to read the file location given
			inputFile = new Scanner(new File(filePath));
			
			// Iterate through the rows of the file
			while (inputFile.hasNext()) {
				
				// Create a char array of the current row
				char[] lineArray = inputFile.nextLine().toCharArray();

				// Iterate through every character in the current row
				for (int column = 0; column < lineArray.length; column++) {
					
					// Set the matrix char to the line char
					levelMatrix[row][column] = lineArray[column];
					
					// Check if current char is player
					if (lineArray[column] == CAIApplication.ID_PLAYER) {
						
						// Declare direction variable
						int dir = 0;
							
						// Change dir according to the current level
						switch (CAIApplication.activityLevel) {
						case 1:
							dir = 3;
							break;
						case 2:
							dir = 3;
							break;
						case 3:
							dir = 1;
							break;
						case 4:
							dir = 2;
							break;
						case 5:
							dir = 3;
							break;
						}
						
						// Check if player doesn't exist yet
						if (player == null) {
							
							// Add a new player
							player = new Mover(column, row, dir);
							add(player);
							
						} else {
							
							// Set the preexisting player
							player.setNewSpawn(column, row, dir);
							
						}
						
					}
						
				}
				
				// Increment the row number, as if it were a for-loop
				row++;
				
			}
			
			// Close the input file
			inputFile.close();
			
		// If the file cannot be found, return an error message.
		} catch (FileNotFoundException error) {
			
			System.out.println("File not found");
			
		}
		
	}
	
	// This method loads the level image
	public void loadLevel() {
		
		isFinished = false;
		
		// Load the board first
		loadBoard("levels/level_" + CAIApplication.activityLevel + ".txt");

		// Set the background image
		backgroundLabel.setIcon(Utility.scaleImageIcon(Icons.LEVEL[CAIApplication.activityLevel - 1], WIDTH, HEIGHT));
		
		String soundFile = "sounds/";
		switch(CAIApplication.activityLevel) {
		case(1):
			soundFile += "Trees....wav";
			break;
		case(2):
			soundFile += "Stardust Diving.wav";
			break;
		case(3):
			soundFile += "I Definitely Promised You A Rose Garden.wav";
			break;
		case(4):
			soundFile += "H20_HCL.wav";
			break;
		case(5):
			soundFile += "Fade.wav";
			break;
		}

		frame.setClip(soundFile);
		
	}

	// This method runs the command deque
	public void run(Deque<Integer> commandsQueue) {
		
		// Set the deque field
		this.commandsQueue = commandsQueue;
		
		// Commence the first command
		nextCommand();

		// Start the run timer
		runTimer.start();
		
	}
	
	// This method starts the next command
	private void nextCommand() {
		
		// Get the next command id
		int command = commandsQueue.poll();
		
		// Check if the command is to rotate clockwise
		if (command == -2) {
			
			player.rotateClockwise();
			
		// Check if the command is the rotate counter clockwise
		} else if (command == -1) {
		
			player.rotateCounterClockwise();
			
		// Check if the player is able to move to the next column
		} else if (player.getNextColumn() > 0 && player.getNextRow() > 0 && 
				player.getNextColumn() < CAIApplication.COLUMNS && player.getNextRow() < CAIApplication.ROWS){
			
			// Check if the player reaches the finish
			if (levelMatrix[player.getNextRow()][player.getNextColumn()] == CAIApplication.ID_FINISH) {
				
				Utility.playSound("sounds/save.wav", false);
				
				// Disable the run button
				frame.enableAll(false);
				
				// Increase the level
				CAIApplication.activityLevel++;

				// Create a quick dialogue
				Utility.createQuickDialogue(frame, frame.getCurrDialogue(), "yay you won!", Icons.BASIL_PROFILE[1]);
				
				// Reset the player image to idle
				player.updateSprite();
				
				// Stop the run timer
				runTimer.stop();

				// Set the finished variable to true
				isFinished = true;
				
			}
			
			// Check if the next space is not a wall
			else if (levelMatrix[player.getNextRow()][player.getNextColumn()] != CAIApplication.ID_WALL)
				player.move();

			// Check if there are more move commands
			if (command > 1) 
				commandsQueue.addFirst(command - 1); // Decrease the move command by one
			
		}
		
	}
	
	// This method is called when an action is performed
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Get the source of the action
		if (e.getSource() == runTimer) {
			
			// Check if there are no more commands
			if (commandsQueue.isEmpty()) {

				player.updateSprite();
				runTimer.stop();
				endTimer.start(); // Start the end timer

			// Otherwise, run the next command
			} else {
			
				nextCommand();
			
			}
			
		} else if (e.getSource() == endTimer) {
			
			// Enable the run button and reset the board
			frame.enableAll(true);
			endTimer.stop();
			player.reset();
			
		}
		
	}
	
	// Getter for is finished
	public boolean isFinished() {
		return isFinished;
	}
	
}
