import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Dialogue extends JPanel implements ActionListener {

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
	private int currIndex = 0;
	
	// Create a button for passing along the current index (used for displaying diagrams)
	private JButton currIndexButton;
	
	// Create a list to hold the dialogue list
	private ArrayList<String> dialogueList;
	private int charIndex = 0; // Variable to hold current char index
	
	// Create a variable to hold the profile icon list
	private ArrayList<ImageIcon> profileIconList;
	
	// Constructor that is called when a new dialogue is created
	// Optional perameters for storing current index
	//https://stackoverflow.com/questions/965690/how-do-i-use-optional-parameters-in-java
	public Dialogue(ArrayList<String> dialogueList, ArrayList<ImageIcon> profileIconList, JButton... currIndexButton) {
		
		// Set fields
		this.dialogueList = dialogueList;
		this.profileIconList = profileIconList;
		
		if (currIndexButton != null && currIndexButton.length != 0) // If the current index button exists
			this.currIndexButton = currIndexButton[0]; // Pass along the first and only index of the array
		
		// Create the profile panel
		Utility.formatPanel(profilePanel);
		profilePanel.setLayout(new BorderLayout());
		
		// Create the profile label
		profileLabel.setIcon(Utility.scaleImageIcon(profileIconList.get(currIndex), 150, 150));
		profileLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); // Add a border to center the image in the panel
		
		// Add the profile image
		profileLabel.setBounds(0, 0, 170, 170);
		profilePanel.add(profileLabel, BorderLayout.SOUTH); // Put the image at the bottom of the panel

		// Create the name panel
		Utility.formatPanel(namePanel);
		
		
		// Create the name label
		nameLabel.setText("BASIL");
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(Fonts.name);
		
		// Add the name label
		nameLabel.setBounds(0, 0, 200, 75);
		namePanel.add(nameLabel);
		
		// Create the dialogue panel
		Utility.formatPanel(dialoguePanel);
		dialoguePanel.setLayout(null);
		
		// Create the dialogue label
		dialogueLabel.setText("<html></html>"); // https://stackoverflow.com/questions/685521/multiline-text-in-jlabel
		dialogueLabel.setForeground(Color.WHITE);
		dialogueLabel.setFont(Fonts.dialogue);
		dialogueLabel.setVerticalAlignment(SwingConstants.TOP); // Set the text to align to the top
		dialogueLabel.setBorder(BorderFactory.createEmptyBorder(30, 30, 0, 0));
		
		// Add the dialogue label
		dialogueLabel.setBounds(0, 0, 1350, 350);
		dialoguePanel.add(dialogueLabel);
		
		// Create the back button
		Utility.formatButton(backButton);
		backButton.addActionListener(this);
		backButton.setIcon(Utility.scaleImageIcon(Icons.ARROW_LEFT, 100, 100));

		backButton.setVisible(false);
		backButton.setEnabled(false);
		backButton.setBounds(50, 200, 100, 100);
		dialoguePanel.add(backButton);
		
		// Create the next button
		Utility.formatButton(nextButton);
		nextButton.addActionListener(this);
		nextButton.setIcon(Utility.scaleImageIcon(Icons.ARROW_RIGHT, 100, 100));
		
		nextButton.setBounds(1200, 200, 100, 100);
		dialoguePanel.add(nextButton);
		
		// Start the typewriter animation
		typewriterTimer.start();
		
	}
	
	// This method updates the dialogue when the dialogue changes
	private void updateDialogue() {
		
		if (currIndex >= 0)
			profileLabel.setIcon(Utility.scaleImageIcon(profileIconList.get(currIndex), 150, 150));
		
		dialogueLabel.setText("<html></html>");
		charIndex = 0;
		
		if (currIndexButton != null) {

			currIndexButton.setText("" + currIndex); // Set the text to track the current index
			currIndexButton.doClick(); // Trigger the action performed function in the frame this is in
			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// If the typewriter timer goes off
		if (e.getSource() == typewriterTimer) {
			
			// If the dialogue is done
			if (charIndex >= dialogueList.get(currIndex).length()) {

				typewriterTimer.stop();
				return;
				
			}
			
			// Add another character for the typewriter effect
			// Put the character in between the <html> and the </html> so it can be formatted to line wrap automatically
			dialogueLabel.setText(dialogueLabel.getText().substring(0, dialogueLabel.getText().length() - 7) + // first part "<html>(current text)"
					dialogueList.get(currIndex).charAt(charIndex) + // next character
					dialogueLabel.getText().substring(dialogueLabel.getText().length() - 7)); // ending "</html>"
			
			// Increment the char index
			charIndex++;
			
		} else if (e.getSource() == backButton) {

			currIndex--;
			
			updateDialogue();
			
			typewriterTimer.start();
			
			if (currIndex <= 0) {
				
				backButton.setVisible(false);
				backButton.setEnabled(false);
				
			}
			
		} else if (e.getSource() == nextButton) {

			if (charIndex < dialogueList.get(currIndex).length()) {
				
				typewriterTimer.stop();
				dialogueLabel.setText("<html>" + dialogueList.get(currIndex) + "</html>");
				charIndex = dialogueLabel.getText().length();
				
			} else if (currIndex >= dialogueList.size() - 1) {

				currIndex = -1;
				
				updateDialogue();
				
				typewriterTimer.stop();
				dialoguePanel.setVisible(false);
				dialoguePanel.setEnabled(false);
				namePanel.setVisible(false);
				namePanel.setEnabled(false);
				profilePanel.setVisible(false);
				profilePanel.setEnabled(false);
				
			} else {
				
				currIndex++;
				
				updateDialogue();
				
				profileLabel.setIcon(Utility.scaleImageIcon(profileIconList.get(currIndex), 150, 150));
				
				typewriterTimer.start();
				backButton.setVisible(true);
				backButton.setEnabled(true);
			
			}
			
		}
		
	}

	public JPanel getProfilePanel() {
		return profilePanel;
	}

	public void setProfilePanel(JPanel profilePanel) {
		this.profilePanel = profilePanel;
	}

	public JPanel getNamePanel() {
		return namePanel;
	}

	public void setNamePanel(JPanel namePanel) {
		this.namePanel = namePanel;
	}

	public JPanel getDialoguePanel() {
		return dialoguePanel;
	}

	public void setDialoguePanel(JPanel dialoguePanel) {
		this.dialoguePanel = dialoguePanel;
	}

	public int getCurrIndex() {
		return currIndex;
	}

	public void setCurrIndex(int currIndex) {
		this.currIndex = currIndex;
	}
	
}
