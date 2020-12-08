import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Driver {

	public static void main(String[] args) {
		//manages frames in another class made to do so, this is so staticitis doesnt occur
		FrameManager frameManager = new FrameManager();
		GitHubHelper helper=new GitHubHelper();
		helper.gitClone("/Users/breannagaran/Desktop/CS435/", "https://github.com/bgaran/TestClone.git");
	}

}
