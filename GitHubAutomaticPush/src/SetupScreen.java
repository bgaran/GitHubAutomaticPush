import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
//import 
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class SetupScreen extends JPanel{
	
	String filePath;
	//GitHub github
	
	private JLabel infoLabel, filePathLabel, feedbackLabel;
	
	private JTextField filePathTextField;
	
	private JFileChooser fileChooser; //TODO: INITIALIZE IN CONSTRUCTOR & IMPLEMENT
	
	//TODO: IMPLEMENT RADIO BUTTONS TO SELECT OS IF OS CANNOT BE AUTOMATICALLY DETECTED BY BRE & COURTNEY
	
	private JButton submitButton, browseButton;
	
	private JToggleButton darkModeToggleButton; //used to toggle between dark mode and light mode
	
	private Font bigWords; //used to resize the text

	public SetupScreen(int width, int height, Color bgColor) {
		setPreferredSize(new Dimension(width, height));
		setBackground(bgColor); 

		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		
		//initialize all UI elements
		infoLabel = new JLabel("Please input your github credentials below.");
		filePathLabel = new JLabel("File path to current project src folder: ");
		feedbackLabel = new JLabel("");
		
		filePathTextField = new JTextField("ex. H:\\workspace\\CS128 HW1\\src\\");
		
		submitButton = new JButton("Submit");
		browseButton = new JButton("Browse...");
		darkModeToggleButton = new JToggleButton("Toggle Dark Mode Theme");
		
		bigWords = new Font("Sans Serif", Font.PLAIN, width/30); //Makes it so the text is easily seeable, using one 30th of the window's width for the font size
		
		//set all UI elements to share this same font
		infoLabel.setFont(bigWords);
		filePathLabel.setFont(bigWords);
		feedbackLabel.setFont(bigWords);
		filePathTextField.setFont(bigWords);
		submitButton.setFont(bigWords);
		browseButton.setFont(bigWords);
		darkModeToggleButton.setFont(bigWords);
		
		//position all UI elements correctly
		infoLabel.setPreferredSize(new Dimension(width*3/4,height/4)); //width*3/4 is a janky way of ensuring it's on its own line
		filePathLabel.setPreferredSize(new Dimension(width/4,height/8)); 
		filePathTextField.setPreferredSize(new Dimension(width/2,height/8)); 
		submitButton.setPreferredSize(new Dimension(width/2,height/8)); 
		feedbackLabel.setPreferredSize(new Dimension(width*3/4,height/5)); //width*3/4 is a janky way of ensuring it's on its own line
		darkModeToggleButton.setPreferredSize(new Dimension(width/2,height/8)); 

		
		//finally, add all UI elements to the LogInScreen
		this.add(infoLabel);
		this.add(filePathLabel);
		this.add(filePathTextField);
		this.add(submitButton);
		this.add(feedbackLabel);
		this.add(darkModeToggleButton);
		
		
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//TODO
			}
			
		});
		
		//If default text, sets it to blank
		filePathTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if(filePathTextField.getText().equals("ex. H:\\workspace\\CS128 HW1\\src\\")) {
					filePathTextField.setText("");
					filePathTextField.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(filePathTextField.getText().equals("")) {
					filePathTextField.setText("ex. H:\\workspace\\CS128 HW1\\src\\");
					filePathTextField.setForeground(Color.gray);
				}
			}
			
		});
	}
}
