import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

/*
 * This class creates the concepts page
 * It uses the dialogue to explain the concepts
 * Diagrams are displayed depending on the dialogue number
 */
public class ConceptsFrame extends JFrame implements ActionListener{
	
	// Declare the panels for the dialogue
	private JPanel profilePanel;
	private JPanel namePanel;
	private JPanel dialoguePanel;
	
	// Declare the back panel for the back button
	private JPanel backPanel;

	// Declare the index button, which is used for retrieving the dialogue number
	private JButton currIndexButton = new JButton("0");
	
	// Declare the lists to store dialogue information
	private ArrayList<JPanel> diagramList = new ArrayList<>();
	private ArrayList<String> textList = new ArrayList<>();
	private ArrayList<ImageIcon> iconList = new ArrayList<>();
	
	// This constructor is called when a new frame is created
	public ConceptsFrame() {
		
		// Format the frame
		Utility.formatFrame(this);
		setLayout(null);
		
		// Fill the dialogue lists
		fillLists();
		
		// Create a new dialogue with the filled lists
		Dialogue dialogue = new Dialogue(textList, iconList, currIndexButton);
		
		// Add action listener to the index button to allow for detecting if the dialogue changes
		currIndexButton.addActionListener(this);
		currIndexButton.doClick(); // Activate the button to process the first dialogue
		
		// Set the panels using the dialogue created
		profilePanel = dialogue.getProfilePanel();
		namePanel = dialogue.getNamePanel();
		dialoguePanel = dialogue.getDialoguePanel();
		
		// Create the back panel using utility class
		backPanel = Utility.createBackPanel(this);
		
		// Create the profile panel
		profilePanel.setBounds(1200, 200, 170, 170);
		add(profilePanel);
		
		// Create the name panel
		namePanel.setBounds(25, 300, 200, 75);
		add(namePanel);
		
		// Create the dialogue panel
		dialoguePanel.setBounds(25, 400, 1350, 300);
		add(dialoguePanel);
		
		// Create the dialogue panel
		backPanel.setBounds(0, 0, 300, 100);
		add(backPanel);
		
		// Show the frame
		setVisible(true);
		
	}

	// This method fill dialogue lists with the data read from a file
	private void fillLists() {

		// Set the file path
		final String filePath = "dialogue/concepts";
		
		// Declare the current dialogue text and icon
		String currText = "";
		ImageIcon currIcon = null;
		
		// Declare the row variable to keep track of the row number while traversing the file
		int row = 0;
		
		// Declare the scanner
		Scanner inputFile;
		
		// Try to get the image, if it fails, send error message
		try {
			
			// Set the scanner to read the file location given
			inputFile = new Scanner(new File(filePath));
			
			// Iterate through the rows of the file
			while (inputFile.hasNext()) {
				
				// Get the current line
				String line = inputFile.nextLine();
				
				// Check if the line is a seperator, meaning this is the end of the current dialogue
				if (line.indexOf(CAIApplication.ID_SEPARATOR) == 0){
					
					// Get the profile id
					int profileID = Integer.parseInt(""+line.charAt(CAIApplication.ID_SEPARATOR.length()));
					
					// Match the profile id
					currIcon = matchId(profileID);
					
					// Add the text and icon to the lists
					textList.add(currText);
					iconList.add(currIcon);
					
					// Reset the current text
					currText = "";
					
				// Otherwise the line is a dialogue line
				} else {

					// Add the line to the current text
					currText += line;
					
				}
				
				// Increment the row number, as if it were a for-loop
				row++;
				
			}
			
			// Close the input file
			inputFile.close();
			
		// If the file cannot be found, return an error message.
		} catch (FileNotFoundException error) {
			
			System.out.println("File not found: " + filePath);
			
		}
		
	}
	
	// This method matches the id given with a profile icon
	private ImageIcon matchId(int id) {
		
		// Get the according image icon
		switch(id) {
			case CAIApplication.ID_NEUTRAL:
				return Icons.BASIL_NEUTRAL;
			case CAIApplication.ID_ANGRY:
				return Icons.BASIL_ANGRY;
		}
		
		// If no image has been returned, return nothing
		return null;
	}

	// This method is called whenever an action is performed
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Check which action was performed
		if (e.getSource() == currIndexButton) {
			
			// Remove every diagram because they were from the previous dialogue
			for (JPanel panel : diagramList) {
				
				// Remove it
				panel.setVisible(false);
				panel.setEnabled(false);
				remove(panel);
				
			}
			
			// Get the current dialogue number
			int currIndex = Integer.parseInt(currIndexButton.getText());
			
			// Check which dialogue number it is on (0-indexed)
			switch (currIndex) {
			
				// No more dialogue left
				case -1:
					
					// Close the frame and go back to title page
					dispose();
					new TitleFrame();
					
				// Dialogue 1
				case 1:
					
					// Create the diagram panel to hold the diagram
					JPanel diagramPanel = new JPanel();
					diagramPanel.setOpaque(false);
					
					// Create the diagram label image
					JLabel diagramLabel = new JLabel(Icons.DIAGRAM_1);
					diagramPanel.add(diagramLabel);
					
					// Add the diagram panel
					diagramPanel.setBounds(300, 150, 500, 500);
					add(diagramPanel);
					
					// Add the diagram panel to the diagram list to be later disposed of
					diagramList.add(diagramPanel);
					
					// Break out of switch statement
					break;
					
				case 2:
					
			
			}
			
		}
		
	}
	
}
