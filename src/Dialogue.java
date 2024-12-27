import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Dialogue implements ActionListener {

	// Declare panels to be used by other classes
	private JPanel profilePanel = new JPanel();
	private JPanel namePanel = new JPanel();
	private JPanel dialoguePanel = new JPanel();
	
	// Declare the swing components held by the panels
	private JLabel profileLabel = new JLabel();
	private JLabel dialogueLabel = new JLabel();
	private JLabel nameLabel = new JLabel();
	private JButton nextButton = new JButton();
	private JButton backButton = new JButton();
	
	// Create the timer for the typewriter effect
	private Timer typewriterTimer = new Timer(CAIApplication.TYPEWRITER_DURATION, this);
	
	// Create a variable to hold the current dialogue number
	private int currDialogue = 0;
	
	// Create a list to hold the dialogue list
	private ArrayList<String> dialogueList;
	private char charIndex = 0; // Variable to hold current char index
	
	// Create a variable to hold the profile icon list
	private ArrayList<ImageIcon> profileIconList;
	
	// Constructor that is called when a new dialogue is created
	public Dialogue(ArrayList<String> dialogueList, ArrayList<ImageIcon> profileIconList) {
		
		// Set fields
		this.dialogueList = dialogueList;
		this.profileIconList = profileIconList;
		
		// Scale the profile icon
		profileIcon = Utility.scaleImageIcon(profileIcon, 150, 150);
		
		// Create the profile panel
		Utility.formatPanel(profilePanel);
		profilePanel.setLayout(new BorderLayout());
		
		// Create the profile label
		profileLabel.setIcon(profileIcon);
		profileLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); // Add a border to center the image in the panel
		
		// Add the profile image
		profilePanel.add(profileLabel, BorderLayout.SOUTH); // Put the image at the bottom of the panel

		// Create the name panel
		Utility.formatPanel(namePanel);
		
		// Create the name label
		nameLabel.setText("BASIL");
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(Fonts.nameFont);
		
		// Add the name label
		namePanel.add(nameLabel);
		
		// Create the dialogue panel
		Utility.formatPanel(dialoguePanel);
		dialoguePanel.setLayout(null);
		
		// Create the dialogue label
		dialogueLabel.setText("<html></html>"); // https://stackoverflow.com/questions/685521/multiline-text-in-jlabel
		dialogueLabel.setForeground(Color.WHITE);
		dialogueLabel.setFont(Fonts.dialogueFont);
		dialogueLabel.setVerticalAlignment(SwingConstants.TOP); // Set the text to align to the top
		
		// Add the dialogue label
		dialogueLabel.setBounds(50, 50, 1250, 250);
		dialoguePanel.add(dialogueLabel);
		
		// Create the back button
		Utility.formatButton(backButton);
		backButton.addActionListener(this);
		backButton.setIcon(Utility.scaleImageIcon(Icons.ARROW_LEFT, 100, 100));
		
		backButton.setBounds(50, 200, 100, 100);
		dialoguePanel.add(backButton);
		
		// Create the next button
		Utility.formatButton(nextButton);
		nextButton.addActionListener(this);
		nextButton.setIcon(Utility.scaleImageIcon(Icons.ARROW_RIGHT, 100, 100));
		
		nextButton.setBounds(1050, 200, 100, 100);
		dialoguePanel.add(nextButton);
		
		// Start the typewriter animation
		typewriterTimer.start();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// If the typewriter timer goes off
		if (e.getSource() == typewriterTimer) {
			
			// Set the current variables
			String currDialogue = dialogueQueue.peek();
			String currText = dialogueLabel.getText();
			
			// If the dialogue is done
			if (charIndex >= currDialogue.length()) {

				return;
				
			}
			
			// Add another character for the typewriter effect
			// Put the character in between the <html> and the </html> so it can be formatted to line wrap automatically
			dialogueLabel.setText(currText.substring(0, currText.length() - 7) + currDialogue.charAt(charIndex) + currText.substring(currText.length() - 7));
			
			// Increment the char index
			charIndex++;
			
		} else if (e.getSource() == backButton) {
			
			
			
		} else if (e.getSource() == nextButton) {
			
			
			
		}
		
	}
	
	public JPanel getProfilePanel() {
		return profilePanel;
	}

	public void setProfilePanel(JPanel profilePanel) {
		this.profilePanel = profilePanel;
	}

	public JPanel getDialoguePanel() {
		return dialoguePanel;
	}

	public void setDialoguePanel(JPanel dialoguePanel) {
		this.dialoguePanel = dialoguePanel;
	}

	public JPanel getNamePanel() {
		return namePanel;
	}

	public void setNamePanel(JPanel namePanel) {
		this.namePanel = namePanel;
	}

	public JLabel getProfileLabel() {
		return profileLabel;
	}

	public void setProfileLabel(JLabel profileLabel) {
		this.profileLabel = profileLabel;
	}

	public JLabel getDialogueLabel() {
		return dialogueLabel;
	}

	public void setDialogueLabeltLabel(JLabel dialogueLabel) {
		this.dialogueLabel = dialogueLabel;
	}

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}

	public JButton getNextButton() {
		return nextButton;
	}

	public void setNextButton(JButton nextButton) {
		this.nextButton = nextButton;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}
	
}
