<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../Templates/header.jsp" %>
    <!-- Esto podría haber sido html en vez de jsp -->
    <!-- esto es una instrucc java para recuperar la ruta del proyecto
        <form action="${pageContext.request.contextPath}/Tienda/buscarPorNombre" method="get">
    -->
       <form action="../Tienda/buscarPorNombre" method="post">
        <label>Nombre</label>
        <input type="text" name="nombre" id="nombre">
	    <input type="submit" name="enviar" value="Enviar">
	    <input type="reset" name="reiniciar" value="Reiniciar">
    </form> 
<%@ include file="../Templates/footer.jsp" %>