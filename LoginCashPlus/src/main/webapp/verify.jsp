<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Verify Email - LoginCashPlus</title>
</head>
<body>

    <h2>Verify Your Email</h2>

    <form action="VerifyServlet" method="post">
    <label>Email:</label>
        <input type="email" name="email" required>
        
        <label for="verificationCode">Enter the verification code:</label>
        <input type="text" name="verificationCode" required><br>

        <button type="submit">Verify</button>
    </form>

    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

</body>
</html>
