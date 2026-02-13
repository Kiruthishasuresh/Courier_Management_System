package com.wipro.courier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.wipro.courier.bean.CourierBean;
import com.wipro.courier.util.DBUtil;

public class CourierDAO {
	public String createRecord(CourierBean bean) {
		Connection connect=DBUtil.getDBConnection();
		String query="insert into courier_tb values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps=connect.prepareStatement(query);
			ps.setString(1,bean.getCourierId());
			ps.setString(2,bean.getSenderName());
			ps.setString(3, bean.getReceiverName());
			ps.setString(4,bean.getCourierItem());
			ps.setDate(5, new java.sql.Date(bean.getShipDate().getTime()));
			ps.setDate(6, new java.sql.Date(bean.getDeliveryDate().getTime()));
			ps.setString(7,bean.getStatus());
			ps.setString(8, bean.getRemarks());
			int row=ps.executeUpdate();
			if(row>0) {
				return bean.getCourierId();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "FAIL";
		
	}
	public CourierBean fetchRecord(String senderName, java.util.Date shipDate) {
		CourierBean cb=null;
		Connection connect=DBUtil.getDBConnection();
		String query="select * from courier_tb where  SENDERNAME=? AND  SHIP_DATE=?";
		try {
			PreparedStatement ps=connect.prepareStatement(query);
			ps.setString(1,senderName);
			ps.setDate(2, new java.sql.Date(shipDate.getTime()));
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				cb=new CourierBean();
			    cb.setCourierId(rs.getString(1));
	            cb.setSenderName(rs.getString(2));
	            cb.setReceiverName(rs.getString(3));
	            cb.setCourierItem(rs.getString(4));
	            cb.setShipDate(rs.getDate(5));
	            cb.setDeliveryDate(rs.getDate(6));
	            cb.setStatus(rs.getString(7));
	            cb.setRemarks(rs.getString(8));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cb;
	}
	public String generateCourierID(String senderName, java.util.Date shipDate) {
		Connection connection=DBUtil.getDBConnection();
		String query=" select COURIER_SEQ.NEXTVAL from dual";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int sequence_no=rs.getInt(1);
			DateFormat f = new SimpleDateFormat("yyyyMMdd"); 
			String temp = f.format(shipDate);
			String CourierId=temp+senderName.substring(0,2).toUpperCase()+sequence_no;
			return CourierId;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean recordExists(String senderName, java.util.Date date) {
		Connection connect=DBUtil.getDBConnection();
		String query="select * from courier_tb where  SENDERNAME=? AND  SHIP_DATE=?";
		try {
			PreparedStatement ps=connect.prepareStatement(query);
			ps.setString(1,senderName);
			ps.setDate(2, new java.sql.Date(date.getTime()));
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return false;
	}
	public List<CourierBean> fetchAllRecords(){
		List<CourierBean>CourierRecord=new ArrayList<>();
		CourierBean cb=null;
		Connection connect=DBUtil.getDBConnection();
		String query="select * from courier_tb";
		try {
			PreparedStatement ps=connect.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				cb=new CourierBean();
			    cb.setCourierId(rs.getString(1));
	            cb.setSenderName(rs.getString(2));
	            cb.setReceiverName(rs.getString(3));
	            cb.setCourierItem(rs.getString(4));
	            cb.setShipDate(rs.getDate(5));
	            cb.setDeliveryDate(rs.getDate(6));
	            cb.setStatus(rs.getString(7));
	            cb.setRemarks(rs.getString(8));
	            CourierRecord.add(cb);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CourierRecord;
		
	}
}
