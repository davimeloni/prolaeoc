package com.ibm.proalaeoc.utils;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import com.ibm.prolaeoc.model.Operator;



public class LoginListener implements PhaseListener {


	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {

		FacesContext context = event.getFacesContext();
	    String nomePagina = context.getViewRoot().getViewId();
	
    	//se ja esta na pagina de login, continua nela sem problemas
	    if ("/login.xhtml".equals(nomePagina)) {
	        return;
	    }

    	//se nao esta na pagina de login, verifica se esta logado
	    Operator operatorlogged = (Operator) context.getExternalContext()
	            .getSessionMap().get("operatorLogged");

    	//se estiver logado ok
	    if(operatorlogged != null) {
	        return;
	    }

    	//se nao estiver logado, redireciona para a pagina de login
	    NavigationHandler handler = context.getApplication().getNavigationHandler();
	    handler.handleNavigation(context, null, "/login?faces-redirect=true");
	    context.renderResponse();
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		System.out.println("Fase " + event.getPhaseId());
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
	
	

}
