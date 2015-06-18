<%@page import="es.curso.model.entity.Cliente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../Templates/header.jsp" %>
    <a href="/15_Github/index.html">Inicio</a> 
    <h1><%=request.getAttribute("titulo") %></h1>
    
    <form action="../Tienda/modificar3" method="post">
        <%Cliente cliente = (Cliente)request.getAttribute("cliente"); %>
        <label>Id del cliente a modificar</label>
        <input type="hidden" name="id" id="id" value=<%= cliente.getId() %> >
        <label>Nombre</label>
        <input type="text" name="nombres" id="nombres" value=<%= cliente.getNombres() %> >
        <label>Apellido</label>
        <input type="text" name="apellidos" id="apellidos" value=<%= cliente.getApellidos() %>>
        <label>Dni</label>
        <input type="text" name="dni" id="dni" value=<%= cliente.getDni() %>>
        
        <input type="submit" name="enviar" value="Enviar">
    
    </form>
<%@ include file="../Templates/footer.jsp" %>