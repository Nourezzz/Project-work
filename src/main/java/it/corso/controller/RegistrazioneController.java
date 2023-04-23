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
import it.corso.service.AnagraficaService;


@Controller
@RequestMapping("/registrazione")
public class RegistrazioneController {

	@Autowired
	private AnagraficaService anagraficaService;
	
	@GetMapping
	public String getPage(
			Model model,
			@RequestParam(name = "id", required = false) Integer id)
	{
		Anagrafica anagrafica = id == null ? new Anagrafica() : anagraficaService.getAnagraficaById(id);
		model.addAttribute("anagrafica", anagrafica);
		return "registrazione";
	}
	
	@PostMapping
	public String registraAnagrafica(@ModelAttribute("anagrafica") Anagrafica anagrafica
			)
	{
		anagraficaService.registraAnagrafica(anagrafica);
		return "redirect:/utente_login";
	}
	
}
