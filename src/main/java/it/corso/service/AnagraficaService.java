package it.corso.service;

import java.util.List;
import it.corso.model.Anagrafica;
import jakarta.servlet.http.HttpSession;


public interface AnagraficaService 
{
	void registraAnagrafica(Anagrafica anagrafica);
	Anagrafica getAnagraficaById(int id);
	List<Anagrafica> getAnagrafica();
	void cancellaAnagrafica(Anagrafica anagrafica);
	boolean loginUtente( String username, String password, HttpSession session);
	boolean loginAdmin(String username, String password, HttpSession session);
}
