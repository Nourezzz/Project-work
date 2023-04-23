package it.corso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.corso.model.Anagrafica;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/homepage")
public class IndexController {

	@GetMapping
	public String getPage(HttpSession session, Model model) {
		if(session.getAttribute("profilo") == null)
            return "index";
        Anagrafica profilo = (Anagrafica) session.getAttribute("profilo");
        model.addAttribute("profilo", profilo);
        return "index";
	}
	
}
