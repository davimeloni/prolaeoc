package com.ibm.prolaeoc.model;

public enum Location {
	HORTOLANDIA("HORTOLANDIA"), 
	TUTOIA("TUTOIA"), 
	RECIFE("RECIFE"), 
	PASTEOUR("PASTEOUR");
	
	private String description;

	Location(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
