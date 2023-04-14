package it.corso.service;

import java.util.List;

import it.corso.model.Prenotazione;


public interface PrenotazioneService 
{
	void registraPrenotazione(Prenotazione prenotazione, String data,String time, int idAnagrafica, int idTatuaggio);
	Prenotazione getPrenotazioneById(int id);
	List<Prenotazione> getPrenotazione();
	void cancellaPrenotazione(Prenotazione prenotazione);
}
