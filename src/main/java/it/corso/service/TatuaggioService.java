package it.corso.service;

import java.util.List;

import it.corso.model.Tatuaggio;

public interface TatuaggioService 
{
	void registraTatuaggio(Tatuaggio tatuaggio);
	Tatuaggio getTatuaggioById(int id);
	List<Tatuaggio> getTatuaggio();
	void cancellaTatuaggio(Tatuaggio tatuaggio);
}
