import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Container;

/**
 * Manages the frame to swap between panels
 * @author Griffin White
 *
 */
public class FrameManager {

	Dimension screen;
	
	private int screenWidth;
	
	private int screenHeight;
	
	private LoginScreen login;
	
	private SetupScreen setup;
	
	private GitCommandCenterScreen center;
	
	private JFrame frame;
	
	private Container container;
	
	public GitHubHelper git; //made public because it is not instantiated
	
	enum swappablePanel{ //for swapPanel() method
		LOGIN,
		SETUP,
		CENTER
	};
	
	/**
	 * Does things that would otherwise be in the main method, needed so that the driver can feed itself into the panels so that swapping can occur.
	 */
	public FrameManager() {
		git = new GitHubHelper();
		
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = screen.width/2;
		screenHeight = screen.height/2;
		
		login = new LoginScreen(screenWidth, screenHeight, Color.white, this);
		setup = new SetupScreen(screenWidth, screenHeight, Color.white, this);
		center = new GitCommandCenterScreen(screenWidth, screenHeight, Color.white, this);
		
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
	public void swapPanel(swappablePanel panelName) {
		if(panelName == swappablePanel.LOGIN) {
			swapPanel(login);
		}
		else if(panelName == swappablePanel.SETUP) {
			swapPanel(setup);
		}
		else if (panelName == swappablePanel.CENTER) {
			swapPanel(center);
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
