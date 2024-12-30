import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class ConceptsFrame extends JFrame implements ActionListener{
	
	private JPanel profilePanel;
	private JPanel namePanel;
	private JPanel dialoguePanel;
	private JPanel backPanel;
	
	private ArrayList<JPanel> diagramList = new ArrayList<>();

	private JButton currIndexButton = new JButton("0");
	
	public ConceptsFrame() {
		
		Utility.formatFrame(this);
		setLayout(null);
		
		ArrayList<String> q = new ArrayList<>();
		q.add("error");
		
		ArrayList<ImageIcon> a = new ArrayList<>();
		a.add(Icons.BASIL_ANGRY);
		
		Dialogue dialogue = new Dialogue(q, a, currIndexButton);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == currIndexButton) {
			
			for (JPanel panel : diagramList) {
				
				panel.setVisible(false);
				panel.setEnabled(false);
				remove(panel);
				
			}
			
			int currIndex = Integer.parseInt(currIndexButton.getText());
			
			switch (currIndex) {
			
				case -1:
					
					dispose();
					new TitleFrame();
					
				case 1:
					
					JPanel diagramPanel = new JPanel();
					diagramPanel.setOpaque(false);
					
					JLabel diagramLabel = new JLabel(Icons.DIAGRAM_1);
					diagramPanel.add(diagramLabel);
					
					diagramPanel.setBounds(300, 150, 500, 500);
					add(diagramPanel);
					
					diagramList.add(diagramPanel);
					
					break;
					
				case 2:
					
			
			}
			
		}
		
	}
	
}
