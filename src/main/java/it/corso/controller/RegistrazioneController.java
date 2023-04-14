package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import it.corso.model.Anagrafica;
import it.corso.model.Profilo;
import it.corso.service.AnagraficaService;
import it.corso.service.ProfiloService;

@Controller
@RequestMapping("/registrazione")
public class RegistrazioneController {

	@Autowired
	private AnagraficaService anagraficaService;
	
	@Autowired
	private ProfiloService profiloService;
	
	@GetMapping
	public String getPage(
			Model model,
			@RequestParam(name = "id", required = false) Integer id)
	{
		Anagrafica anagrafica = id == null ? new Anagrafica() : anagraficaService.getAnagraficaByID(id);
		model.addAttribute("anagrafica", anagrafica);
		Profilo profilo = id == null ? new Profilo() : profiloService.getProfiloById(id);
		model.addAttribute("profilo", profilo);
		return "registrazione";
	}
	
	@PostMapping
	public String registraAnagrafica(@ModelAttribute("anagrafica") Anagrafica anagrafica,
			@ModelAttribute("profilo") Profilo profilo)
	{
		anagraficaService.registraAnagrafica(anagrafica);
		profiloService.registraProfilo(profilo);
		return "redirect:/login";
	}
	
}
