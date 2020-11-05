import org.eclipse.jgit.*;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.Repository;

public class GitHubHelper {

	Git git;
	//Repository repo;
	/**
	 * This method connects to GitHub with the given user name and password
	 * @param username - GitHub User Name (String)
	 * @param password - GitHub Password (String)
	 */
	public void ConnectToGitHub(String username, String password)
	{
		try {
			git.cloneRepository()
			  .setURI("https://github.com/bgaran/SeniorDesignTemplateProject.git")
			  //.setDirectory("/path/to/repo")
			  .call();
		} catch (InvalidRemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//repo=getRepository();
	}
	
	/**
	 * This method saves the currenct progress and
	 * pushes the current project to the connected GitHub repository
	 */
	public void githubPush()
	{
		git.commit();
		git.push();
	}
}
