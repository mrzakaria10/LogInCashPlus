<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
    
    
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>LogInCashPlus - Sign In</title>
</head>
<body>
	<form action="<c:url value='login'/>" method="post">
	<label for="email">email :</label>
	<input type="email" name="email" placeholder="zakariajhd@gmail.com" required><br>
	
	<label for="password">password :</label>
	<input type="password" name="password" placeholder="admin000" required><br>
	
	<button type="submit">Login</button>
	</form>
	 <a href="<c:url value='login.jsp'/>">Voir la liste</a>
	
</body>
</html>