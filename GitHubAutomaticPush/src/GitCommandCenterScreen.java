import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
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
import javax.swing.JToggleButton;

public class GitCommandCenterScreen extends JPanel{
	
	String filePath;
	
	private FrameManager frameManager; //LINKED TO SWAP PANELS
	
	private JLabel infoLabel, feedbackLabel;
		
	private JButton pushButton, pullButton, diffButton, startButton, stopButton;
	
	private JToggleButton darkModeToggleButton; //used to toggle between dark mode and light mode
	
	private Font bigWords; //used to resize the text

	public GitCommandCenterScreen(int width, int height, Color bgColor, FrameManager frameManager) {
		setPreferredSize(new Dimension(width, height));
		setBackground(bgColor); 

		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		
		this.frameManager = frameManager;
		
		//initialize all UI elements
		infoLabel = new JLabel("Perform git commands using the buttons.");
		feedbackLabel = new JLabel("");
		
		pushButton = new JButton("Push");
		pullButton = new JButton("Pull");
		diffButton = new JButton("Diff");
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");

		
		darkModeToggleButton = new JToggleButton("Dark Mode");
		
		bigWords = new Font("Sans Serif", Font.PLAIN, width/30); //Makes it so the text is easily seeable, using one 30th of the window's width for the font size
		
		//set all UI elements to share this same font
		infoLabel.setFont(bigWords);
		feedbackLabel.setFont(bigWords);
		pushButton.setFont(bigWords);
		pullButton.setFont(bigWords);
		diffButton.setFont(bigWords);
		startButton.setFont(bigWords);
		stopButton.setFont(bigWords);
		darkModeToggleButton.setFont(bigWords);
		
		//position all UI elements correctly
		infoLabel.setPreferredSize(new Dimension(width*3/4,height/4)); //width*3/4 is a janky way of ensuring it's on its own line
		pushButton.setPreferredSize(new Dimension(width/4,height/10));
		pullButton.setPreferredSize(new Dimension(width*3/4,height/10));
		diffButton.setPreferredSize(new Dimension(width*3/4,height/10));
		startButton.setPreferredSize(new Dimension(width*3/4,height/10));
		stopButton.setPreferredSize(new Dimension(width*3/4,height/10));
		feedbackLabel.setPreferredSize(new Dimension(width*3/4,height/5)); //width*3/4 is a janky way of ensuring it's on its own line
		darkModeToggleButton.setPreferredSize(new Dimension(width/2,height/10)); 

		
		//finally, add all UI elements to the GitCommandCenterScreen
		this.add(infoLabel);
		this.add(pushButton);
		this.add(pullButton);
		this.add(diffButton);
		this.add(startButton);
		this.add(stopButton);
		this.add(feedbackLabel);
		this.add(darkModeToggleButton);
		
		//Button actions when clicked
		pushButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frameManager.git.githubPush();
			}
			
		});
		
		pullButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frameManager.git.githubPull();
			}
			
		});
		
		diffButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frameManager.git.githubDiff();
			}
			
		});
		
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//TODO
			}
			
		});
		
		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//TODO
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
                    feedbackLabel.setForeground(Color.white);
                    
                } 
                else {  
                	//switch OFF dark mode
                    setBackground(bgColor);
                    infoLabel.setForeground(Color.black);
                    feedbackLabel.setForeground(Color.black);
                }
			}
			
		});
		

		
	}
}

