package com.ibm.prolaeoc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "LAEOCBADGE" , schema="DMEZA")
public class Badge implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;
	private long serial;
	private long pin;
	private String location;
	private String work_location;
	private Date creation_date;
	private Date send_date;
	private Date reception_date;
	private Date received_date;
	
	private String handbag;
	private Operator operator;
	
	private String status;
	
	private boolean isActive;
	//manager approval
	//photo
	
	@SequenceGenerator(name="SEQ_LAEOCBADGE", sequenceName="DMEZA.SEQ_LAEOCBADGE")
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

	public long getSerial() {
		return serial;
	}

	public void setSerial(long serial) {
		this.serial = serial;
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
	
	
	

}
