package it.corso.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.corso.dao.PrenotazioneDao;
import it.corso.model.Anagrafica;
import it.corso.model.Prenotazione;
import it.corso.model.Tatuaggio;

@Service
public class PrenotazioneServiceImpl implements PrenotazioneService {

//	@Override
//	public void registraPrenotazione(Prenotazione prenotazione, String data, String time, int idAnagrafica,
//			int[] idTatuaggi) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public Prenotazione getPrenotazioneById(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Prenotazione> getPrenotazione() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void cancellaPrenotazione(Prenotazione prenotazione) {
//		// TODO Auto-generated method stub
//
//	}
	
	@Autowired
	private PrenotazioneDao prenotazioneDao;
	
	@Autowired
	private AnagraficaService anagraficaService;
	
	@Autowired
	private TatuaggioService tatuaggioService;
	
	@Override
	public void registraPrenotazione(Prenotazione prenotazione, String data,String time, int idAnagrafica, int idTatuaggio)
	{
		//impostazione data
		try
		{
			LocalDate dataPrenotazione = LocalDate.parse(data);
			prenotazione.setData(dataPrenotazione);
			LocalTime oraPrenotazione = LocalTime.parse(time);
			prenotazione.setOra(oraPrenotazione);
		} catch (Exception e)
		{
			prenotazione.setData(LocalDate.now());
			prenotazione.setOra(LocalTime.now());
		}
		//impostazione cliente
		Anagrafica cliente = anagraficaService.getAnagraficaByID(idAnagrafica);
		prenotazione.setAnagrafica(cliente);
		Tatuaggio tatuaggio = tatuaggioService.getTatuaggioById(idTatuaggio);
		prenotazione.setTatuaggio(tatuaggio);
		double sconto = (prenotazione.getTatuaggio().getPrezzo()*10)/100;
		prenotazione.setSconto(sconto);
		//calcolo totale ordine
		//registrazione ordine
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
		//rimozione dell'ordine da cancellare dalla lista ordini del cliente collegato (gli altri ordini restano)
		prenotazione.getAnagrafica().getPrenotazioni().remove(prenotazione);
		//pulizia lista prodotti dell'ordine da cancellare
		prenotazione.getTatuaggio();
		//cancellazione ordine
		prenotazioneDao.delete(prenotazione);
	}

}
