package es.curso.persistence.model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.curso.model.entity.Usuario;
import es.curso.persistence.model.dao.UsuarioDao;

public class UsuarioDaoJdbc implements UsuarioDao{
	private Connection cx;
	
	
	public UsuarioDaoJdbc() {
		super();
	}

	@Override
	public Usuario searchForUserNamePasswordlogin(String userName, String password) {
		Usuario usuario = null;
		
		try {
			abrirConexion();
			PreparedStatement ps = 
				cx.prepareStatement("SELECT * FROM USUARIO WHERE USERNAME = ? AND "
						+ "PASSWORD = ?");
				ps.setString(1, userName);
				ps.setString(2, password);
				ResultSet consulta=ps.executeQuery();
				if(consulta.next()){
					usuario=new Usuario();
					usuario.setId(consulta.getInt("id"));
					usuario.setNombres(consulta.getString("nombres"));
					usuario.setApellidos(consulta.getString("apellidos"));
					usuario.setUserName(consulta.getString("userName"));
					usuario.setPassword(consulta.getString("password"));
				}
				
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			cerrarConexion();
		}
		
		return usuario;
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
