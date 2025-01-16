import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.sound.sampled.Clip;
import javax.swing.*;

/*
 * This class creates the assessment frame to assess their knowledge
 * It creates the quiz and gives the user feedback when they are done
 */
public class AssessmentFrame extends JFrame implements ActionListener{

	// Create the index button
	private JButton currDialogue = new JButton("0");
	
	// Create the back panel
	private JPanel backPanel;

	// Create lists for the components
	private ArrayList<Component> compList = new ArrayList<>();

	// Create answers list
	private final String[] ANSWERS = {"1", "1", "3", "VOID", "HIERARCHICAL", "1", "2", "0", "3", "RETURN"};
	
	// Create array for the selected options
	private JTextField[] selectedArray = new JTextField[ANSWERS.length];
	
	// Declare a counter for the score
	private int score;

	// Create the background music
	private Clip clip = Utility.playSound("sounds/A Home for Flowers (Tulip).wav", true);
	
	// Create variable for the state
	private State dialogueState;
	
	// https://www.w3schools.com/java/java_enums.asp
	// Create the state enum
	enum State{
		FINISHED,
		QUESTION,
		FEEDBACK
	}
	
	// This constructor is called when a new assessment frame is created
	public AssessmentFrame() {
		
		// Format the frame
		Utility.formatFrame(this);
		setLayout(null);
		
		// Add action listener to current dialogue
		currDialogue.addActionListener(this);
		
		// Fill the answers array
		for (int index = 0; index < ANSWERS.length; index++) {
			selectedArray[index] = new JTextField();
		}
		
		// Create a quick introductory dialogue
		Utility.createQuickDialogue(this, currDialogue, 
				"Welcome to the Assessment, where you can test your knowledge in methods!", Icons.BASIL_PROFILE[1]);
		
		// Set the state of the assessment
		dialogueState = State.QUESTION;
		
		// Set the starting assessment level
		CAIApplication.assessmentLevel = 0;

		// Add the back panel
		backPanel = Utility.createBackPanel(this);
		
		backPanel.setBounds(0, 0, 300, 100);
		add(backPanel);
		
		// Show the frame
		setVisible(true);
		
	}
	
	// This method creates the text field
	private void createBlank(final int questionIndex, String text, int x, int y, int width, int height) {
		
		// Create the dialogue
		Dialogue dialogue = Utility.createQuickDialogue(this, currDialogue, 
				text, Icons.BASIL_PROFILE[0]);
		
		// Format the text field
		JTextField textField = new JTextField();
		Utility.formatTextField(textField);
		
		// Add it to the comp list for disposal
		compList.add(textField);
		
		// Add the text field
		textField.setBounds(x,y,width,height);
		dialogue.getDialoguePanel().add(textField);
		
		// Put the new text field in the selected array
		selectedArray[questionIndex] = textField;
		
	}

	// This method creates the options
	private void createOptions(final int questionIndex, String text, String... options) {
		
		// Create the dialogue
		Dialogue dialogue = Utility.createQuickDialogue(this, currDialogue, 
				text, Icons.BASIL_PROFILE[0]);
		
		// Create the button group
		ButtonGroup group = new ButtonGroup();
		
		// Loop through each button
		for (int buttonNum = 0; buttonNum < options.length; buttonNum++) {
			
			// Get the text of the option
			String option = options[buttonNum];
			
			// Create a radio button and format it
			JRadioButton optionButton = new JRadioButton(option);
			Utility.formatRadioButton(optionButton);

			// Create a final variable for the button number
			final int selectedNum = buttonNum;
			
			// Add a action listener to the button
			optionButton.addActionListener(new ActionListener() {
				
				// This method is ran when the button is pressed
				public void actionPerformed(ActionEvent e) {
					
					// Set the text to the number option selected
					selectedArray[questionIndex].setText(selectedNum+"");
					
				}
				
			});
			
			// Set the default to the first button
			if (buttonNum == 0) optionButton.doClick();
			
			// Add the button to the comp list and group
			compList.add(optionButton);
			group.add(optionButton);

			// Add the button
			optionButton.setBounds(50, 100 + 30 * buttonNum, 600, 30);
			dialogue.getDialoguePanel().add(optionButton);
			
		}
		
	}
	
	// This method creates a diagram
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
	
