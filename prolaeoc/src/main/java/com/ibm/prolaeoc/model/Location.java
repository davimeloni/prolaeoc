package com.ibm.prolaeoc.model;

public enum Location {
//	HORTOLANDIA("HORTOLANDIA"), 
//	TUTOIA("TUTOIA"), 
//	RECIFE("RECIFE"), 
	AGUA_BRANCA("Agua Branca"),
	BARUERI("Barueri"),
	BELEM("Belem"),
	BELO_HORIZONTE("Belo Horizonte"),
	BIRMANN("Birmann"),
	BRASILIA("Brasília"),
	CAXIAS_DO_SUL("Caxias do Sul"),
	CENU("Cenu"),
	CURITIBA("Curitiba"),
	FLORIANOPOLIS("Florianopolis"),
	FORTALEZA("Fortaleza"),
	GOIANIA("Goiania"),
	HORTOLANDIA("Hortolandia"),
	JOINVILLE("Joinville"),
	LONDRINA("Londrina"),
	MANAUS("Manaus"),
	PASTEOUR("Pasteour"),
	PORTO_ALEGRE("Porto Alegre"),
	RECIFE("Recife"),
	SALVADOR("Salvador"),
	SALVADOR_SUAREZ("Salvador Suarez"),
	TUTOIA("Tutoia"),
	UBERLANDIA("Uberlandia");
	
	
	private String description;

	Location(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
