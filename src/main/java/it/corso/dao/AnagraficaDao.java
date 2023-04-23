package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Anagrafica;

public interface AnagraficaDao extends CrudRepository<Anagrafica, Integer> 
{
	Anagrafica findByProfiloUsernameAndProfiloPassword(String username, String password);
	
	
}
