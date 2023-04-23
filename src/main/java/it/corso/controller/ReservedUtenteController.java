package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import it.corso.model.Anagrafica;
import it.corso.model.Prenotazione;
import it.corso.service.AnagraficaService;
import it.corso.service.PrenotazioneService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/utente_reserved")
public class ReservedUtenteController {
	
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	@Autowired
	private AnagraficaService anagraficaService;
	
	 @GetMapping
	    public String getPage(HttpSession session, Model model)
	    {
	        //se l'utente non è loggato lo rimando a login
	        if(session.getAttribute("profilo") == null)
	            return "redirect:/utente_login";
	        //altrimenti gli faccio vedere l'area riservata
	        Anagrafica profilo = (Anagrafica) session.getAttribute("profilo");
	        List<Anagrafica> clienti = anagraficaService.getAnagrafica();
	        model.addAttribute("clienti", clienti);
	        model.addAttribute("profilo", profilo);
	        List<Prenotazione> prenotazioni = prenotazioneService.getPrenotazione();
	        model.addAttribute("prenotazioni", prenotazioni);
	        return "reservedUtente";
	    }
	    
	    
}
