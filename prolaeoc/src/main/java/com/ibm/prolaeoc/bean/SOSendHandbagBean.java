package com.ibm.prolaeoc.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.ibm.prolaeoc.DAO.DAO;
import com.ibm.prolaeoc.model.Badge;
import com.ibm.prolaeoc.model.Handbag;

@ManagedBean(name = "sendhandbagbean")
public class SOSendHandbagBean {
	
	private Badge badge;
	private List<Badge> badges;
	private List<Badge> badgesToHandbag;
	private boolean badgeChecked;
	private Handbag handbag;
	private DateFormat df = new SimpleDateFormat("dd/MM/yy");
	private Date actualDate = new Date();
	
	
	//list badges from location
	public void listToHandbag(String location) {
		this.badges = new DAO<Badge>(Badge.class).listHandbag("HORTOLANDIA");
	}
	
	//update badges checkeds ---- only when click submit
	public void isBadgeChecked() {
			this.badge.setStatus("Sent_To_Reception");
			this.badge.setHandbag(this.handbag.getHandbag_number());
			new DAO<Badge>(Badge.class).update(this.badge);		
	}
	
	public void sendHandbag() {
		this.handbag.getHandbag_number();
		this.handbag.setCreation_date(actualDate);
		new DAO<Handbag>(Handbag.class).add(this.handbag);
		
	}
	
	//create report to print
	public void createReportToPrint() {
		
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

	public void setBadgeChecked(boolean badgeChecked) {
		this.badgeChecked = badgeChecked;
	}

	public Handbag getHandbag() {
		return handbag;
	}

	public void setHandbag(Handbag handbag) {
		this.handbag = handbag;
	}
	
	
}
