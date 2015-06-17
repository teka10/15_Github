package es.curso.persistence.model.dao;

import java.util.ArrayList;

import es.curso.model.entity.Cliente;

public interface ClienteDao {
	public void create(Cliente cliente);
	
	public ArrayList<Cliente> findAll();
	public ArrayList<Cliente> searchByName(String name);
	public boolean delete(Integer id);
	public Cliente searchById(Integer id);
	public boolean modificarPorId(Cliente cliente);
}
