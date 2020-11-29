/**
 * Manages the frame to swap between panels
 * @author Griffin White
 *
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Container;

public class FrameManager {

	Dimension screen;
	
	private int screenWidth;
	
	private int screenHeight;
	
	private LoginScreen login;
	
	private SetupScreen setup;
	
	private GitCommandCenterScreen center;
	
	private JFrame frame;
	
	private Container container;
	
	
	/**
	 * Does things that would otherwise be in the main method, needed so that the driver can feed itself into the panels so that swapping can occur.
	 */
	public FrameManager() {
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = screen.width/2;
		screenHeight = screen.height/2;
		
		login = new LoginScreen(screenWidth, screenHeight, Color.white, this);
		setup = new SetupScreen(screenWidth, screenHeight, Color.white, this);
		center = new GitCommandCenterScreen(screenWidth, screenHeight, Color.white);
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(login);  
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * swaps the current panel to one specified. If none are specified, prints an error to tell the developer to use a valid name.
	 * @param panelName - simply the name of the class you wish to set 
	 */
	public void swapPanel(String panelName) {
		if(panelName.equals("login") || panelName.equals("Login")) {
			swapPanel(login);
		}
		else if(panelName.equals("setup") || panelName.equals("Setup")) {
			swapPanel(setup);
		}
		else if (panelName.equals("center") || panelName.equals("Center")){
			swapPanel(center);
		}
		else {
			System.err.println("Please use a valid name to swap to.");
		}
	}
	
	/**
	 * Swaps the current panel to the one specified.
	 * @param panel - JPanel to swap the frame to
	 */
	public void swapPanel(JPanel panel) {
		container = frame.getContentPane();
        container.removeAll();
        container.add(panel);
        container.validate();
        container.repaint();
	}

}
