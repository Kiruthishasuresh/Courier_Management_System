package com.wipro.courier.service;
import java.util.Date;
import java.util.List;

import com.wipro.courier.bean.CourierBean;
import com.wipro.courier.dao.CourierDAO;
import com.wipro.courier.util.InvalidInputException;

public class Administrator {
	CourierDAO cdao=new CourierDAO();
	public String addRecord(CourierBean bean){
		try{
			if(bean==null||bean.getSenderName()==null||bean.getReceiverName()==null||bean.getShipDate()==null||bean.getCourierItem()==null)
			{
				throw new InvalidInputException();
			}
			else if(bean.getSenderName().length()<2) {
				return"INVALID SENDER NAME";
			}
			else if(bean.getReceiverName().length()<2) {
				return"INVALID RECEIVER NAME";
			}
			else if(bean.getCourierItem().length()<2) {
				return"INVALID COURIER ITEM";
			}
			else {
				
				if(cdao.recordExists(bean.getSenderName(),bean.getShipDate())) {
					return"ALREADY EXISTS";
				}
				else {
					String Courierid=cdao.generateCourierID(bean.getSenderName(),bean.getShipDate());
					CourierBean bean2=new CourierBean();
					bean2.setCourierId(Courierid);
					bean2.setCourierItem(bean.getCourierItem());
					bean2.setShipDate(bean.getShipDate());
					bean2.setDeliveryDate(bean.getDeliveryDate());
					bean2.setSenderName(bean.getSenderName());
					bean2.setReceiverName(bean.getReceiverName());
					bean2.setStatus(bean.getStatus());
					bean2.setRemarks(bean.getRemarks());
					String status=cdao.createRecord(bean2);
					return status;
				}
			}
		}
		catch(InvalidInputException e) {
			return e.toString();
		}
	}
	public CourierBean viewRecord(String senderName, Date shipDate) {
		CourierDAO cdao=new CourierDAO();
		return cdao.fetchRecord(senderName, shipDate);
		
	}
	public List<CourierBean>viewAllRecords(){
		CourierDAO cdao=new CourierDAO();
		return 	cdao.fetchAllRecords();
		
	}


}
