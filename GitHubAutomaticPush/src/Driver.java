import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;

public class Driver {

	public static void main(String[] args) {
//		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//		int screenWidth = screen.width/2;
//		int screenHeight = screen.height/2;
//		
//		LogInScreen logIn = new LogInScreen(screenWidth, screenHeight, Color.white);
//		JFrame frame = new JFrame();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.add(logIn);  
//		frame.pack();
//		frame.setVisible(true); 

		String os = System.getProperty("os.name");
		String projectName = "SeniorDesignTemplateProject";
		String projectPath = "/Users/breannagaran/git/SeniorDesignTemplateProject/SeniorDesignTemplateProject";
		String repoPath = "https://github.com/bgaran/SeniorDesignTemplateProject.git";

		if (os.contains("Win")) {
			// TODO: not focus on the terminal
			try {
				Runtime.getRuntime()
						.exec("cmd /c start cmd.exe /K \"cd ../" + projectName + "\"" + "\" && git status"
								+ " && git config --global user.email \"ccrawford21@jcu.edu\""
								+ " && git config --global user.name \"Courtney\""
								+ " && git add . && git commit -m \"testing commit and push\" && git push\"");

			} catch (IOException e) {
				System.out.println(e.toString());
			}

		} else if (os.contains("Mac")) {
			System.out.println(os);
			try {
				String command=" cd "+projectPath
						+ "\" && git status"
						+ " && git config --global user.email \"bgaran21@jcu.edu\"" 
						+ " && git config --global user.name \"Breanna\""
						 + " && git add . && git commit -m \"testing auto commit\" && git push\"";
				String[] cmd2 = { "/bin/bash -c "+command};
				String[] cmd = { "open -a /Applications/Utilities/Terminal.app"
//        				+ "\" && cd "+projectPath
//                        + "\" && git status"
//                        + " && git config --global user.email \"bgaran21@jcu.edu\"" 
//                        + " && git config --global user.name \"Breanna\""
//                        + " && git add . && git commit -m \"testing auto commit\" && git push\""
				};
				Runtime.getRuntime().exec(cmd2);

			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}
	}
}
