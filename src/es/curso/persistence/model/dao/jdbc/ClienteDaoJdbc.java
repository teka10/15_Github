package es.curso.persistence.model.dao.jdbc;

import java.util.ArrayList;

import es.curso.model.entity.Cliente;
import es.curso.persistence.model.dao.ClienteDao;

public class ClienteDaoJdbc implements ClienteDao{
	private ArrayList<Cliente> clientes;
	
	@Override
	public void create(Cliente cliente) {
		// instrucc de alta en BBDD
		// 1. concetar BBDD
		// 2. preparar las sentencias para añadir
		// 3. ejecutar las sentencias sql
		// 4. cerrar conexión
		
	}

	public ClienteDaoJdbc() {
		super();
		clientes = new ArrayList<Cliente>();
	}

	@Override
	public ArrayList<Cliente> findAll() {
		
		clientes.add(new Cliente(1, "Pepe", "Pérez", "1256j"));
		clientes.add(new Cliente(2, "Aaaaa", "Iiiii", "125df6j"));
		clientes.add(new Cliente(3, "Bbbbb", "Jjjjjj", "122456j"));
		clientes.add(new Cliente(4, "Ccccc", "Kkkk", "1233556j"));
		clientes.add(new Cliente(5, "Ddddd", "Lllll", "126756j"));
		clientes.add(new Cliente(6, "Eeeee", "Mmmm", "125896j"));
		clientes.add(new Cliente(7, "Ffffff", "Nnnn", "125696j"));
		clientes.add(new Cliente(8, "Ggggg", "Ooooo", "76"));
		clientes.add(new Cliente(9, "Hhhhh", "Pppp", "46890"));
		
		return clientes;
	}

	
}
