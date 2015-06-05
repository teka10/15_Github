<%@page import="es.curso.model.entity.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalDateTime"%>
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
    <a href="/15_Github/index.html">Inicio</a> 
    <!--  tabla html dimánicamente usando java -->
    <h1><%=request.getAttribute("titulo") %></h1>
    <span><%=LocalDateTime.now() %></span>
    <span> Valor enviado desde el servlet Tienda IVA</span>
    <span><%=request.getAttribute("iva")%></span>
    <table>
        <tr>
            <th>id</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>DNI</th>
        </tr>
        <% ArrayList<Cliente> clientes =  (ArrayList<Cliente>)request.getAttribute("clientes");%>
        
        <%for(Cliente c:clientes){%>
        	<tr>
				<td><%=c.getId() %></td>
                <td><%=c.getNombres() %></td>
				<td><%=c.getApellidos() %></td>
				<td><%=c.getDni() %></td>
        	</tr>
        <%}%>
        

        
        <tr>
        </tr>
    
    
    </table>
</body>
</html>