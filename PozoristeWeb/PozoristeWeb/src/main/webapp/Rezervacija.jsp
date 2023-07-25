<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rezervacija</title>
</head>
<body>
	Rezervacija karte za predstavu ${izvodjenje.predstava.naziv }, na sceni ${izvodjenje.scena.naziv}, za datum ${izvodjenje.datum}:<br/>
	<form:form action="/Pozoriste/predstave/sacuvajRezervaciju" method="post" modelAttribute="karta">
		<table>
			<tr><td>Mesto:</td><td>
				<form:select path="mesto">
					<c:forEach items="${mesta}" var="m">
						<form:option value="${m.idMesto}">${m.broj}, ${m.red}, ${m.sekcija}</form:option>
					</c:forEach>
				</form:select>
			</td></tr>
			<tr><td>ID posetioca:</td><td><form:input path="posetilac"/></td></tr>
			<tr><td><input type="submit" value="Izaberi"></td></tr>
		</table>
	</form:form>

	<c:if test="${uspesnaRezervacija}">
		Uspesna rezervacija. Broj karte je ${karta.idKarta}
	</c:if>
</body>
</html>