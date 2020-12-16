import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class GitHubHelper {
	String os = System.getProperty("os.name").toLowerCase();
	String projectPath;

	/**
	 * This method connects to GitHub with the given user name and password.
	 * 
	 * Right now it is not used, but may be needed in future development when handing
	 * user credentials.
	 * 
	 * @param username - GitHub User Name (String)
	 * @param password - GitHub Password (String)
	 */
	public String ConnectToGitHub(String username, String password, String filePath) {
		return "";
	}

	/**
	 * This method saves the current progress and pushes the current project to the
	 * connected GitHub repository
	 * @author Courtney Crawford
	 * @author Breanna Garan
	 * @throws IOException 
	 */
	public String githubPush() throws IOException {
		if (os.contains("win")) {
			
				Runtime.getRuntime().exec("cmd /c start cmd.exe /c \"cd " + projectPath + "\""
						+ "\" && git add . && git commit -m \""+LocalDateTime.now()+"\" && git push\"");
				
				return "Any new changes have been pushed";	
		}
		else{
			System.out.println(os);
				String command = " cd " + projectPath 
				+ " && git add . && git commit -m \""+LocalDateTime.now()+"\" && git push";

				return excecuteMacCommand(command);
		}
	}

	/***
	 * In Windows:
	 * This method will compare the most recent version of code in the repo with
	 * what the user has in their local and show it in the command prompt/terminal
	 * window. From there, the user can identify any differences and manually change
	 * them in their local
	 * 
	 * Red is what is currently in the repository Green is any differences the user
	 * has compared to the repository
	 * 
	 * @author Courtney Crawford
	 * @author Breanna Garan
	 * @throws IOException 
	 */
	public String githubDiff() throws IOException {
		if (os.contains("win")) {
				Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd " + projectPath + "\"" 
						+ "\" && git fetch" + " && git diff @{upstream}" +" && pause"+ " && exit\"");
				
				return "Your diff will appear in your command prompt window. Anything that is red or green is "
						+ "where your local file and the current repo file differ. Red is what is currently in "
						+ "the repo and green is what is currently in your local file. If you see a colon : at the "
						+ "end of the command prompt, you are in an interactive session to view the differences. Use "
						+ "the arrow keys to navigate and type \"q\" to end the interactive session. Then press any "
						+ "key to exit the command prompt window";
		} else {
				System.out.println(os);
				
				String command = " cd " + projectPath + " && git diff";
				return excecuteMacCommand(command);
		}
	}

	/**
	 * This method will pull the latest version of code in the repo and overwrite
	 * the local code with this copy
	 * @author Courtney Crawford
	 * @author Breanna Garan
	 * @throws IOException 
	 */
	public String githubPull() throws IOException {
		if (os.contains("win")) {
				Runtime.getRuntime().exec("cmd /c start cmd.exe /c \"cd " + projectPath + "\"" 
						+ "\" && git pull\"");
				
				return "Your local files should now reflect what is currently in the repository";
		}
		else{
				String command = " cd " + projectPath + " && git pull";
				return excecuteMacCommand(command);
		}
	}
	
	/**
	 * This method will clone the given git repository to the given local file path.
	 * You must then add that file from your local computer to the package explorer.
	 * This will set up the repository for use with this application.
	 * 
	 * @author Courtney Crawford
	 * @author Breanna Garan
	 * @throws IOException
	 */
	public String gitClone(String destPath, String gitURL) throws IOException {
		if (os.contains("win")) {
			Runtime.getRuntime().exec("cmd /c start cmd.exe /c \"cd " + destPath + "\"" 
					+ "\" && git clone " + gitURL + "\"");
			
			return "The cloned project should now be in your specified folder. To use this project, in Eclipse "
					+ "select File > Open Projects from File System... and navigate to this cloned project using "
					+ "the Directory button. Note: You may need to change the file path to this new cloned project "
					+ "in this Git Helper to use the other git commands";
		} else {
			String command = " cd " + destPath + " && git clone " + gitURL;
			return excecuteMacCommand(command);
		}
	}
	
/**
 * This method takes a command and executes it for a Mac Terminal.
 * @param command - String terminal command with Mac syntax.
 * e.g. "cd filePath && git pull"
 * @author Breanna Garan
 */
	public String excecuteMacCommand(String command) {
		try {
			String[] cmd = { "bash", "-c", command };
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
			int status = p.exitValue();
			System.out.println("Program terminated with exit status " + status);
			if (status != 0) {
				String result = new BufferedReader(new InputStreamReader(p.getErrorStream())).lines()
					.collect(Collectors.joining("\n"));
				System.out.println(result);
				return result;
			} else {
				String result = new BufferedReader(new InputStreamReader(p.getInputStream())).lines()
					.collect(Collectors.joining("\n"));
				System.out.println(result);
				return result;
			}
		}
		catch (IOException e) {
			System.out.println(e.toString());
			return e.toString();
		}
		catch(Exception e) {
			System.out.println(e.toString());
			return e.toString();
		}
	}
}
