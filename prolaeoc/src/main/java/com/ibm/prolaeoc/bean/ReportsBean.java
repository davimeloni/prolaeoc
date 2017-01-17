package com.ibm.prolaeoc.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import com.ibm.prolaeoc.DAO.DAO;

@ManagedBean
@ViewScoped
public class ReportsBean {

	// Chart Bar for Amount of Badges Created by Operator
	public BarChartModel getBadgesByOperator() {

		BarChartModel model = new BarChartModel();
		model.setAnimate(true);

		ChartSeries badgesSerie = new ChartSeries();
		badgesSerie.setLabel("Amount of Badges Created by Operator");

		List<Object[]> badges = DAO.listBadgesByOperator();

		for (Object[] result : badges) {
			String name = (String) result[0];
			int count = ((Number) result[1]).intValue();
			badgesSerie.set(name, count);
		}

		model.addSeries(badgesSerie);

		return model;
	}

	// public List<Object[]> getTotalBadgesByOperator() {
	//
	// List<Object[]> badges = DAO.listBadgesByOperator();
	// Map<String,Integer> badgesMap = new HashMap<String,Integer>();
	//
	// for (Object[] result : badges) {
	// String name = (String) result[0];
	// int count = ((Number) result[1]).intValue();
	// badgesMap.put(name, count);
	// }
	//
	// return badges;
	// }

	// **********************************************************************************

	// Report Status Badges
	public PieChartModel getBadgesByStatus() {

		PieChartModel pie = new PieChartModel();

		List<Object[]> badges = DAO.listBadgesByStatus();
		
		pie.setLegendPosition("w");
		pie.setShowDataLabels(true);
		pie.setFill(false);
		pie.setDataFormat("value");

		for (Object[] result : badges) {
			String status = (String) result[0];
			int number = ((Number) result[1]).intValue();
			pie.set(status, number);
			System.out.println(status + "  -  " + number);
		}

		return pie;

	}
}
