package it.corso.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		if(session.getAttribute("admin") != null || session.getAttribute("profilo") != null) 
			session.invalidate();
		//se l'utente è loggato lo indirizzo ad area riservata
		else if(session.getAttribute("profilo") != null)
			return "redirect:/utente_reserved";
		//altrimenti gli faccio vedere il form
		model.addAttribute("logError", logError != null); //true oppure false
		return "login_utente";
	}
	
	@PostMapping
	public String gestioneLogin(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpSession session)
	{
		if(!anagraficaService.loginUtente(username, password, session))
			return "redirect:/utente_login?le";
		return "redirect:/utente_reserved";
	}
	
}
