package com.ibm.prolaeoc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.Gson;

@Entity
@Table(name = "BADGE" , schema="DMEZA" )
public class Badge implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private long id;
	private long pin;
	private String work_location;
	private Date creation_date;
	private Date send_date;
	private Date reception_date;
	private Date received_date;
	
	private String handbag;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "operator_id")
	private Operator operator;
	
	private String status;
	
	private boolean isActive;
	//manager approval
	//photo
	private String uid;
	private String office;
	private String building;
	private String location;
	private String officePhone;
	private String orgtitle;
	private String mobilePhone;
	private String bio;
	private String isEmployee;
	private String floor;
	private String email;
	private String name;
	private String imageUrl;
	private String notesId;
	
	@SequenceGenerator(name="SEQ_LAEOCBADGE", allocationSize=1, initialValue=1,sequenceName="DMEZA.SEQ_LAEOCBADGE")
	@Id @GeneratedValue(generator="SEQ_LAEOCBADGE", strategy=GenerationType.SEQUENCE)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPin() {
		return pin;
	}

	public void setPin(long pin) {
		this.pin = pin;
	}
	

	
	@Temporal(TemporalType.DATE)
	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getSend_date() {
		return send_date;
	}

	public void setSend_date(Date send_date) {
		this.send_date = send_date;
	}

	@Temporal(TemporalType.DATE)
	public Date getReception_date() {
		return reception_date;
	}

	public void setReception_date(Date reception_date) {
		this.reception_date = reception_date;
	}

	@Temporal(TemporalType.DATE)
	public Date getReceived_date() {
		return received_date;
	}

	public void setReceived_date(Date received_date) {
		this.received_date = received_date;
	}


	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getWork_location() {
		return work_location;
	}

	public void setWork_location(String work_location) {
		this.work_location = work_location;
	}

	public String getHandbag() {
		return handbag;
	}

	public void setHandbag(String handbag) {
		this.handbag = handbag;
	}
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getOrgtitle() {
		return orgtitle;
	}

	public void setOrgtitle(String orgtitle) {
		this.orgtitle = orgtitle;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getIsEmployee() {
		return isEmployee;
	}

	public void setIsEmployee(String isEmployee) {
		this.isEmployee = isEmployee;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getNotesId() {
		return notesId;
	}

	public void setNotesId(String notesId) {
		this.notesId = notesId;
	}

		// Convert objec to JSON
		public String toJSON(){
			return new Gson().toJson(this);
		}

}
