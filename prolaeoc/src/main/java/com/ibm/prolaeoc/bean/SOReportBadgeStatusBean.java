package com.ibm.prolaeoc.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;

import com.ibm.prolaeoc.DAO.DAO;
import com.ibm.prolaeoc.model.Badge;

@ManagedBean(name="reportbadgestatusbean")
@ViewScoped
public class SOReportBadgeStatusBean {
	
	private String serialString;
	private List<Badge> badges;
	private Badge badge = new Badge();
	private Badge badgeToDelete = new Badge();
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private Date last_update_date = new Date();
	private String dateForm = df.format(last_update_date);
	
	//construtor
	public SOReportBadgeStatusBean () {
		
	}
	
	
	public void findBadgeBySerial(ActionEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
			this.badges = new DAO<Badge>(Badge.class).listReportByUID(this.serialString);
			
			if (this.badges.isEmpty()) {
				System.out.println("employee not found!");
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "emplyoee not found!", "Error!"));
			}
		
		for (Badge b: badges) {
			if (b.getReceived_date() != null) {
				this.last_update_date = b.getReceived_date();
			} else if (b.getReception_date() != null) {
				this.last_update_date = b.getReception_date();
			} else if (b.getSend_date() != null) {
				this.last_update_date = b.getSend_date();
			} else {
				this.last_update_date = b.getCreation_date();
			}
		}
	}
	
	//delete badge
		public void deleteBadge() {
			new DAO<Badge>(Badge.class).remove(this.badgeToDelete);
		}

	
	//Getters and Setters
	public String getSerialString() {
		return serialString;
	}

	public void setSerialString(String serialString) {
		this.serialString = serialString;
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


	public Badge getBadgeToDelete() {
		return badgeToDelete;
	}


	public void setBadgeToDelete(Badge badgeToDelete) {
		this.badgeToDelete = badgeToDelete;
	}

	public DateFormat getDf() {
		return df;
	}

	public void setDf(DateFormat df) {
		this.df = df;
	}

	public Date getLast_update_date() {
		return last_update_date;
	}

	public void setLast_update_date(Date last_update_date) {
		this.last_update_date = last_update_date;
	}

	public String getDateForm() {
		return dateForm;
	}

	public void setDateForm(String dateForm) {
		this.dateForm = dateForm;
	}
	
}
