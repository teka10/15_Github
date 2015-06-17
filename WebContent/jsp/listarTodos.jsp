<%@page import="es.curso.model.entity.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../Templates/header.jsp" %>
<script type="text/javascript">
  function enviar(boton){
	  // asigna al formulario el formulario que está en la lista de formularios en la 
	  //posición indicada por el nombre del botón pulsado, que lleva un secuencial
	  var formulario=document.forms.item(boton.name)
	 
	  // alteramos el actión según el botón pulsado
	  switch (boton.value){
	  case "Modificar":
		  formulario.action="../Tienda/modificar3";
		  formulario.submit();
		  break;
	  case "Eliminar":
		  formulario.action="../Tienda/borrar";
          formulario.submit();
		  break;
		//el editar no funciona, hay q hacerlo como Manuel ha hecho modificar
	  case "Editar":

		  document.getElementById("nombre " + boton.name).disabled=false;
		  document.getElementById("apellido " + boton.name).disabled=false;
		  document.getElementById("dni " + boton.name).disabled=false;
		  break;
	  }
  }
</script>
    <a href="/15_Github/index.html">Inicio</a> 
    <!--  tabla html dimánicamente usando java -->
    <h1><%=request.getAttribute("titulo") %></h1>
    <span><%=LocalDateTime.now() %></span>

    <table>
        <tr>
            <th>id</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>DNI</th>
        </tr>
        <% ArrayList<Cliente> clientes =  (ArrayList<Cliente>)request.getAttribute("clientes");%>
        
        <% int n=1; %>
        <%for(Cliente c:clientes){%>
		<!-- 
		<form action="../Tienda/borrar" method="post">
		   	<tr>
				<td><input type="text" name="id" value="<%= c.getId() %>"></td>
                <td><input type="text" name="nombres" value="<%=c.getNombres() %>"></td>
				<td><input type="text" name="apellidos" value="<%=c.getApellidos() %>"></td>
				<td><input type="text" name="dni" value="<%=c.getDni() %>"></td>
			
   				<td><input type="submit" value="Eliminar" name="boton"/></td>
   				<td><input type="submit" formaction="../Tienda/modificar3" name="boton" value="Modificar"/></td>
        	</tr>
		</form>
		-->
		<!-- otra forma con java script. Con el script de arriba
		Le enviamos el boton pulsado y el script decide a q parte del servlet lo envía-->
		<form id="formulario" action="" method="post" onsubmit="return false;">
            <tr>
            <!-- asi funciona con los botones de listarTodos
                <td><input type="text" name="id <%=n %>" id="id <%=n %>" value="<%= c.getId() %>"></td>
                <td><input type="text" name="nombres" id="nombre <%=n %>" value="<%=c.getNombres() %>" ></td>
                <td><input type="text" name="apellidos" id="apellido <%=n %>" value="<%=c.getApellidos() %>" ></td>
                <td><input type="text" name="dni" id="dni <%=n %>" value="<%=c.getDni() %>" ></td>
             lo de abajo es para q funcione con formularios intermedios
             -->
                <td><input type="text" name="id <%=n %>" id="id" value="<%= c.getId() %>"></td>
                <td><input type="text" name="nombres" id="nombre" value="<%=c.getNombres() %>" ></td>
                <td><input type="text" name="apellidos" id="apellido" value="<%=c.getApellidos() %>" ></td>
                <td><input type="text" name="dni" id="dni" value="<%=c.getDni() %>" ></td>
                
                            
                <td><input type="submit" value="Eliminar" name="<%=n %>"
                onclick="enviar(this);"/></td>
                <td><input type="submit" value="Editar" name="<%=n %>" 
                onclick="enviar(this)"/></td>
                <td><input type="submit" value="Modificar" name="<%=n %>" 
                onclick="enviar(this)"/></td>
            </tr>
        
        </form>
        <%=n++ %>
        <%}%>
        
        <tr>
        </tr>
    </table>
<%@ include file="../Templates/footer.jsp" %>