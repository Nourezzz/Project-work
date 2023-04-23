package it.corso.model;

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
@Table(name = "tatuaggi") // collega l'omonima tabella nel Db a questa classe 
public class Tatuaggio 
{
	@Id// Questo attributo individua la chiave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Ci adeguiamo al modo del database di gestione della chiave primaria della tabella clienti
	//attributi di istanza della classe 
	private int id;
	
	@Column(name = "descrizione")
	private String descrizione;
	
	@Column(name = "immagine")
	private String immagine;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_categoria", referencedColumnName = "id")
	
	private Categoria categoria;

	@Column(name = "prezzo")
	private double prezzo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getImmagine() {
		return immagine;
	}
	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
