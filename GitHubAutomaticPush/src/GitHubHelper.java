import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class GitHubHelper {
	
	public GitHubHelper() {
		String os = System.getProperty("os.name");
		String projectName = "SeniorDesignTemplateProject";
		String repoPath = "https://github.com/bgaran/SeniorDesignTemplateProject.git";
		
		if (os.contains("Win")) {
				//TODO: not focus on the terminal
				try {
					Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd ../"+projectName+"\""
							+ "\" && git status"
							//may or may not need these
							//+ " && git config --global user.email \"ccrawford21@jcu.edu\"" 
							//+ " && git config --global user.name \"Courtney\""
							+ " && git add . && git commit -m \"testing commit and push\" && git push\"");
		            
		        } catch (IOException e) {
		            System.out.println(e.toString());
		        }
		}
	}

	
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
		
	}
}
