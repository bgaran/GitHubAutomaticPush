import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class GitHubHelper {
	String os = System.getProperty("os.name");
	// String repoPath =
	// "https://github.com/bgaran/SeniorDesignTemplateProject.git"; //if using
	// existing repo, will need to be user input
	String projectPath;

	/**
	 * This method connects to GitHub with the given user name and password
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
	 */
	public void githubPush() {
		if (os.contains("Win")) {
			// TODO: not focus on the terminal
			try {
				Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd " + projectPath + "\""
				// may or may not need these; if so, need to be user input
				// + " && git config --global user.email \"ccrawford21@jcu.edu\""
				// + " && git config --global user.name \"Courtney\""
						+ "\" && git add . && git commit -m \"Autocommit\" && git push" + " && pause" + " && exit\"");

			} catch (IOException e) {
				System.out.println(e.toString());
			}
		} else if (os.contains("Mac")) {
			System.out.println(os);
			try {
				String command = " cd " + projectPath 
				+ " && git add . && git commit -m \"testing auto commit\" && git push";

				excecuteMacCommand(command);
			}
			catch (Exception e) {
				System.out.println("Caught exception");
			}
		}
	}

	/***
	 * This method will compare the most recent version of code in the repo with
	 * what the user has in their local and show it in the command prompt/terminal
	 * window. From there, the user can identify any differences and manually change
	 * them in their local
	 * 
	 * Red is what is currently in the repository Green is any differences the user
	 * has compared to the repository
	 */
	public void githubDiff() {
		if (os.contains("Win")) {
			// TODO: not focus on the terminal
			try {
				Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd " + projectPath + "\"" + "\" && git diff"
						+ " && pause" + " && exit\"");

			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}
	}

	/**
	 * This method will pull the latest version of code in the repo and overwrite
	 * the local code with this copy
	 */
	public void githubPull() {
		if (os.contains("Win")) {
			// TODO: not focus on the terminal
			try {
				Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd " + projectPath + "\"" + "\" && git pull"
						+ " && pause" + " && exit\"");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (os.contains("Mac")) {
			System.out.println(os);
			try {

				String command = " cd " + projectPath + " && git pull";
				excecuteMacCommand(command);
			}
			catch (Exception e) {
				System.out.println("Caught exception");
			}

		}
	}
/**
 * This method takes a command and executes it for a Mac Terminal.
 * @param command - Must be properly formatted with the syntax for a Mac command
 */
	public void excecuteMacCommand(String command) {
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
		} else {

			String result = new BufferedReader(new InputStreamReader(p.getInputStream())).lines()
					.collect(Collectors.joining("\n"));
			System.out.println(result);
		}
		}
		catch (IOException e) {
			System.out.println(e.toString());
		}
		catch(Exception e) {
			System.out.println("Caught exception");
		}
	}
}
