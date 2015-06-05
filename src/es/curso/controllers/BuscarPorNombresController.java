package es.curso.controllers;

import java.util.ArrayList;

import es.curso.model.entity.Cliente;

public interface BuscarPorNombresController {
	public abstract ArrayList<Cliente> buscarPorNombre(String cadenaNombre);
	
	
}
