import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ConceptsFrame extends JFrame {
	
	private JPanel profilePanel;
	private JPanel namePanel;
	private JPanel dialoguePanel;
	
	public ConceptsFrame() {
		
		Utility.formatFrame(this);
		setLayout(null);
		
		Queue<String> q = new LinkedList<String>();
		q.add("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAA AAAAAAAAA AAAAAAAAA AAAAAAA AAAAAAAA AAAAAAAAAA AAAAAA AAAA");
		
		Dialogue dialogue = new Dialogue(q, Icons.BASIL_NEUTRAL);
		
		profilePanel = dialogue.getProfilePanel();
		namePanel = dialogue.getNamePanel();
		dialoguePanel = dialogue.getDialoguePanel();
		
		profilePanel.setBounds(1200, 200, 170, 170);
		add(profilePanel);
		
		namePanel.setBounds(25, 300, 200, 75);
		add(namePanel);
		
		dialoguePanel.setBounds(25, 400, 1350, 300);
		add(dialoguePanel);
		
		setVisible(true);
		
	}
	
}
