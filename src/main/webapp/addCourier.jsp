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
<legend>AddCourier</legend>
Sender Name:
<input type="text" name="sname"/>
<br>
Receiver Name:
<input type="text" name="rname"/>
<br>
Courier Item:
<input type="text" name="citem"/>
<br>
Ship Date:
<input type="date" name="shipdate" placeholder="yyyyMMdd"/>
<br>
Delivery Date:
<input type="date" name="deliverydate" placeholder="yyyyMMdd"/>
<br>
Status:
Active <input type="radio" name="status" value="Active"/>
Inactive <input type="radio" name="status" value="Inactive"/>
<br>
Remarks:
<input type="text" name="remarks"/>
<input type="hidden" name="operation" value="newRecord">
<input type="submit" value="Submit">
</fieldset>
</form>

</body>
</html>