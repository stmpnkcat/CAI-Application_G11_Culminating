import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class AssessmentFrame extends JFrame implements ActionListener{
	
	private JPanel profilePanel;
	private JPanel namePanel;
	private JPanel dialoguePanel;

	Dialogue dialogue;
	
	private JPanel backPanel;

	private JButton currIndexButton = new JButton("0");

	private ArrayList<Component> compList = new ArrayList<>();
	private ArrayList<String> textList = new ArrayList<>();
	private ArrayList<ImageIcon> iconList = new ArrayList<>();
	
	private int[] selectedArray = new int[10];
	
	public AssessmentFrame() {
		
		Utility.formatFrame(this);
		setLayout(null);
		
		fillLists();
		
		dialogue = new Dialogue(textList, iconList, currIndexButton);
		
		currIndexButton.addActionListener(this);
		currIndexButton.doClick();
		
		profilePanel = dialogue.getProfilePanel();
		namePanel = dialogue.getNamePanel();
		dialoguePanel = dialogue.getDialoguePanel();
		backPanel = Utility.createBackPanel(this);
		
		profilePanel.setBounds(1200, 200, 170, 170);
		add(profilePanel);
		
		namePanel.setBounds(25, 300, 200, 75);
		add(namePanel);
		
		dialoguePanel.setBounds(25, 400, 1350, 300);
		add(dialoguePanel);
		
		backPanel.setBounds(0, 0, 300, 100);
		add(backPanel);
		
		setVisible(true);
		
	}

	private void addOptions(int questionIndex, String... options) {
		
		ButtonGroup group = new ButtonGroup();
		
		for (int buttonNum = 0; buttonNum < options.length; buttonNum++) {
			
			String option = options[buttonNum];
			
			JRadioButton optionButton = new JRadioButton(option);
			
			Utility.formatRadioButton(optionButton);
			
			final int selectedIndex = questionIndex;
			final int selectedNum = buttonNum;
			optionButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectedArray[selectedIndex] = selectedNum;
				}
			});
			
			compList.add(optionButton);
			group.add(optionButton);

			optionButton.setBounds(50, 100 + 30 * buttonNum, 200, 30);
			dialogue.getDialoguePanel().add(optionButton);
			
		}
		
	}

	private void fillLists() {

		textList.add("What is a method?");
		iconList.add(Icons.BASIL_NEUTRAL);
		
		textList.add("What is a method?");
		iconList.add(Icons.BASIL_NEUTRAL);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == currIndexButton) {
			
			for (Component comp : compList) {
				
				comp.setVisible(false);
				comp.setEnabled(false);
				remove(comp);
				
			}
			
			int currIndex = Integer.parseInt(currIndexButton.getText());
			
			switch (currIndex) {
			
				case -1:
					
				case 0:                                                                                                                                                                                                                                                               
					addOptions(1, "A", "B", "C");
			}
		}
		
	}
	
}
