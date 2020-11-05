import java.awt.Color;
import java.awt.Dimension;

//import 
import javax.swing.JPanel;

public class LogInScreen extends JPanel{
	
	String filePath;
	//GitHub github

	public LogInScreen(int height, int width, Color bgColor) {
		setPreferredSize(new Dimension(width, height));
		setBackground(bgColor); 

		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
	}
}
