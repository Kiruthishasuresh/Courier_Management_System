<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="java.util.List,com.wipro.courier.bean.CourierBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
All Courier Details:
<% 
List<CourierBean>list=(List<CourierBean>)request.getAttribute("viewAllRecords");
String message=(String)request.getAttribute("message");
%>
<% if (list == null || list.isEmpty()) { %>
    <h3><%= message %></h3>
<% } else { %>
<%
for (CourierBean cb : list) {
%>
<table border="1">
    <tr>
        <th>Courier ID</th>
        <th>Sender Name</th>
        <th>Receiver Name</th>
        <th>Courier Item</th>
        <th>Ship Date</th>
        <th>Delivery Date</th>
        <th>Status</th>
        <th>Remarks</th>
    </tr>
    <tr>
        <td><%= cb.getCourierId() %></td>
        <td><%= cb.getSenderName() %></td>
        <td><%= cb.getReceiverName() %></td>
        <td><%= cb.getCourierItem() %></td>
        <td><%= cb.getShipDate() %></td>
        <td><%= cb.getDeliveryDate() %></td>
        <td><%= cb.getStatus() %></td>
        <td><%= cb.getRemarks() %></td>
    </tr>
</table>
<br>

<%
}
 %>
 <%
}
 %>
</body>
</html>