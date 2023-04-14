package it.corso.model;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity // trasforma la classe cliente nell'Entity cliente , permettendo di essere mappata nella tabella 
@Table(name= "anagrafica") // collega l'omonima tabella nel Db a questa classe 
public class Anagrafica 
{
	//attributi di istanza
	@Id// Questo attributo individua la chiave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Ci adeguiamo al modo del database di gestione della chiave primaria della tabella clienti
	private int id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cognome")
	private String cognome;
	
	@Column(name = "data_di_nascita")
	private String data_di_nascita;
	
	@Column(name = "email")
	private String email;
	
	
	@OneToOne(cascade = CascadeType.ALL)// questo specifica la relazione che intercorre tra cliente e profilo, ovvero ad un profilo corrisponde solo un cliente
	@JoinColumn(name = "id_profilo", referencedColumnName = "id")// Con la joinColumn, vado a relazionare la foreign key della classe dove ci troviamo (clienti)  con la referenceColumnname
	//della tabella relazionata, ovvero in questo caso l'"id" della tabella profilo
	private Profilo profilo;
	
	@OneToMany// distingue il tipo di relazione uno a tanti
	(
			// da rivedere 
		mappedBy = "anagrafica", // Gestisce la relazione dal lato inverso 	
		cascade = CascadeType.ALL,
		fetch = FetchType.EAGER, // con eager tutti i dati vengono salvati subito nella collezione, mentre con lazy si rimanda. Fetch si usa quandoè presente una Lista(list,Arraylist)
		orphanRemoval = true // Significa che gli ordini rimasti orfani dal cliente, vengono cancellati 
		
		
	)
	private List<Prenotazione> prenotazioni = new ArrayList<>();
	
	//getter e setter 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Profilo getProfilo() {
		return profilo;
	}
	public void setProfilo(Profilo profilo) {
		this.profilo = profilo;
	}
	public String getData_di_nascita() {
		return data_di_nascita;
	}
	public void setData_di_nascita(String data_di_nascita) {
		this.data_di_nascita = data_di_nascita;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}
	public void setPrenotazioni(List<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}
	
	
}