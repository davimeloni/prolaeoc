package com.ibm.prolaeoc.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.ibm.prolaeoc.DAO.DAO;
import com.ibm.prolaeoc.DAO.OperatorDAO;
import com.ibm.prolaeoc.model.Operator;

@ManagedBean
@RequestScoped
public class LoginBean {

	private Operator operator = new Operator();

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public LoginBean() {

	}

	public String doLogin() {
		System.out.println("###### doing the login for Operator " + this.operator.getEmail());

		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.operator = new OperatorDAO().exists(this.operator);

			// if (this.operator.getEmail() != null) {
			ExternalContext ec = context.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			session.setAttribute("operatorLogged", this.operator);

			if ("recepcionist".equals(this.operator.getType())) {
				context.getExternalContext().getSessionMap().put("operatorLogged", this.operator);
				return "rcollecthandbag?faces-redirect=true";
			} else {
				context.getExternalContext().getSessionMap().put("operatorLogged", this.operator);
				return "soregisterbadge?faces-redirect=true";
			}
			// }

		} catch (Exception e) {
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User not found!", "Error!"));

			return "login?faces-redirect=true";
		}

	}

	public String deslogar() {

		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("operatorLogged");
		return "login?faces-redirect=true";
	}

}
