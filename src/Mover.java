import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Mover extends JLabel implements ActionListener{

	private Timer tickTimer;
	
	private int defaultRow;
	private int defaultColumn;
	private int defaultDirection;
	
	private int row;
	private int column;
	
	private int dRow;
	private int dColumn;
	
	private int direction;

	// Constructor for when mover is initialized
	public Mover(int column, int row, int direction) {
		super();
		
		tickTimer = new Timer(CAIApplication.TICK_DELAY, this);

		// Setting the fields
		defaultRow = row;
		defaultColumn = column;
		defaultDirection = direction;

		reset();
		
	}
	
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
	
	public void updateSprite() {
		
		setIcon(Utility.scaleImageIcon(Icons.BASIL_IDLE[direction],
				CAIApplication.TILE_SIZE, CAIApplication.TILE_SIZE));
		
	}
	
	public void reset() {
		
		row = defaultRow;
		column = defaultColumn;
		direction = defaultDirection;
		
		setBounds(column * CAIApplication.TILE_SIZE, row * CAIApplication.TILE_SIZE, 
				CAIApplication.TILE_SIZE, CAIApplication.TILE_SIZE);
		
		updateSprite();
		
	}
	
	public void move() {

		setIcon(Utility.scaleImageIcon(Icons.BASIL_WALK[direction],
				CAIApplication.TILE_SIZE, CAIApplication.TILE_SIZE));
		
		setDisplacement();
		
		tickTimer.start();
		
	}
	
	public void rotateClockwise() {
		
		direction++;
		if (direction > 3) direction -= 4;
		
		updateSprite();
		
	}
	
	public void rotateCounterClockwise() {

		direction--;
		if (direction < 0) direction += 4;
		
		updateSprite();
		
	}

	
	// This method gets the next row based on the delta values
	public int getNextRow() {
		
		return row + dRow;
		
	}
	
	// This method gets the next column based on the delta values
	public int getNextColumn() {
		
		return column + dColumn;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		int currentX = getX();
		int currentY = getY();

		int targetX = (column + dColumn) * CAIApplication.TILE_SIZE;
		int targetY = (row + dRow) * CAIApplication.TILE_SIZE;
		
		if (currentX < targetX) currentX += CAIApplication.STEP_LENGTH;
		else if (currentX > targetX) currentX -= CAIApplication.STEP_LENGTH;
		if (currentY < targetY) currentY += CAIApplication.STEP_LENGTH;
		else if (currentY > targetY) currentY -= CAIApplication.STEP_LENGTH;
		
		setBounds(currentX, currentY, CAIApplication.TILE_SIZE,
				CAIApplication.TILE_SIZE);

		if (currentX == targetX && currentY == targetY) {
			
			row += dRow;
			column += dColumn;
			
			tickTimer.stop();
			
		}
		
	}
	
}
