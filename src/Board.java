import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.Scanner;

import javax.swing.*;

public class Board extends JPanel implements ActionListener {

	private final int WIDTH = CAIApplication.COLUMNS * CAIApplication.TILE_SIZE;
	private final int HEIGHT = CAIApplication.ROWS * CAIApplication.TILE_SIZE;
	
	private ActivityFrame frame;
	
	private JLabel backgroundLabel;
	
	private Mover player;
	
	private Timer runTimer;
	private Timer endTimer;

	private int level;
	private boolean isFinished;
	private char[][] levelMatrix;
	
	private Deque<Integer> commandsQueue;
	
	public Board(ActivityFrame frame) {
		
		this.frame = frame;
		
		runTimer = new Timer(CAIApplication.MOVE_DELAY, this);
		endTimer = new Timer(CAIApplication.END_DELAY, this);

		levelMatrix = new char[CAIApplication.ROWS][CAIApplication.COLUMNS];

		Utility.formatPanel(this);
		setLayout(null);
		
		backgroundLabel = new JLabel();

		level = 1;
		loadLevel();
		
		backgroundLabel.setBounds(5, 5, WIDTH, HEIGHT);
		add(backgroundLabel);
		
	}
	
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
					
					levelMatrix[row][column] = lineArray[column];
					
					// Set the collision
					if (lineArray[column] == CAIApplication.ID_PLAYER) {
						
						if (level == 1) {

							player = new Mover(column, row, 3);
							add(player);
							
						} else if (level == 2){
							
							player.setNewSpawn(column, row, 1);
							
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
	
	public void loadLevel() {
		
		loadBoard("levels/level_" + level + ".txt");
		
		backgroundLabel.setIcon(Utility.scaleImageIcon(Icons.LEVEL[level - 1], WIDTH, HEIGHT));
		
	}

	public void run(Deque<Integer> commandsQueue) {
		
		System.out.println(player.getNextRow() + " " + player.getNextColumn());
		
		this.commandsQueue = commandsQueue;
		
		nextCommand();

		runTimer.start();
		
	}

	private void nextCommand() {
		
		int command = commandsQueue.poll();
		
		if (command == -2) {
			
			player.rotateClockwise();
			
		} else if (command == -1) {
		
			player.rotateCounterClockwise();
			
		} else if (command < 12 && player.getNextColumn() > 0 && player.getNextRow() > 0 && 
				player.getNextColumn() < CAIApplication.COLUMNS && player.getNextRow() < CAIApplication.ROWS){
			
			if (levelMatrix[player.getNextRow()][player.getNextColumn()] == CAIApplication.ID_FINISH) {
				
				frame.enableAll(false);
				
				level++;

				Utility.createQuickDialogue(frame, frame.getCurrIndexButton(), "yay you won!", Icons.BASIL_NEUTRAL);
				
				player.updateSprite();
				
				runTimer.stop();

				isFinished = true;
				
			}
			else if (levelMatrix[player.getNextRow()][player.getNextColumn()] != CAIApplication.ID_WALL)
				player.move();

			if (command > 1) 
				commandsQueue.addFirst(command - 1);
			
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == runTimer) {
			
			if (commandsQueue.isEmpty()) {

				player.updateSprite();
				runTimer.stop();
				endTimer.start();
				return;
				
			}
			
			nextCommand();
			
		} else if (e.getSource() == endTimer) {
			
			frame.enableAll(true);
			endTimer.stop();
			player.reset();
			
		}
		
	}
	
	public boolean isFinished() {
		return isFinished;
	}
	
}
