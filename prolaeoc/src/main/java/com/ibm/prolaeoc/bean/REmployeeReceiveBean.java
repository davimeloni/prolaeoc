package com.ibm.prolaeoc.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.ibm.prolaeoc.DAO.DAO;
import com.ibm.prolaeoc.model.Badge;

@ManagedBean(name="employeereceivebean")
public class REmployeeReceiveBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Badge> badges;
	private Badge badge = new Badge();
	private Date actualDate = new Date();
	
	public void listBadgesToActive() {
		this.badges = new DAO<Badge>(Badge.class).listBadgesToActivate();
	}
	
	public void findBadgeById(String id) {
		long l = Long.parseLong(id);
		this.badge = new DAO<Badge>(Badge.class).searchForId(l);
	}
	
	public void activateBadge() {
		this.badge.setStatus("EmployeeActive");
		this.badge.setReceived_date(actualDate);
		this.badge.setActive(true);
		
		new DAO<Badge>(Badge.class).update(badge);
	}

	public List<Badge> getBadges() {
		return badges;
	}

	public void setBadges(List<Badge> badges) {
		this.badges = badges;
	}

	public Badge getBadge() {
		return badge;
	}

	public void setBadge(Badge badge) {
		this.badge = badge;
	}

	public Date getActualDate() {
		return actualDate;
	}

	public void setActualDate(Date actualDate) {
		this.actualDate = actualDate;
	}
	
}
