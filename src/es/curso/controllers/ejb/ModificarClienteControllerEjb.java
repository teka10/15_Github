package es.curso.controllers.ejb;

import es.curso.controllers.ModificarClienteController;
import es.curso.model.entity.Cliente;
import es.curso.persistence.model.dao.ClienteDao;
import es.curso.persistence.model.dao.jdbc.ClienteDaoJdbc;

public class ModificarClienteControllerEjb implements ModificarClienteController{

	@Override
	public boolean modificarPorId(Cliente cliente) {
		ClienteDao daoCliente = new ClienteDaoJdbc();
		
		return daoCliente.modificarPorId(cliente);
	}

}
