package it.corso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import it.corso.model.Admin;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pagina_riservata")
public class ReservedController {

    @GetMapping
    public String getPage(HttpSession session, Model model)
    {
        //se l'utente non è loggato lo rimando a login
        if(session.getAttribute("admin") == null)
            return "redirect:/admin_login";
        //altrimenti gli faccio vedere l'area riservata
        Admin utente = (Admin) session.getAttribute("admin");
        model.addAttribute("admin", utente);
        return "reserved";
    }

}