	// This method creates the next question
	private void createNextQuestion() {
		
		// Set the state of the dialogue
		dialogueState = State.FEEDBACK;
		
		// Create the question depending on the current level
		switch (CAIApplication.assessmentLevel) {
		
		case 0: // level 0
			
			// Create it
			createOptions(0, 
					"What is a method in Java?",
					"A data type", 
					"A block of code that performs a specific task", 
					"A data structure", 
					"A way of doing something");
			break; // Break out of switch
			
		case 1:
			
			// Create the diagram
			createDiagram(Icons.DIAGRAM_SYNTAX, 370, 100, 500, 500);
			
			createOptions(1,
					"The syntax of the above diagram is CORRECT and no errors will occur.",
					"True",
					"False");
			break;
			
		case 2:
			
			createOptions(2, 
					"What DON'T you need for a method in Java?", 
					"Access modifier", 
					"Return type", 
					"Method name", 
					"You need all of the above");
			break;
			
		case 3:

			createBlank(3, "A method that does not return any value is declared with the return type ______.",
					710, 20, 100, 50);
			break;
			
		case 4:

			createBlank(4, "Methods are a ______________ form of management.",
					170, 20, 180, 50);
			break;
		
		case 5:
			
			createOptions(5, 
					"A static method can call ANY method in its class without restrictions.",
					"True",
					"False");
			break;
			
		case 6:
			
			createOptions(6,
					"Which of the following is true about static methods?",
					"They require an object to be called",
					"They cannot call other static methods",
					"They belong to the class and not to an object",
					"They are always private");
			break;
			
		case 7:
			
			createOptions(7, 
					"What happens if more method calls are made than can be stored in the program execution stack?",
					"Stack overflow error",
					"Compilation error",
					"Null pointer exception",
					"The method will not be called");
			break;
			
		case 8:
			
			createOptions(8,
					"Which of the following allows multiple methods with the same name in a class?",
					"Method overriding",
					"Private methods",
					"Protected methods",
					"Method overloading");
			break;
			
		case 9:
			
			createBlank(9,"The ________ keyword is used to explicitly return a value from a method.",
					70, 20, 100, 50);
			break;
			
		}
		
	}
	
	// This method returns the feedback
	private String getFeedback() {
		
		// Declare the text
		String text = "";
		
		// Check the assessment level
		switch (CAIApplication.assessmentLevel) {
		
		case 0: // If the level is 0
			
			// Set the text
			text = "That's wrong, a method is a block of code that performs a specific task.";
			break; // Break out of switch
			
		case 1:
			text = "It would be FALSE because the method has a return type JLabel, and nothing was returned.";
			break;
		case 2:
			text = "You need all of those, so the correct answer is None of the above.";
			break;
		case 3:
			text = "Wrong, a method that does not return any value is declared with return type VOID.";
			break;
		case 4:
			text = "Nope, methods are a HIERARCHICAL form of management.";
			break;
		case 5:
			text = "Incorrect, static methods can only call other static methods or non-static methods with a reference.";
			break;
		case 6:
			text = "Static methods belong to a class and not to an object.";
			break;
		case 7:
			text = "A stack overflow error will occur.";
			break;
		case 8:
			text = "Method overiding allows multiple methods with the same name to be used in a class.";
			break;
		case 9:
			text = "The RETURN keyword is used to explicitly return a value from a method.";
			break;
		
		}
		
		// Return the text
		return text;
		
	}
	
	// This method creates the feedback
	private void createFeedback() {
		
		// Set the state of the dialogue
		dialogueState = State.QUESTION;
		
		// Check if the option is correct ignoring spaces
		if (selectedArray[CAIApplication.assessmentLevel].getText().replace(" ", "")
				.equalsIgnoreCase(ANSWERS[CAIApplication.assessmentLevel])) {
			
			// Play a sound effect
			Utility.playSound("sounds/save.wav", false);
			
			// Give a quick dialogue how it is correct
			Utility.createQuickDialogue(this, currDialogue, "Nice, you got it correct!", Icons.BASIL_PROFILE[1]);
			score++; // Increment score
		
		// Otherwise the option is wrong
		} else {
			
			// Play a wrong sound effect
			Utility.playSound("sounds/buzzer.wav", false);
			
			// Create a quick dialogue using the feedback method
			Utility.createQuickDialogue(this, currDialogue, getFeedback() , Icons.BASIL_PROFILE[2]);
			
		}
		
		// Increment the assessment level
		CAIApplication.assessmentLevel++;
		
	}

	// This method creates the final feedback at the end of the quiz
	private void createFinalFeedback() {
	
		// Set the dialogue state
		dialogueState = State.FINISHED;
		
		// Check if the score is failing (below 50)
		if (score <= ANSWERS.length / 2) {
			
			// Create the dialogue
			Utility.createQuickDialogue(this, currDialogue, "You got " + score + 
					" questions correct out of " + ANSWERS.length + ". You FAIL!", Icons.BASIL_PROFILE[3]);
		
		// Otherwise the score is passing
		} else {
			
			// Create the dialogue
			Utility.createQuickDialogue(this, currDialogue, "You got " + score + 
					" questions correct out of " + ANSWERS.length + ". You PASS!", Icons.BASIL_PROFILE[1]);
			
		}
		
	}
	
	// This method is called whenever an action occurs
	@Override
	public void actionPerformed(ActionEvent e) {

		// Check the source of the action
		if (e.getSource() == currDialogue) {
			
			// Remove the components on the previous dialogue
			for (Component comp : compList) {
				
				comp.setVisible(false);
				comp.setEnabled(false);
				remove(comp);
				
			}
			
			// Check if the quiz is done
			if (dialogueState == State.FINISHED) {
				
				// Stop the music
				for (Clip clip : CAIApplication.clipList) {
	        		clip.stop();
	        	}
				
				// Close the frame and open the title
				dispose();
				new TitleFrame();
				
			// Check if there are no more questions
			} else if (CAIApplication.assessmentLevel >= ANSWERS.length) {

				// Create the final feedback
				createFinalFeedback();
			
			// Check if the state is question
			} else if (dialogueState == State.QUESTION){
				
				// Create the next question
				createNextQuestion();
			
			// Check if the state is feedback
			} else if (dialogueState == State.FEEDBACK) {
				
				// Create the next feedback
				createFeedback();
				
			}

		}
		
	}
	
}
