<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/estilos.css">
</head>
<body>
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
</body>
</html>