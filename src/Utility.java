import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/*
 * This class provides other classes with utility methods
 * It helps with formatting and other features
 */
public class Utility {

	// Method to play a sound
	public static Clip playSound(String soundFile, boolean isLooping) {
		
		Clip clip = null;

		// Try/catch for getting the file
		// https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
		try {

			File file = new File("./" + soundFile); // Create the sound file
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(file.toURI().toURL()); // Create the audo input
																								// stream using the file
			clip = AudioSystem.getClip(); // Get the clip of the sound
			clip.open(audioIn); // Open the clip
			
			if (isLooping) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				CAIApplication.clipList.add(clip);
			}
			
			clip.start(); // Start the clip

		} catch (Exception e) {

			System.err.println(e.getMessage());

		}
		
		return clip;

	}
	
	// This method scales the image given
	public static ImageIcon scaleImageIcon(ImageIcon imageIcon, int newWidth, int newHeight) {
		return new ImageIcon(imageIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT));
	}
	

	// This method formats the frame given
	public static void formatFrame(JFrame frame) {
		
		frame.setSize(CAIApplication.SCREEN_WIDTH, CAIApplication.SCREEN_HEIGHT); // Set the size
		frame.setTitle("Modular Programming - Methods"); // Set the title
		frame.setIconImage(Icons.LOGO.getImage()); // Set the logo
		frame.getContentPane().setBackground(new Color(200, 255, 200)); // Set the background color
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the frame to close when the program ends
		frame.setResizable(false); // Stop the frame from being resized
		
	}

	// This method formats the panel given
	public static void formatPanel(JPanel panel) {
		
		panel.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK, 1), 
				new LineBorder(Color.white, 5))); // Set the white border
		panel.setBackground(Color.BLACK); // Color background black
		
	}
	
	// This method formats the title button
	public static void formatTitleButton(JButton button) {

		// Change the button's appearance
		button.setFont(Fonts.button);
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(0, 50, 50));
		
		// Create the border for the button
		Border empty = BorderFactory.createEmptyBorder(0, 25, 25, 25);

		Border line = BorderFactory.createLineBorder(Color.BLACK, 1);
		Border line2 = BorderFactory.createLineBorder(Color.white, 5);
		
		Border compound = BorderFactory.createCompoundBorder(line, empty);
		
		Border compound2 = BorderFactory.createCompoundBorder(line, line2);
		
		Border compoundFinal = BorderFactory.createCompoundBorder(compound2, compound);
		
		// Set the border for the button
		button.setBorder(compoundFinal);
	}
	
	// This method formats the frame given
	public static void formatButton(JButton button) {
		
		// Change the button's appearance
		button.setFont(Fonts.button);
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLACK);
		
		button.setOpaque(false);
		button.setBorderPainted(false);
		
	}
	
	// This method formats the textarea given
	public static void formatTextArea(JTextArea textArea) {

		textArea.setFont(Fonts.code);
		textArea.setRows(5);
		textArea.setCaretColor(Color.WHITE);
		textArea.setBackground(new Color(0, 50, 50));
		textArea.setForeground(Color.WHITE);
		textArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 1), 
				BorderFactory.createEmptyBorder(5,5,5,5)));
		
	}
	
	// This method formats the radio button given
	public static void formatRadioButton(JRadioButton rb) {

		rb.setFont(Fonts.rb);
		rb.setForeground(Color.WHITE);
		
		rb.setOpaque(false);
		rb.setContentAreaFilled(false);
		rb.setBorderPainted(false);
		
	}

	// This method formats the text field given
	public static void formatTextField(JTextField tf) {
		
		tf.setFont(Fonts.dialogue);
		tf.setForeground(Color.WHITE);
		tf.setBackground(new Color(0, 50, 50));
		tf.setCaretColor(Color.WHITE);
		tf.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.WHITE, 1),
				BorderFactory.createEmptyBorder(0, 10, 0, 0))); // Create outline border
		
	}
	
	public static JPanel createBackPanel(final JFrame frame) {
	    // The clip is not reassigned, so it can be effectively final
	    JPanel backPanel = new JPanel();
	    backPanel.setOpaque(false);

	    JButton titleButton = new JButton();
	    titleButton.setIcon(scaleImageIcon(Icons.ARROW_LEFT, 100, 100));
	    formatButton(titleButton);

	    titleButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {

	        	for (Clip clip : CAIApplication.clipList) {
	        		clip.stop();
	        	}
	        	
	        	playSound("sounds/cancel.wav", false);
	        	
	            frame.dispose();
	            new TitleFrame();
	        }
	    });

	    JLabel backLabel = new JLabel("BACK");
	    backLabel.setForeground(Color.BLACK);
	    backLabel.setFont(Fonts.button2);

	    backPanel.add(titleButton);
	    backPanel.add(backLabel);

	    return backPanel;
	}

	// This method creates a quick one line dialogue
	public static Dialogue createQuickDialogue(JFrame frame, JButton currIndexButton, String text, ImageIcon profile) {
		
		// Create lists
		ArrayList<String> dialogueList = new ArrayList<>();
		ArrayList<ImageIcon> imageList = new ArrayList<>();
		
		// Add to the lists
		dialogueList.add(text);
		imageList.add(profile);
		
		// Create the dialogue
		Dialogue dialogue = new Dialogue(dialogueList, imageList, currIndexButton);
		
		// Create the panels
		JPanel profilePanel = dialogue.getProfilePanel();
		JPanel namePanel = dialogue.getNamePanel();
		JPanel dialoguePanel = dialogue.getDialoguePanel();
		
		// Set bounds of the panels
		profilePanel.setBounds(1205, 200, 170, 170);
		namePanel.setBounds(25, 300, 200, 75);
		dialoguePanel.setBounds(25, 400, 1350, 300);
		
		// Add the panels
		frame.add(profilePanel);
		frame.add(namePanel);
		frame.add(dialoguePanel);
		
		return dialogue;
		
	}
	
}
