import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JLabel;
//import 
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class LoginScreen extends JPanel{
	
	String filePath;
	//GitHub github
	
	private FrameManager frameManager; //LINKED TO SWAP PANELS
	
	private JLabel infoLabel, usernameLabel, passwordLabel, feedbackLabel;
	
	private JTextField usernameTextField, passwordTextField;
	
	private JButton submitButton;
	
	private JToggleButton darkModeToggleButton; //used to toggle between dark mode and light mode
	
	private Font bigWords; //used to resize the text
	
	public String username, password;
	
	private GridBagLayout gbl;
	
	private GridBagConstraints gbc;

	public LoginScreen(int width, int height, Color bgColor, FrameManager frameManager) {
		setPreferredSize(new Dimension(width, height));
		setBackground(bgColor); 

		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		
		this.frameManager = frameManager;
		
		gbl = new GridBagLayout();
		
		gbc = new GridBagConstraints();
		
		this.setLayout(gbl);
		
		//initialize all UI elements
		infoLabel = new JLabel("Please input your github credentials below.");
		usernameLabel = new JLabel("Username: ");
		passwordLabel = new JLabel("Password: ");
		feedbackLabel = new JLabel("");
		
		usernameTextField = new JTextField("Username...");
		passwordTextField = new JTextField("Password...");
		
		submitButton = new JButton("Submit");
		darkModeToggleButton = new JToggleButton("Dark Mode");
		
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
		
		//initialize color of text fields to default, change color once focus gained
		usernameTextField.setForeground(Color.gray);
		passwordTextField.setForeground(Color.gray);
		
//		//position all UI elements correctly
//		infoLabel.setPreferredSize(new Dimension(width-1,height/4)); //width-1 is a janky way of ensuring it's on its own line
//		usernameLabel.setPreferredSize(new Dimension(width/4,height/8)); 
//		usernameTextField.setPreferredSize(new Dimension(width/2,height/8)); 
//		passwordLabel.setPreferredSize(new Dimension(width/4,height/8)); 
//		passwordTextField.setPreferredSize(new Dimension(width/2,height/8)); 
//		submitButton.setPreferredSize(new Dimension(width/2,height/8)); 
//		feedbackLabel.setPreferredSize(new Dimension(width-1,height/5)); //width-1 is a janky way of ensuring it's on its own line
//		darkModeToggleButton.setPreferredSize(new Dimension(width/2,height/8)); 

		
		//finally, add all UI elements to the LogInScreen
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		this.add(infoLabel, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weighty = .33;
		this.add(usernameLabel, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weighty = .66;
		this.add(usernameTextField, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weighty = .66;
		this.add(passwordLabel, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weighty = .33;
		this.add(passwordTextField, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 0;
		gbc.gridwidth = 2;
		this.add(submitButton, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		this.add(feedbackLabel, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		this.add(darkModeToggleButton, gbc);
		
		
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				username = usernameTextField.getText();
				password = passwordTextField.getText();
				
				//REPLACE THIS CODE LATER
				feedbackLabel.setText("success");
				//if feedbackLabel 
				if(feedbackLabel.getText().equals("success")) {
					frameManager.swapPanel(FrameManager.swappablePanel.SETUP);
				}
			}
			
		});
		
		//If default text, sets it to blank
		usernameTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if(usernameTextField.getText().equals("Username...")) {
					usernameTextField.setText("");
					usernameTextField.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(usernameTextField.getText().equals("")) {
					usernameTextField.setText("Username...");
					usernameTextField.setForeground(Color.gray);
				}
			}
			
		});
		
		//If default text, sets it to blank
		passwordTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if(passwordTextField.getText().equals("Password...")) {
					passwordTextField.setText("");
					passwordTextField.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(passwordTextField.getText().equals("")) {
					passwordTextField.setText("Password...");
					passwordTextField.setForeground(Color.gray);
				}
			}
			
		});
		
		//When darkModeToggleButton is toggled
		darkModeToggleButton.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				
                if (state == ItemEvent.SELECTED) { 
                	//switch ON dark mode
                    setBackground(Color.DARK_GRAY);
                    infoLabel.setForeground(Color.white);
                    usernameLabel.setForeground(Color.white);
                    passwordLabel.setForeground(Color.white);
                    feedbackLabel.setForeground(Color.white);
                    
                } 
                else {  
                	//switch OFF dark mode
                    setBackground(bgColor);
                    infoLabel.setForeground(Color.black);
                    usernameLabel.setForeground(Color.black);
                    passwordLabel.setForeground(Color.black);
                    feedbackLabel.setForeground(Color.black);
                }
			}
			
		});
		
	}
}
