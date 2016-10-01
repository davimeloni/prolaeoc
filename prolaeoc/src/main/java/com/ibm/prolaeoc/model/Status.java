package com.ibm.prolaeoc.model;

public enum Status {
	CREATED("Created"),
	SENT("Sent"),
	RECEP_RECEIVE("Recep_Receive"),
	EMPLOYEE_RECEIVE("Employee_Receive");
	
	private String status;
	
	Status (String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
