package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.corso.model.Anagrafica;
import it.corso.model.Categoria;
import it.corso.model.Tatuaggio;
import it.corso.service.CategoriaService;
import it.corso.service.TatuaggioService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/galleria")
public class GalleriaController {
	
	@Autowired
	private TatuaggioService tatuaggioService;
	
	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public String getPage(HttpSession session, Model model) {
		
        Anagrafica profilo = (Anagrafica) session.getAttribute("profilo");
        model.addAttribute("profilo", profilo);
		List<Tatuaggio> tatuaggi = tatuaggioService.getTatuaggio();
		Tatuaggio tatuaggio = new Tatuaggio();
		model.addAttribute("tatuaggi", tatuaggi);
		model.addAttribute("tatuaggio", tatuaggio);
		List<Categoria> categorie = categoriaService.getCategoria();
		Categoria categoria = new Categoria();
		model.addAttribute("categorie", categorie);
		model.addAttribute("categoria", categoria);
		if(session.getAttribute("profilo") == null)
            return "galleria";
		return "galleria";
	}
}
