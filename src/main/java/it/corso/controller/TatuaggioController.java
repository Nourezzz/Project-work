package it.corso.controller;
	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestMapping;

import it.corso.model.Tatuaggio;
import it.corso.service.TatuaggioService;


	@Controller
	@RequestMapping("/tatuaggio")
	public class TatuaggioController {
	
		@Autowired
		private TatuaggioService tatuaggioService;
		
		@GetMapping
		public String getPage(Model model)
		{
			List<Tatuaggio> tatuaggi = tatuaggioService.getTatuaggio();
			Tatuaggio tatuaggio = new Tatuaggio();
			model.addAttribute("tatuaggi", tatuaggi);
			model.addAttribute("tatuaggio", tatuaggio);
			return "tatuaggi";
		}
		
		@PostMapping
		public String registraTatuaggio(@ModelAttribute("tatuaggio") Tatuaggio tatuaggio)
		{
			tatuaggioService.registraTatuaggio(tatuaggio);
			return "redirect:/homepage";
		}
		
//		@GetMapping("/cancellatatuaggio")
//		public String cancellaProdotto(@RequestParam("id") int id)
//		{
//			Tatuaggio tatuaggio = tatuaggioService.getTatuaggioById(id);
//			tatuaggioService.cancellaTatuaggio(tatuaggio);
//			return "redirect:/prodotti";
//		}

	}


