import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AssessmentFrame extends JFrame implements ActionListener{
	
	// Create the panels
	private JPanel profilePanel;
	private JPanel namePanel;
	private JPanel dialoguePanel;

	// Create the dialogue
	private Dialogue dialogue;

	// Create the index button
	private JButton currDialogue = new JButton("0");
	
	// Create the back panel
	private JPanel backPanel;

	// Create lists for the components
	private ArrayList<Component> compList = new ArrayList<>();
	
	// Create lists for the dialogue content
	private ArrayList<String> textList = new ArrayList<>();
	private ArrayList<ImageIcon> iconList = new ArrayList<>();

	private final String[] answers = {"1", "wasd"};
	
	// Create array for the selected options
	private JTextField[] selectedArray = new JTextField[answers.length];
	
	// Create variable for if it is finished
	private boolean isFinished = false;
	
	// This constructor is called when a new assessment frame is created
	public AssessmentFrame() {
		
		// Format the frame
		Utility.formatFrame(this);
		setLayout(null);
		
		// Fill the dialogue lists
		fillLists();

		// Fill the selected array
		for (int index = 0; index < answers.length; index++) {
			selectedArray[index] = new JTextField();
		}
		
		// Create the dialogue
		dialogue = new Dialogue(textList, iconList, currDialogue);
		
		// Set up the current dialogue receiver
		currDialogue.addActionListener(this);
		currDialogue.doClick();
		
		// Get each of the panels
		profilePanel = dialogue.getProfilePanel();
		namePanel = dialogue.getNamePanel();
		dialoguePanel = dialogue.getDialoguePanel();
		
		// Create the back panel
		backPanel = Utility.createBackPanel(this);
		
		// Set the bounds of each of the panels
		profilePanel.setBounds(1200, 200, 170, 170);
		namePanel.setBounds(25, 300, 200, 75);
		dialoguePanel.setBounds(25, 400, 1350, 300);
		
		// Add each of the panels
		add(profilePanel);
		add(namePanel);
		add(dialoguePanel);
		
		// Add the back panel
		backPanel.setBounds(0, 0, 300, 100);
		add(backPanel);
		
		// Show the frame
		setVisible(true);
		
	}
	
	// This method creates the text field
	private JTextField createTextField(final int questionIndex, int x, int y, int width, int height) {
		
		// Format the text field
		JTextField textField = new JTextField();
		Utility.formatTextField(textField);
		
		// Add it to the comp list for disposal
		compList.add(textField);
		
		// Add the text field
		textField.setBounds(x,y,width,height);
		dialogue.getDialoguePanel().add(textField);
		
		return textField;
		
	}

	// This method creates the options
	private void createOptions(final int questionIndex, String... options) {
		
		ButtonGroup group = new ButtonGroup();
		
		for (int buttonNum = 0; buttonNum < options.length; buttonNum++) {
			
			String option = options[buttonNum];
			
			JRadioButton optionButton = new JRadioButton(option);
			
			Utility.formatRadioButton(optionButton);

			final int selectedNum = buttonNum;
			optionButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("question index " + questionIndex + " selectedNum " + selectedNum);
					selectedArray[questionIndex].setText(selectedNum+"");
				}
			});
			
			if (buttonNum == 0) optionButton.doClick();
			
			compList.add(optionButton);
			group.add(optionButton);

			optionButton.setBounds(50, 100 + 30 * buttonNum, 200, 30);
			dialogue.getDialoguePanel().add(optionButton);
			
		}
		
	}

	// This method fills the lists
	private void fillLists() {

		textList.add("What is a method?");
		iconList.add(Icons.BASIL_PROFILE[0]);
		
		textList.add("What is a method?");
		iconList.add(Icons.BASIL_PROFILE[0]);
		
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
			
			// Get the current dialogue number
			int dialogueNum = Integer.parseInt(currDialogue.getText());
			
			switch (dialogueNum) {
			
				case -1:
					if (isFinished) {
						
						dispose();
						new TitleFrame();
						
					} else {

						int numCorrect = 0;
						for (int index = 0; index < selectedArray.length; index++) {
							JTextField tf = selectedArray[index];
							String text = tf.getText();
							
							if (text.equals(answers[index])) {
								numCorrect++;
							}
						}
						
						Utility.createQuickDialogue(this, currDialogue, 
								"you got " + numCorrect + " questions correct out of " + 
						answers.length + " total questions correct. ", Icons.BASIL_PROFILE[1]);
						
						isFinished = true;
						
					}
					break;
				case 0:
					createOptions(0, "A", "B", "C", "D");
					break;
				case 1:
					selectedArray[1] = createTextField(1, 50, 100, 100, 30);
					break;
				case 2:
					
			}
			
		}
		
	}
	
}
