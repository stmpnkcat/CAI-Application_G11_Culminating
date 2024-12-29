import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * This class creates the title page of the program
 * It allows the user to navigate to other portions of the program
 */
public class TitleFrame extends JFrame implements ActionListener{
	
	// Declaring the swing components
	private JLabel headerLabel;
	private JLabel titleLabel;
	private JPanel buttonPanel;
	private JButton conceptsButton; 
	private JButton activityButton;
	private JButton assessmentButton;
	private JButton exitButton;

	// The constructor called when the frame is created
	public TitleFrame() {

		// Format the frame
		Utility.formatFrame(this);
		setLayout(null);

		// Create the header label
		headerLabel = new JLabel("A Modular Programming CAI: ");
		headerLabel.setFont(Fonts.header);
		headerLabel.setForeground(Color.BLACK);
		
		headerLabel.setBounds(250, -100, 1440, 300);
		add(headerLabel);
		
		// Create the title label
		titleLabel = new JLabel("METHODS");
		titleLabel.setFont(Fonts.title);
		titleLabel.setForeground(Color.BLACK);
		
		titleLabel.setBounds(50, -120, 1440, 400);
		add(titleLabel);
		
		// Set the background of the panel
		buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(0, 255, 100));
		
		// Create the concepts button
		conceptsButton = new JButton("CONCEPTS");
		Utility.formatButton(conceptsButton);
		conceptsButton.addActionListener(this);
		
		buttonPanel.add(conceptsButton);
		
		// Create the activity button
		activityButton = new JButton("ACTIVITY");
		Utility.formatButton(activityButton);
		activityButton.addActionListener(this);
		
		buttonPanel.add(activityButton);
		
		// Create the assessment button
		assessmentButton = new JButton("ASSESSMENT");
		Utility.formatButton(assessmentButton);
		assessmentButton.addActionListener(this);
		
		buttonPanel.add(assessmentButton);
		
		// Create the exit button
		exitButton = new JButton("EXIT");
		Utility.formatButton(exitButton);
		exitButton.addActionListener(this);
		
		buttonPanel.add(exitButton);
		
		// Create the button panel
		buttonPanel.setBounds(1000, 400, 300, 500);
		add(buttonPanel);
		
		// Showing the frame
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Get the source of the action
		if (e.getSource() == conceptsButton) {
			
			dispose();
			
			// Open the concepts frame
			new ConceptsFrame();
			
		} else if (e.getSource() == activityButton) {
			
			dispose();
			
			// Open the activity frame
			new ActivityFrame();
			
		} else if (e.getSource() == assessmentButton) {
			
			dispose();
			
			// Open the assessment frame
			new AssessmentFrame();
			
		} else if (e.getSource() == exitButton) {
			
			// Close the program
			System.exit(0);
			
		}
		
	}
	
}
