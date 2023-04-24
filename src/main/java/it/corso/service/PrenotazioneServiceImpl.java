package it.corso.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.corso.dao.PrenotazioneDao;
import it.corso.model.Anagrafica;
import it.corso.model.Prenotazione;


@Service
public class PrenotazioneServiceImpl implements PrenotazioneService {
	
	@Autowired
	private PrenotazioneDao prenotazioneDao;
	
	@Autowired
	private AnagraficaService anagraficaService;
	
	
	@Override
	public void registraPrenotazione(Prenotazione prenotazione, int anagrafica)
	{
		//impostazione data
//		try
//		{
//			LocalDate dataPrenotazione = LocalDate.parse(data);
//			prenotazione.setData(dataPrenotazione);
//			LocalTime oraPrenotazione = LocalTime.parse(time);
//			prenotazione.setOra(oraPrenotazione);
//		} catch (Exception e)
//		{
//			prenotazione.setData(LocalDate.now());
//			prenotazione.setOra(LocalTime.now());
//		}
		
		Anagrafica cliente = anagraficaService.getAnagraficaById(anagrafica);
		prenotazione.setAnagrafica(cliente);
		prenotazioneDao.save(prenotazione);
	}
	
	@Override
	public Prenotazione getPrenotazioneById(int id)
	{
		return prenotazioneDao.findById(id).get();
	}

	@Override
	public List<Prenotazione> getPrenotazione()
	{
		return (List<Prenotazione>) prenotazioneDao.findAll();
	}

	@Override
	public void cancellaPrenotazione(Prenotazione prenotazione)
	{
		prenotazione.getAnagrafica().getPrenotazioni().remove(prenotazione);
		prenotazioneDao.delete(prenotazione);
	}

}
