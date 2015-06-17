package es.curso.persistence.model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.curso.model.entity.Cliente;
import es.curso.persistence.model.dao.ClienteDao;

public class ClienteDaoJdbc implements ClienteDao{
	private Connection cx;
	
	public ClienteDaoJdbc() {
		super();
	}

	@Override
	public void create(Cliente cliente) {
		try {
			// instrucc de alta en BBDD
			// 1. conectar BBDD
			abrirConexion();
			
			// 2. preparar las sentencias para añadir
			PreparedStatement ps = 
				cx.prepareStatement("INSERT INTO CLIENTE VALUES(?,?,?,?)");
				//con esto rellenamos los interrogantes;
			ps.setInt(1, 0); // da igual q id ponemos pq es autonum
			ps.setString(2, cliente.getNombres());
			ps.setString(3, cliente.getApellidos());
			ps.setString(4, cliente.getDni());
		
			// 3. ejecutar las sentencias sql
			// devuelve el nº filas afectadas
			ps.executeUpdate();
			// queda pendiente hacer commit
			cx.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				cx.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally{
		// 4. cerrar conexión
		cerrarConexion();
		}
	}

	@Override
	public ArrayList<Cliente> findAll() {	
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			abrirConexion();
			PreparedStatement ps = cx.prepareStatement("SELECT * FROM CLIENTE");
	
			// el objeto resultSet recoge el resultado de la consulta
			ResultSet consulta = ps.executeQuery();
			//hay q recorrer el objeto resulSet con un whilen mirando si hay un siguiente
			//verifica si hay siguiente elemento y se mueve a él.
			//El resulSet empieza antes del primero. Con next, pasa al siguiente, que es 
			// el primero y trabaja con él
			while (consulta.next()) {
				Cliente c = new Cliente();
				// lo q está entre comillas el el nmbre del campo en la tabla
				c.setId(consulta.getInt("id"));
				c.setNombres(consulta.getString("nombres"));
				c.setApellidos(consulta.getString("apellidos"));
				c.setDni(consulta.getString("dni"));
				clientes.add(c);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			cerrarConexion();
		}
		return clientes;
	}
	
	@Override
	public ArrayList<Cliente> searchByName(String name) {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			abrirConexion();
			PreparedStatement ps = 
			  cx.prepareStatement("SELECT * FROM CLIENTE WHERE nombres LIKE ?");
			ps.setString(1, "%" + name + "%");
			ResultSet consulta = ps.executeQuery();
			
			while (consulta.next()) {
				Cliente c = new Cliente();
				c.setId(consulta.getInt("id"));
				c.setNombres(consulta.getString("nombres"));
				c.setApellidos(consulta.getString("apellidos"));
				c.setDni(consulta.getString("dni"));
				clientes.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			cerrarConexion();
		}
		return clientes;
	}

	
	@Override
	public boolean delete(Integer id) {
		boolean resultado=false;
		try {
			abrirConexion();
			PreparedStatement ps = 
			  cx.prepareStatement("DELETE FROM CLIENTE WHERE id = ?");
			ps.setInt(1,id);
			int filas= ps.executeUpdate();
			if (filas>0)
				resultado=true;
			cx.commit();
		} catch (SQLException e) {
			try {
				cx.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			cerrarConexion();
		}
	   return  resultado;
	}

	@Override
	public Cliente searchById(Integer id) {
		Cliente cliente = new Cliente();
		
		try {
			abrirConexion();
			PreparedStatement ps = 
			  cx.prepareStatement("SELECT * FROM CLIENTE WHERE id = ?");
			ps.setInt(1, id);
			ResultSet consulta = ps.executeQuery();
			consulta.next(); // así pasamos a la primera ocurrencia del resultset
			cliente.setId(consulta.getInt("id"));
			cliente.setNombres(consulta.getString("nombres"));
			cliente.setApellidos(consulta.getString("apellidos"));
			cliente.setDni(consulta.getString("dni"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			cerrarConexion();
		}
		return cliente;
	}
	
	@Override
	public boolean modificarPorId(Cliente cliente) {
		boolean resultado=false;
		try {
			abrirConexion();
			PreparedStatement ps = 
			  cx.prepareStatement("UPDATE CLIENTE SET nombres=?, apellidos=?, dni=?"
			  		+ "WHERE ID = ?");
			ps.setString(1, cliente.getNombres());
			ps.setString(2, cliente.getApellidos());
			ps.setString(3, cliente.getDni());
			ps.setInt(4, cliente.getId());
			int filas= ps.executeUpdate();
			if (filas>0)
				resultado=true;
			cx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				cx.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			cerrarConexion();
		}
		return resultado;
	}
	
	private void abrirConexion(){
		try {
			//determinar si tengo el driver o conector (de mysql)
			Class.forName("com.mysql.jdbc.Driver");
			//establecer conexion. Hay q darle localización de BBDD, usu y pass
			cx = DriverManager.getConnection("jdbc:mysql://localhost:3306/Tienda",
					"rootTienda", "rootTienda");
			//poner el autocommit en falso
			cx.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void cerrarConexion(){
		try {
			if (cx!=null)
				cx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}




	
	
}
