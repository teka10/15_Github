<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilos.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tienda</title>
</head>
<body>
    <%
      response.setHeader("Cache-control","no-cache");//fuerza a la cache a 
      response.setHeader("Cache-control","no-store"); //dice a la cache q no almacene info
      response.setDateHeader("Expires",0);
      response.setHeader("pragma","no-cache"); //compatibilidad http 1.0
    %>
    <% HttpSession miSesion = request.getSession(); %>
	    
    <nav>
    <% if (miSesion.getAttribute("userName")!=null){%>
	    <a href="${pageContext.request.contextPath}/Tienda/altaCliente">Alta cliente</a>
	    <a href="${pageContext.request.contextPath}/Tienda/listarTodos">Listar Todos</a>
	    <a href="${pageContext.request.contextPath}/Tienda/buscarPorNombre">Buscar por nombre</a>
	    <a href="${pageContext.request.contextPath}/Tienda/borrar">Borrar cliente</a>
	    <a href="${pageContext.request.contextPath}/Tienda/modificar1">Modificar datos de cliente</a>
	    <a href="${pageContext.request.contextPath}/Tienda/logout">Logout</a>
	    <p>Usuario: <%=miSesion.getAttribute("userName") %></p>
	    <p>Bienvenido: <%=miSesion.getAttribute("nombreCompleto") %></p>
	    <p>Máximo período de inactividad: <%=miSesion.getMaxInactiveInterval() %>segundos</p>
	    <p></p>
	    <p></p>
	    <p></p>
	    <p></p>
	    
	    
	    
	<%}else{%>
	    <a href="${pageContext.request.contextPath}/Tienda/login">Login</a>
	<%} %>
    </nav>
