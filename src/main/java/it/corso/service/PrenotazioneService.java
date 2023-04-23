package it.corso.service;

import java.util.List;


import it.corso.model.Prenotazione;

public interface PrenotazioneService 
{
	void registraPrenotazione(Prenotazione prenotazione, int anagrafica);
	Prenotazione getPrenotazioneById(int id);
	List<Prenotazione> getPrenotazione();
	void cancellaPrenotazione(Prenotazione prenotazione);
}
