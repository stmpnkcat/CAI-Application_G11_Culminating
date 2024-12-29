import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.Deque;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ActivityFrame extends JFrame implements ActionListener{

	private JPanel profilePanel;
	private JPanel namePanel;
	private JPanel dialoguePanel;
	private JPanel backPanel;
	private JPanel codePanel;
	private JPanel runPanel;
	
	private JLabel codeLabel;
	private JTextArea codeTextArea;
	private JLabel runLabel;
	private JButton runButton;
	
	private Board board = new Board(this);
	
	public ActivityFrame() {
		
		Utility.formatFrame(this);
		setLayout(null);
		
//		ArrayList<String> d = new ArrayList<>();
//		ArrayList<ImageIcon> i = new ArrayList<>();
//		
//		d.add("w");
//		i.add(Icons.BASIL_NEUTRAL);
//		
//		Dialogue dialogue = new Dialogue(d, i);
//		
//		profilePanel = dialogue.getProfilePanel();
//		namePanel = dialogue.getNamePanel();
//		dialoguePanel = dialogue.getDialoguePanel();
//		backPanel = Utility.createBackPanel(this);
//		
//		profilePanel.setBounds(1200, 200, 170, 170);
//		add(profilePanel);
//		
//		namePanel.setBounds(25, 300, 200, 75);
//		add(namePanel);
//		
//		dialoguePanel.setBounds(25, 400, 1350, 300);
//		add(dialoguePanel);
		
		board.setBounds(50, 105, CAIApplication.COLUMNS * CAIApplication.TILE_SIZE + 10, 
				CAIApplication.ROWS * CAIApplication.TILE_SIZE + 10);
		add(board);
		
		codePanel = new JPanel();
		Utility.formatPanel(codePanel);
		codePanel.setLayout(null);
		
		codeTextArea = new JTextArea();
		Utility.formatTextArea(codeTextArea);
		
		codeTextArea.setBounds(100, 300, 400, 200);
		codePanel.add(codeTextArea);
		
		codeLabel = new JLabel();
		codeLabel.setIcon(Icons.TEMPLATE_CODE);
		
		codeLabel.setBounds(5, 5, 560, 540);
		codePanel.add(codeLabel);
		
		codePanel.setBounds(800, 105, 570, 550);
		add(codePanel);
		
		runPanel = new JPanel();
		runPanel.setOpaque(false);
		
		runLabel = new JLabel("RUN");
		runLabel.setFont(Fonts.button);
		runPanel.add(runLabel);
		
		runButton = new JButton("X");
		runButton.addActionListener(this);
		runPanel.add(runButton);
		
		runPanel.setBounds(1000, 660, 300, 100);
		add(runPanel);
		
		backPanel = Utility.createBackPanel(this);
		
		backPanel.setBounds(0, 0, 300, 100);
		add(backPanel);

		codeTextArea.grabFocus();
		
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == runButton) {
			
			runButton.setEnabled(false);
			
			Deque<Integer> q = new ArrayDeque<>();
			q.add(4);
			q.add(-1);
			q.add(1);
			
			board.run(q);
			
		}
		
	}
	
	public void runButtonEnabled(boolean bool) {
		
		runButton.setEnabled(bool);
		
	}
	
}
