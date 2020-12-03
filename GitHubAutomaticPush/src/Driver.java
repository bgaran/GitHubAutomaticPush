import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.stream.Collectors;
import java.io.BufferedReader;

import javax.swing.JFrame;
import javax.xml.stream.events.Characters;



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
				
				String command=" cd "+projectPath //+" && git pull "//+" && git add . ; git commit -m \"testing auto commit\" ; git push"
						//+ "\" && git status"
						//+ " && git config --global user.email \"bgaran21@jcu.edu\"" 
						//+ " && git config --global user.name \"Breanna\""
						 + " && git add . ; git commit -m \"testing auto commit\" ; git push"
						;
			
				String[] cmd = {"bash","-c", command}; 
				Process p = Runtime.getRuntime().exec(cmd);
						p.waitFor();
		                int status = p.exitValue();
		                System.out.println("Program terminated with exit status " + status);
		                if (status != 0) {
		                	
		                	String result = new BufferedReader(new InputStreamReader(p.getErrorStream()))
		                			  .lines().collect(Collectors.joining("\n"));
		                	System.out.println(result);
		                }
		                else {
		                	
		                	String result = new BufferedReader(new InputStreamReader(p.getInputStream()))
		                			  .lines().collect(Collectors.joining("\n"));
		                	System.out.println(result);
		                }
		                //Runtime.getRuntime().exec("/bin/bash -c /Applications/Utilities/Terminal.app");
				
		            } 

//
			 catch (IOException e) {
				System.out.println(e.toString());
			} 
			catch (Exception e) {
                System.out.println("Caught exception");
        }
			
		}
	}
}

