package com.ibm.prolaeoc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LAEOCOPERATOR" , schema="DMEZA")
public class Operator implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;
	private String email;
	private String password;
	private String type;
	private boolean enabled;
	
	@SequenceGenerator(name="SEQ_LAEOCOPERATOR", sequenceName="DMEZA.SEQ_LAEOCOPERATOR")
	@Id @GeneratedValue(generator="SEQ_LAEOCOPERATOR", strategy=GenerationType.SEQUENCE)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	

}
