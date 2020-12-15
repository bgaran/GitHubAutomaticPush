import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import 
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class SetupScreen extends JPanel{
	
	private FrameManager frameManager; //LINKED TO SWAP PANELS
	
	private JLabel infoLabel, filePathLabel, feedbackLabel;
	
	private JTextField filePathTextField;
	
	private JFileChooser fileChooser;
	
	private String filePath;
		
	private JButton submitButton, browseButton, backButton; //TODO: implement backButton
	
	private JToggleButton darkModeToggleButton; //used to toggle between dark mode and light mode
	
	private Font bigWords, mediumWords; //used to resize the text
	
	private GridBagLayout gbl; //layout
	
	private GridBagConstraints gbc; //constraints for individual components for gbl

	public SetupScreen(int width, int height, Color bgColor, FrameManager frameManager) {
		setPreferredSize(new Dimension(width, height));
		setBackground(bgColor); 

		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		
		this.frameManager = frameManager;
		
		gbl = new GridBagLayout();
		
		gbc = new GridBagConstraints();
		
		this.setLayout(gbl);
		
		//initialize all UI elements
		infoLabel = new JLabel("Please input your file path to current project src folder.");
		filePathLabel = new JLabel("File path:");
		feedbackLabel = new JLabel("");

		filePathTextField = new JTextField("ex. H:\\workspace\\CS128 HW1\\src\\");
		
		submitButton = new JButton("Submit");
		browseButton = new JButton("Browse...");
//		backButton = new JButton("Back");
		
		darkModeToggleButton = new JToggleButton("Dark Mode");
		
		//initialize file chooser
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setDialogTitle("Select the current project's src folder:");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setPreferredSize(new Dimension(width/2,height/2));
		
		
		//create fonts
		bigWords = new Font("Sans Serif", Font.PLAIN, width/30); //Makes it so the text is easily seeable, using one 30th of the window's width for the font size
		mediumWords = new Font("Sans Serif", Font.PLAIN, width/60);
		
		//set all UI elements to share this same font
		infoLabel.setFont(bigWords);
		filePathLabel.setFont(bigWords);
		feedbackLabel.setFont(bigWords);
		filePathTextField.setFont(bigWords);
		submitButton.setFont(bigWords);
//		backButton.setFont(bigWords);
		browseButton.setFont(bigWords);
		darkModeToggleButton.setFont(bigWords);
		
		//initialize color of text fields to default, change color once focus gained
		filePathTextField.setForeground(Color.gray);

		//align all UI elements correctly
	    infoLabel.setHorizontalAlignment(JLabel.CENTER);
		
		//finally, add all UI elements to the SetupScreen
	    gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0; //center
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.weighty = .25;
		this.add(infoLabel, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0; 
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = .33;
		this.add(filePathLabel, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1; 
		gbc.gridy = 1;
		gbc.weightx = .33;
		this.add(browseButton, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2; 
		gbc.gridy = 1;
		gbc.weightx = .9;
		this.add(filePathTextField, gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0; 
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		gbc.weightx = .25;
		this.add(submitButton, gbc);
		
//		gbc.fill = GridBagConstraints.NONE;
//		gbc.gridx = 0; 
//		gbc.gridy = 3;
//		gbc.gridwidth = 3;
//		this.add(backButton, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0; 
		gbc.gridy = 4;
		gbc.gridwidth = 3;
		this.add(feedbackLabel, gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0; 
		gbc.gridy = 5;
		gbc.gridwidth = 3;
		this.add(darkModeToggleButton, gbc);
		
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!filePathTextField.getText().equals("ex. H:\\workspace\\CS128 HW1\\src\\") && !filePathTextField.getText().equals("")) { //simple checking for 
					filePath = filePathTextField.getText();
					//GHH.ConnectToGitHub(username, password, filePath);
					frameManager.git.projectPath=filePath;
					
					feedbackLabel.setText("");
					if(feedbackLabel.getText().equals("")) {
						frameManager.swapPanel(FrameManager.swappablePanel.CENTER);
					}
				}else {
					feedbackLabel.setText("Please input a file path");
				}
			}
			
		});
		
		SetupScreen setup = this; //used for the showOpenDialog method that prompts the user to choose what directory to use
		
		browseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (fileChooser.showOpenDialog(setup) == JFileChooser.APPROVE_OPTION) { 
				    filePathTextField.setText(""+fileChooser.getSelectedFile());
				    feedbackLabel.setText("Please select your src folder.");
				}
				else {
					feedbackLabel.setText("Please select your src folder.");
					//TODO check for whether or not the file path ends in /src/, if not, warn the user.
				}
			}
			
		});
		
//		backButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {	
//					frameManager.swapPanel(FrameManager.swappablePanel.LOGIN);
//			}
//			
//		});
		
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
		
		//When darkModeToggleButton is toggled
		darkModeToggleButton.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				
				if (state == ItemEvent.SELECTED) { 
                	//switch ON dark mode
                	frameManager.isDarkMode=true;
                } 
                else {  
                	//switch OFF dark mode
                	frameManager.isDarkMode=false;
                }
                
                updateUITheme(frameManager.isDarkMode);
			}
			
		});				
	}
	
	/**
	 * Updates the aesthetics of the current panel based on whether it is light or dark mode
	 * NOTE: does not update isDarkMode in frame manager
	 * @param isDarkMode - if this is set to dark mode or not
	 * 
	 * @author Griffin White
	 * @author April Miller
	 */
	public void updateUITheme(boolean isDarkMode) {
		if(isDarkMode) {
			//switch ON dark mode
			setBackground(Color.DARK_GRAY);
            infoLabel.setForeground(Color.white);
            filePathLabel.setForeground(Color.white);
            feedbackLabel.setForeground(Color.white);
		}
		else {
			setBackground(Color.WHITE);
            infoLabel.setForeground(Color.black);
            filePathLabel.setForeground(Color.black);
            feedbackLabel.setForeground(Color.black);
		}
	}
	
	/**
	 * Called every time repaint() is called, used to update UI Theme from FrameManager
	 * 
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * 
	 * @author Griffin White
	 */
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D)graphics;
		
		//update UI theme when repaint() is called
		updateUITheme(frameManager.isDarkMode);
		
		if(this.frameManager.isDarkMode) { //if it is in dark mode
			darkModeToggleButton.setSelected(true); //set it so the togglebutton is on
		}
		else {
			darkModeToggleButton.setSelected(false); //set it so the togglebutton is off
		}
		
		//reset feedbackLabel
		feedbackLabel.setText("");
	}
	
	//private void (set)
}
