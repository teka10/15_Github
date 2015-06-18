package es.curso.controllers.ejb;

import es.curso.controllers.LoginController;
import es.curso.model.entity.Usuario;
import es.curso.persistence.model.dao.UsuarioDao;
import es.curso.persistence.model.dao.jdbc.UsuarioDaoJdbc;


public class LoginControllerEjb implements LoginController{

	@Override
	public Usuario login(String userName, String password) {
		UsuarioDao usuarioDao = new UsuarioDaoJdbc();
		Usuario usuario = usuarioDao.searchForUserNamePasswordlogin(userName, password);
		return usuario;
	}

}
