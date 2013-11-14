package com.sangupta.behance.constants;

public enum ProjectType {
	
	Academia("academia"),
	
	Advertising("advertising"),
	
	Animation("animation"),
	
	Architecture("architecture"),
	
	ArtDirection("art+direction"),
	
	AutomotiveDesign("automotive+design"),
	
	Blogging("blogging"),
	
	Branding("branding");
	
	// Internal attribute definition
	
	private ProjectType(String name) {
		this.fieldName = name;
	}
	
	private String fieldName;
	
	public String getFieldName() {
		return this.fieldName;
	}

}
