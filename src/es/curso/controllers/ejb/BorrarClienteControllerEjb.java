package es.curso.controllers.ejb;

import es.curso.controllers.BorrarClienteController;
import es.curso.persistence.model.dao.ClienteDao;
import es.curso.persistence.model.dao.jdbc.ClienteDaoJdbc;

public class BorrarClienteControllerEjb implements BorrarClienteController{

	@Override
	public boolean borrarPorId(Integer id) {
		ClienteDao daoCliente = new ClienteDaoJdbc();
		return daoCliente.delete(id);
		
	}

}
