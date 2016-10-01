package com.ibm.prolaeoc.model;

public enum Location {
	HORTOLANDIA("Hortolandia"), 
	TUTOIA("Tutoia"), 
	RECIFE("Recife"), 
	PASTEOUR("Pasteour");
	
	private String description;

	Location(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
