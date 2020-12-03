import java.io.IOException;

public class GitHubHelper {
	
	String os = System.getProperty("os.name");
	String projectName = "SeniorDesignTemplateProject";	//will need to be user input
	String repoPath = "https://github.com/bgaran/SeniorDesignTemplateProject.git";	//if using existing repo, will need to be user input
	
		
	/**
	 * This method connects to GitHub with the given user name and password
	 * @param username - GitHub User Name (String)
	 * @param password - GitHub Password (String)
	 */
	public String ConnectToGitHub(String username, String password, String filePath)
	{
		return "";
	}
	
	/**
	 * This method saves the current progress and
	 * pushes the current project to the connected GitHub repository
	 */
	public void githubPush()
	{
		if (os.contains("Win")) {
			//TODO: not focus on the terminal
			try {
				Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd ../"+projectName+"\""
						//may or may not need these; if so, need to be user input
						//+ " && git config --global user.email \"ccrawford21@jcu.edu\"" 
						//+ " && git config --global user.name \"Courtney\""
						+ "\" && git add . && git commit -m \"Autocommit\" && git push\"");
	            
	        } catch (IOException e) {
	            System.out.println(e.toString());
	        }
		}
	}
	
	/***
	 * This method will compare the most recent version of code in the repo with what the user has
	 * in their local and show it in the command prompt/terminal window. From there, the user can identify
	 * any differences and manually change them in their local
	 * 
	 * Red is what is currently in the repository
	 * Green is any differences the user has compared to the repository
	 */
	public void githubDiff() {
		if (os.contains("Win")) {
			//TODO: not focus on the terminal
			try {
				Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd ../"+projectName+"\""
						+ "\" && git diff\"");
	            
	        } catch (IOException e) {
	            System.out.println(e.toString());
	        }
		}
	}
	
	/**
	 * This method will pull the latest version of code in the repo and overwrite the local code
	 * with this copy
	 */
	public void githubPull() {
		if (os.contains("Win")) {
			//TODO: not focus on the terminal
			try {
				Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd ../"+projectName+"\""
						+ "\" && git pull\"");
			} catch (IOException e) {
				e.printStackTrace();
			}     
		}
	}
}
