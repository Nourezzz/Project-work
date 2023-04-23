package it.corso.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity // trasforma la classe cliente nell'Entity cliente , permettendo di essere mappata nella tabella 
@Table(name = "prenotazioni") // collega l'omonima tabella nel Db a questa classe 
public class Prenotazione 
{
	//attributi di istanza della classe 
	@Id// Questo attributo individua la chiave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Ci adeguiamo al modo del database di gestione della chiave primaria della tabella clienti
	private int id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "data")
	private LocalDate data;
	
	@DateTimeFormat(pattern = "HH:mm")
	@Column(name = "time")
	private LocalTime ora;
	
	@ManyToOne(cascade = CascadeType.REFRESH) //refresh mi sta ad indicare che le modifiche che faccio su una determinata tabella hanno effetto solo su quella
	@JoinColumn(name = "id_anagrafica", referencedColumnName = "id")// Con la joinColumn, vado a relazionare la foreign key della classe dove ci troviamo (ordini)  con la referenceColumnname
	//della tabella relazionata, ovvero in questo caso l'"id" della tabella "clienti"
	private Anagrafica anagrafica;// deve avere lo stesso nome dell'attributo nella classe proprietario (Cliente classe)
	
	//getter e setter 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Anagrafica getAnagrafica() {
		return anagrafica;
	}
	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public LocalTime getOra() {
		return ora;
	}
	public void setOra(LocalTime ora) {
		this.ora = ora;
	}
	
}
