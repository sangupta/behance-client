package com.sangupta.behance.domain;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author sangupta
 *
 */
public class Project {
	
	public String id;
	
	public String name;
	
	public long publishedOn;
	
	public long createdOn;
	
	public long modifiedOn;
	
	public String url;
	
	public List<String> fields;
	
	public Map<Integer, String> covers;
	
	public List<Owner> owners;
	
	public Stats stats;
	
	public int matureContent;
	
	public String description;
	
	public List<Module> modules;
	
	public Copyright copyright;
	
	public List<FeaturedOn> featuredOn;

	public static class ProjectList {

		public List<Project> projects;
		
	}

	public static class SingleProject {
		
		public Project project;
	}

}
