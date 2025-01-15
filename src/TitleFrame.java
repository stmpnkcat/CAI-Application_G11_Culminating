import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.Clip;
import javax.swing.*;

/*
 * This class creates the title page of the program
 * It allows the user to navigate to other portions of the program
 */
public class TitleFrame extends JFrame implements ActionListener{
	
	// Declare the swing components
	private JLabel headerLabel;
	private JLabel titleLabel;
	
	// Declare the background image label
	private JLabel imageLabel;
	
	// Declare the button panel
	private JPanel buttonPanel;
	
	// Declare the buttons
	private JButton conceptsButton;
	private JButton activityButton;
	private JButton assessmentButton;
	private JButton exitButton;

	// Play the background music
	private Clip clip = Utility.playSound("sounds/A Home for Flowers (Sunflower).wav", true);

	// The constructor called when the frame is created
	public TitleFrame() {

		// Format the frame
		Utility.formatFrame(this);
		setLayout(null);

		// Create the header label
		headerLabel = new JLabel("A Modular Programming CAI: ");
		headerLabel.setFont(Fonts.header);
		headerLabel.setForeground(new Color(0, 50, 50));
		
		// Add the header label
		headerLabel.setBounds(400, -100, 1440, 300);
		add(headerLabel);
		
		// Create the title label
		titleLabel = new JLabel("METHODS");
		titleLabel.setFont(Fonts.title);
		titleLabel.setForeground(new Color(0, 50, 50));
		
		// Add the title label
		titleLabel.setBounds(300, -120, 1440, 400);
		add(titleLabel);
		
		// Set the background of the panel
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
		buttonPanel.setOpaque(false);
		
		// Create the concepts button
		conceptsButton = new JButton("CONCEPTS");
		Utility.formatTitleButton(conceptsButton);
		conceptsButton.addActionListener(this);
		
		// Add the button to the panel
		buttonPanel.add(conceptsButton);
		
		// Create the activity button
		activityButton = new JButton("ACTIVITY");
		Utility.formatTitleButton(activityButton);
		activityButton.addActionListener(this);

		// Add the button to the panel
		buttonPanel.add(activityButton);
		
		// Create the assessment button
		assessmentButton = new JButton("ASSESSMENT");
		Utility.formatTitleButton(assessmentButton);
		assessmentButton.addActionListener(this);

		// Add the button to the panel
		buttonPanel.add(assessmentButton);
		
		// Create the exit button
		exitButton = new JButton("EXIT");
		Utility.formatTitleButton(exitButton);
		exitButton.addActionListener(this);

		// Add the button to the panel
		buttonPanel.add(exitButton);
		
		// Create the button panel
		buttonPanel.setBounds(100, 650, 1200, 100);
		add(buttonPanel);
		
		// Create the background image
		imageLabel = new JLabel(Icons.BASIL_PLUSH);
		imageLabel.setBounds(350, 100, 847, 1031);
		add(imageLabel);
		
		// Showing the frame
		setVisible(true);
		
	}
	
	// Method the close the title frame
	private void closeFrame() {
		
		// Close the frame and stop the background music
		dispose();
		clip.stop();
		
	}

	// This method is called whenever an action is performed
	@Override
	public void actionPerformed(ActionEvent e) {

		// Get the source of the action
		if (e.getSource() == conceptsButton) {
			
			// Play the select noise
			Utility.playSound("sounds/select.wav", false);
			
			// Close the frame
			closeFrame();
			
			// Open the concepts frame
			new ConceptsFrame();
			
		} else if (e.getSource() == activityButton) {
			
			// Play the select noise
			Utility.playSound("sounds/select.wav", false);
			
			// Close the frame
			closeFrame();
			
			// Open the activity frame
			new ActivityFrame();
			
		} else if (e.getSource() == assessmentButton) {
			
			// Play the select noise
			Utility.playSound("sounds/select.wav", false);
			
			// Close the frame
			closeFrame();
			
			// Open the assessment frame
			new AssessmentFrame();
			
		} else if (e.getSource() == exitButton) {
			
			// Play the select sound
			Utility.playSound("sounds/select.wav", false);
			
			// Close the program
			System.exit(0);
			
		}
		
	}
	
}
