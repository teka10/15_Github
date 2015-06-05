package es.curso.controllers.ejb;

import java.util.ArrayList;

import es.curso.controllers.BuscarPorNombresController;
import es.curso.model.entity.Cliente;
import es.curso.persistence.model.dao.ClienteDao;
import es.curso.persistence.model.dao.jdbc.ClienteDaoJdbc;

public class BuscarPorNombreControllerEjb implements BuscarPorNombresController{

	@Override
	public ArrayList<Cliente> buscarPorNombre(String cadenaNombre) {
		ClienteDao clienteDao = new ClienteDaoJdbc();
		ArrayList<Cliente> clientes = clienteDao.searchByName(cadenaNombre);		
		return clientes;
		
	}
}
