package es.curso.controllers;

import es.curso.model.entity.Usuario;

public interface LoginController {
	public abstract Usuario login(String userName, String password);
}
