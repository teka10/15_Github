package es.curso.controllers.ejb;

import es.curso.controllers.DarAltaClienteController;
import es.curso.model.entity.Cliente;
import es.curso.persistence.model.dao.ClienteDao;
import es.curso.persistence.model.dao.jdbc.ClienteDaoJdbc;

public class DarAltaClienteControllerEjb implements DarAltaClienteController{
	private ClienteDao clienteDao;
	@Override
	public void agregar(Cliente cliente) {
		// lógica del negocio para agregar un cliente
		// 1. verificar datos
		// 2. Añadir cliente --> llamar a la capa DAO para q haga el alta
		// 3. Enviar email informativo al comercial
		// 4. Enviar email al cliente
		clienteDao = new ClienteDaoJdbc();
		clienteDao.create(cliente);
		
	}

}
