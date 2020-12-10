import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	
	//private JCheckBox darkModeCheckBox; //used to switch between dark mode and light mode
		
	private JButton pullButton, diffButton, backButton;
	
	private JToggleButton pushToggleButton, darkModeToggleButton;
	
	private Font bigWords, mediumWords; //used to resize the text
	
	private TimerTask pushTask;
	
	private Timer pushTimer;

	public GitCommandCenterScreen(int width, int height, Color bgColor, FrameManager frameManager) {
		setPreferredSize(new Dimension(width, height));
		setBackground(bgColor); 

		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		
		this.setLayout(new GridLayout(3,2, 20, 200));
		
		this.frameManager = frameManager;
		
		//initialize all UI elements
		infoLabel = new JLabel("Perform git commands.");
		spacingLabel1 = new JLabel("");
		spacingLabel2 = new JLabel("");
		spacingLabel3 = new JLabel("");
		spacingLabel4 = new JLabel("");
		feedbackLabel = new JLabel("feedback");
		
		pushToggleButton = new JToggleButton("Push");
		pullButton = new JButton("Pull");
		diffButton = new JButton("Diff");
		backButton = new JButton("Back");

		
		darkModeToggleButton = new JToggleButton("Dark Mode");
		
		if(this.frameManager.isDarkMode) { //if it is in dark mode
			darkModeToggleButton.setSelected(true); //set it so the togglebutton is on
		}
		
		bigWords = new Font("Sans Serif", Font.PLAIN, width/30); //Makes it so the text is easily seeable, using one 30th of the window's width for the font size
		mediumWords = new Font("Sans Serif", Font.PLAIN, width/40);
		//set all UI elements to share this same font
		infoLabel.setFont(mediumWords);
		feedbackLabel.setFont(bigWords);
		darkModeToggleButton.setFont(bigWords);
		pushToggleButton.setFont(bigWords);
		pushToggleButton.setBackground(new Color(255, 110, 99));
		pullButton.setFont(bigWords);
		pullButton.setBackground(new Color(127, 245, 125));
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
		this.add(darkModeToggleButton);
		this.add(backButton);
		this.add(feedbackLabel);
		this.add(spacingLabel4);
		this.add(pushToggleButton);
		this.add(pullButton);
		this.add(diffButton);
		
		//Button actions when clicked
		pushToggleButton.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				
                if (state == ItemEvent.SELECTED) {
                	//activate pushing
                	pushToggleButton.setText("Pushing...");
                	resumeTimer();
                    
                } 
                else {  
                	//deactivate pushing
                	pushToggleButton.setText("Push");
                	pushToggleButton.setBackground(new Color(255, 110, 99));
                	pauseTimer();


                }
			}
			
		});
		
		pullButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(pushToggleButton.getText().toLowerCase().equals("pushing...")) {
					JOptionPane.showMessageDialog(null,
						    "You are currently pushing. Please cancel pushing to pull.",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE); //display dialog box with warning
				}else {
					frameManager.git.githubPull();
				}
			}
			
		});
		
		diffButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(pushToggleButton.getText().toLowerCase().equals("pushing...")) {
					JOptionPane.showMessageDialog(null,
						    "You are currently pushing. Please cancel pushing to perform diff.",
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
				if(pushToggleButton.getText().toLowerCase().equals("pushing...")) {
					JOptionPane.showMessageDialog(null,
						    "You are currently pushing. Please cancel pushing to perform diff.",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE); //display dialog box with warning
				}else {
					frameManager.swapPanel(FrameManager.swappablePanel.SETUP);
				}
			}
			
		});
		
		//When darkModeCheckBox is checked/unchecked
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
	 * Cancels or stops the existing pshTimer and pushTask. Once canceled, a new Timer and TimerTask must be created with method resumeTimer().
	 * @author April
	 */
	private void pauseTimer() {
		this.pushTimer.cancel();
		this.pushTask.cancel();
	}
	
	/**
	 * Resumes the pushTimer and pushTask by creating new instances of each. The pushTask will call framemanager.git.githubPush
	 * and the pshTimer will perform that task every 30 seconds.
	 * @author April
	 */
	private void resumeTimer() {
    	this.pushTask = new TimerTask() {

    	    @Override
    	    public void run() {
    	    	frameManager.git.githubPush();
    	    }
    	};
	    this.pushTimer = new Timer();
	    this.pushTimer.schedule(pushTask, new Date(), 3000); //pull every 30 seconds (30000 milliseconds)
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
            darkModeToggleButton.setForeground(Color.white);
            feedbackLabel.setForeground(Color.white);
            pushToggleButton.setForeground(Color.white);
            pullButton.setForeground(Color.white);
            diffButton.setForeground(Color.white);
		}
		else {
			//switch OFF dark mode
			setBackground(Color.WHITE);
            infoLabel.setForeground(Color.black);
            darkModeToggleButton.setForeground(Color.black);
            feedbackLabel.setForeground(Color.black);
            pushToggleButton.setForeground(Color.black);
            pullButton.setForeground(Color.black);
            diffButton.setForeground(Color.black);
            backButton.setForeground(Color.black);
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
	}
}

