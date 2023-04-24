package it.corso.service;

import java.util.List;

import it.corso.model.Profilo;

public interface ProfiloService {

	void registraProfilo(Profilo profilo);
	Profilo getProfiloById(int id);
	List<Profilo> getProfilo();
	void cancellaProfilo(Profilo profilo);
	
}
