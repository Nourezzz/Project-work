package it.corso.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.corso.dao.AnagraficaDao;
import it.corso.model.Admin;
import it.corso.model.Anagrafica;
import jakarta.servlet.http.HttpSession;

@Service
public class AnagraficaServiceImpl implements AnagraficaService {
	
	@Autowired
	 private AnagraficaDao anagraficaDao;
	
	@Override
	public void registraAnagrafica(Anagrafica anagrafica) {
		
		anagraficaDao.save(anagrafica);

	}

	@Override
	public Anagrafica getAnagraficaById(int id) {
		
		 return anagraficaDao.findById(id).get();
	}

	@Override
	public List<Anagrafica> getAnagrafica() {
		
		return (List<Anagrafica>) anagraficaDao.findAll();
	}

	@Override
	public void cancellaAnagrafica(Anagrafica anagrafica) {
	
		anagraficaDao.delete(anagrafica);

	}
	
	@Override
	public boolean loginUtente(String username, String password, HttpSession session)
	{
		Anagrafica anagrafica = anagraficaDao.findByProfiloUsernameAndProfiloPassword(username, password); // riceve username e pass dal form
		if (anagrafica == null) {
			return false;
		}
		if(username.equalsIgnoreCase(anagrafica.getProfilo().getUsername()) && password.equalsIgnoreCase(anagrafica.getProfilo().getPassword())) {		
			session.setAttribute("profilo", anagrafica);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean loginAdmin(String username, String password, HttpSession session)
	{
		//costanti per il confronto
		final String USERNAME_REGISTRATO = "admin";
		final String PASSWORD_REGISTRATA = "admin";
		//controllo login
		if(username.equalsIgnoreCase(USERNAME_REGISTRATO) && password.equals(PASSWORD_REGISTRATA))
		{
			Admin utente = new Admin();
			utente.setUsername(USERNAME_REGISTRATO);
			utente.setPassword(PASSWORD_REGISTRATA);
			//salvo l'utente in sessione
			session.setAttribute("admin", utente);
			return true;
		}
		//login fallito
		return false;
	}

}
