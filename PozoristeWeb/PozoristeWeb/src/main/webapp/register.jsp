<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<sf:form modelAttribute="user"  action="register"  method="post">
  <table>
  	<tr>
  	   <td>Ime:</td><td><sf:input  path="name"/>
  	   </td>
  	</tr>
  		<tr>
  	   <td>Korisnicko ime:</td><td><sf:input  path="username"/>
  	 </td>
  	</tr>
  	<tr>
  	   <td>Sifra:</td><td><sf:password  path="password"/></td>
  	</tr>
  	<tr>
  	  <td>
  	   Uloga
  	  </td>
  	  <td>
  	  <sf:select path="roles" items="${roles}" itemValue="idRole" itemLabel="name"/>
	   </td>
	</tr>
  	<tr><td/><td><input type="submit" value="Sacuvaj"></tr>
  	</table>
</sf:form>
</body>
</html>