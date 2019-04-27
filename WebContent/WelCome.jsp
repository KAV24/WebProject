<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<form method="post" action="LoginController">
<body>
Keep the page ideal for 30 seconds
	<br> And try reloading the page
	<br> you will be redirected to LOGIN page automatically
	<br>
    <%
		if (session == null) {
			response.sendRedirect("Login.jsp");
			
			} else {
				if (session.getAttribute("username") != null) {
					String name = (String) session.getAttribute("username");
					out.print("Hello, " + name + "  Welcome to ur Profile");
			}
		}
	
	%>
<input type="submit" value="Logout" ><br />
	</br>
	</br>
	</form>
</body>
</html>