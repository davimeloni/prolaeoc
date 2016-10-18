package com.ibm.prolaeoc.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.ibm.prolaeoc.DAO.OperatorDAO;
import com.ibm.prolaeoc.model.Operator;

@ManagedBean
@RequestScoped
public class LoginBean {

	private Operator operator = new Operator();

	public Operator getOperator() {
		return operator;
	}

	public String doLogin() {
		System.out.println("doing the login for Operator " + this.operator.getEmail());

		FacesContext context = FacesContext.getCurrentInstance();
		boolean existe = new OperatorDAO().exits(this.operator);
		if (existe) {
			if ("recepcionist@br.ibm.com".equals(this.operator.getEmail())) {
				context.getExternalContext().getSessionMap().put("operatorLogged", this.operator);
				return "rcollecthandbag?faces-redirect=true";
			} else {
				context.getExternalContext().getSessionMap().put("operatorLogged", this.operator);
				return "soregisterbadge?faces-redirect=true";
			}
		}

		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Operator not found!!"));

		return "login?faces-redirect=true";
	}

	public String deslogar() {
		//System.out.println("lalala");
		//FacesContext.getCurrentInstance().getExternalContext().invalidateSession();		  
		//return "login?faces-redirect=true";
		
		FacesContext context = FacesContext.getCurrentInstance();
	    context.getExternalContext().getSessionMap().remove("operatorLogged");
	    return "login?faces-redirect=true";	
	}
}
