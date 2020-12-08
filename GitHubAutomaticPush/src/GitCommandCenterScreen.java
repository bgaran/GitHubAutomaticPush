import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import 
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class GitCommandCenterScreen extends JPanel{
	
	String filePath;
	
	private FrameManager frameManager; //LINKED TO SWAP PANELS
	
	private JLabel infoLabel, spacingLabel1, spacingLabel2, spacingLabel3, spacingLabel4, feedbackLabel;
	
	private JCheckBox darkModeCheckBox; //used to switch between dark mode and light mode
		
	private JButton pushButton, diffButton, backButton; //TODO: implement backButton
	
	private JToggleButton pullToggleButton;
	
	private Font bigWords; //used to resize the text
	
	private TimerTask pullTask;
	
	private Timer pullTimer;

	public GitCommandCenterScreen(int width, int height, Color bgColor, FrameManager frameManager) {
		setPreferredSize(new Dimension(width, height));
		setBackground(bgColor); 

		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		
		this.setLayout(new GridLayout(3,2, 20, 200));
		
		this.frameManager = frameManager;
		
		//initialize all UI elements
		infoLabel = new JLabel("Perform git commands using the buttons.");
		spacingLabel1 = new JLabel("");
		spacingLabel2 = new JLabel("");
		spacingLabel3 = new JLabel("");
		spacingLabel4 = new JLabel("");
		feedbackLabel = new JLabel("feedback");
		
		pushButton = new JButton("Push");
		pullToggleButton = new JToggleButton("Pull");
		diffButton = new JButton("Diff");
		backButton = new JButton("Back");

		
		darkModeCheckBox = new JCheckBox("Dark Mode");
		
		bigWords = new Font("Sans Serif", Font.PLAIN, width/30); //Makes it so the text is easily seeable, using one 30th of the window's width for the font size
		
		//set all UI elements to share this same font
		infoLabel.setFont(bigWords);
		feedbackLabel.setFont(bigWords);
		darkModeCheckBox.setFont(bigWords);
		darkModeCheckBox.setBackground(Color.white);
		pushButton.setFont(bigWords);
		pushButton.setBackground(new Color(255, 110, 99));
		pullToggleButton.setFont(bigWords);
		pullToggleButton.setBackground(new Color(127, 245, 125));
		diffButton.setFont(bigWords);
		diffButton.setBackground(new Color(109, 162, 247));
		backButton.setFont(bigWords);

		
//		//position all UI elements correctly
//		infoLabel.setPreferredSize(new Dimension(width,height/4)); //width*3/4 is a janky way of ensuring it's on its own line
//		pushButton.setPreferredSize(new Dimension(width/4,height/10));
//		pullButton.setPreferredSize(new Dimension(width*3/4,height/10));
//		diffButton.setPreferredSize(new Dimension(width*3/4,height/10));
//		feedbackLabel.setPreferredSize(new Dimension(width*3/4,height/5)); //width*3/4 is a janky way of ensuring it's on its own line
		
		//finally, add all UI elements to the GitCommandCenterScreen
		this.add(infoLabel);
		this.add(spacingLabel1);
		this.add(darkModeCheckBox);
		this.add(backButton);
		this.add(feedbackLabel);
		this.add(spacingLabel4);
		this.add(pushButton);
		this.add(pullToggleButton);
		this.add(diffButton);
		
		//Button actions when clicked
		pushButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(pullToggleButton.getText().toLowerCase().equals("pulling...")) {
					JOptionPane.showMessageDialog(null,
						    "You are currently pulling. Please cancel pulling to push.",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE); //display dialog box with warning
				}else {
					frameManager.git.githubPush();
				}
			}
			
		});
		
		pullToggleButton.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				
                if (state == ItemEvent.SELECTED) {
                	//activate pulling
                	pullToggleButton.setText("Pulling...");
                	resumeTimer();
                    
                } 
                else {  
                	//deactivate pulling
                	pullToggleButton.setText("Pull");
                	pullToggleButton.setBackground(new Color(127, 245, 125));
                	pauseTimer();


                }
			}
			
		});
		
		diffButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(pullToggleButton.getText().toLowerCase().equals("pulling...")) {
					JOptionPane.showMessageDialog(null,
						    "You are currently pulling. Please cancel pulling to perform diff.",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE); //display dialog box with warning
				}else {
					frameManager.git.githubDiff();
				}
			}
			
		});
		
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(pullToggleButton.getText().toLowerCase().equals("pulling...")) {
					JOptionPane.showMessageDialog(null,
						    "You are currently pulling. Please cancel pulling to return to the file path screen.",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE); //display dialog box with warning
				}else {
					frameManager.swapPanel(FrameManager.swappablePanel.SETUP);
				}
			}
			
		});
		
		//When darkModeCheckBox is checked/unchecked
		darkModeCheckBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				
                if (state == ItemEvent.SELECTED) { 
                	//switch ON dark mode
                    setBackground(Color.DARK_GRAY);
                    infoLabel.setForeground(Color.white);
                    darkModeCheckBox.setBackground(Color.DARK_GRAY);
                    darkModeCheckBox.setForeground(Color.white);
                    feedbackLabel.setForeground(Color.white);
                    pushButton.setForeground(Color.white);
                    pullToggleButton.setForeground(Color.white);
                    diffButton.setForeground(Color.white);
                } 
                else {  
                	//switch OFF dark mode
                    setBackground(bgColor);
                    infoLabel.setForeground(Color.black);
                    darkModeCheckBox.setBackground(Color.white);
                    darkModeCheckBox.setForeground(Color.black);
                    feedbackLabel.setForeground(Color.black);
                    pushButton.setForeground(Color.black);
                    pullToggleButton.setForeground(Color.black);
                    diffButton.setForeground(Color.black);
                    backButton.setForeground(Color.black);
                }
			}
			
		});
				
	}
	
	/**
	 * Cancels or stops the existing pullTimer and pullTask. Once canceled, a new Timer and TimerTask must be created with method resumeTimer().
	 * @author April
	 */
	private void pauseTimer() {
		this.pullTimer.cancel();
		this.pullTask.cancel();
	}
	
	/**
	 * Resumes the pullTimer and pullTask by creating new instances of each. The pullTask will call framemanager.git.githubPull
	 * and the pullTimer will perform that task every 30 seconds.
	 * @author April
	 */
	private void resumeTimer() {
    	this.pullTask = new TimerTask() {

    	    @Override
    	    public void run() {
    	    	frameManager.git.githubPull();
    	    }
    	};
	    this.pullTimer = new Timer();
	    this.pullTimer.schedule(pullTask, new Date(), 30000); //pull every 30 seconds (30000 milliseconds)
	}
}

