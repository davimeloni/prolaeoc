package com.ibm.prolaeoc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "LAEOCHANDBAG" , schema="DMEZA")
public class Handbag implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String handbag_number;
	private Date creation_date;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getHandbag_number() {
		return handbag_number;
	}
	public void setHandbag_number(String handbag_number) {
		this.handbag_number = handbag_number;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	
	

}
