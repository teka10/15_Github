<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!--  Añadimos el header importando header.jsp -->
<%@ include file="Templates/header.jsp" %>
    <form action="${pageContext.request.contextPath}/Tienda/login" 
        method="post" enctype="text/html">
        <br>
        <h1>Login</h1>
        <label>Usuario</label>
        <input type="text" name="userName" value=""/>
        <label>Contraseña</label>
        <input type="password" name="password" value=""/>
        <input type="submit" name="login" value="Login"/>
    </form>
<%@ include file="Templates/footer.jsp" %>