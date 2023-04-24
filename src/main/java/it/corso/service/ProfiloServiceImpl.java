package it.corso.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.ProfiloDao;
import it.corso.model.Profilo;

@Service
public class ProfiloServiceImpl implements ProfiloService {

	@Autowired
	private ProfiloDao profiloDao;
	
	@Override
	public void registraProfilo(Profilo profilo) {
		profiloDao.save(profilo);
	}

	@Override
	public Profilo getProfiloById(int id) {
		
		return profiloDao.findById(id).get();
	}

	@Override
	public List<Profilo> getProfilo() {
		
		return (List<Profilo>) profiloDao.findAll();
	}

	@Override
	public void cancellaProfilo(Profilo profilo) {
		
		profiloDao.delete(profilo);
	}
	
}
