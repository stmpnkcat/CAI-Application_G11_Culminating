import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/*
 * This class creates a mover on the board
 * It moves a jlabel across the screen and changes the image
 */
public class Mover extends JLabel implements ActionListener{

	// Declare the tick timer
	private Timer tickTimer = new Timer(CAIApplication.DELAY_TICK, this);
	
	// Declare the default variables
	private int defaultRow;
	private int defaultColumn;
	private int defaultDirection;
	
	// Declare the current variables
	private int row;
	private int column;
	private int direction;
	
	// Declare the delta variables
	private int dRow;
	private int dColumn;

	// Constructor for when mover is initialized
	public Mover(int column, int row, int direction) {
		super(); // Used to create the object

		// set the spawn
		setNewSpawn(column, row, direction);
		
	}
	
	// This method sets the delta using the direction
	// Retrieved from the PacMan project
	private void setDisplacement() {
		
		// Resetting the delta values
		dRow = 0;
		dColumn = 0;
		
		// Changing the delta values based on the direction
		if (direction == 0)
			dColumn = -1;
		else if (direction == 1)
			dRow = -1;
		else if (direction == 2)
			dColumn = 1;
		else if (direction == 3)
			dRow = 1;
		
	}

	// This method updates the sprite to idle
	public void updateSprite() {
		
		// Set the icon based on the direction
		setIcon(Utility.scaleImageIcon(Icons.BASIL_IDLE[direction],
				CAIApplication.TILE_SIZE, CAIApplication.TILE_SIZE));
		
	}
	
	// This method resets the player to default position
	public void reset() {
		
		// Reset the fields
		row = defaultRow;
		column = defaultColumn;
		direction = defaultDirection;
		dRow = 0;
		dColumn = 0;
		
		// Reset the physical position
		setBounds(column * CAIApplication.TILE_SIZE, row * CAIApplication.TILE_SIZE, 
				CAIApplication.TILE_SIZE, CAIApplication.TILE_SIZE);
		
		// Set idle sprite
		updateSprite();
		
	}
	
	// This method moves the player
	public void move() {

		// Set the image of the player
		setIcon(Utility.scaleImageIcon(Icons.BASIL_WALK[direction],
				CAIApplication.TILE_SIZE, CAIApplication.TILE_SIZE));
		
		// Set the displacement of the player
		setDisplacement();
		
		// Start the tick timer to move the player
		tickTimer.start();
		
	}
	
	// This method rotates the player
	public void rotateClockwise() {
		
		// Increase the direction
		direction++;
		if (direction > 3) direction -= 4; // Keep the direction between 0-3
		
		// Update to idle
		updateSprite();
		
		setDisplacement();
		
	}
	
	// This method rotates the player counter clockwise
	public void rotateCounterClockwise() {

		// Decrease the direction
		direction--;
		if (direction < 0) direction += 4; // Keep the direction between 0-3
		
		// Update to idle
		updateSprite();
		
		setDisplacement(); 
		
	}

	// This method is called when the tick timer goes off
	@Override
	public void actionPerformed(ActionEvent e) {

		// Get the current position
		int currentX = getX();
		int currentY = getY();

		// Get the target position
		int targetX = (column + dColumn) * CAIApplication.TILE_SIZE;
		int targetY = (row + dRow) * CAIApplication.TILE_SIZE;
		
		// Move in the direction where it needs to go
		if (currentX < targetX) currentX += CAIApplication.STEP_LENGTH;
		else if (currentX > targetX) currentX -= CAIApplication.STEP_LENGTH;
		if (currentY < targetY) currentY += CAIApplication.STEP_LENGTH;
		else if (currentY > targetY) currentY -= CAIApplication.STEP_LENGTH;
		
		// Change the physical position of the mover
		setBounds(currentX, currentY, CAIApplication.TILE_SIZE,
				CAIApplication.TILE_SIZE);

		// Check if it is on the target position
		if (currentX == targetX && currentY == targetY) {
			
			// Increase the fields
			row += dRow;
			column += dColumn;
			
			// Stop the timer
			tickTimer.stop();
			
		}
		
	}
	
	// This method sets the spawn of the player
	public void setNewSpawn(int column, int row, int direction) {
		
		// Set the fields
		defaultRow = row;
		defaultColumn = column;
		defaultDirection = direction;

		// Reset the position of the player
		reset();
		
	}
	
	// This method gets the next row based on the delta values
	public int getNextRow() {
		
		return row + dRow;
		
	}
	
	// This method gets the next column based on the delta values
	public int getNextColumn() {
		
		return column + dColumn;
		
	}
	
	// Getters and setters
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
}
