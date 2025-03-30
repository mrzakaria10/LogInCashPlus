<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
    
    
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>LogInCashPlus - Sign In</title>
</head>
<body>
	<form action="login" method="post">
	<input type="email" name="email" placeholder="zakariajhd@gmail.com" required><br>
	<input type="password" name="password" placeholder="PassWord" required><br>
	<button type="submit">Login</button>
	</form>
	<p style="color: red;">${errorMessage}</p>
</body>
</html>