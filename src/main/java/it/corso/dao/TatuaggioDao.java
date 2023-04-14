package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Tatuaggio;

public interface TatuaggioDao extends CrudRepository<Tatuaggio, Integer> {

}
