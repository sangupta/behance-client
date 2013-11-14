package com.sangupta.behance;

import java.util.List;

import com.sangupta.behance.domain.Project;
import com.sangupta.behance.domain.Project.ProjectList;
import com.sangupta.behance.domain.Project.SingleProject;
import com.sangupta.behance.domain.ProjectComments;
import com.sangupta.behance.domain.UserAppreciated;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.UriUtils;

/**
 * 
 * @author sangupta
 *
 */
public class BehanceClient extends AbstractBehanceClient {
	
	/**
	 * Create the behance client.
	 * 
	 * @param apiKey
	 * @param clientSecret
	 */
	public BehanceClient(String apiKey, String clientSecret) {
		super(apiKey, clientSecret);
	}
	
	/**
	 * Search for projects.
	 * 
	 * @param query
	 * @return
	 */
	public List<Project> search(String query) {
		if(AssertUtils.isEmpty(query)) {
			throw new IllegalArgumentException("Query cannot be empty");
		}
		
		ProjectList projects = this.invokeApi("projects?q=" + UriUtils.encodeURIComponent(query), ProjectList.class);
		if(projects != null) {
			return projects.projects;
		}
		
		return null;
	}

	/**
	 * Get project details.
	 * 
	 * @param id
	 * @return
	 */
	public Project getDetails(String id) {
		return this.getDetails(Integer.parseInt(id));
	}
	
	/**
	 * Get project details.
	 * 
	 * @param id
	 * @return
	 */
	public Project getDetails(int id) {
		if (id < 1) {
			throw new IllegalArgumentException("ID cannot be less than or equal to zero");
		}
		
		SingleProject project = this.invokeApi("projects/" + id, SingleProject.class);
		if (project == null) {
			return null;
		}

		return project.project;
	}

	/**
	 * Get the comments for a project.
	 * 
	 * @param id
	 * @return
	 */
	public ProjectComments getComments(int id) {
		return getComments(id, 1);
	}
	
	/**
	 * Get the comments for a project.
	 * 
	 * @param id
	 * @param page
	 * @return
	 */
	public ProjectComments getComments(int id, int page) {
		if (id < 1) {
			throw new IllegalArgumentException("ID cannot be less than or equal to zero");
		}
		
		ProjectComments pc = this.invokeApi("projects/"+ id + "/comments?page=" + page, ProjectComments.class);
		if(pc == null) {
			return pc;
		}
		
		pc.projectID = String.valueOf(id);
		pc.page = page;
		
		return pc;
	}
	
	// ---------------------------
	// User authentication required functions
	// ---------------------------
	
	/**
	 * Returns whether current user has appreciated this project. Requires post_as scope.
	 * 
	 * @param accessToken
	 * @param projectID
	 * @return
	 */
	public UserAppreciated hasUserAppreciatedProject(String accessToken, int projectID) {
		return this.invokeApi("projects/" + projectID + "/view?access_token=" + accessToken, UserAppreciated.class);
	}
	
}
