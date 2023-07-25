<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Izcodjenja na sceni</title>
</head>
<body>
<form action="/Pozoriste/predstave/getIzvodjenjaZaScenu" method="get">
		Scena:<select name="idScena">
			<c:forEach items="${scene}" var="s">
				<option value="${s.idScena} "> ${s.naziv}</option>
			</c:forEach>
		</select>
		<input type="submit" value="Prikaz">
	</form>
	<c:if test="${! empty izvodjenja }">
		<table border="1">
			<tr><th>Datum</th><th>Naziv</th><th>Trajanje</th><th>Uloge</th></tr>
			<c:forEach items="${izvodjenja}" var="i">
				<tr>
					<td>${i.datum}</td>
					<td>${i.predstava.naziv }</td>
					<td>${i.predstava.trajanje }</td>
					<td><a href="/Pozoriste/predstave/getUlogeZaIzvodjenje?idIzvodjenja=${i.idIzvodjenje}">Izvestaj sa ulogama</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>