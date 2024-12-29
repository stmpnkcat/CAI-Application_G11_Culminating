import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.LineBorder;

/*
 * This class provides other classes with utility methods
 * It helps with formatting and other features
 */
public class Utility {

	// Method to play a sound
	public static void playSound(String soundFile) {

		// Try/catch for getting the file
		// https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
		try {

			File file = new File("./" + soundFile); // Create the sound file
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(file.toURI().toURL()); // Create the audo input
																								// stream using the file
			Clip clip = AudioSystem.getClip(); // Get the clip of the sound
			clip.open(audioIn); // Open the clip
			clip.start(); // Start the clip

		} catch (Exception e) {

			System.err.println(e.getMessage());

		}

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
		frame.getContentPane().setBackground(new Color(0, 255, 100)); // Set the background color
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the frame to close when the program ends
		frame.setResizable(false); // Stop the frame from being resized

	}

	// This method formats the panel given
	public static void formatPanel(JPanel panel) {
		
		panel.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK, 1), new LineBorder(Color.WHITE, 5))); // Set the white border
		panel.setBackground(Color.BLACK); // Color background black
		
	}
	
	// This method formats the frame given
	public static void formatButton(JButton button) {
		
		// Change the button's appearance
		button.setFont(Fonts.button);
		button.setForeground(Color.BLACK);
		
		// Make the button's background invisible
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		
	}
	
	// This method formats the textarea given
	public static void formatTextArea(JTextArea textArea) {

		textArea.setFont(Fonts.code);
		textArea.setRows(5);
		textArea.setCaretColor(Color.WHITE);
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.WHITE);
		textArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 1), 
				BorderFactory.createEmptyBorder(5,5,5,5)));
		
	}
	
	public static JPanel createBackPanel(final JFrame frame) {
		
		JPanel backPanel = new JPanel();
		backPanel.setOpaque(false);
		
		JButton titleButton = new JButton();
		titleButton.setIcon(scaleImageIcon(Icons.ARROW_LEFT, 100, 100));
		formatButton(titleButton);
		
		titleButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				new TitleFrame();
				
			}
			
		});
		
		backPanel.add(titleButton);
		
		JLabel backLabel = new JLabel("BACK");
		backLabel.setFont(Fonts.button);
		
		backPanel.add(backLabel);
		
		return backPanel;
		
	}
		
}
