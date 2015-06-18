<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../Templates/header.jsp" %>
    <form action="../Tienda/modificar2" method="post">
        <label>Id</label>
        <input type="text" name="id" id="id">
        <input type="submit" name="enviar" value="Enviar">
        <input type="reset" name="reiniciar" value="Reiniciar">
    </form> 
<%@ include file="../Templates/footer.jsp" %>