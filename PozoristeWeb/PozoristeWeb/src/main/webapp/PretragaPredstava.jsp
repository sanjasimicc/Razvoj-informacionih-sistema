<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pretrazi</title>
</head>
<body>
	<form action="/Pozoriste/predstave/pretraziPredstave">
		Naziv: <input type="text" name="naziv">
		<input type="submit" value="Pretrazi"><br>
	</form> <br>
	
	<c:if test="${!empty izvodjenja }">
		Prikaz izvodjenja za predstavu: ${predstava}
		<table border="1">
			<tr>
				<th>Datum izvodjenja</th>
				<th>Scena</th>
				<th>Rezervacija</th>
			</tr>
			<c:forEach items="${izvodjenja}" var="i">
				<tr>
					<td>${i.datum }</td>
					<td>${i.scena.naziv }</td>
					<td><a href="/Pozoriste/predstave/Rezervisanje?idIzvodjenja=${i.idIzvodjenje}">Rezervisi</a></td>
				</tr>
			</c:forEach>
		</table>
		
	</c:if>
	
</body>
</html>