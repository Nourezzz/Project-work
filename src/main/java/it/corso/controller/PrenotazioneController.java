package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import it.corso.model.Anagrafica;
import it.corso.model.Prenotazione;
import it.corso.service.PrenotazioneService;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/prenotazione")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;
    
  
    @GetMapping
    public String getPage(
    Model model,
    HttpSession session) {
    	if (session.getAttribute("profilo")  == null ) {
    		return"redirect:/utente_login";
			
		}
    	Anagrafica profilo = (Anagrafica) session.getAttribute("profilo");
    	model.addAttribute("profilo", profilo);
    	model.addAttribute("anagrafica" , (Anagrafica)session.getAttribute("profilo"));// passo attributo
    model.addAttribute("prenotazione", new Prenotazione());
    return "prenotazione";
    }

    
    
    
    
    
    @PostMapping("/registraprenotazione")
    public String registraPrenotazione(
    @ModelAttribute("prenotazione") Prenotazione prenotazione,
    HttpSession session) {
    
    if (session.getAttribute("profilo")  != null) {
		Anagrafica anagrafica = (Anagrafica) session.getAttribute("profilo");
		prenotazioneService.registraPrenotazione(prenotazione, anagrafica.getId());
    }
	
    
    
    
    return "redirect:/homepage";
    }
    }



//@PostMapping("/registraprenotazione")
//public String registraPrenotazione(
//@ModelAttribute("prenotazione") Prenotazione prenotazione,
//@RequestParam(name = "data", required = false) String data,
//@RequestParam(name = "ora", required = false) String time,
//@RequestParam(name = "sconto", required = false, defaultValue= "1") double sconto, // basta che non è 0 altrimenti errore 500 sconto is null
//@RequestParam(name = "anagrafica", required = true ) Integer anagrafica,
//@RequestParam(name = "tatuaggio", required = true) Integer tatuaggio)
//{

//package it.corso.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import it.corso.model.Prenotazione;
//import it.corso.service.PrenotazioneService;
//
//
//
//@Controller
//@RequestMapping("/prenotazione")
//public class PrenotazioneController {
//
//@Autowired
//private PrenotazioneService prenotazioneService;
//
//@GetMapping 
//public String getPage(Model model, @RequestParam(name = "id", required = false) Integer id) {
//	Prenotazione prenotazione = id == null ? new Prenotazione() : prenotazioneService.getPrenotazioneById(id);
//	model.addAttribute("prenotazione", prenotazione);
//	return "formPrenotazione"; 
//}			
//  
//@PostMapping 
//public String registraPrenotazione(@ModelAttribute("prenotazione") Prenotazione prenotazione,
//		@RequestParam(name = "sconto", required = false, defaultValue = "0.0") Double sconto,
//		@RequestParam("data") String data,
//		@RequestParam("ora") String ora,
//		@RequestParam("idAnagrafica") Integer idAnagrafica,
//		@RequestParam("idTatuaggio") Integer idTatuaggio) { 
//  
//	prenotazioneService.registraPrenotazione(prenotazione, sconto, data, ora, idAnagrafica, idTatuaggio); 
//	return "redirect:/home"; 
//}
//}
//	
