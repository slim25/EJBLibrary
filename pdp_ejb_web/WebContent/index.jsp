<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Library EJB</title>
</head>
<body>
		<form action="LibraryServlet" method="post">			
			 Please enter a book name <br>
			<input type="text" name="bookName" size="20px">
			<input type="hidden" name="action" value="addBook"  >
			<input type="submit" value="submit">						
		</form>
		
		<form action="LibraryServlet" method="get">			
			<input type="hidden" name="action" value="getBooks"  >
			<input type="submit" value="Get All books">						
		</form>
		<form action="LibraryServlet" method="get">			
			<input type="hidden" name="action" value="printLogs"  >
			<input type="submit" value="Print all logs">						
		</form>
			
</body>
</html>