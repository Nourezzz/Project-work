package it.corso.service;

import java.util.List;
import it.corso.model.Anagrafica;
import it.corso.model.Profilo;
import jakarta.servlet.http.HttpSession;


public interface AnagraficaService 
{
	void registraAnagrafica(Anagrafica anagrafica);
	Anagrafica getAnagraficaByID(int id);
	List<Anagrafica> getAnagrafica();
	void cancellaAnagrafica(Anagrafica anagrafica);
	boolean loginUtente(List<Profilo> profili, String username, String password, HttpSession session);
	boolean loginAdmin(String username, String password, HttpSession session);
	String logout(HttpSession session);
}
