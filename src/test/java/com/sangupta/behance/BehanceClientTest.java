package com.sangupta.behance;

import com.sangupta.behance.domain.Project;
import com.sangupta.behance.domain.ProjectComments;

public class BehanceClientTest {
	
	public static void main(String[] args) {
		String apiKey = "tTxaR4MmREXCUsGU7mRL2A7AWPqG1hZn";
		String clientSecret = "R8aknF1m98ksDC7I2fA.t974KJl2Ox2e";
		
		BehanceClient client = new BehanceClient(apiKey, clientSecret);
//		List<Project> projects = client.searchProjects("cycle");
//		
//		if(AssertUtils.isEmpty(projects)) {
//			System.out.println("No project found.");
//			return;
//		}
//		
//		for(Project project : projects) {
//			System.out.println("Found project: " + project.name);
//		}
		
		final int id = 4889175;
		
		Project project = client.getDetails(id);
		System.out.println(project.name);
		
		ProjectComments comments = client.getComments(id);
		System.out.println(comments.comments.size());
	}

}
