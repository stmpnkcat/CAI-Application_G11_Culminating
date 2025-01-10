import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

public class AssessmentFrame extends JFrame implements ActionListener{

	// Create the index button
	private JButton currDialogue = new JButton("0");
	
	// Create the back panel
	private JPanel backPanel;

	// Create lists for the components
	private ArrayList<Component> compList = new ArrayList<>();

	// Create answers list
	private final String[] ANSWERS = {"1", "1", "4", "HIERARCHICAL"};
	
	// Create array for the selected options
	private JTextField[] selectedArray = new JTextField[ANSWERS.length];
	
	private int score;
	
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
		
		currDialogue.addActionListener(this);
		
		for (int index = 0; index < ANSWERS.length; index++) {
			selectedArray[index] = new JTextField();
		}
		
		Utility.createQuickDialogue(this, currDialogue, 
				"Welcome to the Assessment, where you can test your knowledge in methods!", Icons.BASIL_PROFILE[1]);
		
		dialogueState = State.QUESTION;
		
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
		
		selectedArray[questionIndex] = textField;
		
	}

	// This method creates the options
	private void createOptions(final int questionIndex, String text, String... options) {
		
		Dialogue dialogue = Utility.createQuickDialogue(this, currDialogue, 
				text, Icons.BASIL_PROFILE[0]);
		
		ButtonGroup group = new ButtonGroup();
		
		for (int buttonNum = 0; buttonNum < options.length; buttonNum++) {
			
			String option = options[buttonNum];
			
			JRadioButton optionButton = new JRadioButton(option);
			
			Utility.formatRadioButton(optionButton);

			final int selectedNum = buttonNum;
			optionButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectedArray[questionIndex].setText(selectedNum+"");
				}
			});
			
			if (buttonNum == 0) optionButton.doClick();
			
			compList.add(optionButton);
			group.add(optionButton);

			optionButton.setBounds(50, 100 + 30 * buttonNum, 600, 30);
			dialogue.getDialoguePanel().add(optionButton);
			
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
	
	private void createNextQuestion() {
		
		dialogueState = State.FEEDBACK;
		
		switch (CAIApplication.assessmentLevel) {
		case 0:
			
			createOptions(0, 
					"What is a method in Java?",
					"A data type", 
					"A block of code that performs a specific task", 
					"A data structure", 
					"A way of doing something");
			break;
			
		case 1:
			
			createDiagram(Icons.DIAGRAM_SYNTAX, 370, 100, 500, 500);
			
			createOptions(1,
					"The syntax of the above diagram is CORRECT and no errors will occur. ",
					"True",
					"False");
			break;
			
		case 2:
			
			createOptions(2, 
					"What DON'T you need for a method in Java?", 
					"Access modifier", 
					"Return type",
					"Method name",
					"List of parameters",
					"None of the above");
			break;
			
		case 3:

			createBlank(3, "Methods are a ______________ form of management.",
					170, 20, 180, 50);
			break;
			
		}
		
	}
	
	private void createFeedback() {
		
		dialogueState = State.QUESTION;
		
		System.out.println(selectedArray[CAIApplication.assessmentLevel].getText().replace(" ", ""));
		System.out.println(ANSWERS[CAIApplication.assessmentLevel]);
		
		if (selectedArray[CAIApplication.assessmentLevel].getText().replace(" ", "")
				.equalsIgnoreCase(ANSWERS[CAIApplication.assessmentLevel])) {
			
			Utility.createQuickDialogue(this, currDialogue, "Nice, you got it correct!", Icons.BASIL_PROFILE[1]);
			score++;
			
		} else {
			
			String text = "";
			
			switch (CAIApplication.assessmentLevel) {
			case 0:
				text = "That's wrong, a method is a block of code that performs a specific task.";
				break;
			case 1:
				text = "It would be FALSE because the method has a return type JLabel, and nothing was returned.";
				break;
			case 2:
				text = "You need all of those, so the correct answer is None of the above.";
				break;
			case 3:
				text = "Nope, methods are a HIERARCHICAL form of management.";
				break;
				
			}

			Utility.createQuickDialogue(this, currDialogue, text, Icons.BASIL_PROFILE[2]);
		}
		
		CAIApplication.assessmentLevel++;
		
	}

	private void createFinalFeedback() {
	
		dialogueState = State.FINISHED;
		
		if (score <= ANSWERS.length / 2) {
			
			Utility.createQuickDialogue(this, currDialogue, "You got " + score + 
					" questions correct out of " + ANSWERS.length + ". You FAIL!", Icons.BASIL_PROFILE[3]);
			
		} else {
			
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
			
			if (dialogueState == State.FINISHED) {
				
				dispose();
				new TitleFrame();
				
			} else if (CAIApplication.assessmentLevel >= ANSWERS.length) {

				createFinalFeedback();
				
			} else if (dialogueState == State.QUESTION){
				
				createNextQuestion();
				
			} else if (dialogueState == State.FEEDBACK) {
				
				createFeedback();
				
			}

		}
		
	}
	
}
