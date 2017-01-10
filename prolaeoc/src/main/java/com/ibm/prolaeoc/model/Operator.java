package com.ibm.prolaeoc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "OPERATOR" , schema="PROLAEOC")
public class Operator implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@SequenceGenerator(name="SEQ_LAEOCOPERATOR",allocationSize=1, initialValue=1, sequenceName="PROLAEOC.SEQ_LAEOCOPERATOR")
	@Id @GeneratedValue(generator="SEQ_LAEOCOPERATOR", strategy=GenerationType.SEQUENCE)
	private long id;
	private String name;
	@Column(unique=true)
	private String email;
	private String password;
	private String type;
	private boolean enabled;
	
	
	//@OneToMany(mappedBy="operator_id")
	//private List<Badge> badges = new ArrayList<Badge>();
	
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
	
	/*
	public List<Badge> getBadges() {
		return badges;
	}
	public void addBadge(Badge badge) {
		this.badges.add(badge);
	}
	*/

}
