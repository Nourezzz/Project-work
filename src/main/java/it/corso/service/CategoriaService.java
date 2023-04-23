package it.corso.service;


import java.util.List;
import it.corso.model.Categoria;

public interface CategoriaService {

	Categoria getCategoriaById(int id);
	List<Categoria> getCategoria();
}
