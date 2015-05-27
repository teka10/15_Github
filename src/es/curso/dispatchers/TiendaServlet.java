package es.curso.dispatchers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.curso.controllers.ejb.DarAltaClienteControllerEjb;
import es.curso.model.entity.Cliente;

/**
 * Servlet implementation class TiendaServlet
 */
@WebServlet("/Tienda/*")
public class TiendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TiendaServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// por get llegan listarTodos y buscarPorNombre
		String action=request.getPathInfo().substring(1);
		request.setCharacterEncoding("UTF-8");
		switch (action) {
		case "listarTodos":
			// llama al controlador q hace la consulta
			// con los clientes obtenidos redirige a otra página listarTodos.jsp
			break;
		case "buscarPorNombre":
			//llama al controlador q hace la consulta
			//con el cliente obtenido, redirige 
			break;
		default:
			break;
		}
		// después de recoger la info, se redirige a una vista para mostrarlo .jsp
		RequestDispatcher rd;
		//aqui hay q enviar a la vista el resultado de la consulta  a la BBDD
		rd = request.getRequestDispatcher("/jsp/listarTodos.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// por post llega altaCliente
		String action=request.getPathInfo().substring(1);
		request.setCharacterEncoding("UTF-8");
		switch (action) {
		case "altaCliente":
			// crea un cliente con los datos del formulario
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String dni = request.getParameter("dni");
			
			Cliente cliente = new Cliente(0, nombre, apellido, dni);
			
			// llama al controlador q de el alta
			DarAltaClienteControllerEjb controlador= new DarAltaClienteControllerEjb() ;
			controlador.agregar(cliente);
			break;
		}
	}

}
