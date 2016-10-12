package com.ibm.prolaeoc.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.ibm.prolaeoc.DAO.DAO;
import com.ibm.prolaeoc.model.Badge;
import com.ibm.prolaeoc.model.Location;
import com.ibm.prolaeoc.model.Operator;

@ManagedBean(name="registerbadgebean")
public class SORegisterBadgeBean {

	private Badge badge = new Badge();
	private Location location;
	private String stringLocation;
	private Operator operator = new Operator();
	
	@PostConstruct
	public void generatePIN () {
		this.badge.setPin(new DAO<Long>(Long.class).lastBadgeForPIN() + 1);
	}
	
	public void save() {
		System.out.println("saving badge " + this.badge.getName());
		this.badge.setStatus("Created");
		new DAO<Badge>(Badge.class).add(this.badge);

		this.badge = new Badge();
		
		generatePIN();
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Badge successfully saved"));
		
	}
	

	// getters and setters ----------------------------------------------------------
	public Badge getBadge() {
		return badge;
	}

	public void setBadge(Badge badge) {
		this.badge = badge;
	}

	public String getStringLocation() {
		return stringLocation;
	}

	public void setStringLocation(String stringLocation) {
		this.stringLocation = stringLocation;
	}

	public Location[] getLocation() {
		return Location.values();
	}

}
