package es.curso.dispatchers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.curso.controllers.LoginController;
import es.curso.controllers.ejb.BorrarClienteControllerEjb;
import es.curso.controllers.ejb.BuscarPorIdControllerEjb;
import es.curso.controllers.ejb.BuscarPorNombreControllerEjb;
import es.curso.controllers.ejb.DarAltaClienteControllerEjb;
import es.curso.controllers.ejb.ListarTodosControllerEjb;
import es.curso.controllers.ejb.LoginControllerEjb;
import es.curso.controllers.ejb.ModificarClienteControllerEjb;
import es.curso.model.entity.Cliente;
import es.curso.model.entity.Usuario;

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
		String titulo="Sin titulo";
		RequestDispatcher rd;
		switch (action) {
		case "listarTodos":
			// llama al controlador q hace la consulta
			// con los clientes obtenidos redirige a otra página listarTodos.jsp
			ListarTodosControllerEjb todos =new ListarTodosControllerEjb();
			ArrayList<Cliente> clientes = todos.listarClientes();
			request.setAttribute("clientes", clientes);
			// después de recoger la info, se redirige a una vista para mostrarlo .jsp
			//aqui hay q enviar a la vista el resultado de la consulta  a la BBDD
			// decimos dónde va
			titulo="Listado general de clientes";
			// decimos qué enviamos (nombre del atributo y objeto)
			// para mandar varios datos, se ponen varias instrucc setAttribute
			
			// enviamos la respuesta al jsp, que lo tratará
			request.setAttribute("titulo", titulo);
			rd = request.getRequestDispatcher("/jsp/listarTodos.jsp");
			rd.forward(request, response);
			break;
		case "buscarPorNombre":
			//redirige hacia la vista del formulario para buscar
			rd=request.getRequestDispatcher("/jsp/buscarPorNombre.jsp");
			rd.forward(request, response);
			break;
		case "altaCliente": 
			//cuando altaCliente llega por doGet es pq viene desde el enlace del formulario
			//del index. Tiene q llamar al formulario del alta del cliente
			rd = request.getRequestDispatcher("/jsp/altaClienteView.jsp");
			rd.forward(request, response);
			break;
		case "borrar": 
			//cuando borrar llega por doGet es pq viene desde el enlace del formulario
			//del index. Tiene q llamar al formulario del borrardo
			rd = request.getRequestDispatcher("/jsp/borrar.jsp");
			rd.forward(request, response);
			break;
		case "modificar1": 
			//cuando modificar llega por doGet es pq viene desde el enlace del formulario
			//del index. Tiene q llamar al formulario de la modificacion
			rd = request.getRequestDispatcher("/jsp/buscarPorId.jsp");
			rd.forward(request, response);
			break;
		case "login":
			//viene del menu
			rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
			break;
		
		default: // en cualquier otro caso, ej: dando atrás con los botones de naveg
			
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// por post llega altaCliente
		String action=request.getPathInfo().substring(1);
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd;
		String titulo="Sin titulo";
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
			//cuando ha terminado, vuelve al index
			rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
			break;
		case "buscarPorNombre":
			//recuperar la cadena tecleada en el formulario
			String cadenaNombre=request.getParameter("nombre");
			//hay q llamar al controlador buscarPorNombre
			BuscarPorNombreControllerEjb controladorBusqueda = new BuscarPorNombreControllerEjb();
			ArrayList<Cliente> resultado = controladorBusqueda.buscarPorNombre(cadenaNombre);
			//y redirigir a la vista de consulta. Reutilizamos la vista listarTodos
			request.setAttribute("clientes", resultado);
			titulo="Búsqueda por " + cadenaNombre;
			
			request.setAttribute("titulo", titulo);
			rd = request.getRequestDispatcher("/jsp/listarTodos.jsp");
			rd.forward(request, response);
			break;
		case "borrar":
			Integer id = Integer.parseInt(request.getParameter("id"));

			BorrarClienteControllerEjb controladorBorrar = new BorrarClienteControllerEjb();
			boolean borrado =controladorBorrar.borrarPorId(id);
			response.sendRedirect("listarTodos");
			break;
		case "borrarModificar":
			String seleccion = request.getParameter("boton");
			if (seleccion=="Eliminar"){
				response.sendRedirect("borrar");
			}else{
				response.sendRedirect("modificar3");
			}break;
			
		case "borrar2"://otra forma
			//desde el boton de listar todos
			Enumeration<String> kk = request.getParameterNames();
			Integer id2 = Integer.parseInt(kk.nextElement());
			BorrarClienteControllerEjb controladorBorrar2 = new BorrarClienteControllerEjb();
			boolean borrado2 =controladorBorrar2.borrarPorId(id2);
			response.sendRedirect("listarTodos");
			//if (borrado2) {
			//	response.sendRedirect("listarTodos");
			//} else {
			//	rd = request.getRequestDispatcher("/jsp/borrar.jsp");
			//}

			//rd.forward(request, response);
			break;
		case "modificar2":
			Integer idM = Integer.parseInt(request.getParameter("id"));
			BuscarPorIdControllerEjb controladorBusquedaId =new BuscarPorIdControllerEjb();
			Cliente resultadoM = controladorBusquedaId.buscarPorId(idM);
			
			request.setAttribute("cliente", resultadoM);
			titulo="Modificar cliente";
			request.setAttribute("titulo", titulo);
			rd = request.getRequestDispatcher("/jsp/modificar.jsp");
			rd.forward(request, response);
			break;
		case "modificar3":
			ModificarClienteControllerEjb controladorModificar = new ModificarClienteControllerEjb();
			Cliente clienteM=new Cliente();
			clienteM.setNombres(request.getParameter("nombres"));
			clienteM.setId(Integer.parseInt(request.getParameter("id")));
			clienteM.setApellidos(request.getParameter("apellidos"));
			clienteM.setDni(request.getParameter("dni"));
			
			boolean modificado=controladorModificar.modificarPorId(clienteM);
			if (modificado) {
				response.sendRedirect("listarTodos");
			} else {
				response.sendRedirect("modificar1");
			}
			break;
		case "login":
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			LoginController loginController = new LoginControllerEjb();
			Usuario usuario = loginController.login(userName, password);
			if (usuario != null){
				//metemos los datos del usuario en la sesión
				//matamos la sesión previa 
				HttpSession session = request.getSession(false);
				//creamos una sesión nueva
				session = request.getSession(true);
				String nombreCompleto = usuario.getNombres() + " " + usuario.getApellidos();
				session.setAttribute("nombreCompleto", nombreCompleto);
				session.setAttribute("userName", usuario.getUserName());
				rd=request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
						
			}else{
				response.sendRedirect("login");
			}
			
			break;
		}
	}

}
