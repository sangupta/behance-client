package com.sangupta.behance;

import java.util.List;

import com.sangupta.behance.domain.Project;
import com.sangupta.jerry.util.AssertUtils;

public class BehanceClientTest {
	
	public static void main(String[] args) {
		String apiKey = "";
		String clientSecret = "";
		
		BehanceClient client = new BehanceClient(apiKey, clientSecret);
		List<Project> projects = client.searchProjects("cycle");
		
		if(AssertUtils.isEmpty(projects)) {
			System.out.println("No project found.");
			return;
		}
		
		for(Project project : projects) {
			System.out.println("Found project: " + project.name);
		}
	}

}
