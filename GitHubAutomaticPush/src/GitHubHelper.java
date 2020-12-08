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
	public String githubPush() {
		if (os.toLowerCase().contains("win")) {
			// TODO: not focus on the terminal
			try {
				Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd " + projectPath + "\""
				// may or may not need these; if so, need to be user input
				// + " && git config --global user.email \"ccrawford21@jcu.edu\""
				// + " && git config --global user.name \"Courtney\""
						+ "\" && git add . && git commit -m \"Autocommit\" && git push" + " && pause" + " && exit\"");
				return "Git Push Success!";
			} catch (IOException e) {
				System.out.println(e.toString());
				return e.toString();
			}
		} else if (os.toLowerCase().contains("mac")) {
			System.out.println(os);
			try {
				String command = " cd " + projectPath 
				+ " && git add . && git commit -m \"testing auto commit\" && git push";

				return excecuteMacCommand(command);
			}
			catch (Exception e) {
				System.out.println(e.toString());
				return e.toString();
			}
		}
		else
		{
			return "Your current OS is not supported";
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
	public String githubDiff() {
		if (os.toLowerCase().contains("win")) {
			// TODO: not focus on the terminal
			try {
				Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd " + projectPath + "\"" + "\" && git diff"
						+ " && pause" + " && exit\"");
				return "GitHub Diff Success";

			} catch (IOException e) {
				System.out.println(e.toString());
				return e.toString();
			}
		}
		else if (os.toLowerCase().contains("mac")) {
			System.out.println(os);
			try {

				String command = " cd " + projectPath + " && git diff";
				return excecuteMacCommand(command);
			}
			catch (Exception e) {
				System.out.println(e.toString());
				return e.toString();
			}

		}
		else
		{
			return "Your current OS is not supported";
		}
	}

	/**
	 * This method will pull the latest version of code in the repo and overwrite
	 * the local code with this copy
	 */
	public String githubPull() {
		if (os.toLowerCase().contains("win")) {
			// TODO: not focus on the terminal
			try {
				Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd " + projectPath + "\"" + "\" && git pull"
						+ " && pause" + " && exit\"");
				return "Git Pull Success!";
			} catch (IOException e) {
				e.printStackTrace();
				return e.toString();
			}
		}
		else if (os.toLowerCase().contains("mac")) {
			System.out.println(os);
			try {

				String command = " cd " + projectPath + " && git pull";
				return excecuteMacCommand(command);
			}
			catch (Exception e) {
				System.out.println(e.toString());
				return e.toString();
			}

		}
		else
		{
			return "Your current OS is not supported";
		}
	}
/**
 * This method takes a command and executes it for a Mac Terminal.
 * @param command - Must be properly formatted with the syntax for a Mac command
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
