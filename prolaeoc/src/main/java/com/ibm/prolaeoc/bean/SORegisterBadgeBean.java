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
import javax.faces.view.ViewScoped;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ibm.prolaeoc.DAO.DAO;
import com.ibm.prolaeoc.model.Badge;
import com.ibm.prolaeoc.model.Location;
import com.ibm.prolaeoc.model.Operator;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

@ManagedBean(name = "registerbadgebean")
@ViewScoped
public class SORegisterBadgeBean {

	private Badge badge = new Badge();
	private String searchUID;
	private Badge bluepagesID = new Badge();
	private Location location;
	private String stringLocation;
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private Date actualDate = new Date();
	private String dateForm = df.format(actualDate);

	@PostConstruct
	public void generatePIN() {
		this.badge.setPin(new DAO<Long>(Long.class).lastBadgeForPIN() + 1);
	}

	public void save() {

		// Get current operator
		FacesContext context = FacesContext.getCurrentInstance();
		Operator operatorlogged = (Operator) context.getExternalContext().getSessionMap().get("operatorLogged");

		System.out.println("saving badge " + this.badge.getName());
		this.badge.setUid(this.searchUID.toUpperCase());
		this.badge.setStatus("Created");
		this.badge.setOperator(operatorlogged);
		this.badge.setCreation_date(actualDate);

		if (!this.badge.getUid().isEmpty()) {

			new DAO<Badge>(Badge.class).add(this.badge);

			this.badge = new Badge();

			generatePIN();

			context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Badge successfully saved"));

		} else {
			context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Serial is empty!", "Error!"));
		}

	}

	// getters and setters
	// ----------------------------------------------------------
	public Badge getBadge() {
		return badge;
	}

	public Badge findBadgeByUID() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			Client c = Client.create();
			WebResource wr = c.resource("http://localhost:8080/BluePagesRESTFulAPI/bluepages/" + this.searchUID);
			String json = wr.get(String.class);
			Gson gson = new Gson();
			// Badge badge = gson.fromJson(json, new TypeToken<Badge>() {
			// }.getType());
			Badge[] badges = gson.fromJson(json, Badge[].class);
			badge = badges[0];

		} catch (Exception e) {
			System.out.println("employee does not exist");
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "employee does not exist!", "Error!"));
		}
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

	public Badge getBluepagesID() {
		return bluepagesID;
	}

	public void setBluepagesID(Badge bluepagesID) {
		this.bluepagesID = bluepagesID;
	}

	public DateFormat getDf() {
		return df;
	}

	public void setDf(DateFormat df) {
		this.df = df;
	}

	public String getDateForm() {
		return dateForm;
	}

	public void setDateForm(String dateForm) {
		this.dateForm = dateForm;
	}

	public String getSearchUID() {
		return searchUID;
	}

	public void setSearchUID(String searchUID) {
		this.searchUID = searchUID;
	}

}
