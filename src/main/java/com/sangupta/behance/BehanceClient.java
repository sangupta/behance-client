package com.sangupta.behance;

import java.util.List;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.sangupta.behance.domain.Project;
import com.sangupta.behance.domain.Project.ProjectList;
import com.sangupta.jerry.http.WebInvoker;
import com.sangupta.jerry.http.WebResponse;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.GsonUtils;
import com.sangupta.jerry.util.UriUtils;

/**
 * 
 * 
 */
public class BehanceClient {
	
	private static final Gson GSON = GsonUtils.getGson(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
	
	private static String BASE_URL = "http://www.behance.net/v2/";
	
	private final String apiKey;
	
	private final String clientSecret;
	
	public BehanceClient(String apiKey, String clientSecret) {
		this.apiKey = apiKey;
		this.clientSecret = clientSecret;
	}
	
	public List<Project> searchProjects(String query) {
		if(AssertUtils.isEmpty(query)) {
			throw new IllegalArgumentException("Query cannot be empty");
		}
		
		String url = UriUtils.addWebPaths(BASE_URL, "projects?q=" + UriUtils.encodeURIComponent(query) + "&api_key=" + this.apiKey);
		WebResponse response = WebInvoker.getResponse(url);
		if(response == null || !response.isSuccess()) {
			return null;
		}
		
		ProjectList projects = GSON.fromJson(response.getContent(), ProjectList.class);
		if(projects != null) {
			return projects.projects;
		}
		
		return null;
	}

	
}
