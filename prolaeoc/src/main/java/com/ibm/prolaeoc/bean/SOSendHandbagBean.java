package com.ibm.prolaeoc.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import com.ibm.prolaeoc.DAO.DAO;
import com.ibm.prolaeoc.model.Badge;
import com.ibm.prolaeoc.model.Handbag;

@ManagedBean(name = "sendhandbagbean")
public class SOSendHandbagBean {
	
	private Badge badge = new Badge();
	private List<Badge> badges;
	private List<Badge> badgesToHandbag;
	private Handbag handbag = new Handbag();
	private Date actualDate = new Date();
	private String location;
	private boolean checkBadge;
	
	
	//list badges from location
	public void listToHandbag() {
		this.badges = new DAO<Badge>(Badge.class).listHandbag(this.location);
	}
	
	public void sendHandbag() {
		
		for (Badge b: badgesToHandbag) {
			b.setStatus("Sent_To_Reception");
			b.setHandbag(this.handbag.getHandbag_number());
			new DAO<Badge>(Badge.class).update(b);
		}
		
		this.handbag.getHandbag_number();
		this.handbag.setCreation_date(actualDate);
		new DAO<Handbag>(Handbag.class).add(this.handbag);
		
	}
	
	
	
	//update badges checkeds ---- only when click submit
	/*public void isBadgeChecked() {
			this.badge.setStatus("Sent_To_Reception");
			this.badge.setHandbag(this.handbag.getHandbag_number());
			badgesToHandbag.add(this.badge);
			System.out.println("badge of " + this.badge.getName() + " selected");
	}*/
	
	
	
	//create report to print
	public void createReportToPrint() {
		
	}
	
	
	//delete badge
	public void removeBadge() {
		System.out.println("la la la");
		new DAO<Badge>(Badge.class).remove(this.badge);
		
		this.listToHandbag();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Badge deleted successfully!"));
	}
	
	
	
	// getters and setters ---------------------------------------------
	
	public Badge getBadge() {
		return badge;
	}

	public void setBadge(Badge badge) {
		this.badge = badge;
	}

	public List<Badge> getBadges() {
		return badges;
	}

	public void setBadges(List<Badge> badges) {
		this.badges = badges;
	}

	public List<Badge> getBadgesToHandbag() {
		return badgesToHandbag;
	}

	public void setBadgesToHandbag(List<Badge> badgesToHandbag) {
		this.badgesToHandbag = badgesToHandbag;
	}

	public Handbag getHandbag() {
		return handbag;
	}

	public void setHandbag(Handbag handbag) {
		this.handbag = handbag;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isCheckBadge() {
		return checkBadge;
	}

	public void setCheckBadge(boolean checkBadge) {
		this.checkBadge = checkBadge;
	}
	
	
	
}
