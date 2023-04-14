package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Profilo;
import it.corso.service.AnagraficaService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/utente_login")
public class UtenteController {
	
	@Autowired
	private AnagraficaService anagraficaService;
	
	@GetMapping
	public String getPage(
			@RequestParam(name = "le",required = false) String logError,
			Model model,
			HttpSession session)
	{
		//se l'utente è loggato lo indirizzo ad area riservata
		if(session.getAttribute("utente") != null)
			return "redirect:/homepage";
		//altrimenti gli faccio vedere il form
		model.addAttribute("logError", logError != null); //true oppure false
		return "login_utente";
	}
	
	@PostMapping
	public String gestioneLogin(
			@RequestParam("profili") List<Profilo> profili,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpSession session)
	{
		if(!anagraficaService.loginUtente(profili, username, password, session))
			return "redirect:/utente_login?le";
		return "redirect:/pagina_riservata";
	}
}
