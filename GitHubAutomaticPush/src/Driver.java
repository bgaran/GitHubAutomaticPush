import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Driver {

	public static void main(String[] args) {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screen.width/2;
		int screenHeight = screen.height/2;
		
		LogInScreen logIn = new LogInScreen(screenWidth, screenHeight, Color.white);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(logIn);  
		frame.pack();
		frame.setVisible(true); 

	}

}
