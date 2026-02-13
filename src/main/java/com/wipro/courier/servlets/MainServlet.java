package com.wipro.courier.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.wipro.courier.bean.CourierBean;
import com.wipro.courier.service.Administrator;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operation = request.getParameter("operation");
		if("newRecord".equals(operation)) {
			String result=addRecord(request);
			if("FAIL".equals(result)||result.contains("INVALID")||"ALREADY EXISTS".equals(result)) {
				response.sendRedirect("error.html");
			}
			else {
				response.sendRedirect("success.html");
			}
		}
		else if("viewRecord".equals(operation)) {
			CourierBean cb=viewRecord(request);
			if(cb==null) {
				request.setAttribute("message","No matching records exists! Please try again!");
				RequestDispatcher rd=request.getRequestDispatcher("displayCourier.jsp");
				rd.forward(request, response);
			}
			else {
                request.setAttribute("view_record",cb);
				RequestDispatcher rd=request.getRequestDispatcher("displayCourier.jsp");
				rd.forward(request, response);
			}
		}
		else if("viewAllRecords".equals(operation)) {
			List<CourierBean>courierlist=viewAllRecords(request);
			if (courierlist.isEmpty()) {
	            request.setAttribute("msg", "No records available!");
	            RequestDispatcher rd = request.getRequestDispatcher("displayAllCouriers.jsp");
	            rd.forward(request, response);
	        }
			else{
			request.setAttribute("viewAllRecords",courierlist);
			RequestDispatcher rd=request.getRequestDispatcher("displayAllCouriers.jsp");
			rd.forward(request, response);
			}
		}	
		
	}
	public String addRecord(HttpServletRequest request) {
		Administrator admin=new Administrator();
		CourierBean cb=null;
	    try {
		String senderName=request.getParameter("sname");
	    String recieverName=request.getParameter("rname");
	    String courierItem=request.getParameter("citem");
	    String shipDate = request.getParameter("shipdate");
	    String deliveryDate=request.getParameter("deliverydate");
	    String status=request.getParameter("status");
	    String remarks=request.getParameter("remarks");
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
			Date ship_date = sdf.parse(shipDate);
			Date delivery_Date=sdf.parse(deliveryDate);
		
		cb=new CourierBean();
		cb.setSenderName(senderName);
		cb.setReceiverName(recieverName);
		cb.setCourierItem(courierItem);
		cb.setShipDate(ship_date);
		cb.setDeliveryDate(delivery_Date);
		cb.setStatus(status);
		cb.setRemarks(remarks);
		
	    } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admin.addRecord(cb);
			
	}
	public CourierBean viewRecord(HttpServletRequest request) {
		Administrator admin=new Administrator();
		try {
		String senderName=request.getParameter("sname");
		String shipDate = request.getParameter("shipdate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date ship_date;
	
			ship_date = sdf.parse(shipDate);
			return admin.viewRecord(senderName,ship_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	public List<CourierBean> viewAllRecords(HttpServletRequest request){
		Administrator admin=new Administrator();
		return admin.viewAllRecords();
	}

}
