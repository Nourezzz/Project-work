package it.corso.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import it.corso.model.Admin;
import it.corso.model.Anagrafica;
import it.corso.model.Categoria;
import it.corso.model.Prenotazione;
import it.corso.model.Tatuaggio;
import it.corso.service.AnagraficaService;
import it.corso.service.CategoriaService;
import it.corso.service.PrenotazioneService;
import it.corso.service.TatuaggioService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pagina_riservata")
public class ReservedController {
	
	@Autowired
	private AnagraficaService anagraficaService;
	
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private TatuaggioService tatuaggioService;
	
	private Categoria categoria;
	
	private Tatuaggio tatuaggio;
	
	private Prenotazione prenotazione;

    @GetMapping
    public String getPage(
    		@RequestParam(name = "id", required = false) Integer id,
    		@RequestParam(name = "idT", required = false) Integer idT,
    		HttpSession session,
    		@RequestParam(name = "le",required = false) String logError,
    		@RequestParam(name = "let",required = false) String logErrorT,
    		@RequestParam(name = "lec",required = false) String logErrorC,
    		Model model
    		)
    {
        //se l'utente non è loggato lo rimando a login
        if(session.getAttribute("admin") == null)
            return "redirect:/admin_login";
        
        //altrimenti gli faccio vedere l'area riservata
        Admin utente = (Admin) session.getAttribute("admin");
        model.addAttribute("admin", utente);
        List<Anagrafica> clienti = anagraficaService.getAnagrafica();
		model.addAttribute("clienti", clienti);
        List<Prenotazione> prenotazioni = prenotazioneService.getPrenotazione();
        List<Tatuaggio> tatuaggi = tatuaggioService.getTatuaggio();
        prenotazione = id == null ? new Prenotazione() : prenotazioneService.getPrenotazioneById(id);
        tatuaggio = idT == null ? new Tatuaggio() : tatuaggioService.getTatuaggioById(idT);        
        List<Categoria> categorie = categoriaService.getCategoria();
        model.addAttribute("categoria", categoria);
        model.addAttribute("categorie", categorie);
        model.addAttribute("tatuaggio", tatuaggio);
        model.addAttribute("tatuaggi", tatuaggi);
        model.addAttribute("prenotazione", prenotazione);
		model.addAttribute("prenotazioni", prenotazioni);
		model.addAttribute("logError", logError != null);
		model.addAttribute("logErrorT", logErrorT != null);
		model.addAttribute("logErrorC", logErrorC != null);
        return "reserved";
    }
    
    @GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate(); // remove all session attributes
	    return "redirect:/homepage";
	}
	
	@GetMapping("/cancella_anagrafica")
	public String cancellaAnagrafica(@RequestParam("id") int id)
	{
		Anagrafica profilo = anagraficaService.getAnagraficaById(id);
		anagraficaService.cancellaAnagrafica(profilo);
		return "redirect:/pagina_riservata";
	}
	
	@GetMapping("/cancella_prenotazione")
	public String cancellaPrenotazione(@RequestParam("id") int id)
	{
		Prenotazione prenotazione = prenotazioneService.getPrenotazioneById(id);
		prenotazioneService.cancellaPrenotazione(prenotazione);
		return "redirect:/pagina_riservata";
	}
	
	@PostMapping("/registra_prenotazione")
    public String registraPrenotazione(
    		@RequestParam(name = "data",required=false) LocalDate data,
    		@RequestParam(name = "ora",required=false) LocalTime ora
    		) {
		if(prenotazione.getAnagrafica() != null) {
			
			
		prenotazione.setData(data);
		prenotazione.setOra(ora);
		
		prenotazioneService.registraPrenotazione(prenotazione, prenotazione.getAnagrafica().getId());
    
    return "redirect:/pagina_riservata";
		}
		
		return"redirect:/pagina_riservata?le";
	}
	
	@PostMapping("/registra_tatuaggio")
    public String registraTatuaggio(
    		@RequestParam(name = "descrizione") String descrizione,
    		@RequestParam(name = "prezzo") Double prezzo,
    		@RequestParam(name = "immagine", required = false) MultipartFile immagine,
    		@RequestParam(name = "id", required = false) Integer id
    		) {
			tatuaggio.setDescrizione(descrizione);
			tatuaggio.setPrezzo(prezzo);
			if(immagine != null && !immagine.isEmpty() && !(immagine.getSize() > 1048576))
			try
			{
				String tipo = immagine.getContentType();
				tatuaggio.setImmagine("data:" + tipo + ";base64," + Base64.getEncoder().encodeToString(immagine.getBytes()));
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			else if (immagine == null) {
					String tipo = tatuaggio.getImmagine();
					tatuaggio.setImmagine(tipo);
				
			} 
			else return "redirect:/pagina_riservata?let";
			if(id == null)
				return "redirect:/pagina_riservata?lec";
			
			tatuaggio.setCategoria(categoriaService.getCategoriaById(id));
		
		tatuaggioService.registraTatuaggio(tatuaggio);
    
    return "redirect:/pagina_riservata";

		
		
	}
	
	@GetMapping("/cancella_tatuaggio")
	public String cancellaTatuaggio(@RequestParam("idT") int idT)
	{
		Tatuaggio tatuaggio = tatuaggioService.getTatuaggioById(idT);
		tatuaggioService.cancellaTatuaggio(tatuaggio);
		return "redirect:/pagina_riservata";
	}

}
