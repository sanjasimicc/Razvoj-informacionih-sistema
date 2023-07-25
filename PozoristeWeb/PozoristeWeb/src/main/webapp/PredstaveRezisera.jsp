<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Predstave rezisera</title>
</head>
<body>
	<form action="/Pozoriste/predstave/getPredstaveRezisera" method="get">
		Reziser:<select name="idReziser">
			<c:forEach items="${reziseri}" var="r">
				<option value="${r.idReziser} "> ${r.ime} ${r.prezime}</option>
			</c:forEach>
		</select>
		<input type="submit" value="Prikaz">
	</form>
	<c:if test="${! empty predstave }">
	<form action="/Pozoriste/predstave/generisiIzvestaj" method="get">
		<table border="1">
			<tr><th>Naziv</th><th>Opis</th><th>Trajanje</th></tr>
			<c:forEach items="${predstave }" var="p">
				<tr>
					<td>${p.naziv }</td>
					<td>${p.trajanje }</td>
					<td>${p.opis }</td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="Generisi izvestaj">
		</form>
	</c:if>
	
</body>
</html>