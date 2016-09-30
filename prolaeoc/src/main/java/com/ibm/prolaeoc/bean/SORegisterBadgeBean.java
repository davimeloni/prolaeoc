package com.ibm.prolaeoc.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import com.ibm.prolaeoc.model.Badge;
import com.ibm.prolaeoc.model.Location;
import com.ibm.prolaeoc.repository.BadgeRepository;

@ManagedBean(name="registerbadgebean")
public class SORegisterBadgeBean {

	private Badge badge = new Badge();
	private Location location;
	private String stringLocation;

	private BadgeRepository repository;

	/*public void save_old() {
		repository.add(this.badge);
		badge = new Badge();

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Badge successfully saved"));

	}*/
	
	public String save() {
		System.out.println("Gravando badge " + this.badge);

		new com.ibm.prolaeoc.DAO.DAO<Badge>(Badge.class).adiciona(this.badge);

		this.badge = new Badge();
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Badge successfully saved"));
		return "qualquercoisa";
	}

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

	public Location getLocation() {
		return location;
	}

}
