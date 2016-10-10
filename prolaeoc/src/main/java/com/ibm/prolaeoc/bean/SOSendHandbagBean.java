package com.ibm.prolaeoc.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.event.SelectEvent;

import com.ibm.prolaeoc.DAO.DAO;
import com.ibm.prolaeoc.model.Badge;
import com.ibm.prolaeoc.model.Handbag;

@ManagedBean(name = "sendhandbagbean")
@ViewScoped
public class SOSendHandbagBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	private List<Badge> badges;
	private static String location;
	private List<Badge> selectedBadges;
	private Badge badgeToDelete = new Badge();
	private Badge[] selectedBadges2;
	private Handbag handbag = new Handbag();
	private DateFormat df = new SimpleDateFormat("dd/MM/yy");
	private Date actualDate = new Date();


	//list badges from location
	@PostConstruct
	public void listToHandbag() {
		this.badges = new DAO<Badge>(Badge.class).listHandbag(SOSendHandbagBean.location);
	
		long seq = Long.parseLong(new DAO<String>(String.class).lastHandbagNumber().substring(5)) + 1;
		this.handbag.setHandbag_number("LAEOC" + String.format("%05d", seq));
	}
	
	//constructor
	public SOSendHandbagBean () {
		
	}
	
	//check for selection event
	public void selectListener(SelectEvent event) {
		System.out.println("check selection");
	}
	
	//send handbag, save atributes
	public void sendHanbag () {
		this.handbag.setCreation_date(actualDate);
		
		String s = this.handbag.getHandbag_number().substring(0);
		System.out.println("x");
		
		for(Badge b: selectedBadges2) {
			b.setHandbag(s);
			b.setSend_date(actualDate);
			b.setStatus("SentToReception");
			System.out.println(b.getHandbag() + " | " + b.getSend_date());
			new DAO<Badge>(Badge.class).update(b);
		}
		
		new DAO<Handbag>(Handbag.class).add(this.handbag);
		this.handbag = new Handbag();
		
		this.badges = new DAO<Badge>(Badge.class).listHandbag(SOSendHandbagBean.location);
		
		long seq = Long.parseLong(new DAO<String>(String.class).lastHandbagNumber().substring(5)) + 1;
		this.handbag.setHandbag_number("LAEOC" + String.format("%05d", seq));
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Handbag successfully saved"));
	}
	
	//delete badge
	public void deleteBadge() {
		new DAO<Badge>(Badge.class).remove(this.badgeToDelete);
		this.badges = new DAO<Badge>(Badge.class).listHandbag(SOSendHandbagBean.location);
	}
	

	//--------------------- getters and setters ------------------------
	public List<Badge> getBadges() {
		return badges;
	}

	public void setBadges(List<Badge> badges) {
		this.badges = badges;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Badge> getSelectedBadges() {
		return selectedBadges;
	}

	public void setSelectedBadges(List<Badge> selectedBadges) {
		this.selectedBadges = selectedBadges;
	}

	public Badge[] getSelectedBadges2() {
		return selectedBadges2;
	}

	public void setSelectedBadges2(Badge[] selectedBadges2) {
		this.selectedBadges2 = selectedBadges2;
	}

	public Handbag getHandbag() {
		return handbag;
	}

	public void setHandbag(Handbag handbag) {
		this.handbag = handbag;
	}

	public Date getActualDate() {
		return actualDate;
	}

	public void setActualDate(Date actualDate) {
		this.actualDate = actualDate;
	}

	public DateFormat getDf() {
		return df;
	}

	public void setDf(DateFormat df) {
		this.df = df;
	}

	public Badge getBadgeToDelete() {
		return badgeToDelete;
	}

	public void setBadgeToDelete(Badge badgeToDelete) {
		this.badgeToDelete = badgeToDelete;
	}
	
	
	
}
