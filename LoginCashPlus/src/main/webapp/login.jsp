<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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