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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	private void abrirConexion(){
		try {
			//determinar si tengo el driver o conector (de mysql)
			Class.forName("com.mysql.jdbc.Driver");
			//establecer conexion. Hay q darle localización de BBDD, usu y pass
			cx = DriverManager.getConnection("jdbc:mysql://localhost:3306/Tienda",
					"rootTienda", "rootTienda");
			//poner el autocommit en falso
			//cx.setAutoCommit(false);
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
