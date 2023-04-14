package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Prenotazione;

public interface PrenotazioneDao extends CrudRepository<Prenotazione, Integer> {

}
