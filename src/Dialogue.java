import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

/*
 * This class creates the dialogue object as well as the panels for a dialogue
 * The next button and back button change the dialogue
 * The dialogue has a typewriter animation
 */
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
	private Timer typewriterTimer = new Timer(CAIApplication.DELAY_TYPEWRITER, this);
	
	// Declare a variable for counting the number of ticks
	private int typewriterTick = 0;
	
	// Create a variable to hold the current dialogue number
	private int currDialogue = 0;
	
	// Create a button for passing along the current index (used for displaying diagrams)
	private JButton currDialogueButton;
	
	// Create a list to hold the dialogue list
	private ArrayList<String> dialogueList;
	private int charIndex = 0; // Variable to hold current char index
	
	// Create a variable to hold the profile icon list
	private ArrayList<ImageIcon> profileIconList;
	
	// Constructor that is called when a new dialogue is created
	public Dialogue(ArrayList<String> dialogueList, ArrayList<ImageIcon> profileIconList, JButton currDialogueButton) {
		super(); // Used to create the object
		
		// Set fields
		this.dialogueList = dialogueList;
		this.profileIconList = profileIconList;
		this.currDialogueButton = currDialogueButton; // Pass along the first and only index of the array
		
		// Create the profile panel
		Utility.formatPanel(profilePanel);
		profilePanel.setLayout(new BorderLayout());
		
		// Create the profile label
		profileLabel.setIcon(Utility.scaleImageIcon(profileIconList.get(currDialogue), 150, 150));
		profileLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); // Add a border to center the image in the panel
		
		// Add the profile image
		profileLabel.setBounds(0, 0, 170, 170);
		profilePanel.add(profileLabel, BorderLayout.SOUTH); // Put the image at the bottom of the panel

		// Create the name panel
		Utility.formatPanel(namePanel);
		namePanel.setLayout(null);
		
		// Create the name label
		nameLabel.setText("BASIL");
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(Fonts.name);
		
		// Add the name label
		nameLabel.setBounds(50, 0, 200, 75);
		namePanel.add(nameLabel);
		
		// Create the dialogue panel
		Utility.formatPanel(dialoguePanel);
		dialoguePanel.setLayout(null);
		
		// Create the dialogue label
		dialogueLabel.setText("<html></html>"); // https://stackoverflow.com/questions/685521/multiline-text-in-jlabel
		dialogueLabel.setForeground(Color.WHITE);
		dialogueLabel.setFont(Fonts.dialogue);
		dialogueLabel.setVerticalAlignment(SwingConstants.TOP); // Set the text to align to the top
		dialogueLabel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		
		// Add the dialogue label
		dialogueLabel.setBounds(0, 0, 1350, 350);
		dialoguePanel.add(dialogueLabel);
		
		// Create the back button
		Utility.formatButton(backButton);
		backButton.addActionListener(this);
		backButton.setIcon(Utility.scaleImageIcon(Icons.ARROW_LEFT, 100, 100));

		// Add the back button
		backButton.setVisible(false);
		backButton.setEnabled(false);
		backButton.setBounds(50, 200, 100, 100);
		dialoguePanel.add(backButton);
		
		// Create the next button
		Utility.formatButton(nextButton);
		nextButton.addActionListener(this);
		nextButton.setIcon(Utility.scaleImageIcon(Icons.ARROW_RIGHT, 100, 100));
		
		// Add the next button
		nextButton.setBounds(1200, 200, 100, 100);
		dialoguePanel.add(nextButton);
		
		// Start the typewriter animation
		typewriterTimer.start();
		
	}
	
	// This method clears the dialogue
	private void updateDialogue() {
		
		// If the dialogue is ongoing, update the profile label
		if (currDialogue >= 0)
			profileLabel.setIcon(Utility.scaleImageIcon(profileIconList.get(currDialogue), 150, 150));
		
		// Set the dialogue label text to html format
		dialogueLabel.setText("<html></html>");
		charIndex = 0; // Reset the character index
		
		// Check if the dialogue button exists
		if (currDialogueButton != null) {

			// Set the new dialogue number
			currDialogueButton.setText("" + currDialogue); // Set the text to track the current index
			currDialogueButton.doClick(); // Trigger the action performed function in the frame this is in
			
		}
		
	}

	// This method is called whenever an action occurs
	@Override
	public void actionPerformed(ActionEvent e) {

		// If the typewriter timer goes off
		if (e.getSource() == typewriterTimer) {
			
			typewriterTick++; // Increment the tick counter
			
			// Check if the dialogue is done
			if (charIndex >= dialogueList.get(currDialogue).length()) {

				// Stop the animation
				typewriterTimer.stop();
				return;
				
			}
			
			// Play a sound every 2 ticks
			if (typewriterTick % 2 == 0 && currDialogueButton != null)
				Utility.playSound("sounds/text.wav", false);
			
			// Add another character for the typewriter effect
			// Put the character in between the <html> and the </html> so it can be formatted to line wrap automatically
			dialogueLabel.setText(dialogueLabel.getText().substring(0, dialogueLabel.getText().length() - 7) + // first part "<html>(current text)"
					dialogueList.get(currDialogue).charAt(charIndex) + // next character
					dialogueLabel.getText().substring(dialogueLabel.getText().length() - 7)); // ending "</html>"
			
			// Increment the char index
			charIndex++;
			
		// Check if the back button is pressed
		} else if (e.getSource() == backButton) {
			
			Utility.playSound("sounds/select.wav", false);

			// Decrease the current index
			currDialogue--;
			
			// Update the dialogue
			updateDialogue();
			
			// Start the typewriter animation
			typewriterTimer.start();
			
			// Hide the button if it is the first dialogue
			if (currDialogue <= 0) {
				
				backButton.setVisible(false);
				backButton.setEnabled(false);
				
			}
		
		// Check if the next button is pressed
		} else if (e.getSource() == nextButton) {
			
			// Play a select sound effect
			Utility.playSound("sounds/select.wav", false);

			// Check if there are still more content to show
			if (charIndex < dialogueList.get(currDialogue).length()) {
				
				// Stop the typewriter
				typewriterTimer.stop();
				
				// Finish showing all of the text
				dialogueLabel.setText("<html>" + dialogueList.get(currDialogue) + "</html>");
				charIndex = dialogueLabel.getText().length();
				
			// Check if there is no more dialogue to show
			} else if (currDialogue >= dialogueList.size() - 1) {

				// Set the current index to -1
				currDialogue = -1;
				
				// Update the dialogue
				updateDialogue();
				
				// Stop the typewriter
				typewriterTimer.stop();
				
				// Hide everything
				dialoguePanel.setVisible(false);
				dialoguePanel.setEnabled(false);
				namePanel.setVisible(false);
				namePanel.setEnabled(false);
				profilePanel.setVisible(false);
				profilePanel.setEnabled(false);
				
			// Otherwise show the next dialogue
			} else {
				
				// Increment the index
				currDialogue++;
				
				// Update the dialogue
				updateDialogue();

				// Start the typewriter
				typewriterTimer.start();
				
				// Show the back button
				backButton.setVisible(true);
				backButton.setEnabled(true);
			
			}
			
		}
		
	}

	// Getters and setters
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

	public int getCurrDialogue() {
		return currDialogue;
	}

	public void setCurrDialogue(int currDialogue) {
		this.currDialogue = currDialogue;
	}
	
}
