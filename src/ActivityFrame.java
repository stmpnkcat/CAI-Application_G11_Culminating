import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import javax.swing.ImageIcon;
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
			
			String text = codeTextArea.getText();
			String[] lines = text.split("\n");
			
			Deque<Integer> commandsQueue = new ArrayDeque<>();
			
			boolean isValid = true;
			
			for (String line : lines) {

				if ((line.indexOf("move(") == 0 || line.indexOf("move (") == 0)
						&& line.indexOf(");") == line.length() - 2) {
					
					final String numbers = "1234567890";
					
					int startIndex = line.indexOf('(') + 1;
					int endIndex = line.lastIndexOf(')');
					
					String numberString = "";
					
					for (int i = startIndex; i < endIndex; i++) {
						
						char currChar = line.charAt(i);
						
						if (!numbers.contains("" + currChar)) 
							isValid = false;
						else {
							numberString += currChar;
						}
						
					}
					
					if (isValid) commandsQueue.add(Integer.parseInt(numberString));
					
				} else if (line.indexOf("rotate_clockwise();") == 0 || line.indexOf("rotate_clockwise ();") == 0) {
					
					commandsQueue.add(-2);
					
				} else if (line.indexOf("rotate_counter_clockwise();") == 0 || line.indexOf("rotate_counter_clockwise ();") == 0) {
					
					commandsQueue.add(-1);
					
				} else {

					isValid = false;
					
				}

			}

			if (isValid) {
				
				board.run(commandsQueue);
				
			} else {
				
				runButton.setEnabled(true);
				
				ArrayList<String> dialogueList = new ArrayList<>();
				ArrayList<ImageIcon> imageList = new ArrayList<>();
				
				dialogueList.add("error");
				imageList.add(Icons.BASIL_ANGRY);
				
				Dialogue dialogue = new Dialogue(dialogueList, imageList);
				
				profilePanel = dialogue.getProfilePanel();
				
				namePanel = dialogue.getNamePanel();
				
				dialoguePanel = dialogue.getDialoguePanel();
				
				profilePanel.setBounds(1200, 200, 170, 170);
				namePanel.setBounds(25, 300, 200, 75);
				dialoguePanel.setBounds(25, 400, 1350, 300);
				
				add(profilePanel);
				add(namePanel);
				add(dialoguePanel);
				
			}
			
		}
		
	}
	
	public void runButtonEnabled(boolean bool) {
		
		runButton.setEnabled(bool);
		
	}
	
}
