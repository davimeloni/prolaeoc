package com.ibm.prolaeoc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import org.primefaces.event.SelectEvent;

import com.ibm.prolaeoc.DAO.DAO;
import com.ibm.prolaeoc.model.Badge;
import com.ibm.prolaeoc.model.Handbag;

@ManagedBean(name="collecthandbagbean")
@SessionScoped
public class RCollectHandbagBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Handbag handbag = new Handbag();
	private List<Badge> badges = new ArrayList<>();
	private Badge[] badgesCollected;
	private Badge[] badgesCollected2;
	private Date actualDate = new Date();

	//constructor
	public RCollectHandbagBean() {
		
	}
	
	@PostConstruct
	public void listByHandbag(ActionEvent event) {	
		this.badges = new DAO<String>(String.class).listReception(this.handbag.getHandbag_number());	
	}
	
	public void selectListener(SelectEvent event) {
		System.out.println("check");
	}
	
	public void collectHandbag() {
		
		System.out.println("x");
		
		for(Badge b: badgesCollected2) {
			b.setReception_date(actualDate);
			b.setStatus("InReception");
			System.out.println(b.getName() + " collected");
			new DAO<Badge>(Badge.class).update(b);
		}
		
		this.badges = new DAO<String>(String.class).listReception(this.handbag.getHandbag_number());
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Handbag successfully saved"));
	}

	
	
	//----------------- getters and setters --------------------------
	public Handbag getHandbag() {
		return handbag;
	}

	public void setHandbag(Handbag handbag) {
		this.handbag = handbag;
	}

	public List<Badge> getBadges() {
		return badges;
	}

	public void setBadges(List<Badge> badges) {
		this.badges = badges;
	}

	public Badge[] getBadgesCollected() {
		return badgesCollected;
	}

	public void setBadgesCollected(Badge[] badgesCollected) {
		this.badgesCollected = badgesCollected;
	}

	public Date getActualDate() {
		return actualDate;
	}

	public void setActualDate(Date actualDate) {
		this.actualDate = actualDate;
	}

	public Badge[] getBadgesCollected2() {
		return badgesCollected2;
	}

	public void setBadgesCollected2(Badge[] badgesCollected2) {
		this.badgesCollected2 = badgesCollected2;
	}
	
}
