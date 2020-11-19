import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class GitHubHelper {
	
	public GitHubHelper() {
		String os = System.getProperty("os.name");
		String projectName = "testPull";
		String repoPath = "https://github.com/bgaran/SeniorDesignTemplateProject.git";
		
		if (os.contains("Win")) {
			try {
				//TODO: not focus on the terminal
				Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /K cd ../"+projectName);//+
						//" && cd ../");// cmd.exe /K cd ../"+projectName);
			
				//this stuff doesn't work correctly. don't know how to actually write it
				/*BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
				writer.newLine();
	            writer.write("cd ../"+projectName);
	            writer.newLine();
	            //writer.flush();
	            writer.close();*/
			} catch (IOException e) {
				e.printStackTrace();
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
