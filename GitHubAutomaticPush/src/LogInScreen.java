import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
//import 
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class LogInScreen extends JPanel{
	
	String filePath;
	//GitHub github
	
	private JLabel infoLabel, usernameLabel, passwordLabel, feedbackLabel;
	
	private JTextField usernameTextField, passwordTextField;
	
	private JButton submitButton;
	
	private JToggleButton darkModeToggleButton; //used to toggle between dark mode and light mode
	
	private Font bigWords; //used to resize the text

	public LogInScreen(int width, int height, Color bgColor) {
		setPreferredSize(new Dimension(width, height));
		setBackground(bgColor); 

		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		
		//initialize all UI elements
		infoLabel = new JLabel("Please input your github credentials below.");
		usernameLabel = new JLabel("Username: ");
		passwordLabel = new JLabel("Password: ");
		feedbackLabel = new JLabel("");
		
		usernameTextField = new JTextField("Username...");
		passwordTextField = new JTextField("Password...");
		
		submitButton = new JButton("submit");
		darkModeToggleButton = new JToggleButton("Toggle Dark Mode Theme");
		
		bigWords = new Font("Sans Serif", Font.PLAIN, width/30); //Makes it so the text is easily seeable, using one 30th of the window's width for the font size
		
		//set all UI elements to share this same font
		infoLabel.setFont(bigWords);
		usernameLabel.setFont(bigWords);
		passwordLabel.setFont(bigWords);
		feedbackLabel.setFont(bigWords);
		usernameTextField.setFont(bigWords);
		passwordTextField.setFont(bigWords);
		submitButton.setFont(bigWords);
		darkModeToggleButton.setFont(bigWords);
		
		//position all UI elements correctly
		infoLabel.setPreferredSize(new Dimension(width*3/4,height/4)); //width*3/4 is a janky way of ensuring it's on its own line
		usernameLabel.setPreferredSize(new Dimension(width/4,height/8)); 
		usernameTextField.setPreferredSize(new Dimension(width/2,height/8)); 
		passwordLabel.setPreferredSize(new Dimension(width/4,height/8)); 
		passwordTextField.setPreferredSize(new Dimension(width/2,height/8)); 
		submitButton.setPreferredSize(new Dimension(width/2,height/8)); 
		feedbackLabel.setPreferredSize(new Dimension(width*3/4,height/5)); //width*3/4 is a janky way of ensuring it's on its own line
		darkModeToggleButton.setPreferredSize(new Dimension(width/2,height/8)); 

		
		//finally, add all UI elements to the LogInScreen
		this.add(infoLabel);
		this.add(usernameLabel);
		this.add(usernameTextField);
		this.add(passwordLabel);
		this.add(passwordTextField);
		this.add(submitButton);
		this.add(feedbackLabel);
		this.add(darkModeToggleButton);
		
	}
}
