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

//localhost:8051/login
@Controller
@RequestMapping("/admin_login")
public class AdminController {
	
	@Autowired
	private AnagraficaService anagraficaService;
	
	@GetMapping
	public String getPage(
			@RequestParam(name = "le",required = false) String logError,
			Model model,
			HttpSession session)
	{
		//se l'utente è loggato lo indirizzo ad area riservata
		if(session.getAttribute("admin") != null)
			return "redirect:/pagina_riservata";
		//altrimenti gli faccio vedere il form
		model.addAttribute("logError", logError != null); //true oppure false
		return "login_admin";
	}
	
	@PostMapping
	public String gestioneLogin(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpSession session)
	{
		if(!anagraficaService.loginAdmin(username, password, session))
			return "redirect:/admin_login?le";
		return "redirect:/pagina_riservata";
	}
	
}
