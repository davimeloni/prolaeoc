package com.ibm.prolaeoc.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.faces.bean.ManagedBean;

import com.ibm.prolaeoc.DAO.DAO;
import com.ibm.prolaeoc.model.Badge;

@ManagedBean(name = "locationlistbean")
public class SOLocationListBean {
	
	private Badge badge = new Badge();
	private List<Badge> badges;
	private List<Entry<String, Integer>> locationBase = new ArrayList<>();
	private Map<String, Integer> map = new HashMap<String, Integer>();
	
	public void getLocations() {
		List<String> locations = new DAO<String>(String.class).listCreated();
		//List<String> toPrint = new ArrayList<>();
		
		for (String temp : locations) {
			Integer count = this.map.get(temp);
			this.map.put(temp, (count == null) ? 1 : count + 1);
		}
		this.locationBase = new ArrayList<>(map.entrySet());
	}
	
	
	
	
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




	public List<Entry<String, Integer>> getLocationBase() {
		return locationBase;
	}




	public void setLocationBase(List<Entry<String, Integer>> locationBase) {
		this.locationBase = locationBase;
	}




	public Map<String, Integer> getMap() {
		return map;
	}




	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}



}
