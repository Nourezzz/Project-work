package it.corso.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




@Entity // trasforma la classe cliente nell'Entity cliente , permettendo di essere mappata nella tabella 
@Table(name = "profili") // collega l'omonima tabella nel Db a questa classe 
public class Profilo 
{
	//attributi di istanza della classe 
	@Id// Questo attributo individua la chiave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Ci adeguiamo al modo del database di gestione della chiave primaria della tabella clienti
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	

	//getter e setter 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
