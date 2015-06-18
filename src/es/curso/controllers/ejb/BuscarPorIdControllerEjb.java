package es.curso.controllers.ejb;

import es.curso.controllers.BuscarPorIdController;
import es.curso.model.entity.Cliente;
import es.curso.persistence.model.dao.ClienteDao;
import es.curso.persistence.model.dao.jdbc.ClienteDaoJdbc;

public class BuscarPorIdControllerEjb implements BuscarPorIdController{

	@Override
	public Cliente buscarPorId(Integer id) {
		ClienteDao clienteDao = new ClienteDaoJdbc();
		Cliente cliente = clienteDao.searchById(id);
		return cliente;
	}

}
