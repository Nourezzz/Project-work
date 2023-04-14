package it.corso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.TatuaggioDao;
import it.corso.model.Tatuaggio;

@Service
public class TatuaggioServiceImpl implements TatuaggioService {

	
	@Autowired
	private TatuaggioDao tatuaggioDao;
	
	
	@Override
	public void registraTatuaggio(Tatuaggio tatuaggio) {
		tatuaggioDao.save(tatuaggio);
	}

	@Override
	public Tatuaggio getTatuaggioById(int id) {
		
		return tatuaggioDao.findById(id).get();
	}

	@Override
	public List<Tatuaggio> getTatuaggio() {
		
		return (List<Tatuaggio>) tatuaggioDao.findAll();
	}

	@Override
	public void cancellaTatuaggio(Tatuaggio tatuaggio) {
		
		tatuaggioDao.delete(tatuaggio);
	}

}
