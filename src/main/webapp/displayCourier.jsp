<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="com.wipro.courier.bean.CourierBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Courier Details:
<% CourierBean cb=(CourierBean)request.getAttribute("view_record");
String message=(String)request.getAttribute("message");
%>
<% if(cb==null){%>

<h3><%= message %></h3>
<%
    } else {
%>
<h3>CourierId:<%= cb.getCourierId() %></h3>
<h3>SenderName:<%= cb.getSenderName() %></h3>
<h3>ReceiverName:<%= cb.getReceiverName() %></h3>
<h3>CourierItem:<%= cb.getCourierItem() %></h3>
<h3>ShipDate:<%= cb.getShipDate() %></h3>
<h3>DeliveryDate:<%= cb.getDeliveryDate() %></h3>
<h3>Status:<%= cb.getStatus() %></h3>
<h3>Remarks:<%= cb.getRemarks() %></h3>
<%
}
%>
</body>
</html>