import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
//import 
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class LogInScreen extends JPanel implements MouseListener{
	
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
		
		submitButton = new JButton("Submit");
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
		
		
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//TODO
			}
			
		});
		
		//If default text, sets it to blank
		usernameTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if(usernameTextField.getText().equals("Username...")) {
					usernameTextField.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(usernameTextField.getText().equals("")) {
					usernameTextField.setText("Username...");
				}
			}
			
		});
		
		//If default text, sets it to blank
		passwordTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if(passwordTextField.getText().equals("Password...")) {
					passwordTextField.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(passwordTextField.getText().equals("")) {
					passwordTextField.setText("Password...");
				}
			}
			
		});
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		infoLabel.requestFocusInWindow();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
