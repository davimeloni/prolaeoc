package com.ibm.prolaeoc.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;

import org.primefaces.context.RequestContext;

import com.ibm.prolaeoc.DAO.DAO;
import com.ibm.prolaeoc.model.Badge;

@ManagedBean(name = "employeereceivebean")
@SessionScoped
public class REmployeeReceiveBean {

	private List<Badge> badges;
	private Badge badge = new Badge();
	private Date actualDate = new Date();
	private String serialString;
	private String isReady;
	
	public REmployeeReceiveBean () {
		
	}

	public void findBadgeBySerial(ActionEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.badge = new DAO<Badge>(Badge.class).searchForSerial(this.serialString);
			this.isReady = "Badge Ready to be used!";
			
		} catch (Exception e) {
			System.out.println("badge not found or does not exist");
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Badge not ready to active or does not exist!", "Error!"));
		}
	}

	public void activateBadge() {
		System.out.println(this.badge.getLocation());
		this.badge.setStatus("BadgeReceived");
		this.badge.setReceived_date(actualDate);
		this.badge.setActive(true);

		new DAO<Badge>(Badge.class).update(this.badge);

		this.badge = new Badge();
		this.serialString = null;
		this.isReady = null;
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Badge received!"));
	}

	// -------------------- getters an
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

	public String getSerialString() {
		return serialString;
	}

	public void setSerialString(String serialString) {
		this.serialString = serialString;
	}

	public String getIsReady() {
		return isReady;
	}

	public void setIsReady(String isReady) {
		this.isReady = isReady;
	}

}
