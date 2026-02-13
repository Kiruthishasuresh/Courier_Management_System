<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="MainServlet" method="post">

<fieldset>
<legend>ViewCourier</legend>
Sender Name:
<input type="text" name="sname"/>
<br>
Ship Date:
<input type="date" name="shipdate" placeholder="yyyyMMdd"/>
<input type="hidden" name="operation" value="viewRecord">
<input type="submit" value="Submit">
</fieldset>
</form>
</body>
</html>