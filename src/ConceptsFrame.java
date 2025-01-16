import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.Clip;
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

	// Declare the index button, which is used for retrieving the dialogue number
	private JButton currDialogue = new JButton(CAIApplication.conceptsLevel+"");
	
	// Declare the back panel for the back button
	private JPanel backPanel;
	
	// Declare the topic panel
	private JPanel topicPanel = new JPanel();
	private JLabel topicLabel = new JLabel();
	
	// Declare list for the components
	private ArrayList<Component> compList = new ArrayList<>();
	
	// Declare lists for dialogue information
	private ArrayList<String> textList = new ArrayList<>();
	private ArrayList<ImageIcon> iconList = new ArrayList<>();
	
	// Play the background music
	private Clip clip = Utility.playSound("sounds/A Home for Flowers (Tulip).wav", true);
	
	// This constructor is called when a new frame is created
	public ConceptsFrame() {
		
		// Format the frame
		Utility.formatFrame(this);
		setLayout(null);
		
		// Fill the dialogue lists
		fillLists();
		
		// Create a new dialogue with the filled lists
		Dialogue dialogue = new Dialogue(textList, iconList, currDialogue);
		dialogue.setCurrDialogue(CAIApplication.conceptsLevel);
		
		// Add action listener to the index button to allow for detecting if the dialogue changes
		currDialogue.addActionListener(this);
		currDialogue.doClick(); // Activate the button to process the first dialogue
		
		// Set the panels using the dialogue created
		profilePanel = dialogue.getProfilePanel();
		namePanel = dialogue.getNamePanel();
		dialoguePanel = dialogue.getDialoguePanel();
		
		// Create the back panel using utility class
		backPanel = Utility.createBackPanel(this);
		
		// Create the back panel
		backPanel.setBounds(0, 0, 300, 100);
		add(backPanel);
		
		// Format topic panel
		Utility.formatPanel(topicPanel);
		
		// Add topic text
		topicLabel.setForeground(Color.WHITE);
		topicLabel.setFont(Fonts.dialogue);
		topicPanel.add(topicLabel);

		// Add topic panel
		topicPanel.setBounds(1075, 50, 300, 70);
		add(topicPanel);
		
		// Create the profile panel
		profilePanel.setBounds(1205, 200, 170, 170);
		add(profilePanel);
		
		// Create the name panel
		namePanel.setBounds(25, 300, 200, 75);
		add(namePanel);
		
		// Create the dialogue panel
		dialoguePanel.setBounds(25, 400, 1350, 300);
		add(dialoguePanel);
		
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
				
				// Check if the line is a separator, meaning this is the end of the current dialogue
				if (line.indexOf(CAIApplication.ID_SEPARATOR) == 0){
					
					// Get the profile id and set the icon
					int profileID = Integer.parseInt(""+line.charAt(1));
					currIcon = Icons.BASIL_PROFILE[profileID];
					
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
	
	private void createDiagram(ImageIcon icon, int x, int y, int width, int height) {

		// Create the diagram panel to hold the diagram
		JPanel diagramPanel = new JPanel();
		diagramPanel.setOpaque(false);
		
		// Create the diagram label image
		diagramPanel.add(new JLabel(icon));
		
		// Add the diagram panel
		diagramPanel.setBounds(x, y, width, height);
		add(diagramPanel);
		
		// Add the diagram panel to the diagram list to be later disposed of
		compList.add(diagramPanel);
		
	}

	// This method is called whenever an action is performed
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Check which action was performed
		if (e.getSource() == currDialogue) {
			
			// Remove every component because they were from the previous dialogue
			for (Component comp : compList) {
				
				// Remove it
				comp.setVisible(false);
				comp.setEnabled(false);
				remove(comp);
				
			}
			
			// Get the current dialogue number
			int dialogueNum = Integer.parseInt(currDialogue.getText());
			
			// Save user progress
			CAIApplication.conceptsLevel = dialogueNum;
			
			// Check which dialogue number it is on (0-indexed)
			switch (dialogueNum) {
			
			// No more dialogue left
			case -1:
				
				// Stop the background music
				for (Clip clip : CAIApplication.clipList) {
	        		clip.stop();
	        	}
				
				// Reset the concepts level
				CAIApplication.conceptsLevel = 0;
				
				// Close the frame and go back to title page
				dispose();
				new TitleFrame();
				break;
				
			// Dialogue 0
			case 0:
				
			case 1: // extends onto dialogue 1
				
				// Set the topic title
				topicLabel.setText("Introduction");
				
				// Break out of the switch
				break;
				
			// Dialogue 2
			case 2:

				// Set the topic title
				topicLabel.setText("Introduction");
				
				// Create the diagram image
				createDiagram(Icons.DIAGRAM_EXAMPLE, 300, 200, 850, 150);
				break;
				
			// Dialogue 3
			case 3:
			
			// Dialogue 4
			case 4:
				
				topicLabel.setText("Modularization");
				break;
				
			// Dialogue 5
			case 5:
				
				topicLabel.setText("Modularization");
				
				createDiagram(Icons.DIAGRAM_HIERARCHY, 400, 100, 500, 500);
				break;
				
			// Dialogue 6
			case 6:
				
				topicLabel.setText("Modularization");

				createDiagram(Icons.DIAGRAM_HIERARCHY, 400, 100, 500, 500);
				break;
			
			// Dialogue 7
			case 7:
				
				topicLabel.setText("Static Methods");
				break;
				
			// Dialogue 8
			case 8:
				
				topicLabel.setText("Static Methods");
				
				createDiagram(Icons.DIAGRAM_MATH, 400, 100, 570, 570);
				break;
				
			// Dialogue 9
			case 9:
			
			// Dialogue 10
			case 10:
				
				topicLabel.setText("Multiple Parameters");
				
				createDiagram(Icons.DIAGRAM_PARAMETERS, 370, 70, 760, 330);
				break;
				
			// Dialogue 11
			case 11:
			
			// Dialogue 12
			case 12:
				topicLabel.setText("Calling a Method");
				break;
				
			// Dialogue 13
			case 13:
				
				topicLabel.setText("Returning");
				
				createDiagram(Icons.DIAGRAM_RETURNING, 350, 70, 600, 500);
				break;
				
			// Dialogue 14
			case 14:
				
				topicLabel.setText("Method Call Stack");
				
				createDiagram(Icons.DIAGRAM_CALLSTACK, 400, 50, 500, 500);
				break;
				
			// Dialogue 15
			case 15:
				
				topicLabel.setText("Overloading");
				
				createDiagram(Icons.DIAGRAM_OVERLOADING, 370, 80, 530, 200);
				break;
				
			// Dialogue 16
			case 16:
				
				topicLabel.setText("Conclusion");
				break;
				
			}
			
		}
		
	}
	
}
