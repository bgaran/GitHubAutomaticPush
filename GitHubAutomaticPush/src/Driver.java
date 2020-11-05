import java.awt.Color;

import javax.swing.JFrame;

public class Driver {

	public static void main(String[] args) {
		LogInScreen screen = new LogInScreen(800, 1000, Color.white);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(screen);  
		frame.pack();
		frame.setVisible(true); 

	}

}
