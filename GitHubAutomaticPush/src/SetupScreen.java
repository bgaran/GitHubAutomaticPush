import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
//import 
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class SetupScreen extends JPanel{
	
	String filePath;
	private GitHubHelper GHH = new GitHubHelper();
	
	private FrameManager frameManager; //LINKED TO SWAP PANELS
	
	private JLabel infoLabel, filePathLabel, feedbackLabel;
	
	private JTextField filePathTextField;
	
	private JFileChooser fileChooser; //TODO: INITIALIZE IN CONSTRUCTOR & IMPLEMENT
	
	private JRadioButton windowsButton, macButton;
		
	private JButton submitButton, browseButton;
	
	private JToggleButton darkModeToggleButton; //used to toggle between dark mode and light mode
	
	private Font bigWords, mediumWords; //used to resize the text

	public SetupScreen(int width, int height, Color bgColor, FrameManager frameManager) {
		setPreferredSize(new Dimension(width, height));
		setBackground(bgColor); 

		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		
		this.frameManager = frameManager;
		
		//initialize all UI elements
		infoLabel = new JLabel("Please input your file path to current project src folder.");
		filePathLabel = new JLabel("File path:");
		feedbackLabel = new JLabel("");
		
		filePathTextField = new JTextField("ex. H:\\workspace\\CS128 HW1\\src\\");
		
		submitButton = new JButton("Submit");
		browseButton = new JButton("Browse...");
		
		//radio buttons and their group
		windowsButton = new JRadioButton("Windows");
		macButton = new JRadioButton("Mac");
		ButtonGroup group = new ButtonGroup();
		group.add(windowsButton);
		group.add(macButton);
		
		
		darkModeToggleButton = new JToggleButton("Dark Mode");
		
		bigWords = new Font("Sans Serif", Font.PLAIN, width/30); //Makes it so the text is easily seeable, using one 30th of the window's width for the font size
		mediumWords = new Font("Sans Serif", Font.PLAIN, width/60);
		
		//set all UI elements to share this same font
		infoLabel.setFont(bigWords);
		filePathLabel.setFont(bigWords);
		feedbackLabel.setFont(bigWords);
		filePathTextField.setFont(bigWords);
		submitButton.setFont(bigWords);
		browseButton.setFont(bigWords);
		windowsButton.setFont(mediumWords);
		macButton.setFont(mediumWords);
		darkModeToggleButton.setFont(bigWords);
		
		//position all UI elements correctly
		infoLabel.setPreferredSize(new Dimension(width*3/4,height/4)); //width*3/4 is a janky way of ensuring it's on its own line
		filePathLabel.setPreferredSize(new Dimension(width/4,height/8)); 
		filePathTextField.setPreferredSize(new Dimension(width/2,height/8)); 
		submitButton.setPreferredSize(new Dimension(width/2,height/8)); 
		feedbackLabel.setPreferredSize(new Dimension(width*3/4,height/5)); //width*3/4 is a janky way of ensuring it's on its own line
		windowsButton.setPreferredSize(new Dimension(width/8,height/10));
		macButton.setPreferredSize(new Dimension(width/8,height/10));
		darkModeToggleButton.setPreferredSize(new Dimension(width/2,height/8)); 

		
		//finally, add all UI elements to the SetupScreen
		this.add(infoLabel);
		this.add(filePathLabel);
		this.add(filePathTextField);
		this.add(submitButton);
		this.add(windowsButton);
		this.add(macButton);
		this.add(feedbackLabel);
		this.add(darkModeToggleButton);
		
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//filePath = filePathTextField.getText();
				//GHH.ConnectToGitHub(username, password, filePath);
				
				//REPLACE THIS CODE LATER
				infoLabel.setText("success");
				//if infoLabel 
				if(infoLabel.getText().equals("success")) {
					frameManager.swapPanel("center");
				}
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
		
		//If Windows radio button is selected
		windowsButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e){
				if(e.getSource()==windowsButton) {
					//TODO
				}
			}
		});
		
		//If Mac radio button is selected
		macButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e){
				if(e.getSource()==macButton) {
					//TODO
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
                    filePathLabel.setForeground(Color.white);
                    windowsButton.setBackground(Color.DARK_GRAY);
                    windowsButton.setForeground(Color.white);
                    macButton.setBackground(Color.DARK_GRAY);
                    macButton.setForeground(Color.white);
                    feedbackLabel.setForeground(Color.white);
                    
                } 
                else {  
                	//switch OFF dark mode
                    setBackground(bgColor);
                    infoLabel.setForeground(Color.black);
                    filePathLabel.setForeground(Color.black);
                    windowsButton.setBackground(Color.white);
                    windowsButton.setForeground(Color.black);
                    macButton.setBackground(Color.white);
                    macButton.setForeground(Color.black);
                    feedbackLabel.setForeground(Color.black);
                }
			}
			
		});
		
		//Detect the user's OS and selects the respective radio button
		if(System.getProperty("os.name").contains("Windows") || System.getProperty("os.name").contains("windows")) {
			windowsButton.setSelected(true);
		} else if (System.getProperty("os.name").contains("Mac") || System.getProperty("os.name").contains("mac")) {
			macButton.setSelected(true);
		}
				
	}
}
