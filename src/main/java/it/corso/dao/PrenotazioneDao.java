package it.corso.dao;

import org.springframework.data.repository.CrudRepository;


import it.corso.model.Prenotazione;

public interface PrenotazioneDao extends CrudRepository<Prenotazione, Integer> 
{
	//Prenotazione findByanagrafica(String nome, String cognome, String data_di_nascita, String email);
}
